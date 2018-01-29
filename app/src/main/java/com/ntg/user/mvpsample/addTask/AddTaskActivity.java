package com.ntg.user.mvpsample.addTask;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.ntg.user.mvpsample.Main4Activity;
import com.ntg.user.mvpsample.R;

public class AddTaskActivity extends AppCompatActivity implements AddTaskContract.View {
    EditText title, describtion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

    public void add(View view) {
        title = findViewById(R.id.tittle);
        describtion = findViewById(R.id.Describtion);
        Intent intent3 = new Intent(this, Main4Activity.class);
        Bundle b = new Bundle();
        b.putString("title", title.getText().toString());
        b.putString("description", describtion.getText().toString());
        intent3.putExtras(b);
    }

    @Override
    public void showSuccessDataMsg() {
        Toast.makeText(this, "task added successfully", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showTasks() {
        finish();
    }
}
