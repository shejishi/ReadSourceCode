package com.ellison.aop;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.ellison.aop.annotation_method.AnnotationMethodActivity;
import com.ellison.aop.execution_method.ExecutionMethodActivity;

/**
 * @author ellison
 * @date 2019年06月29日
 * @desc 用一句话描述这个类的作用
 * <p>
 * 邮箱： Ellison.Sun0808@outlook.com
 * 博客： <a href="https://www.jianshu.com/u/b1c92a64018a">简书博客</a>
 */
public class AopDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_aop_page);
    }

    public void aopAnnotationFindMethod(View view) {
        Intent i = new Intent(this, AnnotationMethodActivity.class);
        startActivity(i);
    }

    public void aopExecutionFindMethod(View view) {
        Intent i = new Intent(this, ExecutionMethodActivity.class);
        startActivity(i);
    }
}
