package com.po.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.po.kazan.MainProgram;

public class fuelTypeScreen implements Screen, InputProcessor {

	private MainProgram program;
	public OrthographicCamera camera;
	public SpriteBatch batch;
	
	private Texture bg, fuelTypeHeader_en, fuelTypeHeader_tr;
	private Texture fuelTypeCoal_en, fuelTypeCoal_tr;
	private Texture fuelTypeDiesel_en, fuelTypeDiesel_tr;
	private Texture fuelTypeFuelOil_en, fuelTypeFuelOil_tr;
	private Texture fuelTypeLPG_en, fuelTypeLPG_tr;
	private Texture fuelTypeNaturalGas_en, fuelTypeNaturalGas_tr;
	private Texture fuelTypePellet_en, fuelTypePellet_tr;
	private Texture fuelTypeWood_en, fuelTypeWood_tr;

	private int x, y;
	private boolean sliderOn;
	private Vector3 tap = new Vector3(0,0,0);
	
	public fuelTypeScreen(MainProgram program) {
		this.program = program;
	}

	@Override
	public void render(float delta) {

		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		
		batch.draw(bg,0,0);
		
		if(program.lang.equals("tr")){
			batch.draw(fuelTypeHeader_tr,x,y + 1920 - 160);
			batch.draw(fuelTypeCoal_tr,x,y + 1920 - 160 - 165);
			batch.draw(fuelTypeWood_tr,x,y + 1920 - 160 - 330);
			batch.draw(fuelTypePellet_tr,x,y + 1920 - 160 - 490);
			batch.draw(fuelTypeNaturalGas_tr,x,y + 1920 - 160 - 655);
			batch.draw(fuelTypeLPG_tr,x,y + 1920 - 160 - 820);
			batch.draw(fuelTypeDiesel_tr,x,y + 1920 - 160 - 985);
			batch.draw(fuelTypeFuelOil_tr,x,y + 1920 - 160 - 1150);
		} else {
			batch.draw(fuelTypeHeader_en,x,y + 1920 - 160);
			batch.draw(fuelTypeCoal_en,x,y + 1920 - 160 - 160);
			batch.draw(fuelTypeWood_en,x,y + 1920 - 160 - 320);
			batch.draw(fuelTypePellet_en,x,y + 1920 - 160 - 480);
			batch.draw(fuelTypeNaturalGas_en,x,y + 1920 - 160 - 640);
			batch.draw(fuelTypeLPG_en,x,y + 1920 - 160 - 800);
			batch.draw(fuelTypeDiesel_en,x,y + 1920 - 160 - 960);
			batch.draw(fuelTypeFuelOil_en,x,y + 1920 - 160 - 1120);
		}

		batch.end();
		checkTouch();

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

        FileHandle cityFile = Gdx.files.local("hwlocation.txt");
		String loc = cityFile.readString();

		System.out.println("Loc Fuel type: " + loc);
		
		if(loc.equals("-1 -1")){
			program.actionResolver.getUserLocationFromHardware();
			program.actionResolver.showLongToast("Lokasyon tekrar alÝnÝyor... LŸtfen bekleyin...");
		}
		bg = program.textures.bg;
		fuelTypeHeader_en = program.textures.fuelTypeHeader_en;
		fuelTypeHeader_tr = program.textures.fuelTypeHeader_tr;
		fuelTypeCoal_en = program.textures.fuelTypeCoal_en;
		fuelTypeCoal_tr = program.textures.fuelTypeCoal_tr;
		fuelTypeDiesel_en = program.textures.fuelTypeDiesel_en;
		fuelTypeDiesel_tr = program.textures.fuelTypeDiesel_tr;
		fuelTypeFuelOil_en = program.textures.fuelTypeFuelOil_en;
		fuelTypeFuelOil_tr = program.textures.fuelTypeFuelOil_tr;
		fuelTypeLPG_en = program.textures.fuelTypeLPG_en;
		fuelTypeLPG_tr = program.textures.fuelTypeLPG_tr;
		fuelTypeNaturalGas_en = program.textures.fuelTypeNaturalGas_en;
		fuelTypeNaturalGas_tr = program.textures.fuelTypeNaturalGas_tr;
		fuelTypePellet_en = program.textures.fuelTypePellet_en;
		fuelTypePellet_tr = program.textures.fuelTypePellet_tr;
		fuelTypeWood_en = program.textures.fuelTypeWood_en;
		fuelTypeWood_tr = program.textures.fuelTypeWood_tr;

		x = 0; y = 0;
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
	
	public void checkTouch(){
		
		if(Gdx.input.justTouched()){
			tap.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(tap);

			int x = (int) tap.x;
			int y = (int) tap.y;
			
			y -= this.y;
			
			
			if(x > 0 && x < 550 && y > 1760 && y < 1920){
				program.setScreen(new calculationScreen(program));
			}
			
			if(x > 0 && x < 1080 && y > 1600 && y < 1760){
				program.c.setFuelType(1);
				program.setScreen(new resultScreen(program));
			}
			else if(x > 0 && x < 1080 && y > 1440 && y < 1600){
				program.c.setFuelType(2);
				program.setScreen(new resultScreen(program));
			}
			else if(x > 0 && x < 1080 && y > 1280 && y < 1440){
				program.c.setFuelType(3);
				program.setScreen(new resultScreen(program));
			}
			else if(x > 0 && x < 1080 && y > 1120 && y < 1280){
				program.c.setFuelType(4);
				program.setScreen(new resultScreen(program));
			}
			else if(x > 0 && x < 1080 && y > 960 && y < 1120){
				program.c.setFuelType(5);
				program.setScreen(new resultScreen(program));
			}
			else if(x > 0 && x < 1080 && y > 800 && y < 960){
				program.c.setFuelType(6);
				program.setScreen(new resultScreen(program));
			}
			else if(x > 0 && x < 1080 && y > 640 && y < 800){
				program.c.setFuelType(7);
				program.setScreen(new resultScreen(program));
			}
			
		}
	}

	
	@Override
	public boolean keyDown(int keycode) {
		if(keycode == Keys.BACK){
			
			program.setScreen(new calculationScreen(program));
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
