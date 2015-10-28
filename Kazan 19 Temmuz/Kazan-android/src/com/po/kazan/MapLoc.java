package com.po.kazan;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import android.app.ActionBar;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapLongClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapLoc extends FragmentActivity implements LocationListener, OnMapLongClickListener, 
ActionBar.OnNavigationListener {

	GoogleMap map;
	double lat, lng;
	private ActionBar actionBar;
	MainProgram program;

	/*
	 * 
	 * Activity ilk yaratýldýðýnda çaðrýlan metod. Ýlk kod segmentinde Action Bar'ý initialize eder. 
	 * Ýkinci segmentte haritayý initialize eder. 
	 * 
	 * */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.map_layout);
		/*  actionBar = getActionBar();
		actionBar.setDisplayShowTitleEnabled(false);
		actionBar.setHomeButtonEnabled(true);
		actionBar.setNavigationMode(ActionBar.DISPLAY_HOME_AS_UP);
		actionBar.setIcon(R.drawable.headerleftarrow);*/

		Intent tempIntent = getIntent();
		lat = tempIntent.getDoubleExtra("lat", 39.91364936273377);
		lng = tempIntent.getDoubleExtra("long", 32.85477206616213);

		GooglePlayServicesUtil
		.isGooglePlayServicesAvailable(getApplicationContext());
		map = ((MapFragment) getFragmentManager()
				.findFragmentById(R.id.map)).getMap();

		map.setMyLocationEnabled(true);
		map.setMapType(GoogleMap.MAP_TYPE_NORMAL);

		map.setOnMapLongClickListener(this);
		Toast.makeText(this, "Seçmek istediğiniz lokasyona basılı tutunuz.", Toast.LENGTH_LONG).show();


		/* map.setOnMarkerDragListener(new OnMarkerDragListener() {

            @Override
            public void onMarkerDragStart(Marker marker) {
                // TODO Auto-generated method stub
                // Here your code
                Toast.makeText(MapLocation.this, "Dragging Start",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onMarkerDragEnd(Marker marker) {
                // TODO Auto-generated method stub
                Toast.makeText(
                        MapLocation.this,
                        "Lat " + map.getMyLocation().getLatitude() + " "
                                + "Long " + map.getMyLocation().getLongitude(),
                        Toast.LENGTH_LONG).show();

                lat = map.getMyLocation().getLatitude();
                lng = map.getMyLocation().getLongitude();

                System.out.println("yalla b2a "
                        + map.getMyLocation().getLatitude());
            }

            @Override
            public void onMarkerDrag(Marker marker) {
                // TODO Auto-generated method stub
                // Toast.makeText(MainActivity.this, "Dragging",
                // Toast.LENGTH_SHORT).show();
                System.out.println("Draagging");
            }
        });*/

	}


	public boolean onMarkerClick(final Marker marker) {
		/*
        if (marker.equals(map)) {
            // handle click here
            // map.getMyLocation();
            System.out.println("Clicked");
            double lat = map.getMyLocation().getLatitude();
            System.out.println("Lat" + lat);
            Toast.makeText(MapLocation.this,
                    "Current location " + map.getMyLocation().getLatitude(),
                    Toast.LENGTH_SHORT).show();
        }*/
		return true;
	}

	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
	}

	/*
	 * 
	 * Haritanýn üzerinde uzun basýlý tutulduðunda bu listener'a girer. addMarker metodu yeni marker ekler 
	 * haritanýn üzerine. Ýstersen Title ve Description da koyabilirsin,metodlarý mevcut ama gerek yok. 
	 * Burada aldýðýmýz lat ve long deðerlerini bir türlü pass edemedim! Game objesi pass eder gibi yapabilirsin sanýrým, ben tam çözemedim. 
	 * 
	 * */
	@Override
	public void onMapLongClick(LatLng point) {

		Toast.makeText(MapLoc.this, point.latitude+" "+point.longitude, Toast.LENGTH_SHORT).show();

		map.addMarker(new MarkerOptions()
		.position(new LatLng(point.latitude, point.longitude))
		.draggable(true)
		.icon(BitmapDescriptorFactory
				.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));

		lat = point.latitude;
		lng = point.longitude;

		//ResultData r = new ResultData(lat, lng);

		/*
		try {
			writeToFile(lat + " " + lng);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		try {
			writeToFile(lat + " " + lng);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("write success");

		finish();
	}

	private void writeToFile(String data) throws IOException {

		String path = "/data/data/com.po.kazan/files/hwlocation.txt";
		
		OutputStream myOutput;
		try {
			myOutput = new BufferedOutputStream(new FileOutputStream(path,false));
			
			myOutput.write(data.getBytes()); // new String("TEST")
			myOutput.flush();
			myOutput.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("write success");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.activity_main_actions, menu);

		return super.onCreateOptionsMenu(menu);
	}

	/**
	 * 
	 * Action Bar Sherlock'taki geri tuþu ve check tuþu için listener. Refresh dediðim tuþ check, home dediðim 
	 * tuþ geri dön tuþu.
	 * 
	 * */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Take appropriate action for each action item click
		/*switch (item.getItemId()) {

		case R.id.action_refresh:
			// refresh
			System.out.println("refresh");
			finish();

			return true;
		case android.R.id.home:
			System.out.println("Home");
			finish();
		default:
			return super.onOptionsItemSelected(item);
		}*/
		return true;
	}

	/*
	 * Actionbar navigation item select listener
	 */
	@Override
	public boolean onNavigationItemSelected(int itemPosition, long itemId) {
		// Action to be taken after selecting a spinner item
		return false;
	}
}
