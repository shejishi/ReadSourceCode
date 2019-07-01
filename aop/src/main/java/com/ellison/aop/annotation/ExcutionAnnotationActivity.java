package com.ellison.aop.annotation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.ellison.aop.R;

/**
 * @author ellison
 * @date 2019年06月30日
 * @desc 用一句话描述这个类的作用
 * <p>
 * 邮箱： Ellison.Sun0808@outlook.com
 * 博客： <a href="https://www.jianshu.com/u/b1c92a64018a">简书博客</a>
 */
public class ExcutionAnnotationActivity extends AppCompatActivity {

    private TextView mTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_excution_annotation_find_method);

        mTextView = findViewById(R.id.annotation_tv);
    }

    public void changeText(View view) {
        changeTv();
    }

    @AnnotationFindMethod
    public void changeTv() {
        mTextView.setText("这是改变前的文字");
    }


}
