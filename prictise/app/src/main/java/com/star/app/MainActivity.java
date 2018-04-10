package com.star.app;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.star.app.retrofit.DialogObserver;
import com.star.app.retrofit.RequestCommand;

/**
 * Created by suxing on 2018/4/10.
 */
public class MainActivity extends Activity implements View.OnClickListener {

    private TextView text;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.main_btn).setOnClickListener(this);
        text = findViewById(R.id.main_tv);
    }

    @Override
    public void onClick(View v) {
        RequestCommand.requestRefresh(new configObserver(MainActivity.this));
    }

    private class configObserver extends DialogObserver<String> {
        public configObserver(Context mContext) {
            super(mContext);
        }

        @Override
        public void success(String info) {

        }
    }
}
