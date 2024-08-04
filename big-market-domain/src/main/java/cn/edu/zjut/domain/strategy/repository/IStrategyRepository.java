package cn.edu.zjut.domain.strategy.repository;

import cn.edu.zjut.domain.strategy.model.StrategyAwardEntity;

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

    void saveStrategyAwardSearchRateTables(Long strategyId, int rateRange, HashMap<Integer, Integer> shufflestrategyAwardSearchRateTables);

    int getRateRange(Long strategyId);

    Integer getStrategyAwardAssemble(Long strategyId, int rateKey);
}
