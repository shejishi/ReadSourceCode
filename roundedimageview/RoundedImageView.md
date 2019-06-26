# RoundedImageView 使用

#####  一、了解 `Drawable`
`RoundedImageView` 是继承自`ImageView`的自定义`View`，在了解该类之前，我们应该
了解到`Drawable`这个类的子类及其应用，打开源码，我们看到`Drawable`的子类非常多，
这也就是我们在使用`drawable`文件夹中的资源的时候，可以使用类似图片、`shape`、`Selector`
等资源，其实系统就是将其中的资源转换成了对应的`Drawable`:

![Drawable子类.png](https://upload-images.jianshu.io/upload_images/2158207-90564c2f7deb7df1.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/240/h/540)

在这里，不再赘述`Drawable`中的源码，现在也没有分析到`Drawable`的源码，所以借鉴网上大佬的文章：
[https://blog.csdn.net/monkey646812329/article/details/52947966](https://blog.csdn.net/monkey646812329/article/details/52947966)

查看`Drawable`源码， 发现它是一个抽象类，其中还包含一个抽象内部类，所有实现`Drawable`类并且具有不同状态的子类，都需要实现其内部子类`ConstantState`：
![drawable_method_innerclass.png](https://upload-images.jianshu.io/upload_images/2158207-bf909d753e769b9a.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/240/h/540)

> 源码中对此内部类的注释为：   使用该抽象类来保存共享的常量状态和数据，在同一资源创建的`（BitmapDrawable）`唯一位图保存在其中。
> `newDrawable()`  可以看作是工厂方法来创建`Drawable`实例
>`getConstantState()`  来检索`Drawable`的 `ConstantState`，调用`Drawable`中的`mutate()` 通常是为其创建一个新的`ConstantState`

下面先分析几个我们在项目中常用到的：

##### Selector —— StateListDrawable
```
<?xml version="1.0" encoding="utf-8"?>
<selector xmlns:android="http://schemas.android.com/apk/res/android">
    <item android:drawable="@drawable/bubble_gray_right" 
          android:state_pressed="true"/>
    <item android:drawable="@drawable/bubble_gray_right"/>
</selector>
```
这是一个最常用的`selector`， `view`的不同状态显示不同的`item`， 而在系统中，最终生成的是`StateListDrawable`，  `selector`的常用状态如下：
`android:state_pressed`  是否按下，如一个按钮触摸或者点击。
`android:state_focused`  是否取得焦点，比如用户选择了一个文本框。
`android:state_hovered` 光标是否悬停，通常与focused state相同，它是4.0的新特性
`android:state_selected` 被选中，它与focus state并不完全一样，如一个list view 被选中的时候，它里面的各个子组件可能通过方向键，被选中了。
`android:state_checkable`  组件是否能被check。如：RadioButton是可以被check的。
`android:state_checked`  被checked了，如：一个RadioButton可以被check了。
`android:state_enabled`  能够接受触摸或者点击事件
`android:state_activated` 是否被激活
`android:state_window_focused` 应用程序是否在前台，当有通知栏被拉下来或者一个对话框弹出的时候应用程序就不在前台了

>######注意: 
>如果有多个item，那么系统将自动从上到下进行匹配，最先匹配的将得到的item。（不是通过最佳匹配）如果一个item没有任何的状态说明，那么它将可以被任何一个状态匹配。

##### Shape —— StateListDrawable
```
<?xml version="1.0" encoding="utf-8"?>
<shape xmlns:android="http://schemas.android.com/apk/res/android">
    <solid android:color="@color/white" />
    <corners android:radius="@dimen/dp_10" />
</shape>
```
`Shape`顾名思义是形状的意思，如果你想要画一个矩形、圆形或者椭圆形等二位图片，则可以使用`Shape`这个标签在`xml`中。

##### animation-list —— AnimationDrawable
```
<?xml version="1.0" encoding="utf-8"?>
<animation-list xmlns:android="http://schemas.android.com/apk/res/android"
    android:oneshot="false">
    <item
        android:drawable="@drawable/guess_number_1"
        android:duration="100" />
    <item
        android:drawable="@drawable/guess_number_2"
        android:duration="100" />
    <item
        android:drawable="@drawable/guess_number_3"
        android:duration="100" />
</animation-list>
```

`animation-list` 是一系列的`item`作为节点，每一个节点都是做为一个帧。

##### layer-list —— LayerDrawable
```
<?xml version="1.0" encoding="utf-8"?>
<layer-list
  xmlns:android="http://schemas.android.com/apk/res/android">
    <item android:id="@android:id/background">
        <shape>
            <scale android:scaleWidth="100%" />
            <solid android:color="#1affffff" />
            <corners android:radius="2.0dip" />
        </shape>
    </item>
    <item android:id="@android:id/secondaryProgress">
        <shape>
            <scale android:scaleWidth="100%" />
            <solid android:color="#1affffff" />
            <corners android:radius="2.0dip" />
        </shape>
    </item>
    <item android:id="@android:id/progress">
        <clip>
            <shape>
                <solid android:color="#ffffffff" />
                <corners android:radius="2.0dip" />
            </shape>
        </clip>
    </item>
</layer-list>
```
当我们设置`ProgressBar`的背景时，通常会用到一个`layer-list`来设置背景、进度、第二进度的颜色，系统在加载时就会直接

#####  二、正式理解 `RoundedDrawable`
自定义`view`在创建的时候，会调用构造方法：
```
public RoundedImageView(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
    Log.d(TAG, "RoundedImageView(Context context, AttributeSet attrs, int defStyle)");
    TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.RoundedImageView, defStyle, 0);
    // 获取到拉伸模式
    int index = a.getInt(R.styleable.RoundedImageView_android_scaleType, -1);
    if (index >= 0) {
        setScaleType(SCALE_TYPES[index]);
    } else {
        // 默认的拉伸模式 FIT_CENTER
        setScaleType(ScaleType.FIT_CENTER);
    }
    // 获取到圆角大小
    float cornerRadiusOverride =
            a.getDimensionPixelSize(R.styleable.RoundedImageView_riv_corner_radius, -1);
    // 获取到左上、左下、右上、右下的圆角大小
    mCornerRadii[Corner.TOP_LEFT] =
            a.getDimensionPixelSize(R.styleable.RoundedImageView_riv_corner_radius_top_left, -1);
    mCornerRadii[Corner.TOP_RIGHT] =
            a.getDimensionPixelSize(R.styleable.RoundedImageView_riv_corner_radius_top_right, -1);
    mCornerRadii[Corner.BOTTOM_RIGHT] =
            a.getDimensionPixelSize(R.styleable.RoundedImageView_riv_corner_radius_bottom_right, -1);
    mCornerRadii[Corner.BOTTOM_LEFT] =
            a.getDimensionPixelSize(R.styleable.RoundedImageView_riv_corner_radius_bottom_left, -1);
    // 判断是否设置 左上、左下、右上、右下 的大小，没有就直接设置所有的
    boolean any = false;
    for (int i = 0, len = mCornerRadii.length; i < len; i++) {
        if (mCornerRadii[i] < 0) {
            mCornerRadii[i] = 0f;
        } else {
            any = true;
        }
    }
    if (!any) {
        if (cornerRadiusOverride < 0) {
            cornerRadiusOverride = DEFAULT_RADIUS;
        }
        for (int i = 0, len = mCornerRadii.length; i < len; i++) {
            mCornerRadii[i] = cornerRadiusOverride;
        }
    }
    // 边框大小
    mBorderWidth = a.getDimensionPixelSize(R.styleable.RoundedImageView_riv_border_width, -1);
    if (mBorderWidth < 0) {
        mBorderWidth = DEFAULT_BORDER_WIDTH;
    }
    // 边框颜色
    mBorderColor = a.getColorStateList(R.styleable.RoundedImageView_riv_border_color);
    if (mBorderColor == null) {
        mBorderColor = ColorStateList.valueOf(RoundedDrawable.DEFAULT_BORDER_COLOR);
    }
    mMutateBackground = a.getBoolean(R.styleable.RoundedImageView_riv_mutate_background, false);
    // 是否是圆形图片
    mIsOval = a.getBoolean(R.styleable.RoundedImageView_riv_oval, false);
    // 平铺方式
    final int tileMode = a.getInt(R.styleable.RoundedImageView_riv_tile_mode, TILE_MODE_UNDEFINED);
    if (tileMode != TILE_MODE_UNDEFINED) {
        setTileModeX(parseTileMode(tileMode));
        setTileModeY(parseTileMode(tileMode));
    }
    // X 轴平铺方式
    final int tileModeX =
            a.getInt(R.styleable.RoundedImageView_riv_tile_mode_x, TILE_MODE_UNDEFINED);
    if (tileModeX != TILE_MODE_UNDEFINED) {
        setTileModeX(parseTileMode(tileModeX));
    }
    // Y 轴平铺方式
    final int tileModeY =
            a.getInt(R.styleable.RoundedImageView_riv_tile_mode_y, TILE_MODE_UNDEFINED);
    if (tileModeY != TILE_MODE_UNDEFINED) {
        setTileModeY(parseTileMode(tileModeY));
    }
    updateDrawableAttrs();
    updateBackgroundDrawableAttrs(true);
    if (mMutateBackground) {
        //noinspection deprecation
        super.setBackgroundDrawable(mBackgroundDrawable);
    }
    a.recycle();
}
```
上面主要是一些初始化的操作，最下面调用了`RoundedImageView#updateDrawableAttrs()`:
```
private void updateAttrs(Drawable drawable, ScaleType scaleType) {
    Log.d(TAG, "updateAttrs(Drawable drawable, ScaleType scaleType)");
    if (drawable == null) {
        return;
    }
    if (drawable instanceof RoundedDrawable) {
// 初始化 RoundedDrawable
        ((RoundedDrawable) drawable)
                .setScaleType(scaleType)
                .setBorderWidth(mBorderWidth)
                .setBorderColor(mBorderColor)
                .setOval(mIsOval)
                .setTileModeX(mTileModeX)
                .setTileModeY(mTileModeY);
        if (mCornerRadii != null) {
            ((RoundedDrawable) drawable).setCornerRadius(
                    mCornerRadii[Corner.TOP_LEFT],
                    mCornerRadii[Corner.TOP_RIGHT],
                    mCornerRadii[Corner.BOTTOM_RIGHT],
                    mCornerRadii[Corner.BOTTOM_LEFT]);
        }
        applyColorMod();
    } else if (drawable instanceof LayerDrawable) {
        // loop through layers to and set drawable attrs
        LayerDrawable ld = ((LayerDrawable) drawable);
        for (int i = 0, layers = ld.getNumberOfLayers(); i < layers; i++) {
            updateAttrs(ld.getDrawable(i), scaleType);
        }
    }
}
```

如果在分析源码之前，不知道源码的运行过程，可以先使用`log`来打印每个方法的日志，然后根据日志来分析每个方法，当然这不是很好的方法，但是目前也没有精力去写一个`aop`的切面来打印日志；后面要是写了再补充吧！下面看下这个库的日志打印情况：
![方法运行.png](https://upload-images.jianshu.io/upload_images/2158207-ef13cf03ed3ef85f.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/720/h/520)

自定义`view`的流程就再描述了，我们先看到`RoundedImageView#drawableStateChanged()`方法，该方法中调用了`invalidate()`方法，这个方法是`view`中的方法，追溯到源码中，最终会调用`RoundedImageView`中的`onDraw()`方法，因为它继承自`ImageView`，所以直接看到`ImageView#onDraw()`方法：
```
@Override
protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
// 如果当前的drawable为空则不绘制
    if (mDrawable == null) {
        return; // 无法解析URI
    }
    if (mDrawableWidth == 0 || mDrawableHeight == 0) {
        return;     // 没有东西可以绘制
    }
    if (mDrawMatrix == null && mPaddingTop == 0 && mPaddingLeft == 0) {
        mDrawable.draw(canvas);
    } else {
        final int saveCount = canvas.getSaveCount();
        canvas.save();
        if (mCropToPadding) {
            final int scrollX = mScrollX;
            final int scrollY = mScrollY;
            canvas.clipRect(scrollX + mPaddingLeft, scrollY + mPaddingTop,
                    scrollX + mRight - mLeft - mPaddingRight,
                    scrollY + mBottom - mTop - mPaddingBottom);
        }
        canvas.translate(mPaddingLeft, mPaddingTop);
        if (mDrawMatrix != null) {
            canvas.concat(mDrawMatrix);
        }
        /**
        * 调用到  Drawable 的draw 方法中
        */
        mDrawable.draw(canvas);
        canvas.restoreToCount(saveCount);
    }
}
```
可以看到在`ImageView#onDraw()` 方法中，后面会调用 `Drawable`的`draw()`方法，这也正好是我们看到的`log`中打印的方法，接下来我们分析`RoundedDrawable#draw()`方法：
```
@Override
public void draw(@NonNull Canvas canvas) {
    Log.d(TAG, "draw(@NonNull Canvas canvas)");
    if (mRebuildShader) {
        // 是否需要重新绘制阴影
        BitmapShader bitmapShader = new BitmapShader(mBitmap, mTileModeX, mTileModeY);
        if (mTileModeX == Shader.TileMode.CLAMP && mTileModeY == Shader.TileMode.CLAMP) {
            bitmapShader.setLocalMatrix(mShaderMatrix);
        }
        mBitmapPaint.setShader(bitmapShader);
        mRebuildShader = false;
    }
    if (mOval) {
        // 如果当前图片是圆形，则使用canvas绘制圆形
        if (mBorderWidth > 0) {
            canvas.drawOval(mDrawableRect, mBitmapPaint);
            canvas.drawOval(mBorderRect, mBorderPaint);
        } else {
            canvas.drawOval(mDrawableRect, mBitmapPaint);
        }
    } else {
        // 绘制每个角的圆角情况
        if (any(mCornersRounded)) {
            float radius = mCornerRadius;
            if (mBorderWidth > 0) {
                // 如果有边框
                canvas.drawRoundRect(mDrawableRect, radius, radius, mBitmapPaint);
                canvas.drawRoundRect(mBorderRect, radius, radius, mBorderPaint);
                redrawBitmapForSquareCorners(canvas);
                redrawBorderForSquareCorners(canvas);
            } else {
                // 没有边框直接绘制内容
                canvas.drawRoundRect(mDrawableRect, radius, radius, mBitmapPaint);
                redrawBitmapForSquareCorners(canvas);
            }
        } else {
            // 如果没有一个角有圆角，则直接绘制内容
            canvas.drawRect(mDrawableRect, mBitmapPaint);
            if (mBorderWidth > 0) {
                // 同上，绘制边框
                canvas.drawRect(mBorderRect, mBorderPaint);
            }
        }
    }
}
```
在`draw()`方法中，先绘制`shader`，然后判断是否当前图片是否是圆形，如果是圆形则绘制内容和边框（如果边框宽度大于0），不是圆形则依次判断每个圆角是否有大小，有则绘制圆角内容和边框内容，但是我们看到在绘制的时候，不断的调用了`isStateful()`、`drawableStateChanged()`方法，这是因为在绘制边框和圆角的时候，因为改变了内容和边框的颜色值，所以才会回调到`isStateful()`方法：
![RoundedDrawable#draw().png](https://upload-images.jianshu.io/upload_images/2158207-8364b1342fdd197c.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/540)

这里看到当调用到`redrawBitmapForSquareCorners(Canvas canvas)`方法：
```
private void redrawBitmapForSquareCorners(Canvas canvas) {
    Log.d(TAG, "redrawBitmapForSquareCorners(Canvas canvas)");
    if (all(mCornersRounded)) {
        // no square corners
        return;
    }
    if (mCornerRadius == 0) {
        return; // no round corners
    }
    float left = mDrawableRect.left;
    float top = mDrawableRect.top;
    float right = left + mDrawableRect.width();
    float bottom = top + mDrawableRect.height();
    float radius = mCornerRadius;
    if (!mCornersRounded[Corner.TOP_LEFT]) {
        mSquareCornersRect.set(left, top, left + radius, top + radius);
        canvas.drawRect(mSquareCornersRect, mBitmapPaint);
    }
    if (!mCornersRounded[Corner.TOP_RIGHT]) {
        mSquareCornersRect.set(right - radius, top, right, radius);
        canvas.drawRect(mSquareCornersRect, mBitmapPaint);
    }
    if (!mCornersRounded[Corner.BOTTOM_RIGHT]) {
        mSquareCornersRect.set(right - radius, bottom - radius, right, bottom);
        canvas.drawRect(mSquareCornersRect, mBitmapPaint);
    }
    if (!mCornersRounded[Corner.BOTTOM_LEFT]) {
        mSquareCornersRect.set(left, bottom - radius, left + radius, bottom);
        canvas.drawRect(mSquareCornersRect, mBitmapPaint);
    }
}
```
绘制完`Drawable`之后，接着就调用了`RoundedImageView # setImageDrawable(Drawable drawable)`，这是因为`ImageView # setImageDrawable()`方法回调到子类的方法中，接着就是拿到图片资源，重复上面的流程调用，绘制内容和边框：
![image.png](https://upload-images.jianshu.io/upload_images/2158207-6b5e8d438579e0b5.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/540/h/720)

到此，`RoundedImageView` 就绘制到面板上了~

##### 感谢：
[[译]Android: 自定义 Drawable 教程](https://juejin.im/entry/587de7d2570c3522010fea3d)














