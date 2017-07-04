package com.rxjava2.android.samples.ui.operators.mathematicalandaggregate;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.rxjava2.android.samples.R;

public class MathematicalAndAggregateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mathematical_and_aggregate);
    }

    public void startAverageActivity(View view) {
        startActivity(new Intent(this, AverageExampleActivity.class));
    }

    public void startConcatActivity(View view) {
        startActivity(new Intent(this, ConcatExampleActivity.class));
    }

    public void startCountActivity(View view) {
        startActivity(new Intent(this, CountExampleActivity.class));
    }

    public void startMaxActivity(View view) {
    }

    public void startMinActivity(View view) {
    }

    public void startReduceActivity(View view) {
        startActivity(new Intent(this, ReduceExampleActivity.class));
    }

    public void startSumActivity(View view) {
    }
}
