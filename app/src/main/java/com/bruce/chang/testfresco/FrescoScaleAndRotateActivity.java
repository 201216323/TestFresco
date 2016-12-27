package com.bruce.chang.testfresco;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeController;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.common.RotationOptions;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

public class FrescoScaleAndRotateActivity extends AppCompatActivity {
    SimpleDraweeView sdv_fresco_resize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fresco_scale_and_rotate);
        sdv_fresco_resize = (SimpleDraweeView) findViewById(R.id.sdv_fresco_resize);
        setTitle("图片缩放和旋转");
    }

    //缩放
    void bt_fresco_resize_click(View view) {

        int width = 50;
        int height = 50;
        Uri uri = Uri.parse("http://c.hiphotos.baidu.com/image/pic/item/962bd40735fae6cd21a519680db30f2442a70fa1.jpg");
        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
                .setResizeOptions(new ResizeOptions(width, height)).build();
        PipelineDraweeController controller = (PipelineDraweeController) Fresco.newDraweeControllerBuilder()
                .setOldController(sdv_fresco_resize.getController())
                .setImageRequest(request)
                .build();
        sdv_fresco_resize.setController(controller);
    }

    //旋转
    void bt_fresco_rotate_click(View view) {
        Uri uri2 = Uri.parse("http://c.hiphotos.baidu.com/image/pic/item/962bd40735fae6cd21a519680db30f2442a70fa1.jpg");
        RotationOptions rotationOptions = RotationOptions.forceRotation(RotationOptions.ROTATE_90);
        ImageRequest request1 = ImageRequestBuilder.newBuilderWithSource(uri2)
                .setRotationOptions(RotationOptions.autoRotate())
                .setRotationOptions(rotationOptions)//旋转的时候要使用RotationOptions类
                .build();//但貌似Fresco在旋转的功能上不是很好
        DraweeController controller1 = Fresco.newDraweeControllerBuilder()
                .setImageRequest(request1)
                .setOldController(sdv_fresco_resize.getController()).build();
        sdv_fresco_resize.setController(controller1);
    }
}
