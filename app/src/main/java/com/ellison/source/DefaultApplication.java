package com.ellison.source;

import android.app.Application;

/**
 * @author ellison
 * @date 2019年06月20日
 * @desc 用一句话描述这个类的作用
 * <p>
 * 邮箱： Ellison.Sun0808@outlook.com
 * 博客： <a href="https://www.jianshu.com/u/b1c92a64018a">简书博客</a>
 */
public class DefaultApplication extends Application {

    private static DefaultApplication dbApplication;

    // 构造方法
    // 实例化一次
    public static DefaultApplication getInstance() {
        return dbApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        dbApplication = this;
    }
}
