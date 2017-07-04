package com.rxjava2.android.samples.ui.operators;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.rxjava2.android.samples.R;
import com.rxjava2.android.samples.ui.observable.backpressure.FlowableExampleActivity;
import com.rxjava2.android.samples.ui.operators.combining.CombiningActivity;
import com.rxjava2.android.samples.ui.operators.conditional.ConditionalActivity;
import com.rxjava2.android.samples.ui.operators.connectable.ConnectableExampleActivity;
import com.rxjava2.android.samples.ui.operators.creating.CreatingActivity;
import com.rxjava2.android.samples.ui.operators.errorhanding.ErrorHandlingActivity;
import com.rxjava2.android.samples.ui.operators.filtering.FilteringActivity;
import com.rxjava2.android.samples.ui.operators.mathematicalandaggregate.MathematicalAndAggregateActivity;
import com.rxjava2.android.samples.ui.operators.transforming.TransformingActivity;
import com.rxjava2.android.samples.ui.operators.utility.UtilityActivity;

public class OperatorsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operators);
    }

    public void startCreatingActivity(View view) {
        startActivity(new Intent(this, CreatingActivity.class));
    }

    public void startTransformingActivity(View view) {
        startActivity(new Intent(this, TransformingActivity.class));
    }

    public void startFilteringActivity(View view) {
        startActivity(new Intent(this, FilteringActivity.class));
    }

    public void startCombiningActivity(View view) {
        startActivity(new Intent(this, CombiningActivity.class));
    }

    public void startErrorHandingActivity(View view) {
        startActivity(new Intent(this, ErrorHandlingActivity.class));
    }

    public void startUtilityActivity(View view) {
        startActivity(new Intent(this, UtilityActivity.class));
    }

    public void startConditionalAndBooleanActivity(View view) {
        startActivity(new Intent(this, ConditionalActivity.class));
    }

    public void startMathematicalAndAggregateActivity(View view) {
        startActivity(new Intent(this, MathematicalAndAggregateActivity.class));
    }

    public void startConnectableActivity(View view){
        startActivity(new Intent(this, ConnectableExampleActivity.class));
    }

    public void startBackpressureActivity(View view){
        startActivity(new Intent(this, FlowableExampleActivity.class));
    }
}
