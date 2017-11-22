package com.github2136.selectimamge.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.github2136.base.BaseRecyclerAdapter;
import com.github2136.base.ViewHolderRecyclerView;
import com.github2136.selectimamge.R;
import com.github2136.selectimamge.entity.SelectImage;
import com.github2136.selectimamge.other.GlideApp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yubin on 2017/8/26.
 */

public class SelectImageAdapter extends BaseRecyclerAdapter<SelectImage> {
    private ArrayList<String> mSelectPaths;
    private int mViewSize;
    private int mImgSize;
    private int mSelectCount;
    private RelativeLayout.LayoutParams layoutParams;
    private OnSelectChangeCallback mOnSelectChangeCallback;

    public SelectImageAdapter(Context context, List<SelectImage> list, int selectCount) {
        super(context, list);
        mViewSize = (context.getResources().getDisplayMetrics().widthPixels - 5 * 4) / 3;
        mImgSize = mViewSize;
        layoutParams = new RelativeLayout.LayoutParams(mViewSize, mViewSize);
        mSelectPaths = new ArrayList<>();
        mSelectCount = selectCount;
    }

    public void setOnSelectImageCallback(OnSelectChangeCallback onSelectImageCallback) {
        this.mOnSelectChangeCallback = onSelectImageCallback;
    }

    public ArrayList<String> getSelectPaths() {
        return mSelectPaths;
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.item_util_select_image;
    }

    @Override
    protected void onBindView(final SelectImage selectImage, ViewHolderRecyclerView holder, final int position) {
        ImageView ivImage = holder.getView(R.id.iv_image);
        ImageButton ibCheck = holder.getView(R.id.ib_check);
        ivImage.setLayoutParams(layoutParams);
        GlideApp.with(mContext)
                .load(selectImage.getData())
                .override(mImgSize, mImgSize)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(R.drawable.img_select_fail)
                .into(ivImage);
        if (mSelectPaths.contains(selectImage.getData())) {
            ibCheck.setImageResource(R.drawable.ic_select_checkbox_check);
        } else {
            ibCheck.setImageResource(R.drawable.ic_select_checkbox_uncheck);
        }
        ibCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mSelectPaths.contains(selectImage.getData())) {
                    mSelectPaths.remove(selectImage.getData());
                } else {
                    if (mSelectCount > mSelectPaths.size()) {
                        mSelectPaths.add(selectImage.getData());
                    }
                }
                notifyItemChanged(position);
                if (mOnSelectChangeCallback != null) {
                    mOnSelectChangeCallback.selectChange(mSelectPaths.size());
                }
            }
        });
    }

    public void clearSelectPaths() {
        mSelectPaths = new ArrayList<>();
    }

    public interface OnSelectChangeCallback {
        void selectChange(int selectCount);
    }
}
