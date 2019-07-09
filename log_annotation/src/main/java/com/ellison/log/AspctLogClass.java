package com.ellison.log;

import android.util.Log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.ConstructorSignature;
import org.aspectj.lang.reflect.MethodSignature;


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

    @Pointcut("within(com.makeramen.roundedimageview..*)")
    public void onLoggerMethod() {
    }

    @Before("onLoggerMethod()")
    public void doCacheMethod(JoinPoint point) throws Throwable {
        try {
            final Object object = point.getThis();
            if (object == null) {
                return;
            }
            Signature signature = point.getStaticPart().getSignature();
            if (signature instanceof MethodSignature) {
                //方法执行前
                if(!((MethodSignature) point.getStaticPart().getSignature()).getMethod().toString().contains("android.util.Log")) {
                    Log.e(TAG, ((MethodSignature) point.getStaticPart().getSignature()).getMethod().toString());
                }
            }

            if (signature instanceof ConstructorSignature) {
                if(!((ConstructorSignature) point.getStaticPart().getSignature()).getConstructor().toString().contains("android.util.Log")){
                    Log.e(TAG, ((ConstructorSignature) point.getStaticPart().getSignature()).getConstructor().toString());
                }
            }
            // 执行被切的方法
            ((ProceedingJoinPoint) point).proceed();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}