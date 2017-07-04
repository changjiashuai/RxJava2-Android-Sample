package com.rxjava2.android.samples.ui.operators.creating;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.rxjava2.android.samples.R;

public class CreatingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creating);
    }

    public void startCreateActivity(View view) {
        startActivity(new Intent(this, CreateExampleActivity.class));
    }

    public void startDeferActivity(View view) {
        startActivity(new Intent(this, DeferExampleActivity.class));
    }

    public void startEmptyNeverThrowActivity(View view) {
        startActivity(new Intent(this, EmptyNeverThrowExampleActivity.class));
    }

    public void startFromActivity(View view) {
        startActivity(new Intent(this, FromExampleActivity.class));
    }

    public void startIntervalActivity(View view) {
        startActivity(new Intent(this, IntervalExampleActivity.class));
    }

    public void startJustActivity(View view) {
        startActivity(new Intent(this, JustExampleActivity.class));
    }

    public void startRangeActivity(View view) {
        startActivity(new Intent(this, RangeExampleActivity.class));
    }

    public void startRepeatActivity(View view) {
        startActivity(new Intent(this, RepeatExampleActivity.class));
    }

    public void startStartActivity(View view) {
        startActivity(new Intent(this, StartExampleActivity.class));
    }

    public void startTimerActivity(View view) {
        startActivity(new Intent(this, TimerExampleActivity.class));
    }
}
