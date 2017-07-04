package com.rxjava2.android.samples.ui.operators.utility;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.rxjava2.android.samples.R;
import com.rxjava2.android.samples.ui.operators.creating.FromExampleActivity;
import com.rxjava2.android.samples.ui.operators.creating.IntervalExampleActivity;
import com.rxjava2.android.samples.ui.operators.creating.JustExampleActivity;

public class UtilityActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utility);
    }

    public void startDelayActivity(View view) {
        startActivity(new Intent(this, DelayExampleActivity.class));
    }

    public void startDoActivity(View view) {
        startActivity(new Intent(this, DoExampleActivity.class));
    }

    public void startMaterializeOrDematerializeActivity(View view) {
        startActivity(new Intent(this, MaterializeOrDematerializeExampleActivity.class));
    }

    public void startObserveOnActivity(View view) {
        startActivity(new Intent(this, FromExampleActivity.class));
    }

    public void startSerializeActivity(View view) {
        startActivity(new Intent(this, IntervalExampleActivity.class));
    }

    public void startSubscribeActivity(View view) {
        startActivity(new Intent(this, JustExampleActivity.class));
    }

    public void startTimeIntervalActivity(View view) {
        startActivity(new Intent(this, TimeIntervalExampleActivity.class));
    }

    public void startTimeoutActivity(View view) {
        startActivity(new Intent(this, TimeoutExampleActivity.class));
    }

    public void startTimestampActivity(View view) {
        startActivity(new Intent(this, TimestampExampleActivity.class));
    }

    public void startUsingActivity(View view) {
        startActivity(new Intent(this, UsingExampleActivity.class));
    }
}
