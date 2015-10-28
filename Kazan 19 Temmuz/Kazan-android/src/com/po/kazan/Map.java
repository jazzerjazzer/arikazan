package com.po.kazan;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Map extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.map_layout);
	
		Intent tempIntent = getIntent();
		
		double lat = tempIntent.getDoubleExtra("latitude", 39.91364936273377);
		double lng = tempIntent.getDoubleExtra("longtitude", 32.85477206616213);
		
		String title = tempIntent.getStringExtra("title");
		String description = tempIntent.getStringExtra("description");
		
	
		GoogleMap map = ((MapFragment) getFragmentManager()
				.findFragmentById(R.id.map)).getMap();

		LatLng loc = new LatLng(lat, lng);

		map.setMyLocationEnabled(true);
		map.moveCamera(CameraUpdateFactory.newLatLngZoom(loc, 13));

		map.addMarker(new MarkerOptions()
		.title(title)
		.snippet(description)
		.position(loc));
	}
}
