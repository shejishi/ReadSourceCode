package com.ellison.dragger.car;

import com.ellison.dragger.module.CarModule;

import javax.inject.Inject;

/**
 * @author ellison
 * @date 2019年08月13日
 * @desc 用一句话描述这个类的作用
 * <p>
 * 邮箱： Ellison.Sun0808@outlook.com
 * 博客： <a href="https://www.jianshu.com/u/b1c92a64018a">简书博客</a>
 */
public class BMWCar {

//    Engine mEngine;
//    Seat mSeat;
//    Wheel mWheel;

    @Inject
    Engine mEngine;
    @Inject
    Seat mSeat;
    @Inject
    Wheel mWheel;

    public BMWCar() {
//        mEngine = new Engine();
//        mSeat = new Seat();
//        mWheel = new Wheel();

        System.out.println("create BMWCar~");
    }

}
