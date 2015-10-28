package com.po.kazan;

public class centralContacts {

	private String name;
	private String email;
	private String website;
	private String phone;
	private String fax;
	private String adress;
	private String city;
	private String country;
	private String longitude;
	private String latitude;
	
	public centralContacts(){
		
	}
	
	public centralContacts(String name, String email, String website, String phone,
			String fax, String adress, String city, String country, String longitude,
			String latitude){
		
		this.name = name;
		this.email = email;
		this.website = website;
		this.phone = phone;
		this.fax = fax;
		this.adress = adress;
		this.city = city;
		this.country = country;
		this.longitude = longitude;
		this.latitude = latitude;
		
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	
	
	
}
