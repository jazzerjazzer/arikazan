package com.po.kazan;
 
import android.app.Activity;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
 
public class AndroidTelephonyManager extends Activity implements LocationListener{
 
	String text;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main2);
		TextView tv1 = (TextView)(findViewById(R.id.lat));
		tv1.setText(text);
		TextView tv2 = (TextView)(findViewById(R.id.lng));
	}

	@Override
	public void onLocationChanged(Location loc) {
		
		loc.getLatitude();
		loc.getLongitude();

		text = "My current location is: " +"Latitud = " + loc.getLatitude() 
				+"Longitud = " + loc.getLongitude();
		Toast.makeText( getApplicationContext(), text,Toast.LENGTH_SHORT).show();
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		Toast.makeText( getApplicationContext(),"Gps Enabled",Toast.LENGTH_SHORT).show();

	}

	@Override
	public void onProviderDisabled(String provider) {
		Toast.makeText( getApplicationContext(),"Gps Disabled",Toast.LENGTH_SHORT).show();
		
	}
}