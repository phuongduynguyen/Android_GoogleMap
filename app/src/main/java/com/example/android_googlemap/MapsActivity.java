package com.example.android_googlemap;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.Dash;
import com.google.android.gms.maps.model.Dot;
import com.google.android.gms.maps.model.Gap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.android_googlemap.databinding.ActivityMapsBinding;
import com.google.android.gms.maps.model.PatternItem;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


// First of all, You need add API key in AndroidManifest.xml
public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,
        GoogleMap.OnMarkerClickListener,
        GoogleMap.OnMarkerDragListener,
        GoogleMap.OnMapClickListener,
        GoogleMap.OnInfoWindowClickListener,
        GoogleMap.OnPolylineClickListener,
        GoogleMap.OnPolygonClickListener{

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng haNoi = new LatLng(21 , 105);
        Marker marker = mMap.addMarker(new MarkerOptions()
                .position(haNoi)                                                   // Set location
                .title("Marker in Hanoi")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.notifi))      // Change icon maker to your icon
                .draggable(true)                                                    // Set true to move maker to anywhere
                .snippet("Hello world"));                                           // Like subtitle;


        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions()
                .position(sydney)
                .position(haNoi)                                                    // Set location
                .title("Marker in Sydney")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.notifi))      // Change icon maker to your icon
                .draggable(true)                                                    // Set true to move maker to anywhere
                .snippet("Hello world"));                                           // Like subtitle


        // Turn on click listener
        mMap.setOnMarkerClickListener(this);
        mMap.setOnMapClickListener(this);
        mMap.setOnInfoWindowClickListener(this);


        // Polyline
        LatLng m1 = new LatLng(21 , 105);
        LatLng m2 = new LatLng(31 , 115);
        LatLng m3 = new LatLng(41 , 125);
        LatLng m4 = new LatLng(51 , 135);

        PolylineOptions rectOption = new PolylineOptions()
                .add(m1)
                .add(m2)
                .add(m3)
                .add(m4);

        Polyline polyline = mMap.addPolyline(rectOption);


        // Polygons
        ArrayList<LatLng> latLngs = new ArrayList<>();
        latLngs.add(new LatLng(1,1));
        latLngs.add( new LatLng(2,1));
        latLngs.add(new LatLng(2, 2));
        latLngs.add(new LatLng(1,1));

        PolygonOptions rectOption1 = new PolygonOptions()
                .add(m1, m2, m3, m4)
                .addHole(latLngs)
                .fillColor(Color.BLUE)
                .strokeColor(Color.RED);

        Polygon polygon = mMap.addPolygon(rectOption1);


        // Circle
        CircleOptions circleOptions = new CircleOptions()
                .center(m1)
                .radius(10000);     // in meters

        // Get back the mutable circle
        Circle circle = mMap.addCircle(circleOptions);
        circle.setStrokeColor(Color.RED);


        // List
        List<PatternItem> patternItems = Arrays.asList(
                new Dot(),
                new Gap(10),
                new Dash(50)
        );

        // Stroke using polyline
        PolylineOptions strokeOption = new PolylineOptions()
                .add(m1)
                .add(m2)
                .add(m3)
                .add(m4);

        Polyline polyline2 = mMap.addPolyline(strokeOption);
        polyline2.setPattern(patternItems);


        // Camera will move to position when app start
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    @Override
    public boolean onMarkerClick(@NonNull Marker marker) {
        Toast.makeText(this, "My Position" + marker.getPosition(), Toast.LENGTH_SHORT).show();
        return false;
    }


    @Override
    public void onMarkerDrag(@NonNull Marker marker) {

    }

    @Override
    public void onMarkerDragEnd(@NonNull Marker marker) {
        // Getting the new position lat and long
        Toast.makeText(this, "lat: " + marker.getPosition().latitude + "\n\rlong: " + marker.getPosition().longitude, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMarkerDragStart(@NonNull Marker marker) {

    }

    @Override
    public void onMapClick(@NonNull LatLng latLng) {
        // When map clicked
        Toast.makeText(this, "Map Clicked", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onInfoWindowClick(@NonNull Marker marker) {
        // When information window clicked
        Toast.makeText(this, "Information Window Clicked", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onPolylineClick(@NonNull Polyline polyline) {
        Toast.makeText(this, "Polyline Clicked", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onPolygonClick(@NonNull Polygon polygon) {
        Toast.makeText(this, "Polygon Clicked", Toast.LENGTH_SHORT).show();

    }
}