package com.example.lin.clock;

import android.os.CountDownTimer;
import android.widget.TextView;


/**
 * @author Gordon
 */
public class RepeatCountDownTimer {
    private int duration,loop;
    private TextView display, message;
    private CountDownTimer mainTimer;
    private CountDownTimer tempTimer;

    public RepeatCountDownTimer(int startDuration, int totalLoop, TextView main, TextView end){
        duration = startDuration;
        loop = totalLoop;
        display = main;
        message = end;
        mainTimer = new CountDownTimer(duration, loop) {
            @Override
            public void onTick(long millisUntilFinished) {
                display.setText(millisUntilFinished/1000 + " seconds left");
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
            loop--;
            message.setText(loop + " more");
        }
        else{
            message.setText("done!");
        }
    }

    public void startCountDown(){
        mainTimer.start();
    }

    public void stopCountDown(){
        mainTimer.cancel();
    }
}
