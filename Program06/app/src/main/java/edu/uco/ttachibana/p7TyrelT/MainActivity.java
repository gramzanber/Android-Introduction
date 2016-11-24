package edu.uco.ttachibana.p7TyrelT;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends Activity
{
    private TextView timeView;
    private int MY_PERMISSION_SEND_SMS = 0;
    private int MY_PERMISSION_RECEIVE_SMS = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timeView = (TextView) findViewById(R.id.textViewTime);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
            new TimeTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        else
            new TimeTask().execute();

        // API 23 or higher - runtime permission handling
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (getApplicationContext().checkSelfPermission(
                    android.Manifest.permission.SEND_SMS)
                    != PackageManager.PERMISSION_GRANTED) {
                this.requestPermissions(new String[]{Manifest.permission.SEND_SMS},
                        MY_PERMISSION_SEND_SMS);
            }
            if (getApplicationContext().checkSelfPermission(
                    Manifest.permission.RECEIVE_SMS)
                    != PackageManager.PERMISSION_GRANTED) {
                this.requestPermissions(new String[]{Manifest.permission.RECEIVE_SMS},
                        MY_PERMISSION_RECEIVE_SMS);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();
        if (id == R.id.action_settings) { return true; }
        return super.onOptionsItemSelected(item);
    }

    private class TimeTask extends AsyncTask<Date, String, String>
    {
        @Override
        protected void onPreExecute() { }

        @Override
        protected String doInBackground(Date...resId)
        {
            DateFormat dateFormat = DateFormat.getTimeInstance();
            Calendar cal = Calendar.getInstance();
            Date now = cal.getTime();

            while(now!=null)
            {
                sleep();
                cal = Calendar.getInstance();
                now = cal.getTime();

                publishProgress(dateFormat.format(now));
                   }
        return dateFormat.format(now);
        }

        @Override
        protected void onProgressUpdate(String... now) {
            timeView.setText(now[0]);
        }

        @Override
        protected void onPostExecute(String now) { }

        private void sleep()
        {
            try {
                Thread.sleep(1000);
            } catch(InterruptedException e) {
                Log.e("Main", e.toString());
            }
        }

    }
}
