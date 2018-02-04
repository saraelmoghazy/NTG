package com.ntg.user.mvpsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import io.reactivex.Observable;

public class RxActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx);
        just();
        distinct();
        create();
        filter();
        range();
        elementAt();
        merge();
    }

    void just() {
        Observable.just(1, 2, 3, 4, 5)
                .subscribe(item -> Log.e("onNext", item.toString()));
    }
    public void distinct() {
        Observable.just(1, 2, 1, 2, 3, 4, 3)
                .distinct()
                .subscribe(item -> Log.i("distinct", item.toString()));
    }
    public void create() {
        Observable observable = Observable.create(
                emitter -> Log.i("create", " Observable "));
        observable.subscribe();
    }
    public void filter() {
        Observable.just(1, 3, 5, 6, 9, 12, 14)
                .filter(item -> item > 6).subscribe(item -> Log.i("filter", item.toString()));
    }
    public void range() {
        Observable.range(0, 10)
                .subscribe(item -> Log.i("range", item.toString()));
    }
    public void elementAt() {
        Observable.just(1, 3, 5, 6, 7, 12, 4, 17)
                .elementAt(5)
                .subscribe(item -> Log.i("elementAt", item.toString()));
    }
    public void merge() {
        Observable<String> observable1 = Observable.just("this ", "Rx ");
        Observable<String> observable2 = Observable.just("merge ", "operator");
        Observable.merge(observable1, observable2)
                .subscribe(item -> Log.i("merge", item));
    }
}
