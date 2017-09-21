package com.github2136.selectimamge.activity;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_view);
        mPhotoPaths = getIntent().getStringArrayListExtra(ARG_PHOTOS);
        mCurrentIndex = getIntent().getIntExtra(ARG_CURRENT_INDEX, 0);
        Toolbar tbTitle = (Toolbar) findViewById(R.id.tb_title);
        setSupportActionBar(tbTitle);
        setTitle("0/10");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        SelectViewPager vpPhoto = (SelectViewPager) findViewById(R.id.vp_photo);
        vpPhoto.setAdapter(new PhotoAdapter(getSupportFragmentManager(), mPhotoPaths));
    }

    /**
     * 创建菜单
     */
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.select_menu, menu);
//        return super.onCreateOptionsMenu(menu);
//    }

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
