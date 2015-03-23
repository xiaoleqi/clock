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
    private int loopNum, currentLoop;
    private TextView display, notice;

    public RepeatCountdownTimerRunnable(long millis, int loop, TextView v, TextView n){

        if(loop < 0){
            throw new IllegalArgumentException("loop count must be >= 0");
        }
        if (millis < interval){
            throw new IllegalArgumentException("time must be at least " + interval +  " millisecond");
        }
        this.timeInMillis = millis;
        this.currentMillis = millis;
        this.loopNum = loop;
        this.currentLoop = loop;
        this.display = v;
        this.notice = n;
        // handler must be running in the main ui thread
        this.handler = new Handler(Looper.getMainLooper());
    }

    public void start(){
        Log.d("countdown", "starting");
        //create the runnable which does the countdown
        final Runnable counter = new Runnable() {

            @Override
            public void run() {
                // for debug purposes
                if (Looper.myLooper() == Looper.getMainLooper()){
                   Log.d("countdown", "running on UI thread!");
                }
                if (currentMillis <= 0){
                    // reset countdown time
                    currentMillis = timeInMillis;
                    // repeat countdown if necessary
                    if (currentLoop > 0){
                        Log.d("countdown", "repeating");
                        handler.post(this);
                        currentLoop--;
                        notice.setText(Integer.toString(currentLoop) + " loops remaining");
                    }
                    else{
                        // done with this countdown
                        Log.d("countdown", "done!");
                        display.setText("Done!");
                        notice.setText("");
                        // reset loop count to original
                        currentLoop = loopNum;
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
        notice.setText(Integer.toString(loopNum) + " loops remaining");
        handler.post(counter);
    }

    public void cancel(){
        // stops the countdown runnable and reset time
        handler.removeCallbacksAndMessages(null);
        Log.d("countdown", "cancelled!");
        display.setText("Cancelled");
        currentMillis = timeInMillis;
    }

    public void pause(){
        // TODO: implement method to pause countdown
        // stops the countdown runnable
        handler.removeCallbacksAndMessages(null);
        Log.d("countdown", "pause");
        notice.setText(notice.getText() + " paused");
    }

    public void resume(){
        // TODO: resume countdown with saved time and loop count
    }



}
