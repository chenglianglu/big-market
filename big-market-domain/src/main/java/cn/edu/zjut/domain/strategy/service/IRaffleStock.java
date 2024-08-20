package cn.edu.zjut.domain.strategy.service;

import cn.edu.zjut.domain.strategy.model.vo.StrategyAwardStockKeyVO;

/**
 * @description: 抽奖库存服务接口
 * @author: lcl
 * @email: clianglu@zjut.edu.cn
 * @date: 2024/8/20 15:13
 */
public interface IRaffleStock {
    /**
     * 获取奖品库存消耗队列
     * @return
     * @throws InterruptedException
     */
    StrategyAwardStockKeyVO takeQueueValue() throws InterruptedException;

    /**
     * 更新奖品库消耗记录
     * @param strategyId
     * @param awardId
     */
    void updateStrategyAwardStock(Long strategyId, Integer awardId);

}
