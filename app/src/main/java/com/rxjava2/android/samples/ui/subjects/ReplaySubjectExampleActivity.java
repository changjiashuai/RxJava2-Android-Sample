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
import io.reactivex.subjects.ReplaySubject;

/**
 * Email: changjiashuai@gmail.com
 *
 * Created by CJS on 01/07/17 16:48.
 */
public class ReplaySubjectExampleActivity extends AppCompatActivity {

    private static final String TAG = ReplaySubjectExampleActivity.class.getSimpleName();
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
     * ReplaySubject会发射所有来自原始Observable的数据给观察者，无论它们是何时订阅的。
     * 也有其它版本的ReplaySubject，在重放缓存增长到一定大小的时候或过了一段时间后会丢弃旧的数据（原始Observable发射的）。
     *
     * 如果你把ReplaySubject当作一个观察者使用，注意不要从多个线程中调用它的onNext方法（包括其它的on系列方法），
     * 这可能导致同时（非顺序）调用，这会违反Observable协议， 给Subject的结果增加了不确定性。
     */
    private void doSomeWork() {

        ReplaySubject<Integer> source = ReplaySubject.create();

        source.subscribe(getFirstObserver()); // it will get 1, 2, 3, 4

        source.onNext(1);
        source.onNext(2);
        source.onNext(3);
        source.onNext(4);
        source.onComplete();

        /*
         * it will emit 1, 2, 3, 4 for second observer also as we have used replay
         */
        source.subscribe(getSecondObserver());

    }


    private Observer<Integer> getFirstObserver() {
        return new Observer<Integer>() {

            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, " First onSubscribe : " + d.isDisposed());
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