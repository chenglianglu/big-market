package cn.edu.zjut.domain.strategy.service.annotation;

import cn.edu.zjut.domain.strategy.service.rule.factory.DefaultLogicFactory;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @description: 策略注解
 * @author: lcl
 * @email: clianglu@zjut.edu.cn
 * @date: 2024/8/5 16:15
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface LogicStrategy {

    DefaultLogicFactory.LogicModel logicMode();
}
