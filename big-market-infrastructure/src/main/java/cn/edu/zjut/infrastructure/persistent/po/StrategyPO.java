package cn.edu.zjut.infrastructure.persistent.po;

import lombok.Data;

import java.util.Date;

/**
 * @description: 抽奖策略
 * @author: freer
 * @email: clianglu@zjut.edu.cn
 * @date: 2024/8/2 15:01
 */
@Data
public class StrategyPO {

    /**自增ID */
    private Long id;
    /**抽奖策略ID */
    private Long strategyId;
    /**抽奖策略描述 */
    private String strategyDesc;
    /**规则模型，rule配置的模型同步到此表，便于使用 */
    private String ruleModels;
    /**创建时间 */
    private Date createTime;
    /**更新时间 */
    private Date updateTime;

}
