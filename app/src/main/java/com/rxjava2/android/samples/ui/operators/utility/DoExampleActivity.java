package com.rxjava2.android.samples.ui.operators.utility;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.rxjava2.android.samples.R;
import com.rxjava2.android.samples.utils.AppConstant;

import io.reactivex.Notification;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class DoExampleActivity extends AppCompatActivity {

    private static final String TAG = DoExampleActivity.class.getSimpleName();
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
//        getObservable()
        Observable
                .create(new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(ObservableEmitter<String> e) throws Exception {
                        e.onNext("a");
                        e.onNext("b");
                        e.onNext("c");
                        e.onComplete();
                        e.onNext("d");
                        e.onNext("e");
                        e.onComplete();
                    }
                })
                .serialize()
                .doOnLifecycle(new Consumer<Disposable>() {
                    @Override
                    public void accept(@NonNull Disposable disposable) throws Exception {
                        Log.d(TAG, "doOnLifecycle accept: ");
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        Log.d(TAG, "doOnLifecycle run: ");
                    }
                })
                .doOnEach(new Consumer<Notification<String>>() {
                    @Override
                    public void accept(@NonNull Notification<String> stringNotification) throws Exception {
                        Log.d(TAG, "doOnEach accept: ");
                    }
                })
                .doOnNext(new Consumer<String>() {
                    @Override
                    public void accept(@NonNull String s) throws Exception {
                        Log.d(TAG, "doOnNext accept: " + s);
                    }
                })
                .doAfterNext(new Consumer<String>() {
                    @Override
                    public void accept(@NonNull String s) throws Exception {
                        Log.d(TAG, "doAfterNext accept: " + s);
                    }
                })
                .doOnTerminate(new Action() {
                    @Override
                    public void run() throws Exception {
                        Log.d(TAG, "doOnTerminate run: ");
                    }
                })
                .doAfterTerminate(new Action() {
                    @Override
                    public void run() throws Exception {
                        Log.d(TAG, "doAfterTerminate run: ");
                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        Log.d(TAG, "doFinally run: ");
                    }
                })
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(@NonNull Disposable disposable) throws Exception {
                        Log.d(TAG, "doOnSubscribe accept: ");
                    }
                })
                .doOnComplete(new Action() {
                    @Override
                    public void run() throws Exception {
                        Log.d(TAG, "doOnComplete run: ");
                    }
                })
                .doOnDispose(new Action() {
                    @Override
                    public void run() throws Exception {
                        Log.d(TAG, "doOnDispose run: ");
                    }
                })
                .doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        Log.d(TAG, "doOnError accept: ");
                    }
                })
                // Run on a background thread
                .subscribeOn(Schedulers.io())
                // Be notified on the main thread
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getObserver());
//                .safeSubscribe(getObserver());
    }

    private Observable<String> getObservable() {
        return Observable.just("a", "b", "c", "d", "e", "f");
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