package com.rxjava2.android.samples.ui.operators.filtering;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.rxjava2.android.samples.R;

public class FilteringActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtering);
    }

    public void startDebounceActivity(View view) {
        startActivity(new Intent(this, DebounceExampleActivity.class));
    }

    public void startDistinctActivity(View view) {
        startActivity(new Intent(this, DistinctExampleActivity.class));
    }

    public void startElementAtActivity(View view) {
        startActivity(new Intent(this, ElementAtExampleActivity.class));
    }

    public void startFilterActivity(View view) {
        startActivity(new Intent(this, FilterExampleActivity.class));
    }

    public void startFirstActivity(View view) {
        startActivity(new Intent(this, FirstExampleActivity.class));
    }

    public void startIgnoreElementsActivity(View view) {
        startActivity(new Intent(this, IgnoreElementsExampleActivity.class));
    }

    public void startLastActivity(View view) {
        startActivity(new Intent(this, LastExampleActivity.class));
    }

    public void startSampleActivity(View view) {
        startActivity(new Intent(this, SampleExampleActivity.class));
    }

    public void startSkipActivity(View view) {
        startActivity(new Intent(this, SkipExampleActivity.class));
    }

    public void startSkipLastActivity(View view) {
        startActivity(new Intent(this, SkipLastExampleActivity.class));
    }

    public void startTakeActivity(View view) {
        startActivity(new Intent(this, TakeExampleActivity.class));
    }

    public void startTakeLastActivity(View view) {
        startActivity(new Intent(this, TakeLastExampleActivity.class));
    }
}
