package com.roujiamo.sample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;

public class IntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        findViewById(R.id.btn_go_study).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build("/study/main")
                        .withLong("key1", 666L)
                        .withString("key2", "888")
                        .navigation();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

}
