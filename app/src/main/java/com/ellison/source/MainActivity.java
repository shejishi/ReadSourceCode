package com.ellison.source;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.ellison.source.adapter.RecyclerViewAdapter;
import com.ellison.source.bean.RecyclerViewItemBean;
import com.ellison.source.utils.DensityUtils;

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
                "源码解读系列 —— CircleImageView",
                "阅读的源码都是Github上star数量比较多的项目，其中有一位优秀得开发者整合了star比较多的框架：https://github.com/SenhLinsh/Android-Hot-Libraries",
                RecyclerViewItemBean.STATUS_ING, DensityUtils.dip2px(200.0f + new Random().nextInt(100)));

        RecyclerViewItemBean more1 = new RecyclerViewItemBean(R.drawable.shap_source_read_code_blue_place_img,
                "待续 —— ",
                "后面再分析",
                RecyclerViewItemBean.STATUS_ING, DensityUtils.dip2px(200.0f + new Random().nextInt(100)));

        RecyclerViewItemBean more2 = new RecyclerViewItemBean(R.drawable.shap_source_read_code_yellow_place_img,
                "待续 —— ",
                "后面再分析",
                RecyclerViewItemBean.STATUS_ING, DensityUtils.dip2px(200.0f + new Random().nextInt(100)));

        RecyclerViewItemBean more3 = new RecyclerViewItemBean(R.drawable.shap_source_read_code_red_place_img,
                "待续 —— ",
                "后面再分析",
                RecyclerViewItemBean.STATUS_ING, DensityUtils.dip2px(200.0f + new Random().nextInt(100)));

        list.add(roundedItemBean);
        list.add(more1);
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

        RecyclerViewAdapter viewAdapter = new RecyclerViewAdapter(list);
        mRecyclerView.setAdapter(viewAdapter);

    }


}
