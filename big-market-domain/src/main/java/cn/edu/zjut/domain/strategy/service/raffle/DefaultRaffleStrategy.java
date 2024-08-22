package cn.edu.zjut.domain.strategy.service.raffle;

import cn.edu.zjut.domain.strategy.model.entity.StrategyAwardEntity;
import cn.edu.zjut.domain.strategy.model.vo.RuleTreeVO;
import cn.edu.zjut.domain.strategy.model.vo.StrategyAwardRuleModelVO;
import cn.edu.zjut.domain.strategy.model.vo.StrategyAwardStockKeyVO;
import cn.edu.zjut.domain.strategy.repository.IStrategyRepository;
import cn.edu.zjut.domain.strategy.service.AbstractRaffleStrategy;
import cn.edu.zjut.domain.strategy.service.IRaffleAward;
import cn.edu.zjut.domain.strategy.service.IRaffleStock;
import cn.edu.zjut.domain.strategy.service.armory.IStrategyDispatch;
import cn.edu.zjut.domain.strategy.service.rule.chain.ILogicChain;
import cn.edu.zjut.domain.strategy.service.rule.chain.factory.DefaultChainFactory;
import cn.edu.zjut.domain.strategy.service.rule.tree.factory.DefaultTreeFactory;
import cn.edu.zjut.domain.strategy.service.rule.tree.factory.engine.IDecisionTreeEngine;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * @description: 默认抽奖策略
 * @author: lcl
 * @email: clianglu@zjut.edu.cn
 * @date: 2024/8/6 14:53
 */
@Slf4j
@Service
public class DefaultRaffleStrategy extends AbstractRaffleStrategy implements IRaffleAward, IRaffleStock {

    public DefaultRaffleStrategy(IStrategyRepository strategyRepository, IStrategyDispatch strategyDispatch, DefaultChainFactory defaultChainFactory, DefaultTreeFactory defaultTreeFactory) {
        super(strategyRepository, strategyDispatch, defaultChainFactory, defaultTreeFactory);
    }


    @Override
    public DefaultChainFactory.StrategyAwardVO raffleLogicChain(String userId, Long strategyId) {
        ILogicChain logicChain = defaultChainFactory.openLogicChain(strategyId);
        return logicChain.logic(userId, strategyId);
    }

    @Override
    public DefaultTreeFactory.StrategyAwardVO raffleLogicTree(String userId, Long strategyId, Integer awardId) {
        StrategyAwardRuleModelVO strategyAwardRuleModelVO = strategyRepository.queryStrategyAwardRuleModel(strategyId, awardId);
        if (strategyAwardRuleModelVO == null) {
            return DefaultTreeFactory.StrategyAwardVO.builder().awardId(awardId).build();
        }
        RuleTreeVO ruleTreeVO = strategyRepository.queryRuleTreeVOByTreeId(strategyAwardRuleModelVO.getRuleModels());
        if (ruleTreeVO == null) {
            throw new RuntimeException("存在抽奖策略配置的规则模型 Kev、未在库表 rule tree、rule tree node、rule tree line 配置对应的规则树信息.");
        }
        IDecisionTreeEngine treeEngine = defaultTreeFactory.openLogicTree(ruleTreeVO);
        return treeEngine.process(userId, strategyId, awardId);
    }

    @Override
    public StrategyAwardStockKeyVO takeQueueValue() throws InterruptedException {
        return strategyRepository.takeQueueValue();
    }

    @Override
    public void updateStrategyAwardStock(Long strategyId, Integer awardId) {
        strategyRepository.updateStrategyAwardStock(strategyId, awardId);
    }

    @Override
    public List<StrategyAwardEntity> queryRaffleStrategyAwardList(Long strategyId) {
        return strategyRepository.queryStrategyAwardList(strategyId);
    }
}
