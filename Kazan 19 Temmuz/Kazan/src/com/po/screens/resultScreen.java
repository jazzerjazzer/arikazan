package com.po.screens;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Net.HttpRequest;
import com.badlogic.gdx.Net.HttpResponse;
import com.badlogic.gdx.Net.HttpResponseListener;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.po.kazan.City;
import com.po.kazan.MainProgram;
import com.po.kazan.products;

public class resultScreen implements Screen, InputProcessor {

	private MainProgram program;
	public OrthographicCamera camera;
	public SpriteBatch batch;

	private Texture bg, resultHeader_en, resultHeader_tr;
	private Texture fuelNeeded_en, fuelNeeded_tr;
	private Texture closestLocation_en, closestLocation_tr;
	private Texture selections_en, selections_tr;
	private Texture productLabel;

	private Texture selectedFuelCoal_en, selectedFuelCoal_tr;
	private Texture selectedFuelDiesel_en, selectedFuelDiesel_tr;
	private Texture selectedFuelFuelOil_en, selectedFuelFuelOil_tr;
	private Texture selectedFuelLPG_en, selectedFuelLPG_tr;
	private Texture selectedFuelNaturalGas_en, selectedFuelNaturalGas_tr;
	private Texture selectedFuelPellet_en, selectedFuelPellet_tr;
	private Texture selectedFuelWood_en, selectedFuelWood_tr;

	private Texture annuelFuelCoal_en, annuelFuelCoal_tr;
	private Texture annuelFuelDiesel_en, annuelFuelDiesel_tr;
	private Texture annuelFuelFuelOil_en, annuelFuelFuelOil_tr;
	private Texture annuelFuelLPG_en, annuelFuelLPG_tr;
	private Texture annuelFuelNaturalGas_en, annuelFuelNaturalGas_tr;
	private Texture annuelFuelPellet_en, annuelFuelPellet_tr;
	private Texture annuelFuelWood_en, annuelFuelWood_tr;

	public Texture suggestionsLabel_en, suggestionsLabel_tr;

	private int x, y;
	private Vector3 tap = new Vector3(0,0,0);
	// hourly cons x 6 x 30 x5
	private float yearlyCons = (float) 1.00;
	ArrayList<City> cities;
	ArrayList<products> suggestedProducts;

	private String locationFromMap;
	private String cityName;

	private boolean locFail = false;
	private City nearestCity;

	private Texture kalDigitTexture;
	private BitmapFont kalDigitFont;
	private Texture kalLetterTexture;
	private BitmapFont kalLetterFont;

	private Texture calibriSmallTexture;
	private BitmapFont calibriSmallBitmap;

	private Texture calibriBigTexture;
	private BitmapFont calibriBigBitmap;
	
	private Texture kalLitreTexture;
	private BitmapFont kalLitreBitmap;

	private FileHandle fhTemp;
	String latGPS = "";
	String lngGPS = "";
	boolean read = false, done = false;
	FileHandle preSave = Gdx.files.absolute("/data/data/com.po.kazan/files/save.txt");

	private DecimalFormat format = new DecimalFormat("###,###");

	public resultScreen(MainProgram program) {
		this.program = program;
	}

