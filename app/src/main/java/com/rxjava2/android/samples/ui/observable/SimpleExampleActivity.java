package com.rxjava2.android.samples.ui.observable;

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
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

public class SimpleExampleActivity extends AppCompatActivity {

    private static final String TAG = SimpleExampleActivity.class.getSimpleName();
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

    /*
     * simple example to emit two value one by one
     */
    private void doSomeWork() {
        getObservable()
                // Run on a background thread
                .subscribeOn(Schedulers.io())
                // Be notified on the main thread
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getObserver());
    }

    private Observable<String> getObservable() {
        return Observable.just("Cricket", "Football");
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


    ////-------三级缓存 BEGIN--------////
    private String getStrFromMemory() {
        return "from memory";
    }

    private String getStrFromDisk() {
        return "from disk";
    }

    private String getStrFromNetwork() {
        return "from network";
    }

    Observable<String> memory = Observable.create(new ObservableOnSubscribe<String>() {
        @Override
        public void subscribe(ObservableEmitter<String> e) throws Exception {
            if (getStrFromMemory() != null) {
                e.onNext(getStrFromMemory());
            } else {
                e.onComplete();
            }
        }
    });

    Observable<String> disk = Observable.create(new ObservableOnSubscribe<String>() {
        @Override
        public void subscribe(ObservableEmitter<String> e) throws Exception {
            if (getStrFromDisk() != null) {
                e.onNext(getStrFromDisk());
            } else {
                e.onComplete();
            }
        }
    });

    Observable<String> network = Observable.create(new ObservableOnSubscribe<String>() {
        @Override
        public void subscribe(ObservableEmitter<String> e) throws Exception {
            if (getStrFromNetwork() != null) {
                e.onNext(getStrFromNetwork());
            } else {
                e.onComplete();
            }
        }
    });

    private void getData() {
        Observable.concat(memory, disk, network)
                .first("")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        // TODO: 2017/7/5
                    }

                    @Override
                    public void onSuccess(String s) {
                        Log.d(TAG, "onSuccess: " + s);
                    }

                    @Override
                    public void onError(Throwable e) {
                        // TODO: 2017/7/5  
                    }
                });
    }
    ////-------三级缓存 END--------////


    ////-------多个接口并发取完数据合并 BEGIN--------////
    private void testMerge() {
        //界面需要等到多个接口并发取完数据，再更新, 不保证数据返回的顺序
        Observable<String> o1 = Observable.just("data1");
        Observable<String> o2 = Observable.just("data2");

        Observable.merge(o1, o2)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        Log.d(TAG, "onNext: " + s);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    ////-------多个接口并发取完数据合并 END--------////


    ////-------一个接口依赖另一个接口返回的数据作为请求参数 BEGIN--------////
    private Observable<String> getToken() {
        return Observable.just("Token");
    }

    private Observable<String> getMessage(String token) {
        if (token != null) {
            return Observable.just("message with token");
        }
        return Observable.just("invalid token");
    }

    private void getMessage() {
        getToken().flatMap(new Function<String, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(@NonNull String s) throws Exception {
                return getMessage(s);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(@NonNull String s) throws Exception {
                        Log.d(TAG, "accept: " + s);
                    }
                });
    }
    ////-------一个接口依赖另一个接口返回的数据作为请求参数 END--------////


    ////-------界面按钮需要防止连续点击的情况 BEGIN--------////
    private void testDoubleClick() {
        Observable.just("test").throttleFirst(1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(@NonNull String s) throws Exception {
                        Log.d(TAG, "accept: " + s);
                    }
                });
    }
    ////-------界面按钮需要防止连续点击的情况 END--------////

    ////-------复杂的数据变换 BEGIN--------////
    private void test() {
        Observable.just("1", "2", "2", "3", "4", "5")
                .map(new Function<String, Integer>() {
                    @Override
                    public Integer apply(@NonNull String s) throws Exception {
                        return Integer.parseInt(s);
                    }
                })
                .filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(@NonNull Integer integer) throws Exception {
                        return integer > 1;
                    }
                })
                .distinct()
                .take(3)
                .reduce(new BiFunction<Integer, Integer, Integer>() {
                    @Override
                    public Integer apply(@NonNull Integer integer, @NonNull Integer integer2) throws Exception {
                        return integer + integer2;
                    }
                })
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(@NonNull Integer integer) throws Exception {
                        Log.d(TAG, "accept: " + integer);
                    }
                });
    }
    ////-------复杂的数据变换 END--------////
}