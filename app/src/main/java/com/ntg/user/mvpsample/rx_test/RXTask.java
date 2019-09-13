package com.ntg.user.mvpsample.rx_test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.ntg.user.mvpsample.R;

import io.reactivex.Observable;

/**
 * Created by GM7 on 2/8/2018.
 */

public class RXTask extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_test);
        just();
        create();
        distinct();
        elementAt();
        filter();
        range();
        merge();

    }

    public void just() {
        Observable.just(1, 2, 3, 4, 5)
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
        Observable.just(1,2,3,4,55,6)
                .elementAt(5)
                .subscribe(item -> Log.i("elementAt", item.toString()));
    }

    public void filter() {
        Observable.just(14, 35, 65, 32, 97, 2, 45, 63)
                .filter(item -> item > 50).subscribe(item -> Log.i("filter", item.toString()));
    }

    public void range() {
        Observable.range(0, 5)
                .subscribe(item -> Log.i("range", item.toString()));
    }

    public void merge() {
        Observable<String> observable = Observable.just("Hello ", "World");
        Observable<String> observable2 = Observable.just(" :P", " HHHHHHH");
        Observable.merge(observable, observable2)
                .subscribe(item -> Log.i("merge", item));
    }
}