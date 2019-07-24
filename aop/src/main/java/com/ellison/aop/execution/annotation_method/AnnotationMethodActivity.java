package com.ellison.aop.execution.annotation_method;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
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
public class AnnotationMethodActivity extends AppCompatActivity {

    public static final String TAG = "ExecutionMethodActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_annotation_find_method);

        findViewById(R.id.annotation_method).setOnClickListener(l -> {
            test();

            test1();

            test2("test");
        });

    }

    @ExecutionAnnotationFindMethod
    public ReturnParam test() {
        Log.d(TAG, "test测试Annotation查找方法");
        return null;
    }

    @ExecutionAnnotationFindMethod
    public void test1() {
        Log.d(TAG, "test测试Annotation查找方法");
    }

    @ExecutionAnnotationFindMethod
    public void test2(String str) {
        Log.d(TAG, "test测试Annotation查找方法");
    }

}
