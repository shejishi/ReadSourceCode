package com.ellison.aop.execution.annotation_method;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author ellison
 * @date 2019年07月09日
 * @desc 用一句话描述这个类的作用
 * <p>
 * 邮箱： Ellison.Sun0808@outlook.com
 * 博客： <a href="https://www.jianshu.com/u/b1c92a64018a">简书博客</a>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ExecutionAnnotationFindMethod {
}
