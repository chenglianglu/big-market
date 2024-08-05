package cn.edu.zjut.infrastructure.persistent.dao;

import cn.edu.zjut.infrastructure.persistent.po.StrategyRulePO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @description: 策略规则dao
 * @author: lcl
 * @email: clianglu@zjut.edu.cn
 * @date: 2024/8/2 15:58
 */
@Mapper
public interface IStrategyRuleDao {
    List<StrategyRulePO> queryStrategyRuleList();

    StrategyRulePO queryStrategyRule(StrategyRulePO strategyRuleReq);
}
