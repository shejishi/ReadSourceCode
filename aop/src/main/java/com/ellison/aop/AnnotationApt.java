package com.ellison.aop;

import android.util.Log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * @author ellison
 * @date 2019年07月01日
 * @desc 用一句话描述这个类的作用
 * <p>
 * 邮箱： Ellison.Sun0808@outlook.com
 * 博客： <a href="https://www.jianshu.com/u/b1c92a64018a">简书博客</a>
 */
@Aspect
public class AnnotationApt {

    @Before("execution(@com.ellison.aop.annotation.AnnotationFindMethod * *(..))")
    public void invokMethod(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Log.d("test", "test");
        proceedingJoinPoint.proceed();
    }

}
