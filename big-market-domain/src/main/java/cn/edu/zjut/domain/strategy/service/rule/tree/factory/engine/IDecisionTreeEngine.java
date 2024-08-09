package cn.edu.zjut.domain.strategy.service.rule.tree.factory.engine;

import cn.edu.zjut.domain.strategy.service.rule.tree.factory.DefaultTreeFactory;

/**
 * @description: 规则树引擎接口
 * @author: lcl
 * @email: clianglu@zjut.edu.cn
 * @date: 2024/8/9 14:36
 */
public interface IDecisionTreeEngine {


    DefaultTreeFactory.StrategyAwardData process(String userId, Long strategyId, Integer awardId);
}
