package cn.edu.zjut.domain.strategy.model.vo;

import lombok.*;

/**
 * @description: 规则限定枚举值
 * @author: lcl
 * @email: clianglu@zjut.edu.cn
 * @date: 2024/8/9 14:23
 */

@AllArgsConstructor
@Getter
public enum RuleLimitTypeVO {
    EQUAL(1, "等于"),
    GT(2, "大于"),
    LT(3, "小于"),
    GE(4, "大于&等于"),
    LE(5, "小于&等于"),
    ENUM(6, "枚举"),
    ;


    private final Integer code;
    private final String info;
}
