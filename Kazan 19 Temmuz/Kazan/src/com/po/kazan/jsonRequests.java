package com.po.kazan;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class jsonRequests {

	private String getContacts = "http://ancient-fortress-3725.herokuapp.com/api/v1/getContacts";
	private String getCentralContacts ="http://ancient-fortress-3725.herokuapp.com/api/v1/getCentralContacts";
	private String getProducts = "http://ancient-fortress-3725.herokuapp.com/api/v1/getProducts";
	
	public ArrayList<contacts> contacts = new ArrayList<contacts>();
	public ArrayList<centralContacts> centralContacts = new ArrayList<centralContacts>();
	public ArrayList<products> products = new ArrayList<products>();
	
	public void getContacts() throws IOException{
		
		URL url = new URL(getContacts);
		URLConnection conn = url.openConnection();
		
		BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String line;
		
		while ((line = rd.readLine()) != null) {
			JSONArray jsonArray = new JSONArray(line);
			for(int i=0;i<jsonArray.length();i++){
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				
				int id = (Integer) jsonObject.get("id");
				String name = (String) jsonObject.get("name");
				String phone = (String) jsonObject.get("phone");
				String phone2 = (String) jsonObject.get("phone2");
				String fax = (String) jsonObject.get("fax");
				String website = (String) jsonObject.get("website");
				String email = (String) jsonObject.get("email");
				String adress = (String) jsonObject.get("adress");
				String city = (String) jsonObject.get("city");
				String country = (String) jsonObject.get("country");
				
				contacts c = new contacts(id, name, phone, phone2, website, email,
						adress, city, country);
				
				contacts.add(c);
				
			}
		}
		
	}
	
	public void getCentralContacts() throws IOException{
		
		URL url = new URL(getCentralContacts);
		URLConnection conn = url.openConnection();
		
		BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String line;
		
		while ((line = rd.readLine()) != null) {
			JSONArray jsonArray = new JSONArray(line);
			for(int i=0;i<jsonArray.length();i++){
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				
				String name = (String) jsonObject.get("name");
				String email = (String) jsonObject.get("email");
				String website = (String) jsonObject.get("website");
				String phone = (String) jsonObject.get("phone");
				String fax = (String) jsonObject.get("fax");
				String adress = (String) jsonObject.get("adress");
				String city = (String) jsonObject.get("city");
				String country = (String) jsonObject.get("country");
				String longitude = String.valueOf((Double) jsonObject.get("longitude"));
				String latitude = String.valueOf((Double) jsonObject.get("latitude"));

				centralContacts c = new centralContacts(name, email, website, phone,
						fax, adress, city, country, longitude, latitude);
				
				centralContacts.add(c);
			}
		}
		
	}
	
	public void getProducts() throws IOException{
		
		URL url = new URL(getProducts);
		URLConnection conn = url.openConnection();
		
		BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String line;
		
		while ((line = rd.readLine()) != null) {
			JSONArray jsonArray = new JSONArray(line);
			for(int i=0;i<jsonArray.length();i++){
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				String name = (String) jsonObject.get("name");

				JSONObject cats = (JSONObject) jsonObject.get("category");
				int catId = (Integer) cats.get("id");
				String sortDescriptor =  (String) cats.get("sortDescriptor");
				
				JSONArray locDesc = (JSONArray) cats.get("localizedDescriptions");
				ArrayList<localizedDescriptions> lds = new ArrayList<localizedDescriptions>();
				for(int j=0; j<locDesc.length(); j++){
					JSONObject obj = locDesc.getJSONObject(j);
					
					int ldId = (Integer) obj.get("id");
					String localizedDescription =  (String) obj.get("localizedDescription");
					String region =  (String) obj.get("region");
					
					localizedDescriptions ld = new localizedDescriptions(ldId, localizedDescription, region);
					
					lds.add(ld);
				}
				
				JSONArray products = (JSONArray) jsonObject.get("productTypes");
				ArrayList<productType> pts = new ArrayList<productType>();
				for(int j=0; j<products.length(); j++){
					JSONObject obj = products.getJSONObject(j);
					
					int ptId = (Integer) obj.get("id");
					String pName = (String) obj.get("name");
					String capacity =  String.valueOf((Double) obj.get("capacity"));
					String watt =  String.valueOf((Double) obj.get("watt"));

					productType pt = new productType(ptId, pName, capacity, watt);
					pts.add(pt);
					
				}
				
				JSONArray locals = (JSONArray) jsonObject.get("localizedDescriptions");
				ArrayList<localizedDescriptions> catLocDesc = new ArrayList<localizedDescriptions>();
				for(int j=0; j<locals.length(); j++){
					JSONObject obj = locals.getJSONObject(j);
					
					int ldId = (Integer) obj.get("id");
					String localizedDescription =  (String) obj.get("localizedDescription");
					String region =  (String) obj.get("region");
					
					localizedDescriptions ld = new localizedDescriptions(ldId, localizedDescription, region);
					
					catLocDesc.add(ld);
				}
				
				JSONArray pdfs = (JSONArray) jsonObject.get("pdfUrls");
				ArrayList<String> pdfURLs = new ArrayList<String>();
				for(int j=0; j<pdfs.length(); j++){
					JSONObject obj = pdfs.getJSONObject(j);
					pdfURLs.add((String) obj.get("url"));
				}
				
				JSONArray fuels = (JSONArray) jsonObject.get("fuelTypes");
				ArrayList<String> fuelTypes = new ArrayList<String>();
				for(int j=0; j<fuels.length(); j++){
					JSONObject obj = fuels.getJSONObject(j);
					fuelTypes.add((String) obj.get("fuelType"));
				}
				
				JSONArray images = (JSONArray) jsonObject.get("productImgUrls");
				ArrayList<String> imageURLs = new ArrayList<String>();
				for(int j=0; j<images.length(); j++){
					JSONObject imageObject = images.getJSONObject(j);
					imageURLs.add((String) imageObject.get("imageUrl"));
				}
				
				products p = new products(name, imageURLs.toArray(new String[imageURLs.size()]), pdfURLs.toArray(new String[pdfURLs.size()]),
						fuelTypes.toArray(new String[fuelTypes.size()]), pts.toArray(new productType[pts.size()]), lds.toArray(new localizedDescriptions[lds.size()]), 
						catId, catLocDesc.toArray(new localizedDescriptions[catLocDesc.size()]), sortDescriptor);
				
				this.products.add(p);
			}
		}
		
	}
	
}
