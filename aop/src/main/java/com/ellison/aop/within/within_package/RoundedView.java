package com.ellison.aop.within.within_package;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author ellison
 * @date 2019年07月11日
 * @desc 用一句话描述这个类的作用
 * <p>
 * 邮箱： Ellison.Sun0808@outlook.com
 * 博客： <a href="https://www.jianshu.com/u/b1c92a64018a">简书博客</a>
 */
public class RoundedView extends View {

    private Context mContext;
    private Paint mPaint;

    private float radius = 100;

    public RoundedView(Context context) {
        this(context, null);
    }

    public RoundedView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundedView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mContext = context;
        initView();
    }

    private void initView() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.CYAN);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawCircle(getMeasuredWidth() / 2, getMeasuredHeight() / 2, radius, mPaint);
    }

    public void setRoundSize(float size) {
        this.radius = size;
        invalidate();
    }
}
