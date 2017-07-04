package com.rxjava2.android.samples.ui.operators.utility;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.rxjava2.android.samples.R;
import com.rxjava2.android.samples.utils.AppConstant;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class TimeoutExampleActivity extends AppCompatActivity {

    private static final String TAG = TimeoutExampleActivity.class.getSimpleName();
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
//                doSomeWork();
                testTimeoutWithObservable();
            }
        });
    }

    /*
     * simple example using delay to emit after 2 second
     */
    private void doSomeWork() {
        getObservable().delay(2, TimeUnit.SECONDS)
                .timeout(200, TimeUnit.MILLISECONDS)
                // Run on a background thread
                .subscribeOn(Schedulers.io())
                // Be notified on the main thread
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getObserver());
    }

    private void testTimeoutWithObservable(){
        getObservable()
                .delay(1, TimeUnit.SECONDS)
                .timeout(200, TimeUnit.MILLISECONDS, Observable.just("10", "20"))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getObserver());
    }

    private Observable<String> getObservable() {
        return Observable.just("I'm delay 2s");
    }

    private Observer<String> getObserver() {
        return new Observer<String>() {

            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, " onSubscribe : " + d.isDisposed());
            }

            @Override
            public void onNext(String value) {
                textView.append(" onNext : value : " + value);
                textView.append(AppConstant.LINE_SEPARATOR);
                Log.d(TAG, " onNext : value : " + value);
            }

            @Override
            public void onError(Throwable e) {
                textView.append(" onError : " + e.toString() + ", message:" + e.getMessage());
                textView.append(AppConstant.LINE_SEPARATOR);
                Log.d(TAG, " onError : " + e.getMessage());
            }

            @Override
            public void onComplete() {
                textView.append(" onComplete");
                textView.append(AppConstant.LINE_SEPARATOR);
                Log.d(TAG, " onComplete");
            }
        };
    }


}