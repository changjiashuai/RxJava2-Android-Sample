package com.rxjava2.android.samples.ui.operators.transforming;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.rxjava2.android.samples.R;
import com.rxjava2.android.samples.utils.AppConstant;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Email: changjiashuai@gmail.com
 *
 * Created by CJS on 28/06/17 17:42.
 */
public class FlatMapExampleActivity extends AppCompatActivity {

    private static final String TAG = FlatMapExampleActivity.class.getSimpleName();
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

        getObservable().flatMap(new Function<String, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(@NonNull String s) throws Exception {
                return Observable.just("flatMap func-->" + s);
            }
        }).buffer(3, 1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getObserver());

        // 3 means,  it takes max of three from its start index and create list
        // 1 means, it jumps one step every time
        // so the it gives the following list
        // 1 - flatMap func-->one, flatMap func-->two, flatMap func-->three
        // 2 - flatMap func-->two, flatMap func-->three, flatMap func-->four
        // 3 - flatMap func-->three, flatMap func-->four, flatMap func-->five
        // 4 - flatMap func-->four, flatMap func-->five
        // 5 - flatMap func-->five
    }

    private Observable<String> getObservable() {
        return Observable.just("one", "two", "three", "four", "five");
    }

    private Observer<List<String>> getObserver() {
        return new Observer<List<String>>() {

            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, " onSubscribe : " + d.isDisposed());
            }

            @Override
            public void onNext(List<String> stringList) {
                textView.append(" onNext size : " + stringList.size());
                textView.append(AppConstant.LINE_SEPARATOR);
                Log.d(TAG, " onNext : size :" + stringList.size());
                for (String value : stringList) {
                    textView.append(" value : " + value);
                    textView.append(AppConstant.LINE_SEPARATOR);
                    Log.d(TAG, " : value :" + value);
                }

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