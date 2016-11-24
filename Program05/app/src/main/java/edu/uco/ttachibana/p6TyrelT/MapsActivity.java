package edu.uco.ttachibana.p6TyrelT;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap map;
    private LatLng csPosition = new LatLng(35.653988, -97.472842);
    private LatLng newPosition;
    private String info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_maps);
            Double newLat = Double.parseDouble(getIntent().getStringExtra("CITYLAT"));
            Log.e("TAG", newLat.toString());
            Double newLon = Double.parseDouble(getIntent().getStringExtra("CITYLON"));
            Log.e("TAG", newLon.toString());
            info = getIntent().getStringExtra("CITYINFO");
            newPosition = new LatLng(newLat, newLon);

            if (map != null) {
                CameraPosition camera = new CameraPosition.Builder()
                        .target(newPosition).zoom(18).build();

                map.animateCamera(CameraUpdateFactory.newCameraPosition(camera));
                map.clear(); // clear all markers if any
                map.addMarker(
                        new MarkerOptions().position(newPosition).title("okay")
                );

            }
            MapFragment mapFragment = (MapFragment) getFragmentManager()
                    .findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);
        }
        catch(Exception mapException)
        {
            // This exception was mostly caused by not have a good API key, but not crashing is good
            Log.e("MapActivity", mapException.getMessage());
            Toast.makeText(this, "The developer is a bakayaro!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }


    @Override
    public void onMapReady(GoogleMap map)
    {
        this.map = map;
        CameraPosition camera = new CameraPosition.Builder().target(newPosition).zoom(16).build();
        map.animateCamera(CameraUpdateFactory.newCameraPosition(camera));
        map.clear();
        map.addMarker(new MarkerOptions().position(newPosition).title(info));
        map.getUiSettings().setZoomControlsEnabled(true);
        map.animateCamera(CameraUpdateFactory.newCameraPosition(camera));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }
}
