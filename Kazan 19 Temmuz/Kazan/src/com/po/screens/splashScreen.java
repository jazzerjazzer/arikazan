package com.po.screens;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.json.JSONObject;

import sun.net.www.http.HttpClient;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.po.kazan.MainProgram;
import com.po.kazan.ReadTxt;
import com.po.kazan.centralContacts;
import com.po.kazan.contacts;
import com.po.kazan.localizedDescriptions;
import com.po.kazan.productType;
import com.po.kazan.products;

public class splashScreen implements Screen {

	private MainProgram program;
	public OrthographicCamera camera;
	public SpriteBatch batch;
	private Texture bg;
	private boolean everythingLoaded = false;
	private boolean splash = true, bgLoaded = false, rendered = false;
	private FileHandle centralContactText, contactsText, productsText, file, hardwareLocationFile, 
		alan, mapLocation,save, saveFinal, unitPrice, unitPriceWood, unitPricePellet, unitPriceCoal, unitPriceNaturalGas,
		unitPriceLpg, unitPriceFuelOil, unitPriceDiesel, storey;

	public splashScreen(MainProgram mainProgram) {
		this.program = mainProgram;
	}

	@Override
	public void render(float delta) {
		if (!rendered){
			rendered = true;
		} else {
			if(splash && bgLoaded)
				loadTextures();
			if (everythingLoaded)
				program.setScreen(new mainScreen(program));
		}

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		camera.update();

		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.draw(bg,0,0);
		batch.end();
	}

