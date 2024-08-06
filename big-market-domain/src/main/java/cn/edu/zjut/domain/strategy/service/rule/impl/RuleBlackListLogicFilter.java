package cn.edu.zjut.domain.strategy.service.rule.impl;

import cn.edu.zjut.domain.strategy.model.entity.RuleActionEntity;
import cn.edu.zjut.domain.strategy.model.entity.RuleMatterEntity;
import cn.edu.zjut.domain.strategy.model.vo.RuleLogicCheckTypeVO;
import cn.edu.zjut.domain.strategy.repository.IStrategyRepository;
import cn.edu.zjut.domain.strategy.service.annotation.LogicStrategy;
import cn.edu.zjut.domain.strategy.service.rule.ILogicFilter;
import cn.edu.zjut.domain.strategy.service.rule.factory.DefaultLogicFactory;
import cn.edu.zjut.types.common.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * @description: some desc
 * @author: lcl
 * @email: clianglu@zjut.edu.cn
 * @date: 2024/8/5 16:20
 */
@Slf4j
@Component
@LogicStrategy(logicMode = DefaultLogicFactory.LogicModel.RULE_BLACKLIST)
public class RuleBlackListLogicFilter implements ILogicFilter<RuleActionEntity.RaffleBeforeEntity> {
    @Resource
    private IStrategyRepository strategyRepository;

    @Override
    public RuleActionEntity<RuleActionEntity.RaffleBeforeEntity> filter(RuleMatterEntity ruleMatterEntity) {
        log.info("规则过滤-黑名单 userId:{} strategyId:{} ruleModel:{}", ruleMatterEntity.getUserId(), ruleMatterEntity.getStrategyId(), ruleMatterEntity.getRuleModel());
        String userId = ruleMatterEntity.getUserId();

        String ruleValue = strategyRepository.queryStrategyRuleValue(ruleMatterEntity.getStrategyId(), ruleMatterEntity.getAwardId(), ruleMatterEntity.getRuleModel());
        String[] splitRuleValue = ruleValue.split(Constants.COLON);
        Integer awardId = Integer.parseInt(splitRuleValue[0]);

        //100:user001,user002,user003
        // 过滤其他规则
        String[] userBlackIds = splitRuleValue[1].split(Constants.SPLIT);
        for (String userBlackId : userBlackIds) {
            if (userId.equals(userBlackId)) {
                return RuleActionEntity.<RuleActionEntity.RaffleBeforeEntity>builder()
                        .ruleModel(DefaultLogicFactory.LogicModel.RULE_BLACKLIST.getCode())
                        .data(RuleActionEntity.RaffleBeforeEntity.builder()
                                .strategyId(ruleMatterEntity.getStrategyId())
                                .awardId(awardId)
                                .build())
                        .code(RuleLogicCheckTypeVO.TAKE_OVER.getCode())
                        .info(RuleLogicCheckTypeVO.TAKE_OVER.getInfo())
                        .build();
            }
        }
        return RuleActionEntity.<RuleActionEntity.RaffleBeforeEntity>builder()
                .code(RuleLogicCheckTypeVO.ALLOW.getCode())
                .info(RuleLogicCheckTypeVO.ALLOW.getInfo())
                .build();

    }
}
