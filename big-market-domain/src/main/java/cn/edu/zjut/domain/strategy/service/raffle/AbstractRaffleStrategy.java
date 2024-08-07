package cn.edu.zjut.domain.strategy.service.raffle;

import cn.edu.zjut.domain.strategy.model.entity.RaffleAwardEntity;
import cn.edu.zjut.domain.strategy.model.entity.RaffleFactorEntity;
import cn.edu.zjut.domain.strategy.model.entity.RuleActionEntity;
import cn.edu.zjut.domain.strategy.model.entity.StrategyEntity;
import cn.edu.zjut.domain.strategy.model.vo.RuleLogicCheckTypeVO;
import cn.edu.zjut.domain.strategy.model.vo.StrategyAwardRuleModelVO;
import cn.edu.zjut.domain.strategy.repository.IStrategyRepository;
import cn.edu.zjut.domain.strategy.service.armory.IStrategyDispatch;
import cn.edu.zjut.domain.strategy.service.rule.factory.DefaultLogicFactory;
import cn.edu.zjut.types.enums.ResponseCode;
import cn.edu.zjut.types.exception.AppException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * @description: 抽奖策略抽象类
 * @author: lcl
 * @email: clianglu@zjut.edu.cn
 * @date: 2024/8/5 15:51
 */
@Slf4j
public abstract class AbstractRaffleStrategy implements IRaffleStrategy {
    // 策略仓储服务
    protected IStrategyRepository strategyRepository;
    // 策略调度服务
    protected IStrategyDispatch strategyDispatch;

    public AbstractRaffleStrategy(IStrategyRepository strategyRepository, IStrategyDispatch strategyDispatch) {
        this.strategyRepository = strategyRepository;
        this.strategyDispatch = strategyDispatch;
    }

    @Override
    public RaffleAwardEntity performRaffle(RaffleFactorEntity raffleFactorEntity) {
        // 1. 参数校验
        String userId = raffleFactorEntity.getUserId();
        Long strategyId = raffleFactorEntity.getStrategyId();
        if (null == strategyId || StringUtils.isBlank(userId)) {
            throw new AppException(ResponseCode.ILLEGAL_PARAMETER.getCode(), ResponseCode.ILLEGAL_PARAMETER.getInfo());
        }

        // 2. 策略查询
        StrategyEntity strategy = strategyRepository.queryStrategyEntityByStrategyId(strategyId);

        // 3. 抽奖前 - 规则过滤
        RuleActionEntity<RuleActionEntity.RaffleBeforeEntity> ruleActionEntity = this.doCheckRaffleBeforeLogic(RaffleFactorEntity.builder()
                .userId(userId)
                .strategyId(strategyId)
                .build(), strategy.ruleModels());

        if (RuleLogicCheckTypeVO.TAKE_OVER.getCode().equals(ruleActionEntity.getCode())) {
            if (DefaultLogicFactory.LogicModel.RULE_BLACKLIST.getCode().equals(ruleActionEntity.getRuleModel())) {
                // 黑名单返回固定的奖品ID
                return RaffleAwardEntity.builder()
                        .awardId(ruleActionEntity.getData().getAwardId())
                        .build();
            } else if (DefaultLogicFactory.LogicModel.RULE_WEIGHT.getCode().equals(ruleActionEntity.getRuleModel())) {
                // 权重根据返回的信息进行抽奖
                RuleActionEntity.RaffleBeforeEntity raffleBeforeEntity = ruleActionEntity.getData();
                String ruleWeightValueKey = raffleBeforeEntity.getRuleWeightValueKey();
                Integer awardId = strategyDispatch.getRandomAwardId(strategyId, ruleWeightValueKey);
                return RaffleAwardEntity.builder()
                        .awardId(awardId)
                        .build();
            }
        }

        // 4. 默认抽奖流程
        Integer awardId = strategyDispatch.getRandomAwardId(strategyId);

        // 5. 查询奖品规则「抽奖中(拿到奖品ID时，过滤规则)、抽奖后(扣减完奖品库存后过滤，抽奖中拦截和无库存则走兜底)」
        StrategyAwardRuleModelVO strategyAwardRuleModelVO = strategyRepository.queryStrategyAwardRuleModel(strategyId, awardId);

        // 6. 抽奖中 - 规则过滤
        RuleActionEntity<RuleActionEntity.RaffleCenterEntity> raffleCenterEntity = this.doCheckRaffleCenterLogic(RaffleFactorEntity.builder()
                .userId(userId)
                .strategyId(strategyId)
                .awardId(awardId).build(), strategyAwardRuleModelVO.raffleCenterRuleModelList());
        if (RuleLogicCheckTypeVO.TAKE_OVER.getCode().equals(raffleCenterEntity.getCode())) {
            log.info("[临时日志] 中奖中规则拦截, 通过抽奖后规则 rule_luck_award 走兜底逻辑");
            return RaffleAwardEntity.builder()
                    .awardDesc("中奖中规则拦截, 通过抽奖后规则 rule_luck_award 走兜底逻辑")
                    .build();
        }

        return RaffleAwardEntity.builder()
                .awardId(awardId)
                .build();

    }

    protected abstract RuleActionEntity<RuleActionEntity.RaffleBeforeEntity> doCheckRaffleBeforeLogic(RaffleFactorEntity build, String ...logics);

    protected abstract RuleActionEntity<RuleActionEntity.RaffleCenterEntity> doCheckRaffleCenterLogic(RaffleFactorEntity build, String ...logics);
}
