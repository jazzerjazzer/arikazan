package com.po.kazan;

public class products {

	private String name;
	private String[] imageURLs;
	private String[] pdfURLs;
	private String[] fuelTypes;
	private productType[] productTypes;
	private localizedDescriptions[] locDesc;
	
	private int catId;
	private localizedDescriptions[] catLocDesc;
	private String sortDescriptor;
	
	public products(String name, String[] imageURLs, String[] pdfURLs, 
			String[] fuelTypes, productType[] productTypes, localizedDescriptions[] locDesc, 
			int catId, localizedDescriptions[] catLocDesc, String sortDescriptor){
		
		this.name = name;
		this.imageURLs = imageURLs;
		this.pdfURLs = pdfURLs;
		this.fuelTypes = fuelTypes;
		this.productTypes = productTypes;
		this.locDesc = locDesc;
		
		this.catId = catId;
		this.catLocDesc = catLocDesc;
		this.sortDescriptor = sortDescriptor;
		
	}
	
	public products(){
		
	}
	
	public String[] getImageURLs() {
		return imageURLs;
	}

	public void setImageURLs(String[] imageURLs) {
		this.imageURLs = imageURLs;
	}

	public String[] getPdfURLs() {
		return pdfURLs;
	}

	public void setPdfURLs(String[] pdfURLs) {
		this.pdfURLs = pdfURLs;
	}

	public String[] getFuelTypes() {
		return fuelTypes;
	}

	public void setFuelTypes(String[] fuelTypes) {
		this.fuelTypes = fuelTypes;
	}

	public productType[] getProductTypes() {
		return productTypes;
	}

	public void setProductTypes(productType[] productTypes) {
		this.productTypes = productTypes;
	}

	public localizedDescriptions[] getLocDesc() {
		return locDesc;
	}

	public void setLocDesc(localizedDescriptions[] locDesc) {
		this.locDesc = locDesc;
	}

	public int getCatId() {
		return catId;
	}

	public void setCatId(int catId) {
		this.catId = catId;
	}

	public localizedDescriptions[] getCatLocDesc() {
		return catLocDesc;
	}

	public void setCatLocDesc(localizedDescriptions[] catLocDesc) {
		this.catLocDesc = catLocDesc;
	}

	public String getSortDescriptor() {
		return sortDescriptor;
	}

	public void setSortDescriptor(String sortDescriptor) {
		this.sortDescriptor = sortDescriptor;
	}
	
	public String getName(){
		return this.name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
}
