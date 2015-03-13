package com.example.lin.clock;

import android.os.CountDownTimer;
import android.widget.TextView;


/**
 * @author Gordon
 */
public class RepeatCountDownTimer {
    private int duration,loop;
    private long milliUntilDone;
    private TextView display, message;
    private CountDownTimer mainTimer;
    private CountDownTimer tempTimer;

    public RepeatCountDownTimer(int startDuration, int totalLoop, TextView main, TextView end){
        duration = startDuration;
        loop = totalLoop;
        display = main;
        message = end;
        mainTimer = new CountDownTimer(duration, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                display.setText(millisUntilFinished/1000 + " seconds left");
                milliUntilDone = millisUntilFinished;
            }

            @Override
            public void onFinish() {
                repeatCountDown();
            }
        };
    }

    private void repeatCountDown(){
        if (loop > 0){
            mainTimer.start();
            display.setText(duration/1000 + " seconds left");            
            loop--;
            message.setText((loop + 1) + " more");
        }
        else{
            display.setText("");
            message.setText("done!");
        }
    }

    public void startCountDown(){
        display.setText(duration/1000 + " seconds left");
        mainTimer.start();
        loop--;
        message.setText( (loop + 1) + " more");
    }

    public void stopCountDown(){
        mainTimer.cancel();
    }
}
