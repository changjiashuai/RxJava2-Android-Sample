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
import io.reactivex.subjects.AsyncSubject;

/**
 * 一个AsyncSubject只在原始Observable完成后，发射来自原始Observable的最后一个值。
 * （如果原始Observable没有发射任何值，AsyncObject也不发射任何值）它会把这最后一个值 发射给任何后续的观察者。
 * 然而，如果原始的Observable因为发生了错误而终止，AsyncSubject将不会发射任何数据， 只是简单的向前传递这个错误通知
 */
public class AsyncSubjectExampleActivity extends AppCompatActivity {

    private static final String TAG = AsyncSubjectExampleActivity.class.getSimpleName();
    public static final int NO_RETURN = 0;
    public static final int COMPLETE = 1;
    public static final int ERROR = 2;
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
                testReturnError();
            }
        });
    }

    /* An AsyncSubject emits the last value (and only the last value) emitted by the source
     * Observable, and only after that source Observable completes. (If the source Observable
     * does not emit any values, the AsyncSubject also completes without emitting any values.)
     */
    private void doSomeWork(int type) {

        AsyncSubject<Integer> source = AsyncSubject.create();

        source.subscribe(getFirstObserver()); // it will emit only 4 and onComplete

        source.onNext(1);
        source.onNext(2);
        source.onNext(3);

        /*
         * it will emit 4 and onComplete for second observer also.
         */
        source.subscribe(getSecondObserver());

        source.onNext(4);
        if (type==COMPLETE){
            source.onComplete();
        }
        if (type==ERROR){
            source.onError(new Exception("test send onError"));
        }
    }

    private void testNoReturn(){
        doSomeWork(NO_RETURN);
    }

    private void testReturnComplete(){
        doSomeWork(COMPLETE);
    }

    private void testReturnError(){
        doSomeWork(ERROR);
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