package com.rxjava2.android.samples.ui.subjects;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.rxjava2.android.samples.R;

public class SubjectsActivity extends AppCompatActivity {

    private static final String TAG = SubjectsActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subjects);
    }

    public void startAsyncSubjectActivity(View view) {
        startActivity(new Intent(this, AsyncSubjectExampleActivity.class));
    }

    public void startBehaviorSubjectActivity(View view) {
        startActivity(new Intent(this, BehaviorSubjectExampleActivity.class));
    }

    public void startPublishSubjectActivity(View view) {
        startActivity(new Intent(this, PublishSubjectExampleActivity.class));
    }

    public void startReplaySubjectActivity(View view) {
        startActivity(new Intent(this, ReplaySubjectExampleActivity.class));
    }
}