package com.fatwood.loadingview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        int viewId = view.getId();
        switch(viewId) {
            case R.id.btn_alarm_clock:
                startActivity(ShowAlarmClockActivity.class);
                break;
            case R.id.btn_happy_fat_wood:
                startActivity(ShowHappyFatWoodLoadingActivity.class);
                break;
        }
    }

    private void startActivity(Class classz) {
        Intent intent = new Intent(this, classz);
        startActivity(intent);
    }
}
