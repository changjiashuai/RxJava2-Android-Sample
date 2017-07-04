package com.rxjava2.android.samples.ui.operators.errorhanding;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.rxjava2.android.samples.R;
import com.rxjava2.android.samples.utils.AppConstant;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class CatchExampleActivity extends AppCompatActivity {

    private static final String TAG = CatchExampleActivity.class.getSimpleName();
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
        //catch error
//        Observable.error(new Throwable("test catch fun"))
////                .onErrorResumeNext(new Function<Throwable, ObservableSource<?>>() {
////                    @Override
////                    public ObservableSource<?> apply(@NonNull Throwable throwable) throws Exception {
////                        return null;
////                    }
////                })
////                .onErrorReturnItem(100)
////                .onErrorResumeNext(new Function<Throwable, ObservableSource<?>>() {
////                    @Override
////                    public ObservableSource<?> apply(@NonNull Throwable throwable) throws Exception {
////                        return Observable.just(100);
////                    }
////                })
//                // Run on a background thread
//                .subscribeOn(Schedulers.io())
//                // Be notified on the main thread
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(getObserver());

        //catch exception
        Observable.error(new Exception("test exception"))
                .onExceptionResumeNext(Observable.just(500))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getObserver());
    }

    private void testErrorReturn(){
        Observable.just(1).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorReturn(new Function<Throwable, Integer>() {
                    @Override
                    public Integer apply(@NonNull Throwable throwable) throws Exception {
                        //拦截到错误后，返回一个结果发射，然后就正常结束了
                        return -1;
                    }
                }).subscribe(getObserver());
    }

    private Observable<Integer> getObservable() {
        return Observable.just(1, 2, 3, 4, 5, 6);
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