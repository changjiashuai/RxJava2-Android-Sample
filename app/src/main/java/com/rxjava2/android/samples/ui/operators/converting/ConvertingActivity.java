package com.rxjava2.android.samples.ui.operators.converting;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.rxjava2.android.samples.R;

public class ConvertingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_converting);
    }

    public void startToActivity(View view) {
        startActivity(new Intent(this, ToExampleActivity.class));
    }
}
