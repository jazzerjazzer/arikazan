package com.po.kazan;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AndroidGPSTrackingActivity extends Activity {

	Button btnShowLocation;

	// GPSTracker class
	GPSTracker gps;
	String path;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		System.out.println("qweqweqwe");
		gps = new GPSTracker(AndroidGPSTrackingActivity.this);
		
		// check if GPS enabled		
		if(gps.canGetLocation()){ 

			double latitude = gps.getLatitude();
			double longitude = gps.getLongitude();
			
			System.out.println("lat: " + latitude);
			
			try {
				writeToFile(latitude + " " + longitude);
				finish();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			// \n is for new line
			//Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();	

			//    /data/data/com.po.kazan/files/hwlocation.txt

			
		}else{
			
			try {
				writeToFile(-1 + " " + -1);
				finish();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			gps.showSettingsAlert();
		}

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
}