package com.bruce.chang.testfresco;

import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.decoder.ProgressiveJpegConfig;
import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.imagepipeline.image.ImmutableQualityInfo;
import com.facebook.imagepipeline.image.QualityInfo;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

public class FrescoListenerActivity extends AppCompatActivity {
    SimpleDraweeView sdv_fresco_listener;
    Button bt_fresco_listener;
    TextView tv_fresco_listener;
    TextView tv_fresco_listener2;
    //对所有的图片加载，onFinalImageSet 或者 onFailure 都会被触发。前者在成功时，后者在失败时。
    //如果允许呈现渐进式JPEG，同时图片也是渐进式图片，onIntermediateImageSet会在每个扫描被解码后回调。
    // 具体图片的那个扫描会被解码，参见渐进式JPEG图
    private ControllerListener controllerListener = new BaseControllerListener<ImageInfo>() {
        @Override
        public void onFinalImageSet(String id, ImageInfo imageInfo, Animatable animatable) {
           //前者在成功时
            super.onFinalImageSet(id, imageInfo, animatable);

            if (imageInfo == null) {
                return;
            }

            QualityInfo qualityInfo = imageInfo.getQualityInfo();

            tv_fresco_listener.setText("Final image received! " +
                    "\nSize: " + imageInfo.getWidth()
                    + "x" + imageInfo.getHeight()
                    + "\nQuality level: " + qualityInfo.getQuality()
                    + "\ngood enough: " + qualityInfo.isOfGoodEnoughQuality()
                    + "\nfull quality: " + qualityInfo.isOfFullQuality());
        }

        @Override
        public void onIntermediateImageSet(String id, ImageInfo imageInfo) {
            super.onIntermediateImageSet(id, imageInfo);

            tv_fresco_listener2.setText("IntermediateImageSet image receiced");
        }
        //后者在失败时
        @Override
        public void onFailure(String id, Throwable throwable) {
            super.onFailure(id, throwable);

            tv_fresco_listener.setText("Error loading" + id);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fresco_listener);
        sdv_fresco_listener = (SimpleDraweeView) findViewById(R.id.sdv_fresco_listener);
        bt_fresco_listener = (Button) findViewById(R.id.bt_fresco_listener);
        tv_fresco_listener = (TextView) findViewById(R.id.tv_fresco_listener);
        tv_fresco_listener2 = (TextView) findViewById(R.id.tv_fresco_listener2);
        setTitle("图片加载监听");
    }

    void bt_fresco_listener_click(View view) {

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

        ImagePipelineConfig.newBuilder(this).setProgressiveJpegConfig(jpegConfig).build();
        Uri uri = Uri.parse("http://h.hiphotos.baidu.com/zhidao/pic/item/58ee3d6d55fbb2fbac4f2af24f4a20a44723dcee.jpg");
        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri).setProgressiveRenderingEnabled(true).build();

        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setOldController(sdv_fresco_listener.getController())
                .setImageRequest(request)
                //设置监听器监听图片加载状态
                .setControllerListener(controllerListener)
                .build();

        sdv_fresco_listener.setController(controller);
    }

}
