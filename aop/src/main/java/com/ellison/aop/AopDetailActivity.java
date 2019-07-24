package com.ellison.aop;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;

import com.ellison.aop.execution.annotation_method.AnnotationMethodActivity;
import com.ellison.aop.execution.execution_method.ExecutionMethodActivity;
import com.ellison.aop.within.within_annotation.WithInAnnotationActivity;
import com.ellison.aop.within.within_package.WithinInPackageActivity;

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

    public void aopWithinWithPackage(View view) {
        Intent i = new Intent(this, WithinInPackageActivity.class);
        startActivity(i);
    }

    public void aopWithinWithAnnotation(View view) {
        Intent i = new Intent(this, WithInAnnotationActivity.class);
        startActivity(i);
    }
}
