package com.bruce.chang.testfresco;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;

public class FrescoCircleActivity extends AppCompatActivity {

    SimpleDraweeView sdv_fresco_circleandcorner;
    Button bt_fresco_circle, bt_fresco_corner;
    private GenericDraweeHierarchyBuilder builder;
    private Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fresco_circle);
        setTitle("圆形和圆角图片");
        sdv_fresco_circleandcorner = (SimpleDraweeView) findViewById(R.id.sdv_fresco_circleandcorner);
        bt_fresco_circle = (Button) findViewById(R.id.bt_fresco_circle);
        bt_fresco_corner = (Button) findViewById(R.id.bt_fresco_corner);

         uri = Uri.parse("http://bizhi.zhuoku.com/bizhi/200706/4/20070625/bingbing/012.jpg");
         builder = new GenericDraweeHierarchyBuilder(getResources());

        bt_fresco_circle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 参数设置为圆形
                RoundingParams params = RoundingParams.asCircle();
                GenericDraweeHierarchy hierarchy = builder.setRoundingParams(params).build();
                sdv_fresco_circleandcorner.setHierarchy(hierarchy);
                sdv_fresco_circleandcorner.setImageURI(uri);
            }
        });

        bt_fresco_corner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 配置参数
                RoundingParams params = RoundingParams.fromCornersRadius(50f);//设置圆角大小
                params.setOverlayColor(getResources().getColor(android.R.color.holo_blue_light));//覆盖层
                params.setBorder(getResources().getColor(android.R.color.holo_blue_light), 5);//边框
//                params.setRoundAsCircle(true);//如果是RoundingParams.fromCornersRadius，这个可以强制进行圆形展示
                // 设置圆形参数
                GenericDraweeHierarchy hierarchy = builder.setRoundingParams(params).build();
                sdv_fresco_circleandcorner.setHierarchy(hierarchy);
                // 加载图片
                sdv_fresco_circleandcorner.setImageURI(uri);
            }
        });
    }
}
