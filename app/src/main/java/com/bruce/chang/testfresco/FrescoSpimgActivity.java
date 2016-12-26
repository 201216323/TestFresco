package com.bruce.chang.testfresco;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.facebook.drawee.drawable.ProgressBarDrawable;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.view.SimpleDraweeView;


public class FrescoSpimgActivity extends AppCompatActivity {

    SimpleDraweeView sdv_fresco;
    Button bt_load;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fresco_spimg);
        sdv_fresco = (SimpleDraweeView) findViewById(R.id.sdv_fresco);
        bt_load = (Button) findViewById(R.id.bt_load);
        setTitle("带进度条的图片");
        bt_load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 设置带进度条样式
                GenericDraweeHierarchyBuilder builder = new GenericDraweeHierarchyBuilder(getResources());
                GenericDraweeHierarchy hierarchy = builder.setProgressBarImage(new ProgressBarDrawable()).build();
                sdv_fresco.setHierarchy(hierarchy);
                Uri uri = Uri.parse("http://bizhi.zhuoku.com/bizhi/200706/4/20070625/bingbing/012.jpg");
                // 设置显示图片
                sdv_fresco.setImageURI(uri);
            }
        });

    }
}
