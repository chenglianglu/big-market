package cn.edu.zjut.domain.strategy.service.rule.tree;

import cn.edu.zjut.domain.strategy.service.rule.tree.factory.DefaultTreeFactory;

/**
 * @description: 规则树接口
 * @author: lcl
 * @email: clianglu@zjut.edu.cn
 * @date: 2024/8/9 14:26
 */
public interface ILogicTreeNode {

    DefaultTreeFactory.TreeActionEntity logic(String userId, Long strategyId, Integer awardId, String ruleValue);



}
