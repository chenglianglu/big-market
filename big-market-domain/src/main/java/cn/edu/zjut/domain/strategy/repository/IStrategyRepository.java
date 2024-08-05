package cn.edu.zjut.domain.strategy.repository;

import cn.edu.zjut.domain.strategy.model.StrategyAwardEntity;
import cn.edu.zjut.domain.strategy.model.StrategyEntity;
import cn.edu.zjut.domain.strategy.model.StrategyRuleEntity;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

/**
 * @description: 策略仓储接口
 * @author: lcl
 * @email: clianglu@zjut.edu.cn
 * @date: 2024/8/4 15:07
 */
public interface IStrategyRepository {
    List<StrategyAwardEntity> queryStrategyAwardList(Long strategyId);

    void saveStrategyAwardSearchRateTables(String key, int rateRange, HashMap<Integer, Integer> shufflestrategyAwardSearchRateTables);

    int getRateRange(Long strategyId);

    int getRateRange(String key);

    Integer getStrategyAwardAssemble(String key, int rateKey);

    StrategyEntity queryStrategyEntityByStrategyId(Long strategyId);

    StrategyRuleEntity queryStrategyRule(Long strategyId, String ruleModel);
}
