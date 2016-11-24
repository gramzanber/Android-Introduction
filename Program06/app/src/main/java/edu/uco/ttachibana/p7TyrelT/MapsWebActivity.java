package edu.uco.ttachibana.p7TyrelT;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsWebActivity extends Activity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener
{
    private GoogleMap map;
    private LatLng csPosition = new LatLng(35.654164, -97.472982);
    private LatLng nursing = new LatLng(35.653572, -97.473680);
    private LatLng biology = new LatLng(35.654687, -97.472388);
    private LatLng computer = new LatLng(35.653858, -97.472666);
    private LatLng engineering = new LatLng(35.654980, -97.472803);
    private LatLng math = new LatLng(35.654135, -97.472857);
    private LatLng funeral = new LatLng(35.653316, -97.473399);
    private LatLng chemistry = new LatLng(35.654923, -97.472302);

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_web);

        if (map!= null)
        {
            CameraPosition camera = new CameraPosition.Builder().target(csPosition).zoom(18).build();
            map.animateCamera(CameraUpdateFactory.newCameraPosition(camera));
            map.clear();
            map.addMarker(new MarkerOptions().position(csPosition).title("okay") );
        }
        MapFragment mapFragment = (MapFragment)getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap map)
    {
        this.map = map;
        CameraPosition camera = new CameraPosition.Builder().target(csPosition).zoom(18).build();
        map.animateCamera(CameraUpdateFactory.newCameraPosition(camera));
        map.clear();
        map.addMarker(new MarkerOptions().position(biology).title("Biology"));
        map.addMarker(new MarkerOptions().position(nursing).title("Nursing"));
        map.addMarker(new MarkerOptions().position(engineering).title("Engineering"));
        map.addMarker(new MarkerOptions().position(chemistry).title("Chemistry"));
        map.addMarker(new MarkerOptions().position(math).title("Mathematics"));
        map.addMarker(new MarkerOptions().position(computer).title("Computer Science"));
        map.addMarker(new MarkerOptions().position(funeral).title("Funeral Services"));
        map.getUiSettings().setZoomControlsEnabled(true);
        map.animateCamera(CameraUpdateFactory.newCameraPosition(camera));
        map.setOnMarkerClickListener(this);
    }

    @Override
    public boolean onMarkerClick(Marker marker)
    {
        String web;
        switch (marker.getTitle())
        {
            case "Biology":
                web = "http://biology.uco.edu/";
                break;
            case "Engineering":
                web = "http://www.uco.edu/cms/engineering/";
                break;
            case "Nursing":
                web = "http://www.uco.edu/cms/nursing/";
                break;
            case "Computer Science":
                web = "http://cs.uco.edu/www/";
                break;
            case "Mathematics":
                web = "http://www.math.uco.edu/";
                break;
            case "Funeral Services":
                web = "http://www.uco.edu/cms/funeral/index.asp";
                break;
            case "Chemistry":
                web = "http://www.uco.edu/cms/chemistry/";
                break;
            default:
                web = "http://www.google.com/";
                break;
        }
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(web));
        startActivity(intent);

        return true;
    }
}
