package cn.edu.zjut.domain.strategy.service.rule.chain;

/**
 * @description: 责任链接口
 * @author: lcl
 * @email: clianglu@zjut.edu.cn
 * @date: 2024/8/8 14:12
 */
public interface ILogicChain extends ILogicChainArmory{
    /**
     *责任链接口
     * @param userId
     * @param strategyId
     * @return awardId
     */
    Integer logic(String userId, Long strategyId);


}
