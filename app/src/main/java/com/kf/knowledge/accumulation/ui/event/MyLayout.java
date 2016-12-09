package com.kf.knowledge.accumulation.ui.event;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.FrameLayout;

/**
 * Created by ybg on 2016/12/9.
 */

public class MyLayout extends FrameLayout {

    private static final String TAG = MyLayout.class.getName();

    public MyLayout(Context context) {
        super(context);
    }

    public MyLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }


    /**
     *
     *  默认调用 super.onInterceptTouchEvent(ev)，该方法放回 false。
     *      不拦截TouchEvent
     *
     *
     * @param ev
     * @return
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.d(TAG, "MyLayout onInterceptTouchEvent() ");
        return false;

//        boolean interceptTouchEvent = super.onInterceptTouchEvent(ev);
//        return super.onInterceptTouchEvent(ev);
//        Log.d(TAG, "MyLayout onInterceptTouchEvent() interceptTouchEvent : " + interceptTouchEvent);

//        return super.onInterceptTouchEvent(ev);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG, "MyLayout onTouchEvent() ");
        LogEventUtil.logAction(event, TAG);
        return true ;
    }

}
