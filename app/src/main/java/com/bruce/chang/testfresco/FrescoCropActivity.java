package com.bruce.chang.testfresco;

import android.graphics.PointF;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.view.SimpleDraweeView;

public class FrescoCropActivity extends AppCompatActivity implements View.OnClickListener {
    SimpleDraweeView sdv_fresco_crop;
    TextView tv_fresco_explain;
    Button bt_fresco_center, bt_fresco_centercrop, bt_fresco_focuscrop, bt_fresco_centerinside, bt_fresco_fitcenter, bt_fresco_fitstart, bt_fresco_fitend, bt_fresco_fitxy, bt_fresco_none;
    GenericDraweeHierarchyBuilder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fresco_crop);
        setTitle("图片的不同裁剪");
        initViewAndListener();
        builder = new GenericDraweeHierarchyBuilder(getResources());
    }

    private void initViewAndListener() {
        sdv_fresco_crop = (SimpleDraweeView) findViewById(R.id.sdv_fresco_crop);
        tv_fresco_explain = (TextView) findViewById(R.id.tv_fresco_explain);
        bt_fresco_center = (Button) findViewById(R.id.bt_fresco_center);
        bt_fresco_center.setOnClickListener(this);
        bt_fresco_centercrop = (Button) findViewById(R.id.bt_fresco_centercrop);
        bt_fresco_centercrop.setOnClickListener(this);
        bt_fresco_focuscrop = (Button) findViewById(R.id.bt_fresco_focuscrop);
        bt_fresco_focuscrop.setOnClickListener(this);
        bt_fresco_centerinside = (Button) findViewById(R.id.bt_fresco_centerinside);
        bt_fresco_centerinside.setOnClickListener(this);
        bt_fresco_fitcenter = (Button) findViewById(R.id.bt_fresco_fitcenter);
        bt_fresco_fitcenter.setOnClickListener(this);
        bt_fresco_fitstart = (Button) findViewById(R.id.bt_fresco_fitstart);
        bt_fresco_fitstart.setOnClickListener(this);
        bt_fresco_fitend = (Button) findViewById(R.id.bt_fresco_fitend);
        bt_fresco_fitend.setOnClickListener(this);
        bt_fresco_fitxy = (Button) findViewById(R.id.bt_fresco_fitxy);
        bt_fresco_fitxy.setOnClickListener(this);
        bt_fresco_none = (Button) findViewById(R.id.bt_fresco_none);
        bt_fresco_none.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            //CENTER
            case R.id.bt_fresco_center:
                tv_fresco_explain.setText("居中，无缩放");
                //样式设置
                GenericDraweeHierarchy hierarchy = builder.setActualImageScaleType(ScalingUtils.ScaleType.CENTER).build();
                //显示图片
                imageDisplay(hierarchy);
                break;
            //CENTER_CROP
            case R.id.bt_fresco_centercrop:
                tv_fresco_explain.setText("保持宽高比缩小或放大，使得两边都大于或等于显示边界。居中显示");
                //样式设置
                GenericDraweeHierarchy hierarchy1 = builder.setActualImageScaleType(ScalingUtils.ScaleType.CENTER_CROP).build();
                //显示图片
                imageDisplay(hierarchy1);
                break;
            //FOCUS_CROP
            case R.id.bt_fresco_focuscrop:
                tv_fresco_explain.setText("同centerCrop, 但居中点不是中点，而是指定的某个点,这里我设置为图片的左上角那点");
                //设置focusCrop的缩放形式  并指定缩放的中心点在左上角
                PointF point = new PointF(0f, 0f);
                //样式设置
                GenericDraweeHierarchy hierarchy2 = builder.setActualImageScaleType(ScalingUtils.ScaleType.FOCUS_CROP)
                        .setActualImageFocusPoint(point)
                        .build();
                //显示图片
                imageDisplay(hierarchy2);
                break;
            //CENTER_INSIDE
            case R.id.bt_fresco_centerinside:
                tv_fresco_explain.setText("使两边都在显示边界内，居中显示。如果图尺寸大于显示边界，则保持长宽比缩小图片");
                GenericDraweeHierarchy hierarchy3 = builder.setActualImageScaleType(ScalingUtils.ScaleType.CENTER_INSIDE).build();
                imageDisplay(hierarchy3);
                break;
            //FIT_CENTER
            case R.id.bt_fresco_fitcenter:
                tv_fresco_explain.setText("保持宽高比，缩小或者放大，使得图片完全显示在显示边界内。居中显示");
                GenericDraweeHierarchy hierarchy4 = builder.setActualImageScaleType(ScalingUtils.ScaleType.FIT_CENTER).build();
                imageDisplay(hierarchy4);
                break;
            //FIT_START
            case R.id.bt_fresco_fitstart:
                tv_fresco_explain.setText("保持宽高比，缩小或者放大，使得图片完全显示在显示边界内，不居中，和显示边界左上对齐");
                GenericDraweeHierarchy hierarchy5 = builder.setActualImageScaleType(ScalingUtils.ScaleType.FIT_START).build();
                imageDisplay(hierarchy5);
                break;
            //FIT_END
            case R.id.bt_fresco_fitend:
                tv_fresco_explain.setText("保持宽高比，缩小或者放大，使得图片完全显示在显示边界内，不居中，和显示边界右下对齐");
                GenericDraweeHierarchy hierarchy6 = builder.setActualImageScaleType(ScalingUtils.ScaleType.FIT_END).build();
                imageDisplay(hierarchy6);
                break;
            //FIT_XY
            case R.id.bt_fresco_fitxy:
                tv_fresco_explain.setText("不保持宽高比，填充满显示边界");
                GenericDraweeHierarchy hierarchy7 = builder.setActualImageScaleType(ScalingUtils.ScaleType.FIT_XY).build();
                imageDisplay(hierarchy7);
                break;
            //null
            case R.id.bt_fresco_none:
                tv_fresco_explain.setText("不设置任何样式");
                GenericDraweeHierarchy hierarchy8 = builder.setActualImageScaleType(null).build();
                imageDisplay(hierarchy8);
                break;
            default:
                break;
        }
    }

    //显示图片
    private void imageDisplay(GenericDraweeHierarchy hierarchy) {
        sdv_fresco_crop.setHierarchy(hierarchy);
        Uri uri = Uri.parse("http://bizhi.zhuoku.com/bizhi/200706/4/20070625/bingbing/012.jpg");
        sdv_fresco_crop.setImageURI(uri);
    }
}
