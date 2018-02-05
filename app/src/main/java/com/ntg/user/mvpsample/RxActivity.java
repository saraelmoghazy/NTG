package com.ntg.user.mvpsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

public class RxActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx);
        just();
        create();
        distinct();
        elementAt();
        filter();
        range();
        merge();

    }

    public void just() {
        Observable.just(1, 2, 3, 4, 5, 6, 7, 9)
                .subscribe(item -> Log.i("just", item.toString()));
    }

    public void create() {
        Observable observable = Observable.create(
                emitter -> Log.i("create", "Custom Observable is used now"));
        observable.subscribe();
    }

    public void distinct() {
        Observable.just(1, 1, 1, 2, 2, 2, 3, 3, 3)
                .distinct()
                .subscribe(item -> Log.i("distinct", item.toString()));
    }

    public void elementAt() {
        Observable.just(14, 35, 65, 32, 97, 2, 45, 63)
                .elementAt(5)
                .subscribe(item -> Log.i("elementAt", item.toString()));
    }

    public void filter() {
        Observable.just(14, 35, 65, 32, 97, 2, 45, 63)
                .filter(item -> item > 50).subscribe(item -> Log.i("filter", item.toString()));
    }

    public void range() {
        Observable.range(0, 10)
                .subscribe(item -> Log.i("range", item.toString()));
    }

    public void merge() {
        Observable<String> observable = Observable.just("this ", "is ");
        Observable<String> observable2 = Observable.just("merge ", "operator");
        Observable.merge(observable, observable2)
                .subscribe(item -> Log.i("merge", item));
    }
}
