package com.rxjava2.android.samples.ui.operators.conditional;

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
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Email: changjiashuai@gmail.com
 *
 * Created by CJS on 28/06/17 16:07.
 */
public class TakeUntilExampleActivity extends AppCompatActivity {

    private static final String TAG = TakeUntilExampleActivity.class.getSimpleName();
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

    private boolean isRunning = false;

    /**
     * takeUntil与skipUntil操作符作用相反，订阅并开始发射原始Observable，它还监视你提供的第二个Observable。
     * 如果第二个Observable发射了一项数据或者发射了一个终止通知（ onError通知或一个onComplete通知），
     * TakeUntil返回的Observable会停止发射原始Observable并终止。
     */
    private void doSomeWork() {
        if (!isRunning) {
            Observable
                    .interval(1, TimeUnit.SECONDS)
                    .take(6)
                    .takeUntil(Observable.just(10).delay(3, TimeUnit.SECONDS))
                    .doOnSubscribe(new Consumer<Disposable>() {
                        @Override
                        public void accept(@NonNull Disposable disposable) throws Exception {
                            isRunning = true;
                        }
                    })
                    .doOnTerminate(new Action() {
                        @Override
                        public void run() throws Exception {
                            isRunning = false;
                        }
                    })
                    // Run on a background thread
                    .subscribeOn(Schedulers.io())
                    // Be notified on the main thread
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(getObserver());
        }
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