package com.rxjava2.android.samples.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.rxjava2.android.samples.MyApplication;
import com.rxjava2.android.samples.R;
import com.rxjava2.android.samples.ui.compose.ComposeOperatorExampleActivity;
import com.rxjava2.android.samples.ui.networking.NetworkingActivity;
import com.rxjava2.android.samples.ui.operators.OperatorsActivity;
import com.rxjava2.android.samples.ui.pagination.PaginationActivity;
import com.rxjava2.android.samples.ui.rxbus.RxBusActivity;
import com.rxjava2.android.samples.ui.subjects.SubjectsActivity;

public class SelectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);
    }

    public void startOperatorsActivity(View view) {
        startActivity(new Intent(this, OperatorsActivity.class));
    }

    public void startSubjectsActivity(View view) {
        startActivity(new Intent(this, SubjectsActivity.class));
    }

    public void startNetworkingActivity(View view) {
        startActivity(new Intent(this, NetworkingActivity.class));
    }

    public void startRxBusActivity(View view) {
        ((MyApplication) getApplication()).sendAutoEvent();
        startActivity(new Intent(this, RxBusActivity.class));
    }

    public void startPaginationActivity(View view) {
        startActivity(new Intent(this, PaginationActivity.class));
    }

    public void startComposeOperator(View view) {
        startActivity(new Intent(this, ComposeOperatorExampleActivity.class));
    }
}
