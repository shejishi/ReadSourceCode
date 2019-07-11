package com.ellison.aop.execution.execution_method;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.ellison.aop.R;

/**
 * @author ellison
 * @date 2019年07月02日
 * @desc 用一句话描述这个类的作用
 * <p>
 * 邮箱： Ellison.Sun0808@outlook.com
 * 博客： <a href="https://www.jianshu.com/u/b1c92a64018a">简书博客</a>
 */
public class ExecutionMethodActivity extends AppCompatActivity {

    public static final String TAG = "ExecutionMethodActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_excution_find_method);

        findViewById(R.id.aop_method).setOnClickListener(l -> {
            test();
        });

        findViewById(R.id.aop_method_all).setOnClickListener(l-> testAll());
    }

    public void test() {
        Log.d(TAG, "test测试单个方法");
    }

    public void testAll() {
        Log.d(TAG, "test测试查找所有方法");
    }

}
