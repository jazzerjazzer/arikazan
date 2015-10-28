package com.po.screens;

import java.text.DecimalFormat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.po.kazan.MainProgram;

public class capacityScreen implements Screen, InputProcessor {

	private MainProgram program;
	public OrthographicCamera camera;
	public SpriteBatch batch;

	private Texture bg, capacityHeader_en, capacityHeader_tr;
	private Texture capacityHourly_en, capacityHourly_tr;
	private Texture capacityLabel_en, capacityLabel_tr;
	private Texture capacityYearly_en, capacityYearly_tr;
	private Texture capacityConsumptionHeader_en, capacityConsumptionHeader_tr;

	private Texture capAnnualFuelCoal_en, capAnnualFuelCoal_tr;
	private Texture capAnnualFuelDiesel_en, capAnnualFuelDiesel_tr;
	private Texture capAnnualFuelFuelOil_en, capAnnualFuelFuelOil_tr;
	private Texture capAnnualFuelLPG_en, capAnnualFuelLPG_tr;
	private Texture capAnnualFuelNaturalGas_en, capAnnualFuelNaturalGas_tr;
	private Texture capAnnualFuelPellet_en, capAnnualFuelPellet_tr;
	private Texture capAnnualFuelWood_en, capAnnualFuelWood_tr;

	private Texture capTotalCoal_en, capTotalCoal_tr;
	private Texture capTotalDiesel_en, capTotalDiesel_tr;
	private Texture capTotalFuelOil_en, capTotalFuelOil_tr;
	private Texture capTotalLPG_en, capTotalLPG_tr;
	private Texture capTotalNaturalGas_en, capTotalNaturalGas_tr;
	private Texture capTotalPellet_en, capTotalPellet_tr;
	private Texture capTotalWood_en, capTotalWood_tr;

	private Texture unitValue_en, unitValue_tr;
	private boolean showUnitValue = false;

	private int x, y;
	private Vector3 tap = new Vector3(0,0,0);
	int kw2kcal = 860;
	int houryWatt = 0;
	int yearlyWatt = 0;

	private Texture calibriBigTexture;
	private BitmapFont calibriBigBitmap;

	private Texture calibriSmallTexture;
	private BitmapFont calibriSmallBitmap;


	int hourlyCons, yearlyCons, yearlyFuelCons, calculatedCons, userDefinePrice;
	int finalPrice;
	private DecimalFormat format = new DecimalFormat("###,###");

	public capacityScreen(MainProgram program) {
		this.program = program;
	}