	private void loadTextures() {

		program.textures.load();

		boolean exists = Gdx.files.local("myConfigFile.txt").exists();

		hardwareLocationFile = Gdx.files.local("hwlocation.txt");
		hardwareLocationFile.writeString("*", false); 

		alan = Gdx.files.local("alan.txt");
		alan.writeString("&", false);
		
		unitPrice = Gdx.files.local("unitPrice.txt");
		unitPrice.writeString("%", false);
		
		mapLocation = Gdx.files.local("mapLocation.txt");
		mapLocation.writeString("?", false);
		
		alan = Gdx.files.local("storey.txt");
		alan.writeString("=", false);

		
		program.actionResolver.getUserLocationFromHardware();


		if(!exists){  // config dosyasý yoksa oluþtur, json'larý indir, text dosyasýna yaz. 

			file = Gdx.files.local("myConfigFile.txt");
			file.writeString("create", true);
			
			save = Gdx.files.local("save.txt");
			save.writeString("=", true);

			saveFinal  = Gdx.files.local("saveFinal.txt");
			saveFinal.writeString("", true);
			
			unitPriceWood = Gdx.files.local("unitPriceWood.txt");
			unitPricePellet= Gdx.files.local("unitPricePellet.txt");
			unitPriceCoal = Gdx.files.local("unitPriceCoal.txt");
			unitPriceNaturalGas = Gdx.files.local("unitPriceNaturalGas.txt");
			unitPriceLpg = Gdx.files.local("unitPriceLpg.txt");
			unitPriceFuelOil = Gdx.files.local("unitPriceFuelOil.txt");
			unitPriceDiesel = Gdx.files.local("unitPriceDiesel.txt");
			
			unitPriceWood.writeString("0", true);
			unitPricePellet.writeString("0", true);
			unitPriceCoal.writeString("0", true);
			unitPriceNaturalGas.writeString("0", true);
			unitPriceLpg.writeString("0", true);
			unitPriceFuelOil.writeString("0", true);
			unitPriceDiesel.writeString("0", true);
			
			centralContactText = Gdx.files.local("centralContact.txt");
			contactsText = Gdx.files.local("contacts.txt");
			productsText = Gdx.files.local("products.txt");

			try {
				program.json.getProducts();
				program.json.getContacts();
				program.json.getCentralContacts();

				centralContacts tempCentralContact = new centralContacts();
				contacts tempContact = new contacts();
				products tempProduct = new products();

				for (int i = 0; i < program.json.centralContacts.size(); i++){
					tempCentralContact = program.json.centralContacts.get(i);
					centralContactText.writeString(tempCentralContact.getName() + "#" +tempCentralContact.getEmail() 
							+ "#" + tempCentralContact.getWebsite() + "#" + tempCentralContact.getPhone() + "#" 
							+ tempCentralContact.getFax() + "#" +tempCentralContact.getAdress() + "#" + tempCentralContact.getCity()
							+ "#" + tempCentralContact.getCountry() + "#" + tempCentralContact.getLatitude() + "#" + tempCentralContact.getLongitude()
							+ "\n", true);

				}

				for (int i = 0; i < program.json.contacts.size(); i++){

					tempContact = program.json.contacts.get(i);
					contactsText.writeString(tempContact.getName() +"#" + tempContact.getPhone() + "#"
							+ tempContact.getPhone2() + "#" + tempContact.getWebsite() 
							+ "#" + tempContact.getEmail() + "#" + tempContact.getAdress() + "#" 
							+ tempContact.getCity() + "#" + tempContact.getCountry() + " \n", true);
				}

				for (int i = 0; i < program.json.products.size(); i++){

					tempProduct = program.json.products.get(i);
					productsText.writeString(tempProduct.getName() + "#", true);

					for (int j = 0; j < tempProduct.getImageURLs().length; j++){

						productsText.writeString(tempProduct.getImageURLs()[j]+ "*", true);
					}
					productsText.writeString("#",true);
					for (int j = 0; j < tempProduct.getPdfURLs().length; j++){

						productsText.writeString(tempProduct.getPdfURLs()[j]+ "*", true);
					}
					productsText.writeString("#",true);

					for (int j = 0; j < tempProduct.getFuelTypes().length; j++){

						productsText.writeString(tempProduct.getFuelTypes()[j]+ "*", true);
					}
					productsText.writeString("#",true);

					for (int j = 0; j < tempProduct.getProductTypes().length; j++){

						productsText.writeString(
								tempProduct.getProductTypes()[j].getName()+ "&"
										+ tempProduct.getProductTypes()[j].getCapacity() + "&"
										+ tempProduct.getProductTypes()[j].getWatt() + "*", true);

					}
					productsText.writeString("#",true);

					for(int p=0; p<tempProduct.getCatLocDesc().length; p++)
						if(tempProduct.getCatLocDesc()[p].getRegion().toLowerCase().equals("tr"))
							productsText.writeString(tempProduct.getCatLocDesc()[p].getLocDesc(), true);

					productsText.writeString("#",true);


					for(int p=0; p<tempProduct.getCatLocDesc().length; p++)
						if(tempProduct.getCatLocDesc()[p].getRegion().toLowerCase().equals("en"))
							productsText.writeString(tempProduct.getCatLocDesc()[p].getLocDesc(), true);

					productsText.writeString("#",true);

					productsText.writeString("\n", true);

				}
				String disclaimerTR = "Bu uygulamanÝn ile elde edilen sonular yaklaßÝk deÛerler olarak kabul edilmeli ve bir "
						+ "ŸrŸn almadan šnce uzman gšrŸßŸ alÝnmalÝdÝr. Sadece bu uygulama sonularÝndan hareketle ŸrŸn satÝn alÝmÝndan"
						+ " kaynaklanan sorunlar kullanÝcÝ sorumluluÛundadÝr ve ArÝkazan A.Þ. sorumlu tutulamaz.";
				String disclaimerEN = "Please keep in mind that results obtained by this application are estimated values. "
						+ "An expert evaluation is essential before buying any products. "
						+ "Making a purchase solely on relying this applicationÕs results is userÕs responsibility and "
						+ "in such a case, user accepts Arikazan A.S shall not be kept responsible in any way.";
				if(!program.disclaimerIsShown)
				{	
					if(program.lang.equals("tr"))
						program.actionResolver.displayDisclaimer(disclaimerTR, "Tamam", "Üptal");
					else
						program.actionResolver.displayDisclaimer(disclaimerEN, "OK", "Cancel");	
					program.disclaimerIsShown = true;
				}
				
				program.rt = new ReadTxt();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Json getiremedik :(");
			}
			
			
		}

		else if(exists){

			program.rt = new ReadTxt();
		}

		splash = false;
		everythingLoaded = true;
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1080, 1920);
		batch.setProjectionMatrix(camera.combined);

		bg = new Texture(Gdx.files.internal("main/splash.png"));

		bgLoaded = true;
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		bg.dispose();
	}
}
