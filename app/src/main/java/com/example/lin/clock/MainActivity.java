package com.example.lin.clock;

import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;


public class MainActivity extends ActionBarActivity {
    public TextView tv, msgView;
    public Button b;
    public int i = 3;
    public long total = 6000;
    private RepeatCountDownTimer ct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView)findViewById(R.id.g);
        msgView = (TextView)findViewById(R.id.messageDisplay);
        b = (Button)findViewById(R.id.b);
        ct = new RepeatCountDownTimer(3000, 3, tv, msgView);
        ct.startCountDown();
    }
    /*

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
