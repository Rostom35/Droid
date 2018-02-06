package com.example.adou.mydrone;

import android.graphics.Color;
import android.os.Handler;
import android.os.SystemClock;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.view.animation.Interpolator;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements
        GoogleMap.OnMarkerClickListener,
        OnMapReadyCallback {

    private GoogleMap mMap;
    private Marker mMelbourne;
    private Marker mSydney;
    private Marker mDrone;
    private Marker mDarwin;
    private List<Marker> markers;
    private List<LatLng> markersLatLng;

    private static final LatLng MELBOURNE = new LatLng(-37.81319, 144.96298);
    private static final LatLng DRONE = new LatLng(-24.6980, 133.8807);
    private static final LatLng DARWIN = new LatLng(-12.4634, 130.8456);
    private static final LatLng SYDNEY = new LatLng(-34, 151);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        // Handle click event for floating button
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LatLng current = mMap.getCameraPosition().target;
                String text = "Current camera position (lat:"+current.latitude+",long:"+
                        current.longitude + ")";

                String text2 = "Nombre de markers=" + markers.size();
                // Set our specific action
                Snackbar.make(view, text2, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        markers = new ArrayList<>();
        markersLatLng = new ArrayList<>();
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
        mMap = googleMap;

        // Add a marker in Sydney and move the camera

        mDrone = mMap.addMarker(new MarkerOptions()
                .position(DRONE)
                .title("Drone")
                .snippet("Iris Plus qui coûte 2K€")
                .draggable(true)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE)));

        mSydney = mMap.addMarker(new MarkerOptions()
                .position(SYDNEY)
                .title("Marker in Sydney")
                .draggable(true)); // Make marker draggable
        markers.add(mSydney);
        markersLatLng.add(SYDNEY);

        mMelbourne = mMap.addMarker(new MarkerOptions()
                .position(MELBOURNE)
                .title("Melbourne")
                .snippet("Population: 4,137,400")
                .draggable(true));
        markers.add(mMelbourne);
        markersLatLng.add(MELBOURNE);


        mDarwin = mMap.addMarker(new MarkerOptions()
                .position(DARWIN)
                .title("Marker in Darwin")
                .snippet("Darwin's marker...")
                .draggable(true));
        markers.add(mDarwin);
        markersLatLng.add(DARWIN);


        mMap.moveCamera(CameraUpdateFactory.newLatLng(SYDNEY));

        // add control to perform zoom In/Out
        // mMap.getUiSettings().setZoomControlsEnabled(true);

        // Remove markers control
        mMap.getUiSettings().setMapToolbarEnabled(false);

        // Listener for Drag & Drop
        mMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
            @Override
            public void onMarkerDragStart(Marker marker) {
                LatLng p = marker.getPosition();
                String str = "lat:" + p.latitude + " long: " + p.longitude;
                Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onMarkerDrag(Marker marker) {

            }

            @Override
            public void onMarkerDragEnd(Marker marker) {
                LatLng p = marker.getPosition();
                String str = "lat:" + p.latitude + " long: " + p.longitude;
                Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
            }
        });

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

            @Override
            public void onMapClick(LatLng latLng) {
                Marker newMarker = mMap.addMarker(new MarkerOptions()
                    .position(latLng)
                    .draggable(true));
                markers.add(newMarker);
                markersLatLng.add(latLng);
                drawPolygon();
            }
        });

        drawPolygon();
    }

    public void drawPolygon(){

        mMap.clear();

        // Draw Polygon on our Google Maps
        PolygonOptions rectOptions = new PolygonOptions()
//            .add( MELBOURNE, DARWIN, SYDNEY )
                .addAll( markersLatLng )
                .strokeColor(Color.parseColor("#7F616161"))
                .fillColor(Color.parseColor("#7FBDBDBD"))
                .strokeWidth(3);

        // Add polygon on map
        mMap.addPolygon(rectOptions);

        // put markers
        for (Marker m : markers) {
            mMap.addMarker(new MarkerOptions()
                    .position(m.getPosition())
                    .title(m.getTitle())
                    .snippet(m.getSnippet())
                    .draggable(m.isDraggable()));
        }

        mMap.addMarker(new MarkerOptions()
                .position(mDrone.getPosition())
                .title(mDrone.getTitle())
                .snippet(mDrone.getSnippet())
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE))
                .draggable(mDrone.isDraggable()));
    }

    @Override
    public boolean onMarkerClick(final Marker marker) {
        if (marker.equals(mMelbourne)) {
            // This causes the marker at Melbourne to bounce into position when it is clicked.
            final Handler handler = new Handler();
            final long start = SystemClock.uptimeMillis();
            final long duration = 1500;

            final Interpolator interpolator = new BounceInterpolator();

            handler.post(new Runnable() {
                @Override
                public void run() {
                    long elapsed = SystemClock.uptimeMillis() - start;
                    float t = Math.max(
                            1 - interpolator.getInterpolation((float) elapsed / duration), 0);
                    marker.setAnchor(0.5f, 1.0f + 2 * t);

                    if (t > 0.0) {
                        // Post again 16ms later.
                        handler.postDelayed(this, 16);
                    }
                }
            });
        }

        // We return false to indicate that we have not consumed the event and that we wish
        // for the default behavior to occur (which is for the camera to move such that the
        // marker is centered and for the marker's info window to open, if it has one).
        return false;
    }
}
