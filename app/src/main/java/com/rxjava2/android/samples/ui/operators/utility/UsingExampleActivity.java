package com.rxjava2.android.samples.ui.operators.utility;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.rxjava2.android.samples.R;
import com.rxjava2.android.samples.utils.AppConstant;

import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class UsingExampleActivity extends AppCompatActivity {

    private static final String TAG = UsingExampleActivity.class.getSimpleName();
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
     * Using操作符指示Observable创建一个只在它的生命周期内存在的资源，
     * 当Observable终止时这个资源会被自动释放。
     */
    private void doSomeWork() {
        Observable
                .using(new Callable<TestLifecycleObj>() {//一个用于创建一次性资源的工厂函数
                    @Override
                    public TestLifecycleObj call() throws Exception {
                        return new TestLifecycleObj();
                    }
                }, new Function<TestLifecycleObj, ObservableSource<TestLifecycleObj>>() {
                    //一个用于创建Observable的工厂函数，这个函数返回的Observable就是最终被观察的Observable
                    @Override
                    public ObservableSource<TestLifecycleObj> apply(@NonNull TestLifecycleObj resourceObj) throws Exception {
                        return Observable.just(resourceObj);
                    }
                }, new Consumer<TestLifecycleObj>() {//一个用于释放资源的函数，当Function返回的Observable执行完毕之后会被调用
                    @Override
                    public void accept(@NonNull TestLifecycleObj resourceObj) throws Exception {
//                        resourceObj.release();
                    }
                })
                // Run on a background thread
                .subscribeOn(Schedulers.io())
                // Be notified on the main thread
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getObserver());
    }


    private Observer<TestLifecycleObj> getObserver() {
        return new Observer<TestLifecycleObj>() {

            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, " onSubscribe : " + d.isDisposed());
            }

            @Override
            public void onNext(TestLifecycleObj value) {
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


    class TestLifecycleObj {
        public void release() {
            Log.d(TAG, "release: object resource released.");
        }
    }
}