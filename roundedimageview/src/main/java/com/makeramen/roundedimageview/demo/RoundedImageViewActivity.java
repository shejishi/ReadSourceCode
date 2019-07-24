package com.makeramen.roundedimageview.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.makeramen.roundedimageview.R;

/**
 * @author ellison
 * @date 2019年06月20日
 * @desc 用一句话描述这个类的作用
 * <p>
 * 邮箱： Ellison.Sun0808@outlook.com
 * 博客： <a href="https://www.jianshu.com/u/b1c92a64018a">简书博客</a>
 */
public class RoundedImageViewActivity extends AppCompatActivity {

    /**
     * 进入到当前界面
     *
     * @param activity
     */
    public static void enterRoundedImage(Activity activity) {
        Intent i = new Intent(activity, RoundedImageViewActivity.class);

        activity.startActivity(i);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_rounded_image_view);
    }
}
