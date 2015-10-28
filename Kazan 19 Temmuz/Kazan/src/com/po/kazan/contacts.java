package com.po.kazan;

public class contacts {
	
	private int id;
	private String name;
	private String phone;
	private String phone2;
	private String website;
	private String email;
	private String adress;
	private String city;
	private String country;
	
	public contacts(int id, String name, String phone, String phone2,
			String website, String email, String adress, String city, String country){
		
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.phone2 = phone2;
		this.website = website;
		this.email = email;
		this.adress = adress;
		this.city = city;
		this.country = country;
		
	}
	
	public contacts(){
		
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPhone2() {
		return phone2;
	}
	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	
}
