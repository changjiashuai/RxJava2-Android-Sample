package com.rxjava2.android.samples.ui.operators.transforming;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.rxjava2.android.samples.R;

public class TransformingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transforming);
    }

    public void startBufferActivity(View view) {
        startActivity(new Intent(this, BufferExampleActivity.class));
    }

    public void startFlatMapActivity(View view) {
        startActivity(new Intent(this, FlatMapExampleActivity.class));
    }

    public void startGroupByActivity(View view) {
        startActivity(new Intent(this, GroupByExampleActivity.class));
    }

    public void startMapActivity(View view) {
        startActivity(new Intent(this, MapExampleActivity.class));
    }

    public void startScanActivity(View view) {
        startActivity(new Intent(this, ScanExampleActivity.class));
    }

    public void startWindowActivity(View view) {
        startActivity(new Intent(this, WindowExampleActivity.class));
    }
}
