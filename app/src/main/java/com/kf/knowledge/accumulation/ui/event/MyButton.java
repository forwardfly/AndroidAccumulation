package com.kf.knowledge.accumulation.ui.event;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Button;

/**
 * Created by ybg on 2016/12/9.
 */

public class MyButton extends Button {

    private static final String TAG = MyButton.class.getName();

    public MyButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG, "MyButton onTouchEvent " + super.onTouchEvent(event) );
        LogEventUtil.logAction(event, TAG);
//        return super.onTouchEvent(event);
        return false;
    }

}
