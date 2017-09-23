package com.github2136.selectimamge.activity;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.github2136.selectimamge.R;
import com.github2136.selectimamge.adapter.PhotoAdapter;
import com.github2136.selectimamge.widget.SelectViewPager;

import java.util.List;

/**
 * 查看图片
 */
public class PhotoViewActivity extends AppCompatActivity {
    public static final String ARG_PHOTOS = "PHOTOS";
    public static final String ARG_CURRENT_INDEX = "CURRENT_INDEX";
    private List<String> mPhotoPaths;
    private int mCurrentIndex;
    private SelectViewPager vpPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_view);
        mPhotoPaths = getIntent().getStringArrayListExtra(ARG_PHOTOS);
        mCurrentIndex = getIntent().getIntExtra(ARG_CURRENT_INDEX, 0);
        Toolbar tbTitle = (Toolbar) findViewById(R.id.tb_title);
        setSupportActionBar(tbTitle);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        vpPhoto = (SelectViewPager) findViewById(R.id.vp_photo);
        vpPhoto.setAdapter(new PhotoAdapter(getSupportFragmentManager(), mPhotoPaths));
        vpPhoto.setCurrentItem(mCurrentIndex);
        vpPhoto.addOnPageChangeListener(mPagerChangeListener);
        setTitle();
    }

    private void setTitle() {
        setTitle(String.format("%d/%d", vpPhoto.getCurrentItem() + 1, mPhotoPaths.size()));//标题
    }

    ViewPager.SimpleOnPageChangeListener mPagerChangeListener = new ViewPager.SimpleOnPageChangeListener() {
        @Override
        public void onPageSelected(int position) {
            setTitle();
        }
    };

    /**
     * 菜单点击事件
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }
}
