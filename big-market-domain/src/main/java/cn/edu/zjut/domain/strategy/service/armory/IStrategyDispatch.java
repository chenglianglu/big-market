package cn.edu.zjut.domain.strategy.service.armory;

/**
 * @description: 策略抽奖调度接口
 * @author: lcl
 * @email: clianglu@zjut.edu.cn
 * @date: 2024/8/4 17:40
 */
public interface IStrategyDispatch {

    Integer getRandomAwardId(Long strategyId);

    Integer getRandomAwardId(Long strategyId, String ruleWeightValue);

    Boolean subtractionAwardStock(Long strategyId, Integer awardId);

}
