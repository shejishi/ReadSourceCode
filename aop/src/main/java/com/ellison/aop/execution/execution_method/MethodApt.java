package com.ellison.aop.execution.execution_method;

import android.util.Log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @author ellison
 * @date 2019年07月01日
 * @desc 用一句话描述这个类的作用
 * <p>
 * 邮箱： Ellison.Sun0808@outlook.com
 * 博客： <a href="https://www.jianshu.com/u/b1c92a64018a">简书博客</a>
 */
//@Aspect
public class MethodApt {

    public static final String TAG = "MethodApt";

    @Pointcut("execution(* com.ellison.aop.method.ExecutionMethodActivity.test())")
    public void executionFindMethod() {

    }

    @Before("executionFindMethod()")
    public void invokMethod(JoinPoint point) throws Throwable {
        Log.d(TAG, "具体方法之前");
        ((ProceedingJoinPoint) point).proceed();
        Log.d(TAG, "具体方法之后");
    }

    @Pointcut("execution(* com.ellison.aop.method.ExecutionMethodActivity.**())")
    public void executionFindAllMethod() {

    }

    @Before("executionFindAllMethod()")
    public void invokAllMethod(JoinPoint point) throws Throwable {
        Log.d(TAG, "**方法之前");
        ((ProceedingJoinPoint) point).proceed();
        Log.d(TAG, "**方法之后");
    }



}
