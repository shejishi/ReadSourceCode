package com.ellison.aop.execution.annotation_method;

import android.util.Log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @author ellison
 * @date 2019年07月10日
 * @desc 用一句话描述这个类的作用
 * <p>
 * 邮箱： Ellison.Sun0808@outlook.com
 * 博客： <a href="https://www.jianshu.com/u/b1c92a64018a">简书博客</a>
 */
//@Aspect
public class AnnotationApt {
    public static final String TAG = "AnnotationApt";

    @Pointcut("execution(@com.ellison.aop.execution.annotation_method.ExecutionAnnotationFindMethod ReturnParam *(..))")
    public void annotationFindMethod() {

    }

    @Pointcut("execution(@com.ellison.aop.execution.annotation_method.ExecutionAnnotationFindMethod * *(..))")
    public void annotationNoReturnFindMethod() {

    }

    @Pointcut("execution(@com.ellison.aop.execution.annotation_method.ExecutionAnnotationFindMethod * *(String))")
    public void annotationParamsStringFindMethod() {

    }

    @Before("annotationParamsStringFindMethod()")
    public void invokMethod(JoinPoint point) throws Throwable {
        Log.d(TAG, "具体方法之前");
        ((ProceedingJoinPoint) point).proceed();
    }
}
