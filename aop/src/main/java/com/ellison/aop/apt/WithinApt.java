package com.ellison.aop.apt;

import android.util.Log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @author ellison
 * @date 2019年07月11日
 * @desc 用一句话描述这个类的作用
 * <p>
 * 邮箱： Ellison.Sun0808@outlook.com
 * 博客： <a href="https://www.jianshu.com/u/b1c92a64018a">简书博客</a>
 */
@Aspect
public class WithinApt {

    public static final String TAG = "WithinApt";

    @Pointcut("within(com.ellison.aop.within.*)")
    public void withinFindPackage() {

    }

    @Pointcut("within(@com.ellison.aop.apt.WithinAnnotation *)")
    public void withinAnnotation() {

    }

    @After("withinAnnotation()")
    public void invokeMethod(JoinPoint joinPoint) throws Throwable {
        Log.d(TAG, "具体方法之前");
        ((ProceedingJoinPoint)joinPoint).proceed();
    }

}
