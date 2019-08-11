package com.ellison.source.butterknife;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ellison.source.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author ellison
 * @date 2019年07月24日
 * @desc 用一句话描述这个类的作用
 * <p>
 * 邮箱： Ellison.Sun0808@outlook.com
 * 博客： <a href="https://www.jianshu.com/u/b1c92a64018a">简书博客</a>
 */
public class ButterknifeActivity extends AppCompatActivity {

    @BindView(R.id.butterknife_recycler_view)
    RecyclerView mRecyclerView;
    private Unbinder mBind;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_butterknife);
        mBind = ButterKnife.bind(this);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new ButterknifeAdapter());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mBind != null) {
            mBind.unbind();
        }
    }

    public class ButterknifeAdapter extends RecyclerView.Adapter<ButterknifeAdapter.ButterknifeHolder> {

        @NonNull
        @Override
        public ButterknifeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ButterknifeHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_butterknife_num, parent, false));
        }

        @SuppressLint("SetTextI18n")
        @Override
        public void onBindViewHolder(@NonNull ButterknifeHolder holder, int position) {
            holder.mTextView.setText((position + 1) + "");
        }

        @Override
        public int getItemCount() {
            return Integer.MAX_VALUE;
        }

        class ButterknifeHolder extends RecyclerView.ViewHolder {
            @BindView(R.id.item_butterknife_tv)
            TextView mTextView;

            public ButterknifeHolder(@NonNull View itemView) {
                super(itemView);

                ButterKnife.bind(this, itemView);
            }
        }
    }
}
