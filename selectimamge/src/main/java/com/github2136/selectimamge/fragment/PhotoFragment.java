package com.github2136.selectimamge.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.github.chrisbanes.photoview.PhotoView;
import com.github2136.selectimamge.R;
import com.github2136.selectimamge.other.GlideApp;

public class PhotoFragment extends Fragment {
    public static final String ARG_PHOTO_PATH = "PHOTO_PATH";
    private String photoPath;
    private PhotoView ivPhoto;

    public PhotoFragment() { }

    public static PhotoFragment newInstance(String photoPath) {
        PhotoFragment fragment = new PhotoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PHOTO_PATH, photoPath);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            photoPath = getArguments().getString(ARG_PHOTO_PATH);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_photo, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        ivPhoto = (PhotoView) view.findViewById(R.id.iv_photo);

        GlideApp.with(getContext())
                .load(photoPath)
                .diskCacheStrategy(DiskCacheStrategy.ALL.ALL)
                .placeholder(R.drawable.img_select_place)
                .error(R.drawable.img_select_fail)
                .into(ivPhoto);
    }
}
