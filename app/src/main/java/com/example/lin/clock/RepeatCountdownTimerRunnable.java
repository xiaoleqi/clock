package com.example.lin.clock;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.TextView;

/**
 * Performs countdown in background thread and provides methods for starting, cancelling, pausing
 * and resuming a countdown. The running thread in this class updates UI elements periodically, or
 * upon method calls. Thread running by this class must be handled on the main UI thread.
 *
 * @author Gordon
 *         Created by Gordon on 2015-03-18.
 */
public class RepeatCountdownTimerRunnable {
    private long timeInMillis, currentMillis;
    private static final long INTERVAL = 1000;
    private Handler handler;
    private int loopNum, currentLoop;
    private TextView display, notice;
    private Runnable counter;

    /**
     * Constructs a RepeatCountdownTimerRunnable with duration, number of times to repeat,
     * a view to display remaining time, and a view to display remaining loop.
     *
     * @param millis duration in milliseconds
     * @param loop number of times to repeat this countdown
     * @param v view to display remaining time
     * @param n view to display remaining loop
     */

    public RepeatCountdownTimerRunnable(long millis, int loop, TextView v, TextView n) {

        if (loop < 0) {
            throw new IllegalArgumentException("loop count must be >= 0");
        }
        if (millis < INTERVAL) {
            throw new IllegalArgumentException(String.format("time must be at least %d millisecond",
                    INTERVAL));
        }
        this.timeInMillis = millis;
        this.currentMillis = millis;
        this.loopNum = loop;
        this.currentLoop = loop;
        this.display = v;
        this.notice = n;
        // handler must be running in the main ui thread
        this.handler = new Handler(Looper.getMainLooper());
        // initialize the runnable which performs the countdown
        this.counter = new Runnable() {

            @Override
            public void run() {
                // for debug purposes
                if (Looper.myLooper() == Looper.getMainLooper()) {
                    Log.d("countdown", "running on UI thread!");
                }
                if (currentMillis <= 0) {
                    // reset countdown time
                    currentMillis = timeInMillis;
                    // repeat countdown if necessary
                    if (currentLoop > 0) {
                        Log.d("countdown", "repeating");
                        handler.post(this);
                        currentLoop--;
                        notice.setText(Integer.toString(currentLoop) + " loops remaining");
                    } else {
                        // done with this countdown
                        Log.d("countdown", "done!");
                        display.setText("Done!");
                        notice.setText("");
                        // reset loop count to original
                        currentLoop = loopNum;
                    }
                } else {
                    Log.d("countdown", Long.toString(currentMillis / 1000));
                    display.setText(Long.toString(currentMillis / 1000));
                    currentMillis -= INTERVAL;
                    handler.postDelayed(this, INTERVAL);
                }
            }
        };
    }

    /**
     * Starts the countdown by posting the runnable and updates UI
     */

    public void start() {
        Log.d("countdown", "starting");
        notice.setText(Integer.toString(loopNum) + " loops remaining");
        handler.post(counter);
    }

    /**
     * Stops this countdown and reset the current time counter
     */

    public void cancel() {
        handler.removeCallbacksAndMessages(null);
        Log.d("countdown", "cancelled!");
        display.setText("Cancelled");
        currentMillis = timeInMillis;
    }

    /**
     * Pause this countdown by emptying the handler's callback queue.
     * The current time counter stores the time remaining in this countdown.
     */

    public void pause() {
        // stops the countdown runnable
        handler.removeCallbacksAndMessages(null);
        Log.d("countdown", "pause");
        notice.setText(notice.getText() + " paused");
        // TODO: check this method for possible bugs
    }

    /**
     * Resume the paused countdown by posting the runnable
     */
    public void resume() {
        // TODO: resume countdown with saved time and loop count
        handler.postDelayed(counter, INTERVAL);
    }


}
