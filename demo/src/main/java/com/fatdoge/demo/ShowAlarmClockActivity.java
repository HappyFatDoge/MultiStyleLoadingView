package com.fatdoge.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.SeekBar;

import com.fatdoge.loadingview.alarm.AlarmClockLoadingView;

public class ShowAlarmClockActivity extends AppCompatActivity {

    private SeekBar mSeekBar;
    private AlarmClockLoadingView mAlarmClockLoadingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_alarm_clock_layout);
        initView();
        initEvent();
    }

    private void initView() {
        mSeekBar = findViewById(R.id.seekBar);
        mAlarmClockLoadingView = findViewById(R.id.alarm_clock_loading);

        mAlarmClockLoadingView.setSecondHandSpeed(500);
        try {
            mAlarmClockLoadingView.setProgress(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initEvent() {
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                try {
                    mAlarmClockLoadingView.setProgress(i);
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
}
