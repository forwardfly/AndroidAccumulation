package com.kf.knowledge.accumulation.ui.event;

import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by ybg on 2016/12/9.
 */

public class LogEventUtil {

    public static void logAction(MotionEvent event, final String tag) {

        int action = event.getAction();
        switch(action) {
            case MotionEvent.ACTION_DOWN:
                Log.d(tag, "Action down");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.d(tag, "Action cancel");
                break;
            case MotionEvent.ACTION_UP:
                Log.d(tag, "Action up");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d(tag, "Action move");
                break;
            default:
                Log.d(tag, "unknow action");
        }

    }

}
