package com.example.lin.clock;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
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
        /* debug

        msgView = (TextView)findViewById(R.id.messageDisplay);
        b = (Button)findViewById(R.id.b);
        ct = new RepeatCountDownTimer(3000, 3, tv, msgView);
        ct.startCountDown();
        */
        tv = (TextView)findViewById(R.id.countdown);
        msgView = (TextView)findViewById(R.id.messageDisplay);
        /* attempt to format time
        long tempMinute = TimeUnit.MILLISECONDS.toMinutes(60000);
        long tempSecond = TimeUnit.MILLISECONDS.toSeconds(60000);
        String timeOnscreen = String.format("%d min %d sec", tempMinute, tempSecond);
        tv.setText(timeOnscreen);
        */
        runnableTimer = new RepeatCountdownTimerRunnable(total, i, tv, msgView);

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
    /* debug

    public void selfDestruct(View view) {

        ct.start();

    }

    public void stopClock(View view){
        boolean on = ((ToggleButton) view).isChecked();
        if(on) {
            ct.cancel();
        }else{
            ct.start();
        }
    }
    public void restart() {
        if(i > 0) {
            ct.start();
            i--;
        }
    }
    */

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
