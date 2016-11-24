package edu.uco.ttachibana.p7TyrelT;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class WeatherActivity extends Activity
{
    public static EditText city_entered;

    private final String TITLE = "City: ";
    private final String WEATHER = "Weather: ";
    private final String TEMP = "Temperature: ";
    private final String WIND = "Wind Speed: ";
    private TextView titleView;
    private TextView weatherView;
    private TextView tempView;
    private TextView windView;
    private String coordLat;
    private String coordLon;
    private String sendInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("TOP");
        setContentView(R.layout.activity_weather);
        titleView = (TextView) findViewById(R.id.txt_title);
        weatherView = (TextView) findViewById(R.id.txt_weather);
        tempView = (TextView) findViewById(R.id.txt_temp);
        windView = (TextView) findViewById(R.id.txt_wind);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
            new HttpGetTask(this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, getIntent().getStringExtra("CITY"));
        else
            new HttpGetTask(this).execute(getIntent().getStringExtra("CITY"));
        System.out.println("Bottom");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    private class HttpGetTask extends AsyncTask<String, Void, ArrayList<String>> {
        private static final String TAG = "HttpGetTask";
        final String FORECAST_BASE_URL = "http://api.openweathermap.org/data/2.5/forecast/daily?";
        Context context;

        private HttpGetTask(Context context) {
            this.context = context.getApplicationContext();
        }

        @Override
        protected void onPreExecute() {
            Log.e(TAG, "Is seen?");
            // Here you can show progress bar or something on the similar lines.
            // Since you are in a UI thread here.
            super.onPreExecute();
        }

        @Override
        protected ArrayList<String> doInBackground(String... params) {
            Log.e(TAG, "Doing shit");
            InputStream in = null;
            HttpURLConnection httpUrlConnection = null;
            ArrayList<String> resultArray = null;
            try {
                Uri builtUri = Uri.parse(FORECAST_BASE_URL).buildUpon()
                        .appendQueryParameter("q", params[0] + ",us") // city
                        .appendQueryParameter("type","accurate")
                        .appendQueryParameter("mode", "json") // json format as result
                        .appendQueryParameter("units", "metric") // metric unit
                        .appendQueryParameter("cnt", "1")      // 14 days forecast
                        .appendQueryParameter("APPID", "1304efef9606d9186f584b334279ab6c")
                        .build();

                URL url = new URL(builtUri.toString());
                httpUrlConnection = (HttpURLConnection) url.openConnection();
                in = new BufferedInputStream(
                        httpUrlConnection.getInputStream());
                String data = readStream(in);
                resultArray = Weather.getData(data, params[0]);


            } catch (MalformedURLException exception) {
                Log.e(TAG, "MalformedURLException");
            } catch (IOException exception) {
                Log.e(TAG, "IOException");
            } catch (JSONException e) {
                Log.e(TAG, e.getMessage(), e);
                e.printStackTrace();
            } finally {
                if (null != httpUrlConnection) {
                    httpUrlConnection.disconnect();
                }
                if (in != null) {
                    try {
                        in.close();
                    } catch (final IOException e) {
                        Log.e(TAG, "Error closing stream", e);
                    }
                }

            }

            return resultArray;
        }

        @Override
        protected void onPostExecute(ArrayList<String> result) {
            Log.e(TAG, "lala");
            if (result == null || result.size() == 0) {
                Toast.makeText(WeatherActivity.this, "Invalid weather data. Possibly wrong city", Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent(context, MainActivity.class);
                startActivity(intent2);
                return;
            }
            String cityName = result.remove(0);
            titleView.setText(TITLE + cityName);
            String weatherCondition = result.remove(0);
            weatherView.setText(WEATHER + weatherCondition);
            String tempCondition = result.remove(0);
            tempView.setText(TEMP + tempCondition + "\u2103");
            String windCondition = result.remove(0);
            windView.setText(WIND + windCondition + " m/s");
            coordLon = result.remove(0);
            coordLat = result.remove(0);
            sendInfo = cityName + " " + tempCondition + "℃";

        }

        private String readStream(InputStream in) {
            BufferedReader reader = null;
            StringBuffer data = new StringBuffer("");
            try {
                reader = new BufferedReader(new InputStreamReader(in));
                String line = "";
                while ((line = reader.readLine()) != null) {
                    data.append(line);
                }
            } catch (IOException e) {
                Log.e(TAG, "IOException");
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return data.toString();
        }


    }
}
