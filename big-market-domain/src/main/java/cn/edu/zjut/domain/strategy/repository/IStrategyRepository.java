package cn.edu.zjut.domain.strategy.repository;

import cn.edu.zjut.domain.strategy.model.entity.StrategyAwardEntity;
import cn.edu.zjut.domain.strategy.model.entity.StrategyEntity;
import cn.edu.zjut.domain.strategy.model.entity.StrategyRuleEntity;
import cn.edu.zjut.domain.strategy.model.vo.RuleTreeVO;
import cn.edu.zjut.domain.strategy.model.vo.StrategyAwardRuleModelVO;

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

    String queryStrategyRuleValue(Long strategyId, String ruleModel);

    String queryStrategyRuleValue(Long strategyId, Integer awardId, String ruleModel);

    StrategyAwardRuleModelVO queryStrategyAwardRuleModel(Long strategyId, Integer awardId);

    RuleTreeVO queryRuleTreeVOByTreeId(String treeId);
}
