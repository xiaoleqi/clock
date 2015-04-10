package com.example.lin.clock;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;


public class MainActivity extends ActionBarActivity {
    public TextView tv, msgView;
    public int i = 1;
    public long total = 6000;
    private RepeatCountdownTimerRunnable runnableTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView)findViewById(R.id.countdown);
        msgView = (TextView)findViewById(R.id.messageDisplay);
        /* attempt to format time
        long tempMinute = TimeUnit.MILLISECONDS.toMinutes(60000);
        long tempSecond = TimeUnit.MILLISECONDS.toSeconds(60000);
        String timeOnscreen = String.format("%d min %d sec", tempMinute, tempSecond);
        tv.setText(timeOnscreen);
        */

        try{
            runnableTimer = new RepeatCountdownTimerRunnable(total, i, tv, msgView);
        }
        catch (IllegalArgumentException e){
            Log.d("countdown", e.getMessage());
        }

    }

    public void startCountdown(View v){
        runnableTimer.start();
    }

    public void cancelCountdown(View v){
        runnableTimer.cancel();
    }

    public void pauseCountdown(View v){
        runnableTimer.pause();
    }

    public void resumeCountdown(View v){
        runnableTimer.resume();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
