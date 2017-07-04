package com.rxjava2.android.samples.ui.operators.combining;

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
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Email: changjiashuai@gmail.com
 *
 * Created by CJS
 */
public class SwitchExampleActivity extends AppCompatActivity {

    private static final String TAG = SwitchExampleActivity.class.getSimpleName();
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
     * 把一组Observable转换成一个Observable，对于这组Observable中的每一个Observable所产生的结果，
     * 如果在同一个时间内存在两个或多个Observable提交的结果，只取最后一个Observable提交的结果
     */
    private void doSomeWork() {
        //每隔500毫秒产生一个Observable(下一个Observable产生上一个Observable生命周期结束)
        Observable<Observable<Long>> observable = Observable.interval(0, 500, TimeUnit.MILLISECONDS)
                .map(new Function<Long, Observable<Long>>() {
                    @Override
                    public Observable<Long> apply(@NonNull Long aLong) throws Exception {
                        //每隔200毫秒产生一组数据(0, 10, 20, 30, 40)
                        return Observable.interval(0, 200, TimeUnit.MILLISECONDS)
                                .map(new Function<Long, Long>() {
                                    @Override
                                    public Long apply(@NonNull Long aLong) throws Exception {
                                        return aLong * 10;
                                    }
                                }).take(5);
                    }
                }).take(2);
        Observable.switchOnNext(observable)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getObserver());
    }


    private Observer<Long> getObserver() {
        return new Observer<Long>() {

            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, " onSubscribe : " + d.isDisposed());
            }

            @Override
            public void onNext(Long value) {
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

            @Override
            public void onComplete() {
                textView.append(" onComplete");
                textView.append(AppConstant.LINE_SEPARATOR);
                Log.d(TAG, " onComplete");
            }
        };
    }


}