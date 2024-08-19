package cn.edu.zjut.domain.strategy.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: 规则树节点线VO
 * @author: lcl
 * @email: clianglu@zjut.edu.cn
 * @date: 2024/8/9 14:20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RuleTreeNodeLineVO {
    /** 规则树ID */
    private String treeId;
    /** 规则key节点 From */
    private String ruleNodeFrom;
    /** 规则key节点 To */
    private String ruleNodeTo;
    /** 限定类型;1:=;2:>;3:<;4:>=;5<=;6:enum[枚举范围]*/
    private RuleLimitTypeVO ruleLimitType;
    /**限定值(到下个节点)*/
    private RuleLogicCheckTypeVO ruleLimitValue;
}
