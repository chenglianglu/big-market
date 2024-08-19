package cn.edu.zjut.infrastructure.persistent.po;

import lombok.Data;

/**
 * @description: 规则树节点连线
 * @author: lcl
 * @email: clianglu@zjut.edu.cn
 * @date: 2024/8/10 14:52
 */
@Data
public class RuleTreeNodeLinePO {

    /** 自增ID*/
    private String id;
    /** 规则树ID*/
    private String treeId;
    /** 规则Key节点 From*/
    private String ruleNodeFrom;
    /** 规则Key节点 To*/
    private String ruleNodeTo;
    /** 限定类型；1:=;2:>;3:<;4:>=;5<=;6:enum[枚举范围];*/
    private String ruleLimitType;
    /** 限定值（到下个节点）*/
    private String ruleLimitValue;
    /** 创建时间*/
    private String createTime;
    /** 更新时间*/
    private String updateTime;
}
