package cn.edu.zjut.domain.strategy.service;

import cn.edu.zjut.domain.strategy.model.entity.StrategyAwardEntity;

import java.util.List;

/**
 * @description: 策略奖品接口
 * @author: lcl
 * @email: clianglu@zjut.edu.cn
 * @date: 2024/8/22 15:19
 */
public interface IRaffleAward {
    /**
     * 根据策略id查询抽奖奖品列表配置
     * @param strategyId
     * @return
     */
    List<StrategyAwardEntity> queryRaffleStrategyAwardList(Long strategyId);
}
