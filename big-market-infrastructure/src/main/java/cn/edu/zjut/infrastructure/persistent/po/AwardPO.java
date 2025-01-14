package cn.edu.zjut.infrastructure.persistent.po;

import lombok.Data;

import java.util.Date;

/**
 * @description: some desc
 * @author: lcl
 * @email: clianglu@zjut.edu.cn
 * @date: 2024/8/2 15:52
 */
@Data
public class AwardPO {

    /**自增ID */
    private Long id;
    /**抽奖奖品ID - 内部流转使用 */
    private Integer awardId;
    /**奖品对接标识 - 每一个都是一个对应的发奖策略 */
    private String awardKey;
    /**奖品配置信息 */
    private String awardConfig;
    /**奖品内容描述 */
    private String awardDesc;
    /**创建时间 */
    private Date createTime;
    /**更新时间 */
    private Date updateTime;

}
