package com.rxjava2.android.samples.ui.observable.backpressure;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.rxjava2.android.samples.R;
import com.rxjava2.android.samples.utils.AppConstant;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.subscribers.ResourceSubscriber;

public class FlowableExampleActivity extends AppCompatActivity {

    private static final String TAG = FlowableExampleActivity.class.getSimpleName();
    Button btn;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example);
        btn = (Button) findViewById(R.id.btn);
        textView = (TextView) findViewById(R.id.textView);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doSomeWork();
            }
        });
    }

    private void doSomeWork() {

        Flowable<Integer> flowable = Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> e) throws Exception {
                for (int i =0;i<10000;i++){
                    e.onNext(i);
                }
                e.onComplete();
            }
        }, BackpressureStrategy.DROP);

        flowable.subscribe(new ResourceSubscriber<Integer>() {
            @Override
            public void onNext(Integer integer) {

            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    private SingleObserver<Long> getObserver() {

        return new SingleObserver<Long>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, " onSubscribe : " + d.isDisposed());
            }

            @Override
            public void onSuccess(Long value) {
                textView.append(" onSuccess : value : " + value);
                textView.append(AppConstant.LINE_SEPARATOR);
                Log.d(TAG, " onSuccess : value : " + value);
            }

            @Override
            public void onError(Throwable e) {
                textView.append(" onError : " + e.getMessage());
                textView.append(AppConstant.LINE_SEPARATOR);
                Log.d(TAG, " onError : " + e.getMessage());
            }
        };
    }
}