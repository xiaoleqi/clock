package com.example.lin.clock;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by Gordon on 2015-03-18.
 * Countdown Timer that runs by a runnable with handler
 */
public class RepeatCountdownTimerRunnable {
    private long timeInMillis, currentMillis;
    private static final long interval = 1000;
    private  Handler handler;
    private int loopNum;
    private TextView display;

    public RepeatCountdownTimerRunnable(long millis, int loop, TextView v){

        if(loop <= 0){
            throw new IllegalArgumentException("loop count must be at least 1");
        }
        if (millis <= 0){
            throw new IllegalArgumentException("time must be greater than 0");
        }
        this.timeInMillis = millis;
        this.currentMillis = millis;
        this.loopNum = loop;
        this.display = v;
        // handler must be running in the main ui thread
        this.handler = new Handler(Looper.getMainLooper());
    }

    public void start(){
        Log.d("countdown", "starting");
        final Runnable counter = new Runnable() {
            @Override
            public void run() {
                if (Looper.myLooper() == Looper.getMainLooper()){
                   Log.d("countdown", "running on UI thread!");
                }
                if (currentMillis <= 0){
                    Log.d("countdown", "done!");
                    display.setText("Done!");
                    if (loopNum > 0){
                        currentMillis = timeInMillis;
                        handler.postDelayed(this, interval);
                        loopNum--;
                    }
                }
                else{
                    Log.d("countdown", Long.toString(currentMillis/1000));
                    display.setText(Long.toString(currentMillis/1000));
                    currentMillis -= interval;
                    handler.postDelayed(this, interval);
                }
            }
        };
        loopNum--;
        handler.postDelayed(counter, interval);
    }

    public void cancel(){
        // stops the countdown runnable and reset time
        handler.removeCallbacksAndMessages(null);
        display.setText("Cancelled");
        currentMillis = timeInMillis;
    }

    public void pause(){
        // TODO: implement method to pause countdown
    }

    public void resume(){
        // TODO: resume countdown with saved time and loop count
    }



}
