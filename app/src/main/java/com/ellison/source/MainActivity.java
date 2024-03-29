package com.ellison.source;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.ellison.aop.AopDetailActivity;
import com.ellison.source.adapter.RecyclerViewAdapter;
import com.ellison.source.bean.RecyclerViewItemBean;
import com.ellison.source.butterknife.ButterknifeActivity;
import com.ellison.source.utils.DensityUtils;
import com.makeramen.roundedimageview.demo.RoundedImageViewActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));


        List<RecyclerViewItemBean> list = new ArrayList<>();
        RecyclerViewItemBean roundedItemBean = new RecyclerViewItemBean(R.drawable.icon_circle_image,
                getString(R.string.source_code_rounded_image_view),
                getString(R.string.source_code_rounded_image_view_detail),
                RecyclerViewItemBean.STATUS_FINISH, DensityUtils.dip2px(200.0f + new Random().nextInt(100)), RoundedImageViewActivity.class);

        RecyclerViewItemBean aopItemBean = new RecyclerViewItemBean(R.drawable.icon_aop_aspectj,
                "Aop 详细使用规则",
                "AspectJ 你听说过嘛？",
                RecyclerViewItemBean.STATUS_FINISH, DensityUtils.dip2px(200.0f + new Random().nextInt(100)), AopDetailActivity.class);

        RecyclerViewItemBean butterknifeItemBean = new RecyclerViewItemBean(R.drawable.icon_source_code_butterknife,
                getString(R.string.source_code_butterknife),
                getString(R.string.source_code_butterknife_detail),
                RecyclerViewItemBean.STATUS_ING, DensityUtils.dip2px(200.0f + new Random().nextInt(100)), ButterknifeActivity.class);

        RecyclerViewItemBean more1 = new RecyclerViewItemBean(R.drawable.shap_source_read_code_blue_place_img,
                getString(R.string.source_code_more),
                getString(R.string.source_code_more_detail),
                RecyclerViewItemBean.STATUS_ING, DensityUtils.dip2px(200.0f + new Random().nextInt(100)), RoundedImageViewActivity.class);

        RecyclerViewItemBean more2 = new RecyclerViewItemBean(R.drawable.shap_source_read_code_yellow_place_img,
                getString(R.string.source_code_more),
                getString(R.string.source_code_more_detail),
                RecyclerViewItemBean.STATUS_ING, DensityUtils.dip2px(200.0f + new Random().nextInt(100)), RoundedImageViewActivity.class);

        RecyclerViewItemBean more3 = new RecyclerViewItemBean(R.drawable.shap_source_read_code_red_place_img,
                getString(R.string.source_code_more),
                getString(R.string.source_code_more_detail),
                RecyclerViewItemBean.STATUS_ING, DensityUtils.dip2px(200.0f + new Random().nextInt(100)), RoundedImageViewActivity.class);

        list.add(roundedItemBean);
        list.add(aopItemBean);
        list.add(butterknifeItemBean);
        list.add(more2);
        list.add(more3);
        list.add(more2);
        list.add(more1);
        list.add(more2);
        list.add(more3);
        list.add(more3);
        list.add(more2);
        list.add(more1);
        list.add(more2);
        list.add(more3);
        list.add(more2);
        list.add(more3);
        list.add(more2);
        list.add(more1);
        list.add(more2);
        list.add(more3);
        list.add(more3);
        list.add(more2);
        list.add(more1);
        list.add(more2);
        list.add(more3);
        list.add(more2);

        RecyclerViewAdapter viewAdapter = new RecyclerViewAdapter(this, list);
        mRecyclerView.setAdapter(viewAdapter);

    }


}
