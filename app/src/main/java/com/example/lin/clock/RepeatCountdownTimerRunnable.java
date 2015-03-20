package com.example.lin.clock;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

/**
 * Created by Gordon on 2015-03-18.
 * Countdown Timer that runs by a runnable with handler
 */
public class RepeatCountdownTimerRunnable {
    private long timeInMillis;
    private static final long interval = 1000;
    private  Handler handler;
    private int loopNum;

    public RepeatCountdownTimerRunnable(long millis, int loop){
        this.timeInMillis = millis;
        this.loopNum = loop;
        // handler must be running in the main ui thread
        this.handler = new Handler(Looper.getMainLooper());
    }

    public void start(){
        Log.d("countdown", "starting");
        final Runnable counter = new Runnable() {
            @Override
            public void run() {
                if (timeInMillis <= 0){
                    Log.d("countdown", "done!");
                }
                else{
                    Log.d("countdown", Long.toString(timeInMillis/1000));
                    timeInMillis -= interval;
                    handler.postDelayed(this, interval);
                }
            }
        };
        handler.postDelayed(counter, interval);
    }

}
