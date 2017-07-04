package com.rxjava2.android.samples.ui.operators.creating;

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
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Email: changjiashuai@gmail.com
 *
 * Created by CJS on 28/06/17 16:07.
 */
public class EmptyNeverThrowExampleActivity extends AppCompatActivity {

    private static final String TAG = EmptyNeverThrowExampleActivity.class.getSimpleName();
    Button btnEmpty;
    Button btnNever;
    Button btnThrow;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty_never_throw);
        btnEmpty = (Button) findViewById(R.id.btn_empty);
        btnNever = (Button) findViewById(R.id.btn_never);
        btnThrow = (Button) findViewById(R.id.btn_throw);
        textView = (TextView) findViewById(R.id.textView);

        btnEmpty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doEmpty();
            }
        });
        btnNever.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doNever();
            }
        });
        btnThrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doThrow();
            }
        });
    }

    /*
     * simple example to emit two value one by one
     */
    private void doEmpty() {
        getEmptyObservable()
                // Run on a background thread
                .subscribeOn(Schedulers.io())
                // Be notified on the main thread
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getObserver());
    }

    private void doNever() {
        getNeverObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getObserver());
    }

    private void doThrow() {
        getThrowObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getObserver());
    }

    private Observable<String> getEmptyObservable() {
        return Observable.empty();
    }

    private Observable<String> getNeverObservable() {
        return Observable.never();
    }

    private Observable<String> getThrowObservable() {
        return Observable.error(new Callable<Throwable>() {
            @Override
            public Throwable call() throws Exception {
                return new Throwable("throw error test");
            }
        });
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