	@Override
	public void render(float delta) {
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.draw(bg,0,0);

		if(program.lang.equals("tr")){
			batch.draw(resultHeader_tr,0,1920 - 160);
			batch.draw(selections_tr,0,y+1920 - 160 - 80);

			if(program.c.getFuelType() == 2)
				batch.draw(selectedFuelWood_tr,0,y+1920 - 160 - 80 - 160);
			if(program.c.getFuelType() == 1)
				batch.draw(selectedFuelCoal_tr,0,y+1920 - 160 - 80 - 160);
			if(program.c.getFuelType() == 3)
				batch.draw(selectedFuelPellet_tr,0,y+1920 - 160 - 80 - 160);
			if(program.c.getFuelType() == 4)
				batch.draw(selectedFuelNaturalGas_tr,0,y+1920 - 160 - 80 - 160);
			if(program.c.getFuelType() == 5)
				batch.draw(selectedFuelLPG_tr,0,y+1920 - 160 - 80 - 160);
			if(program.c.getFuelType() == 6)
				batch.draw(selectedFuelDiesel_tr,0,y+1920 - 160 - 80 - 160);
			if(program.c.getFuelType() == 7)
				batch.draw(selectedFuelFuelOil_tr,0,y+1920 - 160 - 80 - 160);

			batch.draw(closestLocation_tr,0,y+1920 - 160 - 80 - 160 - 160);
			batch.draw(fuelNeeded_tr,0,y+1920 - 160 - 80 - 160 - 160 - 80);

			if(program.c.getFuelType() == 2)
				batch.draw(annuelFuelWood_tr,0,y+1920 - 160 - 80 - 160 - 160 - 80 - 320);
			if(program.c.getFuelType() == 1)
				batch.draw(annuelFuelCoal_tr,0,y+1920 - 160 - 80 - 160 - 160 - 80 - 320);
			if(program.c.getFuelType() == 3)
				batch.draw(annuelFuelPellet_tr,0,y+1920 - 160 - 80 - 160 - 160 - 80 - 320);
			if(program.c.getFuelType() == 4)
				batch.draw(annuelFuelNaturalGas_tr,0,y+1920 - 160 - 80 - 160 - 160 - 80 - 320);
			if(program.c.getFuelType() == 5)
				batch.draw(annuelFuelLPG_tr,0,y+1920 - 160 - 80 - 160 - 160 - 80 - 320);
			if(program.c.getFuelType() == 6)
				batch.draw(annuelFuelDiesel_tr,0,y+1920 - 160 - 80 - 160 - 160 - 80 - 320);
			if(program.c.getFuelType() == 7)
				batch.draw(annuelFuelFuelOil_tr,0,y+1920 - 160 - 80 - 160 - 160 - 80 - 320);

			batch.draw(suggestionsLabel_tr,0,y+1920 - 160 - 80 - 160 - 160 - 80 - 320 - 80);


		} else {
			batch.draw(resultHeader_en,0,1920 - 160);
			batch.draw(selections_en,0,y+1920 - 160 - 80);

			if(program.c.getFuelType() == 2)
				batch.draw(selectedFuelWood_en,0,y+1920 - 160 - 80 - 160);
			if(program.c.getFuelType() == 1)
				batch.draw(selectedFuelCoal_en,0,y+1920 - 160 - 80 - 160);
			if(program.c.getFuelType() == 3)
				batch.draw(selectedFuelPellet_en,0,y+1920 - 160 - 80 - 160);
			if(program.c.getFuelType() == 4)
				batch.draw(selectedFuelNaturalGas_en,0,y+1920 - 160 - 80 - 160);
			if(program.c.getFuelType() == 5)
				batch.draw(selectedFuelLPG_en,0,y+1920 - 160 - 80 - 160);
			if(program.c.getFuelType() == 6)
				batch.draw(selectedFuelDiesel_en,0,y+1920 - 160 - 80 - 160);
			if(program.c.getFuelType() == 7)
				batch.draw(selectedFuelFuelOil_en,0,y+1920 - 160 - 80 - 160);

			batch.draw(closestLocation_en,0,y+1920 - 160 - 80 - 160 - 160);
			batch.draw(fuelNeeded_en,0,y+1920 - 160 - 80 - 160 - 160 - 80);

			if(program.c.getFuelType() == 2)
				batch.draw(annuelFuelWood_en,0,y+1920 - 160 - 80 - 160 - 160 - 80 - 320);
			if(program.c.getFuelType() == 1 )
				batch.draw(annuelFuelCoal_en,0,y+1920 - 160 - 80 - 160 - 160 - 80 - 320);
			if(program.c.getFuelType() == 3)
				batch.draw(annuelFuelPellet_en,0,y+1920 - 160 - 80 - 160 - 160 - 80 - 320);
			if(program.c.getFuelType() == 4)
				batch.draw(annuelFuelNaturalGas_en,0,y+1920 - 160 - 80 - 160 - 160 - 80 - 320);
			if(program.c.getFuelType() == 5)
				batch.draw(annuelFuelLPG_en,0,y+1920 - 160 - 80 - 160 - 160 - 80 - 320);
			if(program.c.getFuelType() == 6)
				batch.draw(annuelFuelDiesel_en,0,y+1920 - 160 - 80 - 160 - 160 - 80 - 320);
			if(program.c.getFuelType() == 7)
				batch.draw(annuelFuelFuelOil_en,0,y+1920 - 160 - 80 - 160 - 160 - 80 - 320);

			batch.draw(suggestionsLabel_en,0,y+1920 - 160 - 80 - 160 - 160 - 80 - 320 - 80);

		}

		for(int i=0; i<suggestedProducts.size(); i++)
			batch.draw(productLabel,0,y+1920 - 160 - 80 - 160 - 160 - 80 - 320 - 80 - 500 - 200*i);


		while(yearlyCons < program.c.getHourlyCons()){
			yearlyCons += 0.1; break;
		}

		String consumption = format.format(yearlyCons*6*5*30);

		if (consumption.indexOf(".") != -1 && !program.lang.equals("tr"))
			consumption = consumption.substring(0, consumption.indexOf("."));
		

        FileHandle cityFile = Gdx.files.local("hwlocation.txt");
		String loc = cityFile.readString();

		System.out.println("Loc Result screen: " + loc);
		
		if(program.c.getFuelType() == 1 || program.c.getFuelType() == 2 || program.c.getFuelType() == 3){

			if((""+consumption).length() == 3)
				kalLetterFont.draw(batch, "kg", 550, 1040);

			if((""+consumption).length() == 4)
				kalLetterFont.draw(batch, "kg", 550, 1040);

			else if((""+consumption).length() == 5)
				kalLetterFont.draw(batch, "kg", 600, 1040);

			else if((""+consumption).length() == 6)
				kalLetterFont.draw(batch, "kg", 640, 1040);
		}
		else if (program.c.getFuelType() == 4 || program.c.getFuelType() == 5){
			if((""+consumption).length() == 3){
				kalLetterFont.draw(batch, "m", 550, 1045);
				calibriSmallBitmap.draw(batch, "3", 600, 1065);
			}else if((""+consumption).length() == 4){
				kalLetterFont.draw(batch, "m", 550, 1045);
				calibriSmallBitmap.draw(batch, "3", 600, 1065);
			}else if((""+consumption).length() == 5){
				kalLetterFont.draw(batch, "m", 600, 1045);
				calibriSmallBitmap.draw(batch, "3", 650, 1065);
			}else if((""+consumption).length() == 6){
				kalLetterFont.draw(batch, "m", 650, 1045);
				calibriSmallBitmap.draw(batch, "3", 700, 1065);
			}
		}else{
			if((""+consumption).length() == 3){
				kalLitreBitmap.draw(batch, "lt", 550, 1045);
			}else if((""+consumption).length() == 4){
				kalLitreBitmap.draw(batch, "lt", 550, 1045);
			}else if((""+consumption).length() == 5){
				kalLitreBitmap.draw(batch, "lt", 600, 1045);
			}else if((""+consumption).length() == 6){
				kalLitreBitmap.draw(batch, "lt", 650, 1045);
			}
		}

		kalDigitFont.draw(batch, consumption, 350, 1070);
		
		try{
			if (cityName.length() > 8){
				kalLetterFont.draw(batch, cityName.substring(0,9) + "...", 650, 1460);	
			} else {
				kalLetterFont.draw(batch, cityName, 650, 1460);	
			}
		}catch(Exception e){
			if(!program.toastShown){
				program.actionResolver.showLongToast("Lokasyon al›namad›!");
				program.toastShown = true;
			}
		}
		for(int i=0; i<suggestedProducts.size(); i++){
			calibriBigBitmap.draw(batch, suggestedProducts.get(i).getName() + " " + suggestedProducts.get(i).getProductTypes()[0].getName(), 50, 850 - 200*i);
			if(program.lang.equals("tr")){
				if(suggestedProducts.get(i).getLocDesc()[0].getLocDesc().length() > 50){
					calibriSmallBitmap.draw(batch, suggestedProducts.get(i).getLocDesc()[0].getLocDesc().substring(0,50), 50, 800 - 200*i);
					if(suggestedProducts.get(i).getLocDesc()[0].getLocDesc().length() > 100)
						calibriSmallBitmap.draw(batch, suggestedProducts.get(i).getLocDesc()[0].getLocDesc().substring(50,100), 50, 800 - 200*i - 40);
					if(suggestedProducts.get(i).getLocDesc()[0].getLocDesc().length() > 150)
						calibriSmallBitmap.draw(batch, suggestedProducts.get(i).getLocDesc()[0].getLocDesc().substring(100,150) + "...", 50, 800 - 200*i - 75);
				}
			}
			else{

				if(suggestedProducts.get(i).getLocDesc()[1].getLocDesc().length() > 50){
					calibriSmallBitmap.draw(batch, suggestedProducts.get(i).getLocDesc()[1].getLocDesc().substring(0,50), 50, 800 - 200*i);
					if(suggestedProducts.get(i).getLocDesc()[1].getLocDesc().length() > 100)
						calibriSmallBitmap.draw(batch, suggestedProducts.get(i).getLocDesc()[1].getLocDesc().substring(50,100), 50, 800 - 200*i - 40);
					if(suggestedProducts.get(i).getLocDesc()[1].getLocDesc().length() > 150)
						calibriSmallBitmap.draw(batch, suggestedProducts.get(i).getLocDesc()[1].getLocDesc().substring(100,150)+ "...", 50, 800 - 200*i - 75);

				}
			}
		}


		fhTemp = Gdx.files.absolute("/data/data/com.po.kazan/files/mapLocation.txt");

		try{

			if (!fhTemp.readString().contains("?") && !done){

				read = true;
				latGPS = fhTemp.readString().substring(0,fhTemp.readString().indexOf(" "));
				lngGPS = fhTemp.readString().substring(fhTemp.readString().indexOf(" ") + 1);
			}

		}catch(Exception e){

			System.out.println(e);
		}
		if(read){

			System.out.println("latgps: "+latGPS + " lnggps " + lngGPS);
			double latV = Double.parseDouble(latGPS);
			double lngV = Double.parseDouble(lngGPS);

			findLocation(latV, lngV);
			read = false; done = true;

		}
		batch.end();
		checkTouch();
	}

