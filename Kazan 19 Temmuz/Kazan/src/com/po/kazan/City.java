package com.po.kazan;

public class City {

	public String name;
	public double lat;
	public double lng;
	public double[] month;
	public double precise = 0.00;
	
	public City(String name, double lat, double lng, double[] m){
		this.name = name;
		this.lat = lat;
		this.lng = lng;
		this.month = m;
	}
	
}
