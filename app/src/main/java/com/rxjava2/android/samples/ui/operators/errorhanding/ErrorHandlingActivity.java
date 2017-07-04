package com.rxjava2.android.samples.ui.operators.errorhanding;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.rxjava2.android.samples.R;

public class ErrorHandlingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error_handling);
    }

    public void startCatchActivity(View view) {
        startActivity(new Intent(this, CatchExampleActivity.class));
    }

    public void startRetryActivity(View view) {
        startActivity(new Intent(this, RetryExampleActivity.class));
    }
}
