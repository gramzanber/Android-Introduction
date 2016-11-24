package edu.uco.ttachibana.p6TyrelT;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
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

public class MainActivity extends Activity
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
    private Button mapButton;
    private String coordLat;
    private String coordLon;
    private String sendInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        city_entered = (EditText) findViewById(R.id.city_entered);
        titleView = (TextView) findViewById(R.id.txt_title);
        weatherView = (TextView) findViewById(R.id.txt_weather);
        tempView = (TextView) findViewById(R.id.txt_temp);
        windView = (TextView) findViewById(R.id.txt_wind);
        mapButton = (Button) findViewById(R.id.btn_map);


        final Button loadButton = (Button) findViewById(R.id.button1);
        loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cityString = city_entered.getText().toString();
                Log.e("TAG",cityString);
                if (cityString.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please Enter City",
                            Toast.LENGTH_SHORT).show();
                } else {
                    new HttpGetTask().execute(cityString);
                }
            }
        });
    }

    private class HttpGetTask extends AsyncTask<String, Void, ArrayList<String>> {

        private static final String TAG = "HttpGetTask";

        // Construct the URL for the OpenWeatherMap query
        // Possible parameters are avaiable at OWM's forecast API page, at
        // http://openweathermap.org/API#forecast
        final String FORECAST_BASE_URL =
                "http://api.openweathermap.org/data/2.5/forecast/daily?";
        @Override
        protected ArrayList<String> doInBackground(String... params) {
            // These two need to be declared outside the try/catch
            // so that they can be closed in the finally block.
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
                resultArray = Weather.getData(data);


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
            if (result == null || result.size() == 0) {
                Toast.makeText(MainActivity.this,
                        "Invalid weather data. Possibly wrong city",
                        Toast.LENGTH_SHORT).show();
                mapButton.setVisibility(View.INVISIBLE);
                mapButton.setEnabled(false);
                titleView.setText("");
                weatherView.setText("");
                tempView.setText("");
                windView.setText("");
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
            mapButton.setEnabled(true);
            mapButton.setVisibility(View.VISIBLE);
            coordLon = result.remove(0);
            coordLat = result.remove(0);
            mapButton.setText("Lon: " + coordLon + " " + "Lat: " + coordLat);
            sendInfo = cityName + " " + tempCondition + "℃";


            mapButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                    intent.putExtra("CITYLAT", coordLat);
                    intent.putExtra("CITYLON", coordLon);
                    intent.putExtra("CITYINFO",sendInfo);
                    startActivity(intent);


                }
            });

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }
}
