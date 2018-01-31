package com.ntg.user.mvpsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.reactivestreams.Subscription;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;

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
        reduce();
    }

    void just(){
        Observable.just(1 , 2 , 3 , 4 , 5)
                .subscribe(item -> Log.e("onNext" , item.toString()));
    }

    void create(){
        Observable.create(emitter -> {emitter.onNext("islam");
        emitter.onNext("eldsoke");
        emitter.onNext("gamal");
        }).subscribe(item-> Log.e("onNext" , item.toString()));

    }

    void distinct(){
        Observable.just(2,2,3,4,5).distinct()
                .subscribe(item-> Log.e("onNext" , item.toString()));
    }

    void elementAt(){
        Observable.just(1,2,3,4)
                .elementAt(0)
                .subscribe(item-> Log.e("onNext" , item.toString()));
    }

    void filter(){
        Observable.just("islam","islam eldsoke","magdy","yassin","ali")
                .filter(s-> s.equals("islam"))
                .subscribe(item->Log.e("onNext" , item));
    }

    void range(){
        Observable.range(1,10)
                .doOnNext(integer -> Log.e("integer" , integer.toString()))
                .doOnComplete(() -> Log.e("onCompleted","done"))
                .subscribe();
    }

    void reduce(){
        Observable.just("islam", "eldsoke" , "gamal")
                .reduce((s, s2 ) -> s+s2)
                .subscribe(s -> Log.e("reduce" , s));
    }
}
