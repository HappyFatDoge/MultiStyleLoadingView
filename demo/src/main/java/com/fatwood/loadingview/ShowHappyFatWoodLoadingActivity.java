package com.fatwood.loadingview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;

import com.fatwood.loadingview.fatwood.HappyFatWoodLoadingView;

public class ShowHappyFatWoodLoadingActivity extends AppCompatActivity {

    private HappyFatWoodLoadingView mHappyFatWoodLoadingView;
    private HappyFatWoodLoadingView mHappyFatWoodLoadingViewT;
    private SeekBar mSeekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_happy_fat_wood_loading_layout);
        initView();
        initEvent();
    }

    private void initView() {
        mHappyFatWoodLoadingView = findViewById(R.id.happy_fat_wood_view);
        mHappyFatWoodLoadingViewT = findViewById(R.id.happy_fat_wood_view_02);
        mSeekBar = findViewById(R.id.seekBar);
        try {
            mHappyFatWoodLoadingViewT.setProgress(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initEvent() {
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                try {
                    mHappyFatWoodLoadingViewT.setProgress(progress);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    public void onClick(View view) {
        int viewId = view.getId();
        switch (viewId) {
            case R.id.btn_finish:
                mHappyFatWoodLoadingView.finish();
                break;
        }
    }
}
