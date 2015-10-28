package com.po.kazan;

public class productType {
	
	private int id;
	private String name;
	private String capacity;
	private String watt;
	
	public productType(int id, String name, String capacity, String watt){
		
		this.id = id;
		this.name = name;
		this.capacity = capacity;
		this.watt = watt;
		
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCapacity() {
		return capacity;
	}
	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}
	public String getWatt() {
		return watt;
	}
	public void setWatt(String watt) {
		this.watt = watt;
	}
}
