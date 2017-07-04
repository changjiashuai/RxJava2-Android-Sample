package com.rxjava2.android.samples.ui.operators.conditional;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.rxjava2.android.samples.R;

public class ConditionalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conditional);
    }

    public void startAllActivity(View view) {
        startActivity(new Intent(this, AllExampleActivity.class));
    }

    public void startAmbActivity(View view) {
        startActivity(new Intent(this, AmbExampleActivity.class));
    }

    public void startContainsActivity(View view) {
        startActivity(new Intent(this, ContainsExampleActivity.class));
    }

    public void startDefaultIfEmptyActivity(View view) {
        startActivity(new Intent(this, DefaultIfEmptyExampleActivity.class));
    }

    public void startSequenceEqualActivity(View view) {
        startActivity(new Intent(this, SequenceEqualExampleActivity.class));
    }

    public void startSkipUntilActivity(View view) {
        startActivity(new Intent(this, SkipUntilExampleActivity.class));
    }

    public void startSkipWhileActivity(View view) {
        startActivity(new Intent(this, SkipWhileExampleActivity.class));
    }

    public void startTakeUntilActivity(View view) {
        startActivity(new Intent(this, TakeUntilExampleActivity.class));
    }

    public void startTakeWhileActivity(View view) {
        startActivity(new Intent(this, TakeWhileExampleActivity.class));
    }
}
