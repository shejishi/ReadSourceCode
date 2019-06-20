package com.ellison.source.adapter;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ellison.source.R;
import com.ellison.source.bean.RecyclerViewItemBean;
import com.ellison.source.utils.DensityUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author ellison
 * @date 2019年06月20日
 * @desc 用一句话描述这个类的作用
 * <p>
 * 邮箱： Ellison.Sun0808@outlook.com
 * 博客： <a href="https://www.jianshu.com/u/b1c92a64018a">简书博客</a>
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {

    private List<RecyclerViewItemBean> mBeanList;

    public RecyclerViewAdapter(List<RecyclerViewItemBean> list) {
        mBeanList = list == null ? new ArrayList<RecyclerViewItemBean>() : list;
    }

    public void updateAllData(List<RecyclerViewItemBean> list) {
        mBeanList.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new RecyclerViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_recycler_view, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder recyclerViewHolder, int i) {
        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) recyclerViewHolder.mItemCover.getLayoutParams();
        layoutParams.height = DensityUtils.dip2px(100.0f + new Random().nextInt(20));

        recyclerViewHolder.mItemCover.setImageDrawable(ContextCompat.getDrawable(recyclerViewHolder.mItemCover.getContext(), mBeanList.get(i).getCoverImg()));
        recyclerViewHolder.mTvTitle.setText(mBeanList.get(i).getTitle());
        recyclerViewHolder.mTvInfo.setText(mBeanList.get(i).getDetail());
        switch (mBeanList.get(i).getStatus()) {
            case RecyclerViewItemBean.STATUS_ING:
                recyclerViewHolder.mTvTag.setText("进行中");
                recyclerViewHolder.mTvTag.setTextColor(ContextCompat.getColor(recyclerViewHolder.mTvTag.getContext(), R.color.source_read_status_ing_color));
                recyclerViewHolder.mTvTag.setBackground(ContextCompat.getDrawable(recyclerViewHolder.mTvTag.getContext(), R.drawable.shap_source_read_status_ing));
                break;
            case RecyclerViewItemBean.STATUS_FINISH:
                recyclerViewHolder.mTvTag.setText("已完成");
                recyclerViewHolder.mTvTag.setTextColor(ContextCompat.getColor(recyclerViewHolder.mTvTag.getContext(), R.color.source_read_status_finish_color));
                recyclerViewHolder.mTvTag.setBackground(ContextCompat.getDrawable(recyclerViewHolder.mTvTag.getContext(), R.drawable.shap_source_read_status_finish));
                break;
            default:
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mBeanList.size();
    }

    class RecyclerViewHolder extends RecyclerView.ViewHolder {

        private final ImageView mItemCover;
        private final TextView mTvTitle;
        private final TextView mTvTag;
        private final TextView mTvInfo;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);

            mItemCover = itemView.findViewById(R.id.rounded_item_pic);
            mTvTitle = itemView.findViewById(R.id.rounded_item_tv_title);
            mTvTag = itemView.findViewById(R.id.rounded_item_tv_tag_status);
            mTvInfo = itemView.findViewById(R.id.rounded_item_tv_info);
        }
    }
}
