package com.ellison.dragger.module;

import com.ellison.dragger.car.Engine;
import com.ellison.dragger.car.Seat;
import com.ellison.dragger.car.Wheel;

import dagger.Module;
import dagger.Provides;

/**
 * @author ellison
 * @date 2019年08月13日
 * @desc 用一句话描述这个类的作用
 * <p>
 * 邮箱： Ellison.Sun0808@outlook.com
 * 博客： <a href="https://www.jianshu.com/u/b1c92a64018a">简书博客</a>
 */
@Module
public class CarModule {

    @Provides
    public Engine providerEngine() {
        return new Engine();
    }

    @Provides
    public Seat providerSeat() {
        return new Seat();
    }

    @Provides
    public Wheel providerWheel() {
        return new Wheel();
    }
}
