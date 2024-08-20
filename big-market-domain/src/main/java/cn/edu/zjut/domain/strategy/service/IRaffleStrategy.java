package cn.edu.zjut.domain.strategy.service;

import cn.edu.zjut.domain.strategy.model.entity.RaffleAwardEntity;
import cn.edu.zjut.domain.strategy.model.entity.RaffleFactorEntity;

/**
 * @description: 抽奖策略接口
 * @author: lcl
 * @email: clianglu@zjut.edu.cn
 * @date: 2024/8/5 15:40
 */
public interface IRaffleStrategy {

    RaffleAwardEntity performRaffle(RaffleFactorEntity factorEntity);
}
