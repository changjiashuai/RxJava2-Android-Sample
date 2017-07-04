package com.rxjava2.android.samples.ui.operators.transforming;

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
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.observables.GroupedObservable;

/**
 * Email: changjiashuai@gmail.com
 *
 * Created by CJS on 28/06/17 17:42.
 */
public class GroupByExampleActivity extends AppCompatActivity {

    private static final String TAG = GroupByExampleActivity.class.getSimpleName();
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

        Observable.range(0, 8).groupBy(new Function<Integer, String>() {
            @Override
            public String apply(@NonNull Integer integer) throws Exception {
                return integer % 2 == 0 ? "偶数" : "奇数";
            }
        }).subscribe(new Consumer<GroupedObservable<String, Integer>>() {
            @Override
            public void accept(@NonNull GroupedObservable<String, Integer> stringIntegerGroupedObservable) throws Exception {
                String key = stringIntegerGroupedObservable.getKey();
                Log.i(TAG, "accept: key=" + key);
                if (key.equals("偶数")) {
                    stringIntegerGroupedObservable.subscribe(getObserver(key));
                } else {
                    stringIntegerGroupedObservable.subscribe(getObserver(key));
                }
            }
        });
    }

    private Observer<Integer> getObserver(final String key) {
        return new Observer<Integer>() {

            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, " onSubscribe : " + d.isDisposed());
            }

            @Override
            public void onNext(Integer str) {
                textView.append(key + " value: " + str);
                textView.append(AppConstant.LINE_SEPARATOR);
                Log.d(TAG, " : value :" + str);
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