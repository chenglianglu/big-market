package cn.edu.zjut.domain.strategy.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @description: 规则树节点VO
 * @author: lcl
 * @email: clianglu@zjut.edu.cn
 * @date: 2024/8/9 14:18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RuleTreeNodeVO {
    /** 规则树ID */
    private String treeId;
    /** 规则key */
    private String ruleKey;
    /** 规则描述 */
    private String ruleDesc;
    /** 规则比值 */
    private String ruleValue;

    /**规则连线*/
    private List<RuleTreeNodeLineVO> treeNodeLineVOList;
}
