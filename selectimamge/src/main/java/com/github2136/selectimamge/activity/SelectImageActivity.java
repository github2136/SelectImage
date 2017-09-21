package com.github2136.selectimamge.activity;

import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.Toast;

import com.github2136.base.BaseRecyclerAdapter;
import com.github2136.selectimamge.R;
import com.github2136.selectimamge.other.SelectImageItemDecoration;
import com.github2136.selectimamge.adapter.SelectImageAdapter;
import com.github2136.selectimamge.entity.SelectImage;

import java.util.ArrayList;
import java.util.List;

/**
 * 选择图片
 */
public class SelectImageActivity extends AppCompatActivity {
    private List<SelectImage> mImages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_util_select_image);
        Toolbar tbTitle = (Toolbar) findViewById(R.id.tb_title);
        setSupportActionBar(tbTitle);
        setTitle("0/10");//标题
        // getSupportActionBar().setTitle("标题");
        // getSupportActionBar().setSubtitle("副标题");
        // getSupportActionBar().setLogo(R.drawable.ic_launcher);

        /* 菜单的监听可以在toolbar里设置，也可以像ActionBar那样，通过Activity的onOptionsItemSelected回调方法来处理 */
//        tbTitle.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem item) {
//                switch (item.getItemId()) {
//                    case R.id.action_settings:
//                        Toast.makeText(MainActivity.this, "action_settings", 0).show();
//                        break;
//                    case R.id.action_share:
//                        Toast.makeText(MainActivity.this, "action_share", 0).show();
//                        break;
//                    default:
//                        break;
//                }
//                return true;
//            }
//        });
        //显示返回按钮onOptionsItemSelected中监听id固定为android.R.id.home
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        RecyclerView rvImages = (RecyclerView) findViewById(R.id.rv_images);
        rvImages.setHasFixedSize(true);
        SelectImageItemDecoration selectImageItemDecoration = new SelectImageItemDecoration(3, 5, true);
        rvImages.addItemDecoration(selectImageItemDecoration);
        mImages = getImages();
        SelectImageAdapter selectImageAdapter = new SelectImageAdapter(this, mImages);
        rvImages.setAdapter(selectImageAdapter);
        selectImageAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseRecyclerAdapter baseRecyclerAdapter, int i) {
                Intent intent = new Intent(SelectImageActivity.this, PhotoViewActivity.class);
                ArrayList<String> path = new ArrayList<>();
                path.add(mImages.get(i).getData());
                intent.putStringArrayListExtra(PhotoViewActivity.ARG_PHOTOS, path);
                startActivity(intent);
            }
        });
//        rvImages.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
//            @Override
//            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
//                return false;
//            }
//
//            @Override
//            public void onTouchEvent(RecyclerView rv, MotionEvent e) {
//
//            }
//
//            @Override
//            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
//
//            }
//        });
    }

    private List<SelectImage> getImages() {
        ContentResolver contentResolver = getContentResolver();
        Cursor cursor;
        try {
            cursor = contentResolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, null, null, null);
        } catch (Exception e) {
            e.printStackTrace();
            if (e.getMessage().startsWith("Permission Denial")) {
                new AlertDialog
                        .Builder(this)
                        .setTitle("警告")
                        .setMessage("没有外部存储目录读取权限")
                        .setPositiveButton("关闭", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        })
                        .show();
            } else {
                Toast.makeText(this, "图片数据获取失败", Toast.LENGTH_SHORT).show();
            }
            //Permission Denial: reading com.android.providers.media.MediaProvider uri content://media/external/images/media from pid=4833,
            // uid=10059 requires android.permission.READ_EXTERNAL_STORAGE, or grantUriPermission()
            return null;
        }
        List<SelectImage> images = new ArrayList<>();
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                int columnIndex1 = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DESCRIPTION);
                int columnIndex2 = cursor.getColumnIndex(MediaStore.Images.ImageColumns.PICASA_ID);
                int columnIndex3 = cursor.getColumnIndex(MediaStore.Images.ImageColumns.IS_PRIVATE);
                int columnIndex4 = cursor.getColumnIndex(MediaStore.Images.ImageColumns.LATITUDE);
                int columnIndex5 = cursor.getColumnIndex(MediaStore.Images.ImageColumns.LONGITUDE);
                int columnIndex6 = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATE_TAKEN);
                int columnIndex7 = cursor.getColumnIndex(MediaStore.Images.ImageColumns.ORIENTATION);
                int columnIndex8 = cursor.getColumnIndex(MediaStore.Images.ImageColumns.MINI_THUMB_MAGIC);
                int columnIndex9 = cursor.getColumnIndex(MediaStore.Images.ImageColumns.BUCKET_ID);
                int columnIndex10 = cursor.getColumnIndex(MediaStore.Images.ImageColumns.BUCKET_DISPLAY_NAME);

                int columnIndex11 = cursor.getColumnIndex(MediaStore.MediaColumns._ID);

                int columnIndex12 = cursor.getColumnIndex(MediaStore.MediaColumns.DATA);
                int columnIndex13 = cursor.getColumnIndex(MediaStore.MediaColumns.SIZE);
                int columnIndex14 = cursor.getColumnIndex(MediaStore.MediaColumns.DISPLAY_NAME);
                int columnIndex15 = cursor.getColumnIndex(MediaStore.MediaColumns.TITLE);
                int columnIndex16 = cursor.getColumnIndex(MediaStore.MediaColumns.DATE_ADDED);
                int columnIndex17 = cursor.getColumnIndex(MediaStore.MediaColumns.DATE_MODIFIED);
                int columnIndex18 = cursor.getColumnIndex(MediaStore.MediaColumns.MIME_TYPE);
                int columnIndex19 = cursor.getColumnIndex(MediaStore.MediaColumns.WIDTH);
                int columnIndex20 = cursor.getColumnIndex(MediaStore.MediaColumns.HEIGHT);


                do {
                    SelectImage img = new SelectImage();
                    img.setDescription(cursor.getString(columnIndex1));
                    img.setPicasa_id(cursor.getString(columnIndex2));
                    img.setIs_private(cursor.getInt(columnIndex3));
                    img.setLatitude(cursor.getDouble(columnIndex4));
                    img.setLongitude(cursor.getDouble(columnIndex5));
                    img.setDate_taken(cursor.getInt(columnIndex6));
                    img.setOrientation(cursor.getInt(columnIndex7));
                    img.setMini_thumb_magic(cursor.getInt(columnIndex8));
                    img.setBucket_id(cursor.getString(columnIndex9));
                    img.setBucket_display_name(cursor.getString(columnIndex10));

                    img.set_id(cursor.getLong(columnIndex11));

                    img.setData(cursor.getString(columnIndex12));
                    img.setSize(cursor.getLong(columnIndex13));
                    img.setDisplay_name(cursor.getString(columnIndex14));
                    img.setTitle(cursor.getString(columnIndex15));
                    img.setDate_added(cursor.getLong(columnIndex16));
                    img.setDate_modified(cursor.getLong(columnIndex17));
                    img.setMime_type(cursor.getString(columnIndex18));
                    img.setWidth(cursor.getInt(columnIndex19));
                    img.setHeight(cursor.getInt(columnIndex20));

                    images.add(img);
                } while (cursor.moveToNext());
            }
            cursor.close();
        }
        return images;
    }

    /**
     * 创建菜单
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.select_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

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
