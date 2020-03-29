package com.fatdoge.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;

import com.fatdoge.loadingview.waveball.WaveBallLoadingView;

public class ShowWaveBallLoadingActivity extends AppCompatActivity {

    private SeekBar mSeekBar;
    private WaveBallLoadingView mWaveBallLoadingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_wave_ball_layout);
        initView();
        initEvent();
    }

    private void initView() {
        mSeekBar = findViewById(R.id.seekBar);
        mWaveBallLoadingView = findViewById(R.id.wave_ball_loading_view);

        mWaveBallLoadingView.setDescription("性能优化中...");
        try {
            mWaveBallLoadingView.setProgress(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initEvent() {
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                try {
                    mWaveBallLoadingView.setProgress(i);
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
        if (viewId == R.id.btn_end_anim) {
            mWaveBallLoadingView.endOvalRingAnim();
        }
    }
}
