package com.rxjava2.android.samples.ui.operators.combining;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.rxjava2.android.samples.R;

public class CombiningActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combining);
    }

    public void startAndThenWhenActivity(View view) {
        startActivity(new Intent(this, AndThenWhenExampleActivity.class));
    }

    public void startCombineLatestActivity(View view) {
        startActivity(new Intent(this, CombineLatestExampleActivity.class));
    }

    public void startJoinActivity(View view) {
        startActivity(new Intent(this, JoinExampleActivity.class));
    }

    public void startMergeActivity(View view) {
        startActivity(new Intent(this, MergeExampleActivity.class));
    }

    public void startStartWithActivity(View view) {
        startActivity(new Intent(this, StartWithExampleActivity.class));
    }

    public void startSwitchActivity(View view) {
        startActivity(new Intent(this, SwitchExampleActivity.class));
    }

    public void startZipActivity(View view) {
        startActivity(new Intent(this, ZipExampleActivity.class));
    }
}
