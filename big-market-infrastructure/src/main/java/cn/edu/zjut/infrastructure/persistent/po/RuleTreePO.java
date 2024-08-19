package cn.edu.zjut.infrastructure.persistent.po;

import lombok.Data;

import java.util.Date;

/**
 * @description: 规则树PO
 * @author: lcl
 * @email: clianglu@zjut.edu.cn
 * @date: 2024/8/10 14:44
 */
@Data
public class RuleTreePO {
    /** 自增ID*/
    private Long id;
    /** 规则树ID*/
    private String treeId;
    /** 规则树名称*/
    private String treeName;
    /** 规则树描述*/
    private String treeDesc;
    /** 规则树根入口规则*/
    private String treeRootRuleKey;
    /** 创建时间*/
    private Date createTime;
    /** 更新时间*/
    private Date updateTime;

}
