package com.rxjava2.android.samples.ui.operators.conditional;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.rxjava2.android.samples.R;
import com.rxjava2.android.samples.utils.AppConstant;

import io.reactivex.Observable;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Email: changjiashuai@gmail.com
 *
 * Created by CJS on 28/06/17 16:07.
 */
public class ContainsExampleActivity extends AppCompatActivity {

    private static final String TAG = ContainsExampleActivity.class.getSimpleName();
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
     * Contains操作符用来判断源Observable所发射的数据是否包含某一个数据，
     * 如果包含会返回true，如果源Observable已经结束了却还没有发射这个数据则返回false。
     */
    private void doSomeWork() {
        Observable.just(1, 3, 4, 5)
                .contains(2)
                // Run on a background thread
                .subscribeOn(Schedulers.io())
                // Be notified on the main thread
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getObserver());
    }

    private SingleObserver<Boolean> getObserver() {
        return new SingleObserver<Boolean>() {

            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, " onSubscribe : " + d.isDisposed());
            }

            @Override
            public void onSuccess(Boolean value) {
                textView.append(" onNext : value : " + value);
                textView.append(AppConstant.LINE_SEPARATOR);
                Log.d(TAG, " onNext : value : " + value);
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