package com.fitn.whereareyou;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, OnMarkerClickListener {

    private GoogleMap mMap;
    //Create the Latitude and Longitude values
    private static final LatLng OGUN = new LatLng(7.1424,5.1145);
    private static final LatLng OSUN = new LatLng(7.780051,4.554969);
    private static final LatLng OYO = new LatLng(7.2325,3.553);


    //Create the markers
    private Marker mOgun;
    private Marker mOsun;
    private Marker mOyo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        List<Marker> markers = new ArrayList<>();

        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        mOgun = mMap.addMarker(new MarkerOptions()
        .position(OGUN).title("Ogun State"));
        mOgun.setTag(0);
        markers.add(mOgun);

        mOsun = mMap.addMarker(new MarkerOptions()
                .position(OSUN).title("Osun State").icon((BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))));
        mOsun.setTag(0);
        markers.add(mOsun);

        mOyo = mMap.addMarker(new MarkerOptions()
                .position(OYO).title("Oyo State").icon((BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE))));
        mOyo.setTag(0);
        markers.add(mOyo);

        mMap.setOnMarkerClickListener(this); //Do something if a marker is clicked


        //Loop through the arraylist of markers and add all of them to the map
        for (Marker m : markers){
            //Get the latitude and longitude of each marker to place on map
            LatLng point = new LatLng(m.getPosition().latitude, m.getPosition().longitude);
            mMap.addMarker(new MarkerOptions().position(point));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(point, 7)); //move map to show current location
        }

    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        Integer count = (Integer) marker.getTag();
        if (count != null){
            count = count + 1;
            marker.setTag(count);
            Toast.makeText(this, marker.getTitle() + " has been clicked " + count +
                    (count < 2 ? " time" : " times"), Toast.LENGTH_LONG).show();
        }

     return false;
    }
}
