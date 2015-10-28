package com.po.kazan;

import com.badlogic.gdx.Gdx;

public class ReadTxt {
	boolean exists;
	public String[] tempCentralTxt, tempContactTxt, tempProductTxt;
	public centralContacts centralContactObjectsFromTxt[];
	public contacts contactsObjectsFromTxt[];
	public products productsObjectsFromTxt[];

	public ReadTxt(){

		exists = Gdx.files.local("myConfigFile.txt").exists();

		String tempStr = Gdx.files.local("centralContact.txt").readString();
		String tempStr2 = Gdx.files.local("contacts.txt").readString();
		String tempStr3 = Gdx.files.local("products.txt").readString();

		tempCentralTxt = tempStr.split("\\r?\\n");
		tempContactTxt = tempStr2.split("\\r?\\n");
		tempProductTxt = tempStr3.split("\\r?\\n");

		centralContactObjectsFromTxt = new centralContacts[tempCentralTxt.length];
		contactsObjectsFromTxt = new contacts[tempContactTxt.length];
		productsObjectsFromTxt = new products[tempProductTxt.length];

		System.out.println(centralContactObjectsFromTxt.length);
		System.out.println(contactsObjectsFromTxt.length);
		System.out.println(productsObjectsFromTxt.length);

		createContacts();
		createCentralContacts();
		createProducts();
		//checkTxt();
	}



	public void createCentralContacts(){

		String name,email,website,phone,fax,address, city, country,longitude,latitude;

		for (int i = 0; i < tempCentralTxt.length; i++){

			name = tempCentralTxt[i].substring(0, tempCentralTxt[i].indexOf("#"));
			tempCentralTxt[i] = tempCentralTxt[i].substring(tempCentralTxt[i].indexOf("#")+1);

			email = tempCentralTxt[i].substring(0, tempCentralTxt[i].indexOf("#"));
			tempCentralTxt[i] = tempCentralTxt[i].substring(tempCentralTxt[i].indexOf("#")+1);

			website = tempCentralTxt[i].substring(0, tempCentralTxt[i].indexOf("#"));
			tempCentralTxt[i] = tempCentralTxt[i].substring(tempCentralTxt[i].indexOf("#")+1);

			phone = tempCentralTxt[i].substring(0, tempCentralTxt[i].indexOf("#"));
			tempCentralTxt[i] = tempCentralTxt[i].substring(tempCentralTxt[i].indexOf("#")+1);

			fax = tempCentralTxt[i].substring(0, tempCentralTxt[i].indexOf("#"));
			tempCentralTxt[i] = tempCentralTxt[i].substring(tempCentralTxt[i].indexOf("#")+1);

			address = tempCentralTxt[i].substring(0, tempCentralTxt[i].indexOf("#"));
			tempCentralTxt[i] = tempCentralTxt[i].substring(tempCentralTxt[i].indexOf("#")+1);

			city = tempCentralTxt[i].substring(0, tempCentralTxt[i].indexOf("#"));
			tempCentralTxt[i] = tempCentralTxt[i].substring(tempCentralTxt[i].indexOf("#")+1);

			country = tempCentralTxt[i].substring(0, tempCentralTxt[i].indexOf("#"));
			tempCentralTxt[i] = tempCentralTxt[i].substring(tempCentralTxt[i].indexOf("#")+1);

			longitude = tempCentralTxt[i].substring(0, tempCentralTxt[i].indexOf("#"));
			tempCentralTxt[i] = tempCentralTxt[i].substring(tempCentralTxt[i].indexOf("#")+1);

			latitude = tempCentralTxt[i].substring(0, tempCentralTxt[i].length());

			/*System.out.println("name: " + name);
			System.out.println("email: " + email);
			System.out.println("website: " + website);
			System.out.println("phone: " + phone);
			System.out.println("fax: " + fax);
			System.out.println("address: " + address);
			System.out.println("city: " + city);
			System.out.println("country: " + country);
			System.out.println("longitude: " + longitude);
			System.out.println("latitude: " + latitude);
			 */
			centralContacts c = new centralContacts(name, email, website, phone, fax, address, city, country, longitude, latitude);
			centralContactObjectsFromTxt[i] = c;			
		}
	}

