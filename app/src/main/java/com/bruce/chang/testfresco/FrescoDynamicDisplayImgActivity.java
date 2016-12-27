package com.bruce.chang.testfresco;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

public class FrescoDynamicDisplayImgActivity extends AppCompatActivity {

    LinearLayout ll_fresco;
    SimpleDraweeView simpleDraweeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fresco_dynamic_display_img);
        ll_fresco = (LinearLayout) findViewById(R.id.ll_fresco);
        setTitle("动态展示图片");

        simpleDraweeView = new SimpleDraweeView(this);
        //设置宽高比
        simpleDraweeView.setAspectRatio(1.0f);
    }

    void bt_fresco_loadsmall_click(View view) {
        ll_fresco.removeAllViews();
        final Uri uri = Uri.parse("http://img4q.duitang.com/uploads/item/201304/27/20130427043538_wAfHC.jpeg");

        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
                .build();

        PipelineDraweeController controller = (PipelineDraweeController)
                Fresco.newDraweeControllerBuilder()
                        .setImageRequest(request)
                        .setOldController(simpleDraweeView.getController())
                        .build();
        simpleDraweeView.setController(controller);
        ll_fresco.addView(simpleDraweeView);
    }
}
