package edu.uco.ttachibana.p7TyrelT;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Weather
{
    private static final String TAG = "JSONWeatherData";

    public static ArrayList<String> getData(String forecastJsonStr, String city) throws JSONException
    {
        try
        {
            JSONObject forecastJson = new JSONObject(forecastJsonStr);
            JSONArray weatherArray = forecastJson.getJSONArray("list");
            JSONObject cityJson = forecastJson.getJSONObject("city");
            String cityName = cityJson.getString("name"); // city name
            String tester = city;
            String[] parts = tester.split("\\,");
            String newtester = parts[0];
            Log.e(TAG, "City: " + newtester);Log.e(TAG, "City2: " + cityName);
            if(!cityName.equalsIgnoreCase(newtester))
            {
                Log.e(TAG, "City not found!");
                return null;
            }
            Log.e(TAG, "Found city");
            JSONObject cityCoord = cityJson.getJSONObject("coord"); // coordinate
            double cityLat = cityCoord.getDouble("lat"); //latitude
            double cityLon = cityCoord.getDouble("lon"); // longitude

            ArrayList<String> result = new ArrayList<>();
            result.add(cityName);

            for (int i = 0; i < weatherArray.length(); i++)
            {
                JSONObject dayForecast = weatherArray.getJSONObject(i);
                double windSpeed = dayForecast.getDouble("speed");
                JSONObject weatherObject = dayForecast.getJSONArray("weather").getJSONObject(0);
                String description = weatherObject.getString("description");
                JSONObject temperatureObject = dayForecast.getJSONObject("temp");
                double avg = temperatureObject.getDouble("day");
                result.add(description);
                result.add(Double.toString(avg));
                result.add(Double.toString(windSpeed));
                result.add(Double.toString(cityLon));
                result.add(Double.toString(cityLat));
            }
            return result;

        }
        catch (JSONException e)
        {
            Log.e(TAG, e.getMessage(), e);
            e.printStackTrace();
        }
        return null;
    }
}