	public void createContacts(){

		String name, phone, phone2,website, email, adress, city, country;

		for (int i = 0; i < tempContactTxt.length; i++){

			name = tempContactTxt[i].substring(0, tempContactTxt[i].indexOf("#"));
			tempContactTxt[i] = tempContactTxt[i].substring(tempContactTxt[i].indexOf("#")+1);

			phone = tempContactTxt[i].substring(0, tempContactTxt[i].indexOf("#"));
			tempContactTxt[i] = tempContactTxt[i].substring(tempContactTxt[i].indexOf("#")+1);

			if (tempContactTxt[i].charAt(0) == '#'){ // ikinci telefon yok.

				phone2 = "-1";
				tempContactTxt[i] = tempContactTxt[i].substring(tempContactTxt[i].indexOf("#")+1);

			}else{
				phone2 = tempContactTxt[i].substring(0, tempContactTxt[i].indexOf("#"));
				tempContactTxt[i] = tempContactTxt[i].substring(tempContactTxt[i].indexOf("#")+1);

			}

			if (tempContactTxt[i].charAt(0) == '#'){ // web sitesi yok
				website = "-1";
				tempContactTxt[i] = tempContactTxt[i].substring(tempContactTxt[i].indexOf("#")+1);
			}else{

				website = tempContactTxt[i].substring(0, tempContactTxt[i].indexOf("#"));
				tempContactTxt[i] = tempContactTxt[i].substring(tempContactTxt[i].indexOf("#")+1);
			}

			if (tempContactTxt[i].charAt(0) == '#'){ // e mail yok
				email = "-1";
				tempContactTxt[i] = tempContactTxt[i].substring(tempContactTxt[i].indexOf("#")+1);
			}else{

				email = tempContactTxt[i].substring(0, tempContactTxt[i].indexOf("#"));
				tempContactTxt[i] = tempContactTxt[i].substring(tempContactTxt[i].indexOf("#")+1);
			}

			if (tempContactTxt[i].charAt(0) == '#'){ // adres yok
				adress = "-1";
				tempContactTxt[i] = tempContactTxt[i].substring(tempContactTxt[i].indexOf("#")+1);
			}else{

				adress = tempContactTxt[i].substring(0, tempContactTxt[i].indexOf("#"));
				tempContactTxt[i] = tempContactTxt[i].substring(tempContactTxt[i].indexOf("#")+1);
			}

			city = tempContactTxt[i].substring(0, tempContactTxt[i].indexOf("#"));
			tempContactTxt[i] = tempContactTxt[i].substring(tempContactTxt[i].indexOf("#")+1);

			country = tempContactTxt[i].substring(0, tempContactTxt[i].length());

			/*System.out.println("name: " + name);
			System.out.println("phone: " + phone);
			System.out.println("phone2: " + phone2);
			System.err.println("website: " +website );
			System.err.println("email: " + email);
			System.err.println("adres: " + adress);
			System.err.println("city: " + city);
			System.err.println("country: " + country);
			 */
			contacts c = new contacts(0, name, phone, phone2, website, email, adress, city, country);
			contactsObjectsFromTxt[i] = c;

		}
	}