	@Override
	public void render(float delta) {
		batch.setProjectionMatrix(camera.combined);
		batch.begin();

		batch.draw(bg,0,0);

		if(program.lang.equals("tr")){
			batch.draw(capacityHeader_tr,0,y+1920-160);
			batch.draw(capacityHourly_tr,0,y+1920-160-80);
			batch.draw(capacityLabel_tr,0,y+1920-160-80-320);
			batch.draw(capacityYearly_tr,0,y+1920-160-80-320-80);
			batch.draw(capacityLabel_tr,0,y+1920-160-80-320-80-320);
			batch.draw(capacityConsumptionHeader_tr,0,y+1920-160-80-320-80-320-80);

			if(program.c.getFuelType() == 2)
				batch.draw(capAnnualFuelWood_tr,0,y+1920-160-80-320-80-320-80-320);
			if(program.c.getFuelType() == 1)
				batch.draw(capAnnualFuelCoal_tr,0,y+1920-160-80-320-80-320-80-320);
			if(program.c.getFuelType() == 3)
				batch.draw(capAnnualFuelPellet_tr,0,y+1920-160-80-320-80-320-80-320);
			if(program.c.getFuelType() == 4)
				batch.draw(capAnnualFuelNaturalGas_tr,0,y+1920-160-80-320-80-320-80-320);
			if(program.c.getFuelType() == 5)
				batch.draw(capAnnualFuelLPG_tr,0,y+1920-160-80-320-80-320-80-320);
			if(program.c.getFuelType() == 6)
				batch.draw(capAnnualFuelDiesel_tr,0,y+1920-160-80-320-80-320-80-320);
			if(program.c.getFuelType() == 7)
				batch.draw(capAnnualFuelFuelOil_tr,0,y+1920-160-80-320-80-320-80-320);

			if(program.c.getFuelType() == 2)
				batch.draw(capTotalWood_tr,0,y+1920-160-80-320-80-320-80-320-330);
			if(program.c.getFuelType() == 1)
				batch.draw(capTotalCoal_tr,0,y+1920-160-80-320-80-320-80-320-330);
			if(program.c.getFuelType() == 3)
				batch.draw(capTotalPellet_tr,0,y+1920-160-80-320-80-320-80-320-330);
			if(program.c.getFuelType() == 4)
				batch.draw(capTotalNaturalGas_tr,0,y+1920-160-80-320-80-320-80-320-330);
			if(program.c.getFuelType() == 5)
				batch.draw(capTotalLPG_tr,0,y+1920-160-80-320-80-320-80-320-330);
			if(program.c.getFuelType() == 6)
				batch.draw(capTotalDiesel_tr,0,y+1920-160-80-320-80-320-80-320-330);
			if(program.c.getFuelType() == 7)
				batch.draw(capTotalFuelOil_tr,0,y+1920-160-80-320-80-320-80-320-330);

			calibriBigBitmap.draw(batch, ""+format.format((hourlyCons/860)) + " kW/Saat", 370, 1550);

			calibriSmallBitmap.draw(batch, "("+format.format(hourlyCons)  + " kCal/Saat)", 390, 1450);

			calibriBigBitmap.draw(batch, ""+format.format((yearlyCons/860)) + " kW/YÝl", 320, 1150);
			calibriSmallBitmap.draw(batch, "("+format.format(yearlyCons) + " kCal/YÝl)", 370, 1050);

			if (program.c.getFuelType() == 1 || program.c.getFuelType() == 2 || program.c.getFuelType() == 3)
				calibriBigBitmap.draw(batch, ""+format.format(yearlyFuelCons) + " Kg", 350, 680);
			else{ 
				calibriBigBitmap.draw(batch, ""+format.format(yearlyFuelCons), 400, 680);
				calibriBigBitmap.draw(batch, "m", 600, 680);
				calibriSmallBitmap.draw(batch, "3", 650, 700);

			}

			FileHandle fh = null;

			if(program.c.getFuelType() == 1)
				fh = Gdx.files.absolute("/data/data/com.po.kazan/files/unitPriceCoal.txt");

			if(program.c.getFuelType() == 2)
				fh = Gdx.files.absolute("/data/data/com.po.kazan/files/unitPriceWood.txt");

			if(program.c.getFuelType() == 3)
				fh = Gdx.files.absolute("/data/data/com.po.kazan/files/unitPricePellet.txt");

			if(program.c.getFuelType() == 4)
				fh = Gdx.files.absolute("/data/data/com.po.kazan/files/unitPriceNaturalGas.txt");

			if(program.c.getFuelType() == 5)
				fh = Gdx.files.absolute("/data/data/com.po.kazan/files/unitPriceLpg.txt");

			if(program.c.getFuelType() == 6)
				fh = Gdx.files.absolute("/data/data/com.po.kazan/files/unitPriceDiesel.txt");

			if(program.c.getFuelType() == 7)
				fh = Gdx.files.absolute("/data/data/com.po.kazan/files/unitPriceFuelOil.txt");


			String s = fh.readString();
			double unitPrice = 0;
			System.out.println("PARSE DOUBLE: " + unitPrice);
			if(!s.equals("%"))
				unitPrice = Double.parseDouble(s);

			finalPrice = (int)(unitPrice*yearlyFuelCons);
			calibriBigBitmap.draw(batch, ""+format.format(finalPrice) + " TL", 440, 350);

		} else {
			batch.draw(capacityHeader_en,0,y+1920-160);
			batch.draw(capacityHourly_en,0,y+1920-160-80);
			batch.draw(capacityLabel_en,0,y+1920-160-80-320);
			batch.draw(capacityYearly_en,0,y+1920-160-80-320-80);
			batch.draw(capacityLabel_en,0,y+1920-160-80-320-80-320);
			batch.draw(capacityConsumptionHeader_en,0,y+1920-160-80-320-80-320-80);

			if(program.c.getFuelType() == 2)
				batch.draw(capAnnualFuelWood_en,0,y+1920-160-80-320-80-320-80-320);
			if(program.c.getFuelType() == 1)
				batch.draw(capAnnualFuelCoal_en,0,y+1920-160-80-320-80-320-80-320);
			if(program.c.getFuelType() == 3)
				batch.draw(capAnnualFuelPellet_en,0,y+1920-160-80-320-80-320-80-320);
			if(program.c.getFuelType() == 4)
				batch.draw(capAnnualFuelNaturalGas_en,0,y+1920-160-80-320-80-320-80-320);
			if(program.c.getFuelType() == 5)
				batch.draw(capAnnualFuelLPG_en,0,y+1920-160-80-320-80-320-80-320);
			if(program.c.getFuelType() == 6)
				batch.draw(capAnnualFuelDiesel_en,0,y+1920-160-80-320-80-320-80-320);
			if(program.c.getFuelType() == 7)
				batch.draw(capAnnualFuelFuelOil_en,0,y+1920-160-80-320-80-320-80-320);

			if(program.c.getFuelType() == 2)
				batch.draw(capTotalWood_en,0,y+1920-160-80-320-80-320-80-320-330);
			if(program.c.getFuelType() == 1)
				batch.draw(capTotalCoal_en,0,y+1920-160-80-320-80-320-80-320-330);
			if(program.c.getFuelType() == 3)
				batch.draw(capTotalPellet_en,0,y+1920-160-80-320-80-320-80-320-330);
			if(program.c.getFuelType() == 4)
				batch.draw(capTotalNaturalGas_en,0,y+1920-160-80-320-80-320-80-320-330);
			if(program.c.getFuelType() == 5)
				batch.draw(capTotalLPG_en,0,y+1920-160-80-320-80-320-80-320-330);
			if(program.c.getFuelType() == 6)
				batch.draw(capTotalDiesel_en,0,y+1920-160-80-320-80-320-80-320-330);
			if(program.c.getFuelType() == 7)
				batch.draw(capTotalFuelOil_en,0,y+1920-160-80-320-80-320-80-320-330);



			calibriBigBitmap.draw(batch, ""+format.format((hourlyCons/860)) + " kW/Hour", 370, 1550);

			calibriSmallBitmap.draw(batch, "("+format.format(hourlyCons)  + " kCal/Hour)", 390, 1450);

			calibriBigBitmap.draw(batch, ""+format.format((yearlyCons/860)) + " kW/Year", 320, 1150);
			calibriSmallBitmap.draw(batch, "("+format.format(yearlyCons) + " kCal/Year)", 370, 1050);

			if (program.c.getFuelType() == 1 || program.c.getFuelType() == 2 || program.c.getFuelType() == 3)
				calibriBigBitmap.draw(batch, ""+format.format(yearlyFuelCons) + " Kg", 350, 680);
			else{ 
				calibriBigBitmap.draw(batch, ""+format.format(yearlyFuelCons), 400, 680);
				if ((""+yearlyFuelCons).length() == 3){
					calibriBigBitmap.draw(batch, "m", 540, 680);
					calibriSmallBitmap.draw(batch, "3", 590, 700);
				}else if((""+yearlyFuelCons).length() == 4){

					calibriBigBitmap.draw(batch, "m", 600, 680);
					calibriSmallBitmap.draw(batch, "3", 650, 700);

				}else if((""+yearlyFuelCons).length() == 5){

					calibriBigBitmap.draw(batch, "m", 650, 680);
					calibriSmallBitmap.draw(batch, "3", 700, 700);

				}
			}

			FileHandle fh = null;

			if(program.c.getFuelType() == 1)
				fh = Gdx.files.absolute("/data/data/com.po.kazan/files/unitPriceCoal.txt");

			if(program.c.getFuelType() == 2)
				fh = Gdx.files.absolute("/data/data/com.po.kazan/files/unitPriceWood.txt");

			if(program.c.getFuelType() == 3)
				fh = Gdx.files.absolute("/data/data/com.po.kazan/files/unitPricePellet.txt");

			if(program.c.getFuelType() == 4)
				fh = Gdx.files.absolute("/data/data/com.po.kazan/files/unitPriceNaturalGas.txt");

			if(program.c.getFuelType() == 5)
				fh = Gdx.files.absolute("/data/data/com.po.kazan/files/unitPriceLpg.txt");

			if(program.c.getFuelType() == 6)
				fh = Gdx.files.absolute("/data/data/com.po.kazan/files/unitPriceDiesel.txt");

			if(program.c.getFuelType() == 7)
				fh = Gdx.files.absolute("/data/data/com.po.kazan/files/unitPriceFuelOil.txt");


			String s = fh.readString();
			double unitPrice = 0;
			System.out.println("PARSE DOUBLE: " + unitPrice);
			if(!s.equals("%"))
				unitPrice = Double.parseDouble(s);

			finalPrice = (int)(unitPrice*yearlyFuelCons);
			calibriBigBitmap.draw(batch, ""+format.format(finalPrice) + " TL", 440, 350);
		}

		batch.end();
		checkTouch();

	}

