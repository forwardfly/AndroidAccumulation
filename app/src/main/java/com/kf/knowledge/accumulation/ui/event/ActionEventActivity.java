package com.kf.knowledge.accumulation.ui.event;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.kf.knowledge.accumulation.R;
import com.kf.knowledge.accumulation.base.BaseActivity;


/**
 * Created by ybg on 2016/12/9.
 */

public class ActionEventActivity extends BaseActivity {


    public static void startActivity(Context mContext) {
        Intent startIntent = new Intent(mContext , ActionEventActivity.class);
        mContext.startActivity(startIntent);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setContentView(R.layout.act_action_event);

    }


}