	public void createProducts(){

		String name, sortDescriptor; 
		String[] imageURLs, pdfURLs, fuelTypes;
		productType[] productTypes; 
		localizedDescriptions[] locDesc,catLocDesc;
		int catId; 

		String tempProductType[];

		for (int i = 0; i < tempProductTxt.length; i++){

			name = tempProductTxt[i].substring(0, tempProductTxt[i].indexOf("#"));
			tempProductTxt[i] = tempProductTxt[i].substring(tempProductTxt[i].indexOf("#")+1);

			//System.out.println("name: " + name);

			String temp = tempProductTxt[i].substring(0, tempProductTxt[i].indexOf("#"));
			imageURLs = temp.split("\\*");

			/*for (int j = 0; j < imageURLs.length; j++){
				System.err.println(imageURLs[j]);
			}*/

			tempProductTxt[i] = tempProductTxt[i].substring(tempProductTxt[i].indexOf("#")+1);

			temp = tempProductTxt[i].substring(0, tempProductTxt[i].indexOf("#"));
			pdfURLs = temp.split("\\*");

			/*for (int j = 0; j < pdfURLs.length; j++){
				System.err.println(pdfURLs[j]);
			}*/

			tempProductTxt[i] = tempProductTxt[i].substring(tempProductTxt[i].indexOf("#")+1);
			temp = tempProductTxt[i].substring(0, tempProductTxt[i].indexOf("#"));
			fuelTypes = temp.split("\\*");

			/*for (int j = 0; j < fuelTypes.length; j++){
				System.err.println(fuelTypes[j]);
			}*/

			tempProductTxt[i] = tempProductTxt[i].substring(tempProductTxt[i].indexOf("#")+1);
			temp = tempProductTxt[i].substring(0, tempProductTxt[i].indexOf("#"));

			tempProductType = temp.split("\\*");
			productTypes = new productType[tempProductType.length];

			String tempName;
			String tempWatt;
			String tempCapacity;

			for (int k = 0; k < tempProductType.length; k++){

				//System.err.println(k + " " +tempProductType[k]);
				
				tempName = tempProductType[k].substring(0, tempProductType[k].indexOf("&"));
				tempProductType[k] = tempProductType[k].substring(tempProductType[k].indexOf("&")+1);

				tempCapacity = tempProductType[k].substring(0, tempProductType[k].indexOf("&"));
				tempProductType[k] = tempProductType[k].substring(tempProductType[k].indexOf("&")+1);

				tempWatt = tempProductType[k].substring(0, tempProductType[k].length());

				productType p = new productType(0, tempName, tempCapacity, tempWatt);
				productTypes[k] = p;

			}

			/*System.out.println("**********************pro type***********");
			
			for (int l = 0; l < productTypes.length; l++){
				System.out.println(l + " " +  productTypes[l].getName() + " " 
						+ productTypes[l].getCapacity() + " " + productTypes[l].getWatt());
			}*/

			tempProductTxt[i] = tempProductTxt[i].substring(tempProductTxt[i].indexOf("#")+1);
			String tempDescriptionTR = tempProductTxt[i].substring(0, tempProductTxt[i].indexOf("#"));

			//System.out.println("tempDescriptionTR "+tempDescriptionTR);
			tempProductTxt[i] = tempProductTxt[i].substring(tempProductTxt[i].indexOf("#")+1);
			String tempDescriptionEN = tempProductTxt[i].substring(0, tempProductTxt[i].indexOf("#"));

			//System.out.println("EN: "+tempDescriptionEN);

			localizedDescriptions lcdTR = new localizedDescriptions(0, tempDescriptionTR, "tr");
			localizedDescriptions lcdEN = new localizedDescriptions(0, tempDescriptionEN, "en");

			catLocDesc = new localizedDescriptions[2];

			catLocDesc[0] = lcdTR;
			catLocDesc[1] = lcdEN;
			
			String sortD = tempProductTxt[i];
			
			productsObjectsFromTxt[i] = new products(name, imageURLs, pdfURLs, fuelTypes, productTypes, 
					catLocDesc, 0, catLocDesc, "asd");
		}
	}

	public void checkTxt(){
		
		
		System.out.println("**********Begin all products: ************");
		
		for (int i = 0; i < productsObjectsFromTxt.length; i++){
			System.out.println(productsObjectsFromTxt[i].getName());
			for (int j = 0; j < productsObjectsFromTxt[i].getImageURLs().length; j++){
				System.out.println(productsObjectsFromTxt[i].getImageURLs()[j]);
			}
			for (int j = 0; j < productsObjectsFromTxt[i].getPdfURLs().length; j++){
				System.out.println(productsObjectsFromTxt[i].getPdfURLs()[j]);
			}
			for (int j = 0; j < productsObjectsFromTxt[i].getFuelTypes().length; j++){
				System.out.println(productsObjectsFromTxt[i].getFuelTypes()[j]);
			}
			for (int j = 0; j < productsObjectsFromTxt[i].getProductTypes().length; j++){
				System.out.println(productsObjectsFromTxt[i].getProductTypes()[j].getName() 
						+ " "+ productsObjectsFromTxt[i].getProductTypes()[j].getWatt() 
						+ " " + productsObjectsFromTxt[i].getProductTypes()[j].getCapacity());
			}
			for (int j = 0; j < productsObjectsFromTxt[i].getCatLocDesc().length; j++){
				System.out.println(productsObjectsFromTxt[i].getCatLocDesc()[j].getLocDesc() 
						+ " "+ productsObjectsFromTxt[i].getCatLocDesc()[j].getRegion() );
			}
		}
		System.out.println("**********End all products: ************");
	}
}
