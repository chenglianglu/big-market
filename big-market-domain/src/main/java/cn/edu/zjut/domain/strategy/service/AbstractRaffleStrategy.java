package cn.edu.zjut.domain.strategy.service;

import cn.edu.zjut.domain.strategy.model.entity.RaffleAwardEntity;
import cn.edu.zjut.domain.strategy.model.entity.RaffleFactorEntity;
import cn.edu.zjut.domain.strategy.repository.IStrategyRepository;
import cn.edu.zjut.domain.strategy.service.armory.IStrategyDispatch;
import cn.edu.zjut.domain.strategy.service.rule.chain.factory.DefaultChainFactory;
import cn.edu.zjut.domain.strategy.service.rule.tree.factory.DefaultTreeFactory;
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
public abstract class AbstractRaffleStrategy implements IRaffleStrategy, IRaffleStock {
    // 策略仓储服务
    protected IStrategyRepository strategyRepository;
    // 策略调度服务
    protected IStrategyDispatch strategyDispatch;

    protected DefaultChainFactory defaultChainFactory;

    protected DefaultTreeFactory defaultTreeFactory;

    public AbstractRaffleStrategy(IStrategyRepository strategyRepository, IStrategyDispatch strategyDispatch, DefaultChainFactory defaultChainFactory, DefaultTreeFactory defaultTreeFactory) {
        this.strategyRepository = strategyRepository;
        this.strategyDispatch = strategyDispatch;
        this.defaultChainFactory = defaultChainFactory;
        this.defaultTreeFactory = defaultTreeFactory;
    }

    @Override
    public RaffleAwardEntity performRaffle(RaffleFactorEntity raffleFactorEntity) {
        // 1. 参数校验
        String userId = raffleFactorEntity.getUserId();
        Long strategyId = raffleFactorEntity.getStrategyId();
        if (null == strategyId || StringUtils.isBlank(userId)) {
            throw new AppException(ResponseCode.ILLEGAL_PARAMETER.getCode(), ResponseCode.ILLEGAL_PARAMETER.getInfo());
        }
        // 2.责任链抽奖计算【这步拿到的是初步的抽奖ID，之后需要根据ID处理抽奖】注意;黑名单、权重等非默认抽奖的直接返回抽奖结果
        DefaultChainFactory.StrategyAwardVO chainStrategyAwardVO = raffleLogicChain(userId, strategyId);
        log.info("抽奖策略计算-责任链{} {} {} {}", userId, strategyId, chainStrategyAwardVO.getAwardId(), chainStrategyAwardVO.getLogicModel());
        if (!DefaultChainFactory.LogicModel.RULE_DEFAULT.getCode().equals(chainStrategyAwardVO.getLogicModel())) {
            return RaffleAwardEntity.builder()
                    .awardId(chainStrategyAwardVO.getAwardId())
                    .build();
        }

        // 3.规则树抽奖过滤【奖品ID，会根据抽奖次数判断、库存判断、兜底兜里返回最终的可获得奖品信息】
        DefaultTreeFactory.StrategyAwardVO treeStrategyAwardVO = raffleLogicTree(userId, strategyId, chainStrategyAwardVO.getAwardId());
        log.info("抽奖策略计算-规则树{} {} {} {}", userId, strategyId, treeStrategyAwardVO.getAwardId(), treeStrategyAwardVO.getAwardRuleValue());


        return RaffleAwardEntity.builder()
                .awardId(treeStrategyAwardVO.getAwardId())
                .awardConfig(treeStrategyAwardVO.getAwardRuleValue())
                .build();

    }

    /**
     *
     * @param userId
     * @param strategyId
     * @return 奖品id
     */
    public abstract DefaultChainFactory.StrategyAwardVO raffleLogicChain(String userId, Long strategyId);

    /**
     *
     * @param userId
     * @param strategyId
     * @param awardId
     * @return 过滤结果【奖品ID，会根据抽奖次数判断、库存判断、兜底兜里返回最终的可获得奖品信息】
     */
    public abstract DefaultTreeFactory.StrategyAwardVO raffleLogicTree(String userId, Long strategyId, Integer awardId);

}
