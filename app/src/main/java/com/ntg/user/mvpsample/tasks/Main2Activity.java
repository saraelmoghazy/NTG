package com.ntg.user.mvpsample.tasks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.ntg.user.mvpsample.R;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.TimeoutException;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

public class Main2Activity extends AppCompatActivity {


    EditText addTitle,addDescribtion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        addTitle=findViewById(R.id.title);
        addDescribtion=findViewById(R.id.describtion);
    }

    public void addTask(View view) {


    }
}
