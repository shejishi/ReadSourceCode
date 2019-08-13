package com.ellison.dragger.component;

import com.ellison.dragger.car.BMWCar;
import com.ellison.dragger.module.CarModule;

import dagger.Component;

/**
 * @author ellison
 * @date 2019年08月13日
 * @desc 用一句话描述这个类的作用
 * <p>
 * 邮箱： Ellison.Sun0808@outlook.com
 * 博客： <a href="https://www.jianshu.com/u/b1c92a64018a">简书博客</a>
 */
@Component(modules = CarModule.class)
public interface CarComponent {
    void inject(BMWCar car);
}
