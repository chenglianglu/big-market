package cn.edu.zjut.domain.strategy.model.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: some desc
 * @author: lcl
 * @email: clianglu@zjut.edu.cn
 * @date: 2024/8/5 16:04
 */
@Getter
@AllArgsConstructor
public enum RuleLogicCheckTypeVO {

    ALLOW("0000", "放行;执行后续流程,不受规则引擎的影响"),
    TAKE_OVER("0001", "接管;执行规则引擎的流程")
    ;

    private final String code;

    private final String info;
}
