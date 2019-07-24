package com.ellison.aop.within.within_package;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ellison.aop.R;

/**
 * @author ellison
 * @date 2019年07月11日
 * @desc 用一句话描述这个类的作用
 * <p>
 * 邮箱： Ellison.Sun0808@outlook.com
 * 博客： <a href="https://www.jianshu.com/u/b1c92a64018a">简书博客</a>
 */
public class WithinInPackageActivity extends AppCompatActivity {

    private RoundedView mRoundedView;
    int size = 100;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_within_with_package);

        mRoundedView = findViewById(R.id.rounded_view);
        mRoundedView.setOnClickListener(l -> {
            setSize();
        });
    }

    public void setSize() {
        size += 10;
        mRoundedView.setRoundSize(size);
    }
}
