package com.ellison.source.bean;

import android.support.annotation.DrawableRes;
import android.support.annotation.IntDef;
import android.text.TextUtils;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author ellison
 * @date 2019年06月20日
 * @desc 用一句话描述这个类的作用
 * <p>
 * 邮箱： Ellison.Sun0808@outlook.com
 * 博客： <a href="https://www.jianshu.com/u/b1c92a64018a">简书博客</a>
 */
public class RecyclerViewItemBean {

    public static final int STATUS_ING = 1;

    public static final int STATUS_FINISH = 2;

    @IntDef({STATUS_ING, STATUS_FINISH})
    @Retention(RetentionPolicy.SOURCE)
    @interface SourceStatus {

    }

    private @DrawableRes
    int coverImg;

    private String title;

    private String detail;

    private @SourceStatus
    int status;

    public RecyclerViewItemBean(@DrawableRes int coverImg, String title, String detail, @SourceStatus int status) {
        this.coverImg = coverImg;
        this.title = title;
        this.detail = detail;
        this.status = status;
    }

    public @DrawableRes
    int getCoverImg() {
        return coverImg;
    }

    public RecyclerViewItemBean setCoverImg(@DrawableRes int coverImg) {
        this.coverImg = coverImg;
        return this;
    }

    public String getTitle() {
        return TextUtils.isEmpty(title) ? "" : title;
    }

    public RecyclerViewItemBean setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDetail() {
        return TextUtils.isEmpty(detail) ? "" : detail;
    }

    public RecyclerViewItemBean setDetail(String detail) {
        this.detail = detail;
        return this;
    }

    public int getStatus() {
        return status;
    }

    public RecyclerViewItemBean setStatus(int status) {
        this.status = status;
        return this;
    }
}
