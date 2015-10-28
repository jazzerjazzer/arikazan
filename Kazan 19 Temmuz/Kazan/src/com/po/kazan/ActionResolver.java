package com.po.kazan;

import java.io.IOException;

public interface ActionResolver {
	
	public void showAlertBox(String alertBoxTitle, String alertBoxMessage, String alertBoxButtonText);
    public void openUri(String uri);
    public void showLongToast(CharSequence message);
    
    public void displayPDF(String localUri);
    public void openMap(double lat, double lng, String title, String description);
    
    public String getDisplayLanguage();
    public String getLocationFromMap();
    public String getUserLocationFromHardware();
    public String createAlanDialog();
    public String getValue();
    public String createSaveDialog();
    public String createUnitPriceDialog(String message, String fileName);
    public String createStoreyDialog();
    public void writeStoreyToFile(String data) throws IOException;
    public void displayDisclaimer(String disclaimer, String positive, String negative);
    public void displayGPSAlert();
}