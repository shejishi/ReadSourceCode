package com.ellison.source.utils;

import com.ellison.source.DefaultApplication;

/**
 * @author ellison
 * @date 2019年06月20日
 * @desc 用一句话描述这个类的作用
 * <p>
 * 邮箱： Ellison.Sun0808@outlook.com
 * 博客： <a href="https://www.jianshu.com/u/b1c92a64018a">简书博客</a>
 */
public class DensityUtils {
    private static float density = -1F;
    private static int widthPixels = -1;
    private static int heightPixels = -1;

    private DensityUtils() {
    }

    public static float getDensity() {
        if (density <= 0F) {
            density = DefaultApplication.getInstance().getResources().getDisplayMetrics().density;
        }
        return density;
    }

    public static int dip2px(float dpValue) {
        return (int) (dpValue * getDensity() + 0.5F);
    }

    public static int px2dip(float pxValue) {
        return (int) (pxValue / getDensity() + 0.5F);
    }

    public static int getScreenWidth() {
        if (widthPixels <= 0) {
            widthPixels = DefaultApplication.getInstance().getResources().getDisplayMetrics().widthPixels;
        }
        return widthPixels;
    }


    public static int getScreenHeight() {
        if (heightPixels <= 0) {
            heightPixels = DefaultApplication.getInstance().getResources().getDisplayMetrics().heightPixels;
        }
        return heightPixels;
    }
}
