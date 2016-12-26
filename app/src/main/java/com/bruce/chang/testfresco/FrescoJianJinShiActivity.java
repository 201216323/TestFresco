package com.bruce.chang.testfresco;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.decoder.ProgressiveJpegConfig;
import com.facebook.imagepipeline.image.ImmutableQualityInfo;
import com.facebook.imagepipeline.image.QualityInfo;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

public class FrescoJianJinShiActivity extends AppCompatActivity {

    SimpleDraweeView sdv_fresco_jpeg;
    Button sdv_fresco_askImg;
    private GenericDraweeHierarchyBuilder builder;
    private Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fresco_jian_jin_shi);
        sdv_fresco_jpeg = (SimpleDraweeView) findViewById(R.id.sdv_fresco_jpeg);
        sdv_fresco_askImg = (Button) findViewById(R.id.sdv_fresco_askImg);
        setTitle("渐进式展示图片");

        builder = new GenericDraweeHierarchyBuilder(getResources());

        sdv_fresco_askImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 加载质量配置
                ProgressiveJpegConfig jpegConfig = new ProgressiveJpegConfig() {
                    @Override
                    public int getNextScanNumberToDecode(int scanNumber) {
                        return scanNumber + 2;
                    }

                    @Override
                    public QualityInfo getQualityInfo(int scanNumber) {
                        boolean isGoodEnough = (scanNumber >= 5);

                        return ImmutableQualityInfo.of(scanNumber, isGoodEnough, false);
                    }
                };

                ImagePipelineConfig.newBuilder(FrescoJianJinShiActivity.this).setProgressiveJpegConfig(jpegConfig).build();
                // 获取图片URL
                uri = Uri.parse("http://bizhi.zhuoku.com/bizhi/200706/4/20070625/bingbing/012.jpg");

                // 获取图片请求
                ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri).setProgressiveRenderingEnabled(true).build();

                DraweeController draweeController = Fresco.newDraweeControllerBuilder()
                        .setImageRequest(request)
                        .setTapToRetryEnabled(true)
                        .setOldController(sdv_fresco_jpeg.getController())//使用oldController可以节省不必要的内存分配
                        .build();

                // 设置加载的控制
                sdv_fresco_jpeg.setController(draweeController);
            }
        });
    }
}
