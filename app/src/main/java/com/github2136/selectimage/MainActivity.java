package com.github2136.selectimage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.github2136.selectimamge.activity.SelectImageActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnImg = (Button) findViewById(R.id.im_select_img);
        btnImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SelectImageActivity.class);
                intent.putExtra(SelectImageActivity.ARG_SELECT_COUNT, 5);
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            ArrayList<String> result = data.getStringArrayListExtra(SelectImageActivity.ARG_RESULT);
            for (int i = 0; i < result.size(); i++) {
                String s = result.get(i);
                Log.e("path", s);
            }
        }
    }
}
