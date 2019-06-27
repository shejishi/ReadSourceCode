package com.ellison.log;

import android.util.Log;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;


/**
 * @author ellison
 * @date 2019年06月26日
 * @desc 用一句话描述这个类的作用
 * <p>
 * 邮箱： Ellison.Sun0808@outlook.com
 * 博客： <a href="https://www.jianshu.com/u/b1c92a64018a">简书博客</a>
 */
@Aspect
public class AspctLogClass {

    public static final String TAG = "AspctLogClass";

//    @Pointcut("execution(@com.ellison.log.annotation.LoggerClass * *(..))")
    @Pointcut("execution(* com.makeramen.roundedimageview.. * *(..)")
    public void onLoggerMethod() {
    }

    @Before("onLoggerMethod()")
    public void doCacheMethod(JoinPoint point) throws Throwable {
        final Object object = point.getThis();
        if (object == null) {
            return;
        }
        //方法执行前
        Log.e(TAG, object.toString());

        // 执行被切的方法
        ((ProceedingJoinPoint)point).proceed();
    }
}