	public void checkTouch(){

		if(Gdx.input.justTouched()){
			tap.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(tap);

			int x = (int) tap.x;
			int y = (int) tap.y;

			y -= this.y;
			if (Gdx.input.isKeyPressed(Keys.BACK)){

				program.setScreen(new fuelTypeScreen(program));
			}

			//System.out.println(x + " " + y);

			// geri
			if(x > 0 && x < 260 && y > 1800 && y < 1880){
				program.setScreen(new fuelTypeScreen(program));
			}

			// fuel type
			if(x > 0 && x < 1080 && y > 1520 && y < 1680){
				program.setScreen(new fuelTypeScreen(program));
			}

			// map
			if(x > 0 && x < 1080 && y > 1360 && y < 1500){

				program.resultLocationAcquired= true;
				program.resultLocationFinal = false;

				FileHandle fh = Gdx.files.absolute("/data/data/com.po.kazan/files/mapLocation.txt");
				done = false;
				read = false;
				program.actionResolver.getLocationFromMap();

			}

			//bitti
			if(x > 885 && x < 1030 && y > 1800 && y < 1875){

				int houseType = program.c.getHouseType();
				int storeys = program.c.getStoreys();
				int area = program.c.getArea();
				int height = program.c.getHeight();
				int manto = program.c.getManto();
				int nearAway = program.c.getNearAway();
				int window = program.c.getWindowType();
				int windowHeight = program.c.getWindow();
				int heat = program.c.getHeat();
				int sysSize = program.c.getSysSize();
				int fuelType = program.c.getFuelType();

				//while (preSave.readString().equals(""));

				String date = new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());

				saveFunction(houseType + "#" + storeys + "#" + area + "#" + height + "#" + manto + "#" + 
						nearAway + "#" + window + "#" + windowHeight + "#" + heat + "#" + sysSize + "#" + fuelType + "#" + cityName + " - " + date + "\n");

				program.setScreen(new mainScreen(program));
			}

			// save
			if(x > 600 && x < 840 && y > 1800 && y < 1875){

				program.actionResolver.createSaveDialog();

				int houseType = program.c.getHouseType();
				int storeys = program.c.getStoreys();
				int area = program.c.getArea();
				int height = program.c.getHeight();
				int manto = program.c.getManto();
				int nearAway = program.c.getNearAway();
				int window = program.c.getWindowType();
				int windowHeight = program.c.getWindow();
				int heat = program.c.getHeat();
				int sysSize = program.c.getSysSize();
				int fuelType = program.c.getFuelType();

				//while (preSave.readString().equals(""));

				saveFunction(houseType + "#" + storeys + "#" + area + "#" + height + "#" + manto + "#" + nearAway + "#" + window + "#" + windowHeight + "#" + heat + "#" + sysSize + "#" + fuelType);
				//saveFunction(houseType + "#" + storeys + "#" + area + "#" + height + "#" + manto + "#" + nearAway + "#" + window + "#" + windowHeight + "#" + heat + "#" + sysSize + "#" + fuelType);

			}

			if(x > 0 && x < 1080 && y < 1275 && y > 985){
				program.setScreen(new capacityScreen(program));
			}

			if(x > 0 && x < 1080 && y < 800 && y > 0){
				if(suggestedProducts.size() >= 1 && y < 800 && y > 600)
					program.actionResolver.displayPDF(suggestedProducts.get(0).getPdfURLs()[0]);
				if(suggestedProducts.size() >= 2 && y < 600 && y > 400)
					program.actionResolver.displayPDF(suggestedProducts.get(1).getPdfURLs()[0]);
				if(suggestedProducts.size() >= 3 && y < 400 && y > 200)
					program.actionResolver.displayPDF(suggestedProducts.get(2).getPdfURLs()[0]);
				if(suggestedProducts.size() >= 4 && y < 200 && y > 0)
					program.actionResolver.displayPDF(suggestedProducts.get(3).getPdfURLs()[0]);
			}

		}
	}

	private void saveFunction(String string) {

		FileHandle saveFile = Gdx.files.absolute("/data/data/com.po.kazan/files/saveFinal.txt");

		saveFile.writeString(string, true);

	}

	@Override
	public void show() {
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1080, 1920);
		batch.setProjectionMatrix(camera.combined);

		Gdx.input.setInputProcessor(this);
		Gdx.input.setCatchBackKey(true);

		bg = program.textures.bg;
		resultHeader_tr = program.textures.resultHeader_tr;
		resultHeader_en = program.textures.resultHeader_en;
		fuelNeeded_tr = program.textures.fuelNeeded_tr;
		fuelNeeded_en = program.textures.fuelNeeded_en;
		closestLocation_tr = program.textures.closestLocation_tr;
		closestLocation_en = program.textures.closestLocation_en;
		suggestionsLabel_tr = program.textures.suggestionsLabel_tr;
		suggestionsLabel_en = program.textures.suggestionsLabel_en;
		selections_tr = program.textures.selections_tr;
		selections_en = program.textures.selections_en;

		selectedFuelCoal_en = program.textures.selectedFuelCoal_en;
		selectedFuelCoal_tr = program.textures.selectedFuelCoal_tr;
		selectedFuelDiesel_en = program.textures.selectedFuelDiesel_en;
		selectedFuelDiesel_tr = program.textures.selectedFuelDiesel_tr;
		selectedFuelFuelOil_en = program.textures.selectedFuelFuelOil_en;
		selectedFuelFuelOil_tr = program.textures.selectedFuelFuelOil_tr;
		selectedFuelLPG_en = program.textures.selectedFuelLPG_en;
		selectedFuelLPG_tr = program.textures.selectedFuelLPG_tr;
		selectedFuelNaturalGas_en = program.textures.selectedFuelNaturalGas_en;
		selectedFuelNaturalGas_tr = program.textures.selectedFuelNaturalGas_tr;
		selectedFuelPellet_en = program.textures.selectedFuelPellet_en;
		selectedFuelPellet_tr = program.textures.selectedFuelPellet_tr;
		selectedFuelWood_en = program.textures.selectedFuelWood_en;
		selectedFuelWood_tr = program.textures.selectedFuelWood_tr;

		annuelFuelCoal_en = program.textures.annuelFuelCoal_en;
		annuelFuelCoal_tr = program.textures.annuelFuelCoal_tr;
		annuelFuelDiesel_en = program.textures.annuelFuelDiesel_en;
		annuelFuelDiesel_tr = program.textures.annuelFuelDiesel_tr;
		annuelFuelFuelOil_en = program.textures.annuelFuelFuelOil_en;
		annuelFuelFuelOil_tr = program.textures.annuelFuelFuelOil_tr;
		annuelFuelLPG_en = program.textures.annuelFuelLPG_en;
		annuelFuelLPG_tr = program.textures.annuelFuelLPG_tr;
		annuelFuelNaturalGas_en = program.textures.annuelFuelNaturalGas_en;
		annuelFuelNaturalGas_tr = program.textures.annuelFuelNaturalGas_tr;
		annuelFuelPellet_en = program.textures.annuelFuelPellet_en;
		annuelFuelPellet_tr = program.textures.annuelFuelPellet_tr;
		annuelFuelWood_en = program.textures.annuelFuelWood_en;
		annuelFuelWood_tr = program.textures.annuelFuelWood_tr;

		productLabel = program.textures.productLabel;

		x = 0; y = 0;
		cities = new ArrayList<City>();
		suggestedProducts = new ArrayList<products>();

		kalDigitTexture = new Texture(Gdx.files.internal("kalinga_digit_0.png"));
		kalDigitTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear); 
		kalDigitFont = new BitmapFont(Gdx.files.internal("kalinga_digit.fnt"), new TextureRegion(kalDigitTexture), false);

		kalLetterTexture = new Texture(Gdx.files.internal("kal_letter_0.png"));
		kalLetterTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear); 
		kalLetterFont = new BitmapFont(Gdx.files.internal("kal_letter.fnt"), new TextureRegion(kalLetterTexture), false);

		calibriSmallTexture = new Texture(Gdx.files.internal("cal_small_0.png"));
		calibriSmallTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear); 
		calibriSmallBitmap = new BitmapFont(Gdx.files.internal("cal_small.fnt"), new TextureRegion(calibriSmallTexture), false);

		calibriBigTexture = new Texture(Gdx.files.internal("cal_big_0.png"));
		calibriBigTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear); 
		calibriBigBitmap = new BitmapFont(Gdx.files.internal("cal_big.fnt"), new TextureRegion(calibriBigTexture), false);

		kalLitreTexture = new Texture(Gdx.files.internal("kalLitre_0.png"));
		kalLitreTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear); 
		kalLitreBitmap = new BitmapFont(Gdx.files.internal("kalLitre.fnt"), new TextureRegion(kalLitreTexture), false);
		
		for(int i=0; i<program.rt.productsObjectsFromTxt.length; i++){

			int kw2kcal = 860;
			//String productFuel = program.rt.productsObjectsFromTxt[i].getSortDescriptor();
			String productFuel = program.rt.productsObjectsFromTxt[i].getFuelTypes()[0];
			int capacity = (int) ((program.c.fuelTypeCal[program.c.getFuelType()]*program.c.getHourlyCons()*6*5*30)*program.c.fuelTypeEff[program.c.getFuelType()]);
			capacity = capacity/kw2kcal;

			if((productFuel.equals("kFUEL_TYPE_NATURAL_GAS") || productFuel.equals("kFUEL_TYPE_LPG") ||productFuel.equals("kFUEL_TYPE_DIESEL") ||productFuel.equals("kFUEL_TYPE_FUEL_OIL")) && (program.c.getFuelType() == 4 || program.c.getFuelType() == 5 ||program.c.getFuelType() == 6 ||program.c.getFuelType() == 7)){
				int pCapacity;
				for(int j=0; j<program.rt.productsObjectsFromTxt[i].getProductTypes().length; j++){
					pCapacity = (int)Double.parseDouble(program.rt.productsObjectsFromTxt[i].getProductTypes()[j].getCapacity());
					if(pCapacity > capacity){
						suggestedProducts.add(program.rt.productsObjectsFromTxt[i]);
						break;
					}
				}
			} else if((productFuel.equals("kFUEL_TYPE_WOOD") || productFuel.equals("kFUEL_TYPE_PELLET") ||productFuel.equals("kFUEL_TYPE_COAL")) && (program.c.getFuelType() == 1 || program.c.getFuelType() == 2 ||program.c.getFuelType() == 3)){
				int pCapacity;
				for(int j=0; j<program.rt.productsObjectsFromTxt[i].getProductTypes().length; j++){
					pCapacity = (int)Double.parseDouble(program.rt.productsObjectsFromTxt[i].getProductTypes()[j].getCapacity());
					if(pCapacity > capacity){
						if(program.rt.productsObjectsFromTxt[i].getName().equals("OG") && program.c.getFuelType() == 2)
							suggestedProducts.add(program.rt.productsObjectsFromTxt[i]);
						else if(program.rt.productsObjectsFromTxt[i].getName().equals("KKM") && program.c.getFuelType() == 1)
							suggestedProducts.add(program.rt.productsObjectsFromTxt[i]);
						else if((program.rt.productsObjectsFromTxt[i].getName().equals("ECO-Mini") || program.rt.productsObjectsFromTxt[i].getName().equals("CARIA")) && program.c.getFuelType() == 3)
							suggestedProducts.add(program.rt.productsObjectsFromTxt[i]);
						break;
					}
				}
			}


		}

		FileHandle cityFile = Gdx.files.internal("cities.txt");
		String cityFileContents = cityFile.readString();

		String lines[] = cityFileContents.split("\\r?\\n");

		for(int i=0; i<lines.length; i++){
			double m[] = new double[12];
			String[] line = lines[i].split("#");

			for(int j=0; j<12; j++){
				try{
					m[j] = Double.parseDouble(line[j+3]);
				} catch(Exception e){

				}
			}

			City c = new City(line[2], Double.parseDouble(line[0]), Double.parseDouble(line[1]), m);
			cities.add(c);
		}

		FileHandle fh = Gdx.files.absolute("/data/data/com.po.kazan/files/hwlocation.txt");

		String latGPS = fh.readString().substring(0,fh.readString().indexOf(" "));
		String lngGPS = fh.readString().substring(fh.readString().indexOf(" ") + 1);

		double latV = Double.parseDouble(latGPS);
		double lngV = Double.parseDouble(lngGPS);

		if(latV == -1 || lngV == -1){
			locFail = true;
		}

		if(!locFail)
			findLocation(latV, lngV);


	}

	public void findLocation(double latV, double lngV){

		/*
		FileHandle cityFile = Gdx.files.internal("cities.txt");
		String cityFileContents = cityFile.readString();

		String lines[] = cityFileContents.split("\\r?\\n");
		cities = new ArrayList<City>();

		for(int i=0; i<lines.length; i++){
			double m[] = new double[12];
			String[] line = lines[i].split("#");

			for(int j=0; j<12; j++){
				try{
					m[j] = Double.parseDouble(line[j+3]);
				} catch(Exception e){
					System.out.println(line[j+3]);
				}
			}

			City c = new City(line[2], Double.parseDouble(line[0]), Double.parseDouble(line[1]), m);
			cities.add(c);
		}
		 */

		ArrayList<City> near = new ArrayList<City>();
		int counter = 1;

		while(near.size() == 0){
			for (int i = 0; i < cities.size(); i++){
				if (!(cities.get(i).lat - latV > 0.15*counter || cities.get(i).lat - latV < -0.15*counter) && !(cities.get(i).lng - lngV > 0.15*counter || cities.get(i).lng - lngV < -0.15*counter)){
					near.add(cities.get(i));
				}
			}
			counter++;
			System.out.println("near size: " + near.size());
		}
		System.out.println("nearest city: " + near.get(0).name);
		float avHeat = (float) 0.00;

		for(int i=0; i<3; i++)
			avHeat += near.get(0).month[i];

		for(int i=9; i<12; i++)
			avHeat += near.get(0).month[i];

		avHeat = (float) (avHeat / 6.00);

		program.c.setAvHeat(avHeat);
		program.c.calculate();

		cityName = near.get(0).name;

	}

	@Override
	public boolean keyDown(int keycode) {
		if(keycode == Keys.BACK){
			program.setScreen(new fuelTypeScreen(program));
		}
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

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
		// TODO Auto-generated method stub

	}
}