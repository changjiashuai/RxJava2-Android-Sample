package com.rxjava2.android.samples.ui.operators.errorhanding;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.rxjava2.android.samples.R;
import com.rxjava2.android.samples.utils.AppConstant;

import java.util.concurrent.atomic.AtomicInteger;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiPredicate;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

public class RetryExampleActivity extends AppCompatActivity {

    private static final String TAG = RetryExampleActivity.class.getSimpleName();
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retry);
        textView = (TextView) findViewById(R.id.textView);
    }

    private void retryWithPredicate(final boolean flag) {
        Observable
                .create(new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(ObservableEmitter<String> e) throws Exception {
                        for (int i = 0; i <= 3; i++) {
                            if (i == 2) {
                                e.onError(new Exception("出现错误了!"));
                            } else {
                                e.onNext(i + "");
                            }
                            Thread.sleep(1000);
                        }
                        e.onComplete();
                    }
                })
//                .retry(new Predicate<Throwable>() {
//                    @Override
//                    public boolean test(@NonNull Throwable throwable) throws Exception {
//                        Log.d(TAG, "retry错误: " + throwable.toString());
//                        //false 不让重新发射数据了，调用观察者的onError就终止了
//                        //true  被观察者重新发射请求（一直发射知道没有错误）
//                        return flag;
//                    }
//                })

//                .retry() //一直重试
//                .retry(3) //最多重试3次
                .retry(3, new Predicate<Throwable>() {
                    @Override
                    public boolean test(@NonNull Throwable throwable) throws Exception {
                        //false 不让重新发射数据了，调用观察者的onError就终止了
                        //true 根据指定的次数进行重发请求
                        return flag;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(@NonNull Disposable disposable) throws Exception {
                        Log.d(TAG, "subscribe time: " + disposable.isDisposed());
                    }
                })
                .subscribe(getObserver());//会接到（1+重试次数）次数据消息
    }

    public void testRetry(View view) {
        retryWithPredicate(false);
    }

    public void testRetry2(View view) {
        retryWithPredicate(true);
    }

    private void retryWithBiPredicate(final boolean flag) {
        Observable
                .create(new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(ObservableEmitter<String> e) throws Exception {
                        for (int i = 0; i <= 3; i++) {
                            if (i == 2) {
                                e.onError(new Exception("出现错误了!"));
                            } else {
                                e.onNext(i + "");
                            }
                            Thread.sleep(1000);
                        }
                        e.onComplete();
                    }
                })
                .retry(new BiPredicate<Integer, Throwable>() {
                    @Override
                    public boolean test(@NonNull Integer integer, @NonNull Throwable throwable) throws Exception {
                        Log.d(TAG, "retry错误: " + integer + ", " + throwable.toString());
                        //false 不让重新发射数据了，调用观察者的onError就终止了
                        //true  被观察者重新发射请求（一直发射知道没有错误）
                        return flag;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(@NonNull Disposable disposable) throws Exception {
                        Log.d(TAG, "subscribe time: " + disposable.isDisposed());
                    }
                })
                .subscribe(getObserver());
    }

    public void testRetry3(View view) {
        retryWithBiPredicate(false);
    }

    public void testRetry4(View view) {
        retryWithBiPredicate(true);
    }

    public void testRetryWhen(View view) {
        final AtomicInteger atomicInteger = new AtomicInteger(3);
        Observable
                .create(new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(ObservableEmitter<String> e) throws Exception {
                        for (int i = 0; i <= 3; i++) {
                            atomicInteger.decrementAndGet();
                            if (i == 2) {
                                e.onError(new Exception("出现错误了!"));
                            } else {
                                e.onNext(i + "");
                            }
//                            Thread.sleep(500);
                            try {
                                Thread.sleep(1000);
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                        e.onComplete();
                    }
                })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .retryWhen(new Function<Observable<Throwable>, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(@NonNull Observable<Throwable> throwableObservable) throws Exception {
                        return throwableObservable.flatMap(new Function<Throwable, ObservableSource<?>>() {
                            @Override
                            public ObservableSource<?> apply(@NonNull Throwable throwable) throws Exception {
                                Log.d(TAG, "apply: " + throwable.toString());
                                return Observable.just(100);//不停重试
//                                return Observable.error(new Throwable("retryWhen终止"));
//                                return Observable.error(throwable);//发射oError就终止
                            }
                        });
                    }
                })
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(@NonNull Disposable disposable) throws Exception {
                        Log.d(TAG, "subscribe time: " + atomicInteger.intValue());
                        textView.append(" subscribe time : " + atomicInteger.intValue());
                        textView.append(AppConstant.LINE_SEPARATOR);
                    }
                })
                .subscribe(getObserver());
    }

    private Observer<Object> getObserver() {
        return new Observer<Object>() {

            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, " onSubscribe : " + d.isDisposed());
            }

            @Override
            public void onNext(Object value) {
                textView.append(" onNext : ");
                textView.append(AppConstant.LINE_SEPARATOR);
                textView.append(" value : " + value);
                textView.append(AppConstant.LINE_SEPARATOR);
                Log.d(TAG, " onNext ");
                Log.d(TAG, " value : " + value);
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