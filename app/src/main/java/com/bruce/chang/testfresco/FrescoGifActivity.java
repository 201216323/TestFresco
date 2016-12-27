package com.bruce.chang.testfresco;

import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

public class FrescoGifActivity extends AppCompatActivity {
    SimpleDraweeView sdv_fresco_gif;
    Button bt_fresco_askImg, bt_fresco_stopAnim, bt_fresco_startAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fresco_gif);
        sdv_fresco_gif = (SimpleDraweeView) findViewById(R.id.sdv_fresco_gif);
        bt_fresco_askImg = (Button) findViewById(R.id.bt_fresco_askImg);
        bt_fresco_stopAnim = (Button) findViewById(R.id.bt_fresco_stopAnim);
        bt_fresco_startAnim = (Button) findViewById(R.id.bt_fresco_startAnim);
        setTitle("Gif动画图片");

        bt_fresco_askImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 图片地址
                Uri uri = Uri.parse("http://www.sznews.com/humor/attachement/gif/site3/20140902/4487fcd7fc66156f51db5d.gif");
                DraweeController controller = Fresco.newDraweeControllerBuilder()
                        .setUri(uri)
                        .setAutoPlayAnimations(false)//设置为true将循环播放Gif动画
                        .setOldController(sdv_fresco_gif.getController())
                        .build();

                // 设置控制器
                sdv_fresco_gif.setController(controller);
            }
        });

        bt_fresco_startAnim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animatable animation = sdv_fresco_gif.getController().getAnimatable();

                if (animation != null && !animation.isRunning()) {
                    animation.start();
                }
            }
        });

        bt_fresco_startAnim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animatable animation = sdv_fresco_gif.getController().getAnimatable();

                if (animation != null && !animation.isRunning()) {
                    animation.start();
                }
            }
        });

        bt_fresco_stopAnim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animatable animation = sdv_fresco_gif.getController().getAnimatable();

                if (animation != null && animation.isRunning()) {
                    animation.stop();
                }
            }
        });
    }
}
