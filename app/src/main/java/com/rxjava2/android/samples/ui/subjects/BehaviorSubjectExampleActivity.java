package com.rxjava2.android.samples.ui.subjects;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.rxjava2.android.samples.R;
import com.rxjava2.android.samples.utils.AppConstant;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.BehaviorSubject;

/**
 * Email: changjiashuai@gmail.com
 *
 * Created by CJS on 01/07/17 16:40.
 */
public class BehaviorSubjectExampleActivity extends AppCompatActivity {

    private static final String TAG = BehaviorSubjectExampleActivity.class.getSimpleName();
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

    /**
     * 当观察者订阅BehaviorSubject时，它开始发射原始Observable最近发射的数据
     * （如果此时还 没有收到任何数据，它会发射一个默认值），然后继续发射其它任何来自原始Observable的数据。
     * 然而，如果原始的Observable因为发生了一个错误而终止，BehaviorSubject将不会发射任何数据，只是简单的向前传递这个错误通知。
     */
    private void doSomeWork() {

        BehaviorSubject<Integer> source = BehaviorSubject.create();

        source.subscribe(getFirstObserver()); // it will get 1, 2, 3, 4 and onComplete

        source.onNext(1);
        source.onNext(2);
        source.onNext(3);

        /*
         * it will emit 3(last emitted), 4 and onComplete for second observer also.
         */
        source.subscribe(getSecondObserver());

        source.onNext(4);
        source.onComplete();

    }


    private Observer<Integer> getFirstObserver() {
        return new Observer<Integer>() {

            @Override
            public void onSubscribe(Disposable d) {
                textView.append(" First onSubscribe : isDisposed :" + d.isDisposed());
                Log.d(TAG, " First onSubscribe : " + d.isDisposed());
                textView.append(AppConstant.LINE_SEPARATOR);
            }

            @Override
            public void onNext(Integer value) {
                textView.append(" First onNext : value : " + value);
                textView.append(AppConstant.LINE_SEPARATOR);
                Log.d(TAG, " First onNext value : " + value);
            }

            @Override
            public void onError(Throwable e) {
                textView.append(" First onError : " + e.getMessage());
                textView.append(AppConstant.LINE_SEPARATOR);
                Log.d(TAG, " First onError : " + e.getMessage());
            }

            @Override
            public void onComplete() {
                textView.append(" First onComplete");
                textView.append(AppConstant.LINE_SEPARATOR);
                Log.d(TAG, " First onComplete");
            }
        };
    }

    private Observer<Integer> getSecondObserver() {
        return new Observer<Integer>() {

            @Override
            public void onSubscribe(Disposable d) {
                textView.append(" Second onSubscribe : isDisposed :" + d.isDisposed());
                Log.d(TAG, " Second onSubscribe : " + d.isDisposed());
                textView.append(AppConstant.LINE_SEPARATOR);
            }

            @Override
            public void onNext(Integer value) {
                textView.append(" Second onNext : value : " + value);
                textView.append(AppConstant.LINE_SEPARATOR);
                Log.d(TAG, " Second onNext value : " + value);
            }

            @Override
            public void onError(Throwable e) {
                textView.append(" Second onError : " + e.getMessage());
                textView.append(AppConstant.LINE_SEPARATOR);
                Log.d(TAG, " Second onError : " + e.getMessage());
            }

            @Override
            public void onComplete() {
                textView.append(" Second onComplete");
                textView.append(AppConstant.LINE_SEPARATOR);
                Log.d(TAG, " Second onComplete");
            }
        };
    }


}