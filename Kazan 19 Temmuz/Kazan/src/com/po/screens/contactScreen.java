package com.po.screens;

import java.util.ArrayList;
import java.util.Collections;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.po.kazan.MainProgram;
import com.po.kazan.contacts;

public class contactScreen implements Screen {

	private MainProgram program;
	public OrthographicCamera camera;
	public SpriteBatch batch;

	private Texture bg, slider_en, slider_tr, mainHeader;
	private Texture contactsCountry, partnerInfo2;

	private int x, y;
	private boolean sliderOn;
	private Vector3 tap = new Vector3(0,0,0);
	private BitmapFont f = new BitmapFont();
	private ArrayList <String> countries = new ArrayList <String>();

	private Texture calibriTexture;
	private BitmapFont calibriBitmap;

	public contactScreen(MainProgram program) {
		this.program = program;
	}

	@Override
	public void render(float delta) {
		batch.setProjectionMatrix(camera.combined);
		batch.begin();

		batch.draw(bg,0,0);

		if(program.lang.equals("tr")){
			batch.draw(slider_tr,0,0);
			batch.draw(program.textures.bg,x,y);
			batch.draw(mainHeader,x,y + 1920 - 160);

			batch.draw(contactsCountry,x,y + 1920 - 160 - 120);
			calibriBitmap.draw(batch, "TŸrkiye", x + 200 ,y + 1920 - 160 - 120 - 30);
			for(int i=1; i<=program.rt.centralContactObjectsFromTxt.length; i++) {
				batch.draw(partnerInfo2,x,y + 1920 - 160 - 120 - (520)*i);
				calibriBitmap.draw(batch, program.rt.centralContactObjectsFromTxt[i-1].getName(), x + 100 ,y + 1920 - 160 - 120 - 50 - (520)*i + 510);
				calibriBitmap.draw(batch, program.rt.centralContactObjectsFromTxt[i-1].getAdress(), x + 100 ,y + 1920 - 160 - 120 - 50 - (520)*i + 420);
				calibriBitmap.draw(batch, program.rt.centralContactObjectsFromTxt[i-1].getPhone(), x + 200 ,y + 1920 - 160 - 120 - 50 - (520)*i + 320);
				calibriBitmap.draw(batch, program.rt.centralContactObjectsFromTxt[i-1].getEmail(), x + 200 ,y + 1920 - 160 - 120 - 50 - (520)*i + 220);
				calibriBitmap.draw(batch, program.rt.centralContactObjectsFromTxt[i-1].getWebsite(), x + 200 ,y + 1920 - 160 - 120 - 50 - (520)*i + 120);
			}

		} else {
			batch.draw(slider_en,0,0);
			batch.draw(program.textures.bg,x,y);
			batch.draw(mainHeader,x,y + 1920 - 160);

			batch.draw(contactsCountry,x,y + 1920 - 160 - 120);
			calibriBitmap.draw(batch, "Turkey", x + 200 ,y + 1920 - 160 - 120 - 30);
			for(int i=1; i<=program.rt.centralContactObjectsFromTxt.length; i++) {
				batch.draw(partnerInfo2,x,y + 1920 - 160 - 120 - (520)*i);
				calibriBitmap.draw(batch, program.rt.centralContactObjectsFromTxt[i-1].getName(), x + 100 ,y + 1920 - 160 - 120 - 50 - (520)*i + 510);
				calibriBitmap.draw(batch, program.rt.centralContactObjectsFromTxt[i-1].getAdress(), x + 100 ,y + 1920 - 160 - 120 - 50 - (520)*i + 420);
				calibriBitmap.draw(batch, program.rt.centralContactObjectsFromTxt[i-1].getPhone(), x + 200 ,y + 1920 - 160 - 120 - 50 - (520)*i + 320);
				calibriBitmap.draw(batch, program.rt.centralContactObjectsFromTxt[i-1].getEmail(), x + 200 ,y + 1920 - 160 - 120 - 50 - (520)*i + 220);
				calibriBitmap.draw(batch, program.rt.centralContactObjectsFromTxt[i-1].getWebsite(), x + 200 ,y + 1920 - 160 - 120 - 50 - (520)*i + 120);
			}
		}

		batch.end();
		checkSlider();

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

		bg = program.textures.bg;
		slider_en = program.textures.slider_en;
		slider_tr = program.textures.slider_tr;
		contactsCountry = program.textures.contactsCountry;
		partnerInfo2 = program.textures.partnerInfo2;
		mainHeader = program.textures.mainHeader;

		x = 0; y = 0;

		calibriTexture = new Texture(Gdx.files.internal("calibri_son_0.png"));
		calibriTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear); 
		calibriBitmap = new BitmapFont(Gdx.files.internal("calibri_son.fnt"), new TextureRegion(calibriTexture), false);
		calibriBitmap.setColor(0,0,1,1);


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

	public void checkSlider(){

		if(sliderOn){
			while(x < 900){
				x += 50;
				break;
			}
		} else {
			while(x > 0){
				x -= 50;
				break;
			}
		}

		if(Gdx.input.justTouched()){

			tap.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(tap);

			int x = (int) tap.x;
			int y = (int) tap.y;
			System.out.println("contact screen x : " + x + " y: " + y);
			if(x < 200 && x > 0 && y < 1920 && y > 1750){
				sliderOn = !sliderOn;
			}

			if(sliderOn){
				if(x < 800 && x > 0 && y < 1750 && y > 1600){
					program.setScreen(new mainScreen(program)); // ýsý hesaplama.
				}
				else if(x < 800 && x > 0 && y < 1590 && y > 1430){
					program.setScreen(new productsScreen(program)); // ürünlerimiz.
				}
				else if(x < 800 && x > 0 && y < 1420 && y > 1260){
					program.setScreen(new partnersScreen(program)); // iþ ortaklarýmýz.
				}
				else if(x < 800 && x > 0 && y < 1250 && y > 1090){
					program.setScreen(new contactScreen(program)); // iletiþim.
				}  
				else if((x< 1080 && x > 800) || (y < 1090 && y > 0)){
					program.setScreen(new mainScreen(program)); // geri dön
				}
			} else {

				if(x < 1020 && x > 820 && y < 1480 && y > 1280){
					// Üstteki google maps
					program.actionResolver.openMap(Double.parseDouble(program.rt.centralContactObjectsFromTxt[0].getLongitude()), 
							Double.parseDouble(program.rt.centralContactObjectsFromTxt[0].getLatitude()),"ArÝ Kazan", "Central Office");
				} else if(x < 1020 && x > 820 && y < 980 && y > 780){
					// Alttaki google maps
					program.actionResolver.openMap(Double.parseDouble(program.rt.centralContactObjectsFromTxt[1].getLongitude()), 
							Double.parseDouble(program.rt.centralContactObjectsFromTxt[1].getLatitude()),"ArÝ Kazan", "GšlbaßÝ Plant");
				}
			}
		}
	}

}
