package cn.edu.zjut.domain.strategy.service.rule.chain;

/**
 * @description: 逻辑链装配接口
 * @author: lcl
 * @email: clianglu@zjut.edu.cn
 * @date: 2024/8/8 15:40
 */
public interface ILogicChainArmory {

    ILogicChain appendNext(ILogicChain next);

    ILogicChain next();
}
