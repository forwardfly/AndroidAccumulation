package com.kf.knowledge.accumulation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.kf.knowledge.accumulation.ui.event.ActionEventActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_action_event).setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btn_action_event :
                ActionEventActivity.startActivity(MainActivity.this);
                break;
            default :
                break;
        }

    }

}
