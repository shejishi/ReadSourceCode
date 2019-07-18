package com.ellison.aop.within.within_annotation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.ellison.aop.R;
import com.ellison.aop.apt.WithinAnnotation;

/**
 * @author ellison
 * @date 2019年07月17日
 * @desc 用一句话描述这个类的作用
 * <p>
 * 邮箱： Ellison.Sun0808@outlook.com
 * 博客： <a href="https://www.jianshu.com/u/b1c92a64018a">简书博客</a>
 */
public class WithInAnnotationActivity extends AppCompatActivity {

    @Override
    protected void onStart() {
        super.onStart();
    }

    @WithinAnnotation
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_within_with_annotation);

    }

    public void annotationClickMethod(View view) {
        startAnnotationActivity();
    }

    @WithinAnnotation
    public void startAnnotationActivity() {
        Toast.makeText(this, "开始查找", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
