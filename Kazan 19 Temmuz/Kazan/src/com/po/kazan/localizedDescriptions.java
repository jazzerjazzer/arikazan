package com.po.kazan;

public class localizedDescriptions {

	private int id;
	private String locDesc;
	private String region;
	
	public localizedDescriptions(int id, String locDesc, String region){
		this.id = id;
		this.locDesc = locDesc;
		this.region = region;
	}
	
	public String getLocDesc() {
		return locDesc;
	}
	public void setLocDesc(String locDesc) {
		this.locDesc = locDesc;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	
}