	private void checkTouch() {

		if(Gdx.input.justTouched()){

			tap.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(tap);

			int x = (int) tap.x;
			int y = (int) tap.y;

			y -= this.y;

			if(x > 0 && x < 260 && y > 1810 && y < 1880){
				program.setScreen(new resultScreen(program));
			}

			if(x > 0 && x < 1080 && y > 210 && y < 520){
				showUnitValue = true;
				//toplam tüketim
			}
			//	1-> kšmŸr, 2-> odun, 3-> pellet, 4-> doÛal gaz, 5-> lpg, 6-> dizel, 7-> fuel oil
			if(x > 0 && x < 1080 && y > 210 && y < 520){
				if(program.c.getFuelType() == 1)
					program.actionResolver.createUnitPriceDialog("1 kg kšmŸr iin fiyat giriniz: ", "unitPriceCoal");
				if(program.c.getFuelType() == 2)
					program.actionResolver.createUnitPriceDialog("1 kg odun iin fiyat giriniz: ", "unitPriceWood");
				if(program.c.getFuelType() == 3)
					program.actionResolver.createUnitPriceDialog("1 kg pellet iin fiyat giriniz: ", "unitPricePellet");
				if(program.c.getFuelType() == 4)
					program.actionResolver.createUnitPriceDialog("1 m3 doÛalgaz iin fiyat giriniz: ", "unitPriceNaturalGas");
				if(program.c.getFuelType() == 5)
					program.actionResolver.createUnitPriceDialog("1 m3 LPG iin fiyat giriniz: ", "unitPriceLpg");
				if(program.c.getFuelType() == 6)
					program.actionResolver.createUnitPriceDialog("1 lt dizel iin fiyat giriniz: ", "unitPriceDiesel");
				if(program.c.getFuelType() == 7)
					program.actionResolver.createUnitPriceDialog("1 lt fuel oil iin fiyat giriniz: ", "unitPriceFuelOil");
			}
		}
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

		Gdx.input.setInputProcessor(this);
		Gdx.input.setCatchBackKey(true);

		bg = program.textures.bg;
		yearlyCons = (int) ((program.c.fuelTypeCal[program.c.getFuelType()]*program.c.getHourlyCons()*6*5*30)*program.c.fuelTypeEff[program.c.getFuelType()]);
		hourlyCons = (int) ((program.c.fuelTypeCal[program.c.getFuelType()]*program.c.getHourlyCons())*program.c.fuelTypeEff[program.c.getFuelType()]);
		yearlyFuelCons = (int) (program.c.getHourlyCons()*6*5*30);
		calculatedCons = yearlyFuelCons*userDefinePrice;

		capacityHeader_en = program.textures.capacityHeader_en;
		capacityHeader_tr = program.textures.capacityHeader_tr;
		capacityHourly_en = program.textures.capacityHourly_en;
		capacityHourly_tr = program.textures.capacityHourly_tr;
		capacityLabel_en = program.textures.capacityLabel_en;
		capacityLabel_tr = program.textures.capacityLabel_tr;
		capacityYearly_en = program.textures.capacityYearly_en;
		capacityYearly_tr = program.textures.capacityYearly_tr;
		capacityConsumptionHeader_en = program.textures.capacityConsumptionHeader_en;
		capacityConsumptionHeader_tr = program.textures.capacityConsumptionHeader_tr;

		capAnnualFuelCoal_en = program.textures.capAnnualFuelCoal_en;
		capAnnualFuelCoal_tr = program.textures.capAnnualFuelCoal_tr;
		capAnnualFuelDiesel_en = program.textures.capAnnualFuelDiesel_en;
		capAnnualFuelDiesel_tr = program.textures.capAnnualFuelDiesel_tr;
		capAnnualFuelFuelOil_en = program.textures.capAnnualFuelFuelOil_en;
		capAnnualFuelFuelOil_tr = program.textures.capAnnualFuelFuelOil_tr;
		capAnnualFuelLPG_en = program.textures.capAnnualFuelLPG_en;
		capAnnualFuelLPG_tr = program.textures.capAnnualFuelLPG_tr;
		capAnnualFuelNaturalGas_en = program.textures.capAnnualFuelNaturalGas_en;
		capAnnualFuelNaturalGas_tr = program.textures.capAnnualFuelNaturalGas_tr;
		capAnnualFuelPellet_en = program.textures.capAnnualFuelPellet_en;
		capAnnualFuelPellet_tr = program.textures.capAnnualFuelPellet_tr;
		capAnnualFuelWood_en = program.textures.capAnnualFuelWood_en;
		capAnnualFuelWood_tr = program.textures.capAnnualFuelWood_tr;

		capTotalCoal_en = program.textures.capTotalCoal_en;
		capTotalCoal_tr = program.textures.capTotalCoal_tr;
		capTotalDiesel_en = program.textures.capTotalDiesel_en;
		capTotalDiesel_tr = program.textures.capTotalDiesel_tr;
		capTotalFuelOil_en = program.textures.capTotalFuelOil_en;
		capTotalFuelOil_tr = program.textures.capTotalFuelOil_tr;
		capTotalLPG_en = program.textures.capTotalLPG_en;
		capTotalLPG_tr = program.textures.capTotalLPG_tr;
		capTotalNaturalGas_en = program.textures.capTotalNaturalGas_en;
		capTotalNaturalGas_tr = program.textures.capTotalNaturalGas_tr;
		capTotalPellet_en = program.textures.capTotalPellet_en;
		capTotalPellet_tr = program.textures.capTotalPellet_tr;
		capTotalWood_en = program.textures.capTotalWood_en;
		capTotalWood_tr = program.textures.capTotalWood_tr;

		unitValue_en = program.textures.unitValue_en; 
		unitValue_tr = program.textures.unitValue_tr;

		calibriBigTexture = new Texture(Gdx.files.internal("calibri_capacity/calibri_capacity_screen_big_0.png"));
		calibriBigTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear); 
		calibriBigBitmap = new BitmapFont(Gdx.files.internal("calibri_capacity/calibri_capacity_screen_big.fnt"), new TextureRegion(calibriBigTexture), false);

		calibriSmallTexture = new Texture(Gdx.files.internal("calibri_capacity/calibri_capacity_screen_small_0.png"));
		calibriSmallTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear); 
		calibriSmallBitmap = new BitmapFont(Gdx.files.internal("calibri_capacity/calibri_capacity_screen_small.fnt"), new TextureRegion(calibriSmallTexture), false);


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

	@Override
	public boolean keyDown(int keycode) {
		if(keycode == Keys.BACK){

			program.setScreen(new resultScreen(program));
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
		// TODO Auto-generated method stub
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

}
