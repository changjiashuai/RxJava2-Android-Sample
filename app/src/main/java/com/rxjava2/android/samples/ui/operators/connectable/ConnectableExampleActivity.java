package com.rxjava2.android.samples.ui.operators.connectable;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.rxjava2.android.samples.R;
import com.rxjava2.android.samples.utils.AppConstant;
import com.rxjava2.android.samples.utils.Utils;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.observables.ConnectableObservable;

public class ConnectableExampleActivity extends AppCompatActivity {

    private static final String TAG = ConnectableExampleActivity.class.getSimpleName();
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
//                testRefCount();
            }
        });
    }

    private void doSomeWork() {

        Observable<Long> observable = Observable.interval(1, TimeUnit.SECONDS).take(6);

        //使用publish操作符将普通Observable转换为可连接的Observable
        ConnectableObservable<Long> connectableObservable = observable
//                .publish();
                .replay(5);
        //第一个订阅者订阅，不会开始发射数据
        connectableObservable
                .compose(Utils.<Long>ioToMain())
                .subscribe(getFirstObserver());
        //如果不调用connect方法，connectableObservable则不会发射数据
        //即使没有任何订阅者订阅它，你也可以使用connect方法让一个Observable开始发射数据（或者开始生成待发射的数据）。
        //这样，你可以将一个”冷”的Observable变为”热”的。
        connectableObservable.connect();
        //第二个订阅者延迟2s订阅，这将导致丢失前面2s内发射的数据
        connectableObservable.delaySubscription(2, TimeUnit.SECONDS)//0,1数据丢失
                .compose(Utils.<Long>ioToMain())
                .subscribe(getSecondObserver());
    }

    //RefCount操作符可以看做是Publish的逆向，它能将一个ConnectableObservable对象再重新转化为一个普通的Observable对象，
    //如果转化后有订阅者对其进行订阅将会开始发射数据，后面如果有其他订阅者订阅，
    //将只能接受后面的数据（这也是转化之后的Observable 与普通的Observable的一点区别 ）。
    private void testRefCount() {
        Observable<Long> observable = Observable.interval(1, TimeUnit.SECONDS).take(6);
        ConnectableObservable<Long> connectableObservable = observable.publish();
        connectableObservable.connect();
        Observable<Long> longObservable = connectableObservable.refCount();
        longObservable.delaySubscription(2, TimeUnit.SECONDS)
                .compose(Utils.<Long>ioToMain())
                .subscribe(getFirstObserver());
        longObservable.compose(Utils.<Long>ioToMain()).subscribe(getSecondObserver());
    }

    private Observer<Long> getFirstObserver() {
        return new Observer<Long>() {

            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, " First onSubscribe : " + d.isDisposed());
            }

            @Override
            public void onNext(Long value) {
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

    private Observer<Long> getSecondObserver() {
        return new Observer<Long>() {

            @Override
            public void onSubscribe(Disposable d) {
                textView.append(" Second onSubscribe : isDisposed :" + d.isDisposed());
                Log.d(TAG, " Second onSubscribe : " + d.isDisposed());
                textView.append(AppConstant.LINE_SEPARATOR);
            }

            @Override
            public void onNext(Long value) {
                textView.append(" Second onNext : value : " + value);
                textView.append(AppConstant.LINE_SEPARATOR);
                Log.d(TAG, " Second onNext value : " + value);
            }

            @Override
            public void onError(Throwable e) {
                textView.append(" Second onError : " + e.getMessage());
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

    private Observer<Long> getThirdObserver() {
        return new Observer<Long>() {

            @Override
            public void onSubscribe(Disposable d) {
                textView.append(" Third onSubscribe : isDisposed :" + d.isDisposed());
                Log.d(TAG, " Third onSubscribe : " + d.isDisposed());
                textView.append(AppConstant.LINE_SEPARATOR);
            }

            @Override
            public void onNext(Long value) {
                textView.append(" Third onNext : value : " + value);
                textView.append(AppConstant.LINE_SEPARATOR);
                Log.d(TAG, " Third onNext value : " + value);
            }

            @Override
            public void onError(Throwable e) {
                textView.append(" Third onError : " + e.getMessage());
                Log.d(TAG, " Third onError : " + e.getMessage());
            }

            @Override
            public void onComplete() {
                textView.append(" Third onComplete");
                textView.append(AppConstant.LINE_SEPARATOR);
                Log.d(TAG, " Third onComplete");
            }
        };
    }


}