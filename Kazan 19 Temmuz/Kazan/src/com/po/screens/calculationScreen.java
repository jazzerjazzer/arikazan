package com.po.screens;

import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.InputProcessorQueue;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.po.kazan.MainProgram;
import com.sun.org.apache.bcel.internal.generic.StoreInstruction;

public class calculationScreen implements Screen, GestureListener, InputProcessor {

	private MainProgram program;
	public OrthographicCamera camera;
	public SpriteBatch batch;
	private Texture calibriBigTexture;
	private BitmapFont calibriBigBitmap;
	private boolean firstTimeKeyboard = true;

	private GestureDetector gd = new GestureDetector(this);
	private InputProcessor ip = new InputProcessor() {

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
		public boolean touchDown(int screenX, int screenY, int pointer, int button) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean scrolled(int amount) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean mouseMoved(int screenX, int screenY) {
			// TODO Auto-generated method stub
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
		public boolean keyDown(int keycode) {
			if(keycode == Keys.BACK){

				program.setScreen(new mainScreen(program));
			}		

			return false;
		}
	};

	InputMultiplexer multiplexer = new InputMultiplexer();

	private int x, y, selection = 1, gap = 500;
	private Vector3 tap = new Vector3(0,0,0);
	boolean numpad = false;
	private BitmapFont f = new BitmapFont();

	private Texture bg;
	private Texture calcHeader, numpadImage;

	private Texture calc1_unselected_en, calc1_unselected_tr;
	private Texture calc1_apartment_en, calc1_apartment_tr;
	private Texture calc1_villa_en, calc1_villa_tr;

	private Texture calc_2_0_en, calc_2_0_tr;
	private Texture calc_2_1_en, calc_2_1_tr;
	private Texture calc_2_2_en, calc_2_2_tr;
	private Texture calc_2_3_en, calc_2_3_tr;
	private Texture calc_2_4_en, calc_2_4_tr;
	private Texture calc_2_5_en, calc_2_5_tr;
	private Texture calc_2_6_en, calc_2_6_tr;

	private Texture calc_3_en, calc_3_tr;

	private Texture calc_4_0_en, calc_4_0_tr;
	private Texture calc_4_1_en, calc_4_1_tr;
	private Texture calc_4_2_en, calc_4_2_tr;
	private Texture calc_4_3_en, calc_4_3_tr;
	private Texture calc_4_4_en, calc_4_4_tr;

	private Texture calc_5_0_en, calc_5_0_tr;
	private Texture calc_5_1_en, calc_5_1_tr;
	private Texture calc_5_2_en, calc_5_2_tr;

	private Texture calc_6_unselected_en, calc_6_unselected_tr;
	private Texture calc_6_near_en, calc_6_near_tr;
	private Texture calc_6_away_en, calc_6_away_tr;

	private Texture calc_7_unselected_en, calc_7_unselected_tr;
	private Texture calc_7_1_en, calc_7_1_tr;
	private Texture calc_7_2_en, calc_7_2_tr;
	private Texture calc_7_3_en, calc_7_3_tr;

	private Texture calc_8_unselected_en, calc_8_unselected_tr;
	private Texture calc_8_1_en, calc_8_1_tr;
	private Texture calc_8_2_en, calc_8_2_tr;
	private Texture calc_8_3_en, calc_8_3_tr;

	private Texture calc_9_unselected_en, calc_9_unselected_tr;
	private Texture calc_9_1_en, calc_9_1_tr;
	private Texture calc_9_2_en, calc_9_2_tr;
	private Texture calc_9_3_en, calc_9_3_tr;

	private Texture calc_10_unselected_en, calc_10_unselected_tr;
	private Texture calc_10_1_en, calc_10_1_tr;
	private Texture calc_10_2_en, calc_10_2_tr;
	private Texture calc_10_3_en, calc_10_3_tr;

	private Texture calc_11_en, calc_11_tr;

	int buildingType;
	int storySelected;
	String areaEntered;
	int heightSelected;
	int manto;
	int nearAway;
	int windowSize;
	int windowTypeSelected;
	int tempSelected;
	int sysSizeSelected;
	
	boolean storeyDialog = false;


	public calculationScreen(MainProgram program) {
		this.program = program;
	}



	@Override
	public void render(float delta) {

		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.draw(calcHeader,x,y + 1920 - 160);

		if(program.lang.equals("tr")){

			if(buildingType == 0)
				batch.draw(calc1_unselected_tr,x,y + 1920 - 180 - 500);
			if(buildingType == 1)
				batch.draw(calc1_villa_tr,x,y + 1920 - 180 - 500);
			if(buildingType == 2)
				batch.draw(calc1_apartment_tr,x,y + 1920 - 180 - 500);

			if(storySelected == 0)
				batch.draw(calc_2_0_tr, x, y + 1920 - 180 - 1000);
			if(storySelected == 1)
				batch.draw(calc_2_1_tr, x, y + 1920 - 180 - 1000);
			if(storySelected == 2)
				batch.draw(calc_2_2_tr, x, y + 1920 - 180 - 1000);
			if(storySelected == 3)
				batch.draw(calc_2_3_tr, x, y + 1920 - 180 - 1000);
			if(storySelected == 4)
				batch.draw(calc_2_4_tr, x, y + 1920 - 180 - 1000);
			if(storySelected == 5)
				batch.draw(calc_2_5_tr, x, y + 1920 - 180 - 1000);
			if(storySelected == 6)
				batch.draw(calc_2_6_tr, x, y + 1920 - 180 - 1000);
			if(storySelected >= 6)
				batch.draw(calc_2_6_tr, x, y + 1920 - 180 - 1000);
			

			batch.draw(calc_3_tr,x,y + 1920 - 180 - 1500);

			if(heightSelected == 0)
				batch.draw(calc_4_0_tr, x, y + 1920 - 180 - 2000);
			if(heightSelected == 1)
				batch.draw(calc_4_1_tr, x, y + 1920 - 180 - 2000);
			if(heightSelected == 2)
				batch.draw(calc_4_2_tr, x, y + 1920 - 180 - 2000);
			if(heightSelected == 3)
				batch.draw(calc_4_3_tr, x, y + 1920 - 180 - 2000);
			if(heightSelected == 4)
				batch.draw(calc_4_4_tr, x, y + 1920 - 180 - 2000);

			if(manto == 0)
				batch.draw(calc_5_0_tr, x, y + 1920 - 180 - 2500);
			if(manto == 1)
				batch.draw(calc_5_1_tr, x, y + 1920 - 180 - 2500);
			if(manto == 2)
				batch.draw(calc_5_2_tr, x, y + 1920 - 180 - 2500);

			if(nearAway == 0)
				batch.draw(calc_6_unselected_tr, x, y + 1920 - 180 - 3000);
			if(nearAway == 1)
				batch.draw(calc_6_near_tr, x, y + 1920 - 180 - 3000);
			if(nearAway == 2)
				batch.draw(calc_6_away_tr, x, y + 1920 - 180 - 3000);

			if(windowSize == 0)
				batch.draw(calc_7_unselected_tr, x, y + 1920 - 180 - 3500);
			if(windowSize == 1)
				batch.draw(calc_7_1_tr, x, y + 1920 - 180 - 3500);
			if(windowSize == 2)
				batch.draw(calc_7_2_tr, x, y + 1920 - 180 - 3500);
			if(windowSize == 3)
				batch.draw(calc_7_3_tr, x, y + 1920 - 180 - 3500);

			if(windowTypeSelected == 0)
				batch.draw(calc_8_unselected_tr, x, y + 1920 - 180 - 4000);
			if(windowTypeSelected == 1)
				batch.draw(calc_8_1_tr, x, y + 1920 - 180 - 4000);
			if(windowTypeSelected == 2)
				batch.draw(calc_8_2_tr, x, y + 1920 - 180 - 4000);
			if(windowTypeSelected == 3)
				batch.draw(calc_8_3_tr, x, y + 1920 - 180 - 4000);

			if(tempSelected == 0)
				batch.draw(calc_9_unselected_tr, x, y + 1920 - 180 - 4500);
			if(tempSelected == 1)
				batch.draw(calc_9_1_tr, x, y + 1920 - 180 - 4500);
			if(tempSelected == 2)
				batch.draw(calc_9_2_tr, x, y + 1920 - 180 - 4500);
			if(tempSelected == 3)
				batch.draw(calc_9_3_tr, x, y + 1920 - 180 - 4500);

			if(sysSizeSelected == 0)
				batch.draw(calc_10_unselected_tr, x, y + 1920 - 180 - 5000);
			if(sysSizeSelected == 1)
				batch.draw(calc_10_1_tr, x, y + 1920 - 180 - 5000);
			if(sysSizeSelected == 2)
				batch.draw(calc_10_2_tr, x, y + 1920 - 180 - 5000);
			if(sysSizeSelected == 3)
				batch.draw(calc_10_3_tr, x, y + 1920 - 180 - 5000);

			batch.draw(calc_11_tr,x,y + 1920 - 180 - 5500);

		} else {

			if(buildingType == 0)
				batch.draw(calc1_unselected_en,x,y + 1920 - 180 - 500);
			if(buildingType == 1)
				batch.draw(calc1_villa_en,x,y + 1920 - 180 - 500);
			if(buildingType == 2)
				batch.draw(calc1_apartment_en,x,y + 1920 - 180 - 500);

			if(storySelected == 0)
				batch.draw(calc_2_0_en, x, y + 1920 - 180 - 1000);
			if(storySelected == 1)
				batch.draw(calc_2_1_en, x, y + 1920 - 180 - 1000);
			if(storySelected == 2)
				batch.draw(calc_2_2_en, x, y + 1920 - 180 - 1000);
			if(storySelected == 3)
				batch.draw(calc_2_3_en, x, y + 1920 - 180 - 1000);
			if(storySelected == 4)
				batch.draw(calc_2_4_en, x, y + 1920 - 180 - 1000);
			if(storySelected == 5)
				batch.draw(calc_2_5_en, x, y + 1920 - 180 - 1000);
			if(storySelected == 6)
				batch.draw(calc_2_6_en, x, y + 1920 - 180 - 1000);
			if(storySelected >= 6)
				batch.draw(calc_2_6_en, x, y + 1920 - 180 - 1000);
			

			batch.draw(calc_3_en,x,y + 1920 - 180 - 1500);

			if(heightSelected == 0)
				batch.draw(calc_4_0_en, x, y + 1920 - 180 - 2000);
			if(heightSelected == 1)
				batch.draw(calc_4_1_en, x, y + 1920 - 180 - 2000);
			if(heightSelected == 2)
				batch.draw(calc_4_2_en, x, y + 1920 - 180 - 2000);
			if(heightSelected == 3)
				batch.draw(calc_4_3_en, x, y + 1920 - 180 - 2000);
			if(heightSelected == 4)
				batch.draw(calc_4_4_en, x, y + 1920 - 180 - 2000);

			if(manto == 0)
				batch.draw(calc_5_0_en, x, y + 1920 - 180 - 2500);
			if(manto == 1)
				batch.draw(calc_5_1_en, x, y + 1920 - 180 - 2500);
			if(manto == 2)
				batch.draw(calc_5_2_en, x, y + 1920 - 180 - 2500);

			if(nearAway == 0)
				batch.draw(calc_6_unselected_en, x, y + 1920 - 180 - 3000);
			if(nearAway == 1)
				batch.draw(calc_6_near_en, x, y + 1920 - 180 - 3000);
			if(nearAway == 2)
				batch.draw(calc_6_away_en, x, y + 1920 - 180 - 3000);

			if(windowSize == 0)
				batch.draw(calc_7_unselected_en, x, y + 1920 - 180 - 3500);
			if(windowSize == 1)
				batch.draw(calc_7_1_en, x, y + 1920 - 180 - 3500);
			if(windowSize == 2)
				batch.draw(calc_7_2_en, x, y + 1920 - 180 - 3500);
			if(windowSize == 3)
				batch.draw(calc_7_3_en, x, y + 1920 - 180 - 3500);

			if(windowTypeSelected == 0)
				batch.draw(calc_8_unselected_en, x, y + 1920 - 180 - 4000);
			if(windowTypeSelected == 1)
				batch.draw(calc_8_1_en, x, y + 1920 - 180 - 4000);
			if(windowTypeSelected == 2)
				batch.draw(calc_8_2_en, x, y + 1920 - 180 - 4000);
			if(windowTypeSelected == 3)
				batch.draw(calc_8_3_en, x, y + 1920 - 180 - 4000);

			if(tempSelected == 0)
				batch.draw(calc_9_unselected_en, x, y + 1920 - 180 - 4500);
			if(tempSelected == 1)
				batch.draw(calc_9_1_en, x, y + 1920 - 180 - 4500);
			if(tempSelected == 2)
				batch.draw(calc_9_2_en, x, y + 1920 - 180 - 4500);
			if(tempSelected == 3)
				batch.draw(calc_9_3_en, x, y + 1920 - 180 - 4500);

			if(sysSizeSelected == 0)
				batch.draw(calc_10_unselected_en, x, y + 1920 - 180 - 5000);
			if(sysSizeSelected == 1)
				batch.draw(calc_10_1_en, x, y + 1920 - 180 - 5000);
			if(sysSizeSelected == 2)
				batch.draw(calc_10_2_en, x, y + 1920 - 180 - 5000);
			if(sysSizeSelected == 3)
				batch.draw(calc_10_3_en, x, y + 1920 - 180 - 5000);

			batch.draw(calc_11_en,x,y + 1920 - 180 - 5500);

		}

		FileHandle fh = Gdx.files.absolute("/data/data/com.po.kazan/files/alan.txt");

		String s = fh.readString();
		if(!s.equals("&"))
			calibriBigBitmap.draw(batch, s + "" ,x + 660,y + 1920 - 180 - 1220);

		while(gap < selection*500){
			gap += 50; 
			if(selection > 3 && gap > 1500)
				y += 50;
			break;
		}

		batch.draw(bg, 0, y - 3700 - 250 - gap);
		batch.end();
		
		FileHandle fh1 = Gdx.files.absolute("/data/data/com.po.kazan/files/storey.txt");
		String s1 = fh1.readString();

		if(!s1.equals("=")){
			
			storySelected = Integer.parseInt(s1);
			program.c.setStoreys(Integer.parseInt(s1));
			storeyDialog = true;
		}
		
		System.out.println("Storey over 6: " + storySelected);
		System.out.println("program storey: " + program.c.getStoreys());
		
		try {
			checkTouch();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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


		multiplexer.addProcessor(gd);
		multiplexer.addProcessor(ip);
		Gdx.input.setInputProcessor(multiplexer);
		Gdx.input.setCatchBackKey(true);


		f.scale(2);
		f.setColor(Color.BLACK);

		Gdx.input.setCatchBackKey(true);

		x = 0; y = 0;

		bg = program.textures.bg;
		calcHeader = program.textures.calcHeader;

		calc1_unselected_en = program.textures.calc1_unselected_en;
		calc1_unselected_tr = program.textures.calc1_unselected_tr;
		calc1_apartment_en = program.textures.calc1_apartment_en;
		calc1_apartment_tr = program.textures.calc1_apartment_tr;
		calc1_villa_en = program.textures.calc1_villa_en; 
		calc1_villa_tr = program.textures.calc1_villa_tr;

		calc_2_0_en = program.textures.calc_2_0_en;
		calc_2_0_tr = program.textures.calc_2_0_tr;
		calc_2_1_en = program.textures.calc_2_1_en;
		calc_2_1_tr = program.textures.calc_2_1_tr;
		calc_2_2_en = program.textures.calc_2_2_en; 
		calc_2_2_tr = program.textures.calc_2_2_tr;
		calc_2_3_en = program.textures.calc_2_3_en;
		calc_2_3_tr = program.textures.calc_2_3_tr;
		calc_2_4_en = program.textures.calc_2_4_en; 
		calc_2_4_tr = program.textures.calc_2_4_tr;
		calc_2_5_en = program.textures.calc_2_5_en;
		calc_2_5_tr = program.textures.calc_2_5_tr;
		calc_2_6_en = program.textures.calc_2_6_en;
		calc_2_6_tr = program.textures.calc_2_6_tr;

		calc_3_en = program.textures.calc_3_en;
		calc_3_tr = program.textures.calc_3_tr;

		calc_4_0_en = program.textures.calc_4_0_en; 
		calc_4_0_tr = program.textures.calc_4_0_tr;
		calc_4_1_en = program.textures.calc_4_1_en; 
		calc_4_1_tr = program.textures.calc_4_1_tr;
		calc_4_2_en = program.textures.calc_4_2_en; 
		calc_4_2_tr = program.textures.calc_4_2_tr;
		calc_4_3_en = program.textures.calc_4_3_en; 
		calc_4_3_tr = program.textures.calc_4_3_tr;
		calc_4_4_en = program.textures.calc_4_4_en; 
		calc_4_4_tr = program.textures.calc_4_4_tr;

		calc_5_0_en = program.textures.calc_5_0_en; 
		calc_5_0_tr = program.textures.calc_5_0_tr;
		calc_5_1_en = program.textures.calc_5_1_en; 
		calc_5_1_tr = program.textures.calc_5_1_tr;
		calc_5_2_en = program.textures.calc_5_2_en; 
		calc_5_2_tr = program.textures.calc_5_2_tr;

		calc_6_unselected_en = program.textures.calc_6_unselected_en; 
		calc_6_unselected_tr = program.textures.calc_6_unselected_tr;
		calc_6_near_en = program.textures.calc_6_near_en; 
		calc_6_near_tr = program.textures.calc_6_near_tr;
		calc_6_away_en = program.textures.calc_6_away_en;  
		calc_6_away_tr = program.textures.calc_6_away_tr;

		calc_7_unselected_en = program.textures.calc_7_unselected_en; 
		calc_7_unselected_tr = program.textures.calc_7_unselected_tr;
		calc_7_1_en = program.textures.calc_7_1_en; 
		calc_7_1_tr = program.textures.calc_7_1_tr;
		calc_7_2_en = program.textures.calc_7_2_en; 
		calc_7_2_tr = program.textures.calc_7_2_tr;
		calc_7_3_en = program.textures.calc_7_3_en; 
		calc_7_3_tr = program.textures.calc_7_3_tr;

		calc_8_unselected_en = program.textures.calc_8_unselected_en; 
		calc_8_unselected_tr = program.textures.calc_8_unselected_tr;
		calc_8_1_en = program.textures.calc_8_1_en; 
		calc_8_1_tr = program.textures.calc_8_1_tr;
		calc_8_2_en = program.textures.calc_8_2_en; 
		calc_8_2_tr = program.textures.calc_8_2_tr;
		calc_8_3_en = program.textures.calc_8_3_en; 
		calc_8_3_tr = program.textures.calc_8_3_tr;

		calc_9_unselected_en = program.textures.calc_9_unselected_en; 
		calc_9_unselected_tr = program.textures.calc_9_unselected_tr;
		calc_9_1_en = program.textures.calc_9_1_en; 
		calc_9_1_tr = program.textures.calc_9_1_tr;
		calc_9_2_en = program.textures.calc_9_2_en; 
		calc_9_2_tr = program.textures.calc_9_2_tr;
		calc_9_3_en = program.textures.calc_9_3_en; 
		calc_9_3_tr = program.textures.calc_9_3_tr;

		calc_10_unselected_en = program.textures.calc_10_unselected_en; 
		calc_10_unselected_tr = program.textures.calc_10_unselected_tr;
		calc_10_1_en = program.textures.calc_10_1_en; 
		calc_10_1_tr = program.textures.calc_10_1_tr;
		calc_10_2_en = program.textures.calc_10_2_en; 
		calc_10_2_tr = program.textures.calc_10_2_tr;
		calc_10_3_en = program.textures.calc_10_3_en; 
		calc_10_3_tr = program.textures.calc_10_3_tr;

		calc_11_en = program.textures.calc_11_en; 
		calc_11_tr = program.textures.calc_11_tr; 

		numpadImage = program.textures.numpad;

		calibriBigTexture = new Texture(Gdx.files.internal("calibri_capacity_0.png"));
		calibriBigTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear); 
		calibriBigBitmap = new BitmapFont(Gdx.files.internal("calibri_capacity.fnt"), new TextureRegion(calibriBigTexture), false);
		calibriBigBitmap.setColor(0,0,1,1);

		buildingType = program.c.getHouseType();
		storySelected = program.c.getStoreys();
		areaEntered = ""+program.c.getArea();
		heightSelected = program.c.getHeight();
		manto = program.c.getManto();
		nearAway = program.c.getNearAway();
		windowSize = program.c.getWindow();
		windowTypeSelected = program.c.getWindowType();
		tempSelected = program.c.getHeat();
		sysSizeSelected = program.c.getSysSize();
		
		if(buildingType != 0){
			selection = 11;
			gap = 5500;
		}
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
	public boolean touchDown(float x, float y, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean tap(float x, float y, int count, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean longPress(float x, float y) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean fling(float velocityX, float velocityY, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean pan(float x, float y, float deltaX, float deltaY) {

		if(!numpad){

			this.y += (-2)*deltaY;

			if(this.y > gap - 1500)
				this.y = gap  - 1500;

			if(this.y < 0)
				this.y = 0;
		}

		return false;
	}

	@Override
	public boolean panStop(float x, float y, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean zoom(float initialDistance, float distance) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2,
			Vector2 pointer1, Vector2 pointer2) {
		// TODO Auto-generated method stub
		return false;
	}

	public void checkTouch() throws IOException{


		if(Gdx.input.justTouched()){
			tap.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(tap);

			int x = (int) tap.x;
			int y = (int) tap.y;

			y -= this.y;


			if(x > 0 && x < 550 && y > 1760 && y < 1920){
				program.setScreen(new mainScreen(program));
			}

			if(x > 600 && x < 850 && y > -3530 && y < -3350){
				if(areaEntered.equals("")) areaEntered = "0";
				FileHandle fh = Gdx.files.absolute("/data/data/com.po.kazan/files/alan.txt");

				program.c.setArea(Integer.parseInt(fh.readString()));
				//program.textures.loadFuelType();
				program.setScreen(new fuelTypeScreen(program));
			} 
			else if(x > 480 && x < 700 && y > 1450 && y < 1670){
				buildingType = 1;
				program.c.setHouseType(1);
				selection = 2;
			}
			else if(x > 800 && x < 1000 && y > 1450 && y < 1670){
				buildingType = 2;
				program.c.setHouseType(2);
				selection = 2;
			}
			else if(x > 440 && x < 530 && y > 935 && y < 1040){
				storySelected = 1;
				program.c.setStoreys(1);
				selection = 3;
				program.actionResolver.writeStoreyToFile(""+1);

				if(firstTimeKeyboard){
					program.actionResolver.createAlanDialog();
					selection = 4;
					firstTimeKeyboard = false;
				}
			}
			else if(x > 530 && x < 640 && y > 935 && y < 1040){
				storySelected = 2;
				program.c.setStoreys(2);
				selection = 3;
				program.actionResolver.writeStoreyToFile(""+2);

				if(firstTimeKeyboard){
					program.actionResolver.createAlanDialog();
					selection = 4;
					firstTimeKeyboard = false;
				}
			}
			else if(x > 640 && x < 740 && y > 935 && y < 1040){
				storySelected = 3;
				program.c.setStoreys(3);
				selection = 3;
				program.actionResolver.writeStoreyToFile(""+3);


				if(firstTimeKeyboard){
					program.actionResolver.createAlanDialog();
					selection = 4;
					firstTimeKeyboard = false;
				}
			}
			else if(x > 740 && x < 850 && y > 935 && y < 1040){
				storySelected = 4;
				program.c.setStoreys(4);
				selection = 3;
				program.actionResolver.writeStoreyToFile(""+4);

				if(firstTimeKeyboard){
					program.actionResolver.createAlanDialog();
					selection = 4;
					firstTimeKeyboard = false;
				}
			}
			else if(x > 850 && x < 960 && y > 935 && y < 1040){
				storySelected = 5;
				program.c.setStoreys(5);
				selection = 3;
				program.actionResolver.writeStoreyToFile(""+5);

				if(firstTimeKeyboard){
					program.actionResolver.createAlanDialog();
					selection = 4;
					firstTimeKeyboard = false;
				}
			}
			else if(x > 960 && x < 1080 && y > 935 && y < 1040){
				
				program.actionResolver.createStoreyDialog();
				
				//storySelected = 6;
				//program.c.setStoreys(6);
				selection = 3;
				
				if(firstTimeKeyboard){
					if(storeyDialog){
						program.actionResolver.createAlanDialog();
						selection = 4;
						firstTimeKeyboard = false;
					}
				}
			}
			else if(x > 430 && x < 570 && y > -85 && y < 40){
				heightSelected = 1;
				program.c.setHeight(1);
				selection = 5;
			}
			else if(x > 570 && x < 730 && y > -85 && y < 40){
				heightSelected = 2;
				program.c.setHeight(2);
				selection = 5;
			}
			else if(x > 730 && x < 900 && y > -85 && y < 40){
				heightSelected = 3;
				program.c.setHeight(3);
				selection = 5;
			}
			else if(x > 900 && x < 1055 && y > -85 && y < 40){
				heightSelected = 4;
				program.c.setHeight(4);
				selection = 5;
			}
			else if(x > 470 && x < 730 && y > -570 && y < -465){
				manto = 1;
				program.c.setManto(1);
				selection = 6;
			}
			else if(x > 730 && x < 1000 && y > -570 && y < -465){
				manto = 2;
				program.c.setManto(2);
				selection = 6;
			}
			else if(x > 440 && x < 700 && y > -1050 && y < -910){
				nearAway = 2;
				program.c.setNearAway(2);
				selection = 7;
			}
			else if(x > 760 && x < 1050 && y > -1050 && y < -910){
				nearAway = 1;
				program.c.setNearAway(1);
				selection = 7;
			}
			else if(x > 450 && x < 585 && y > -1590 && y < -1390){
				windowSize = 1;
				program.c.setWindow(1);
				selection = 8;
			}
			else if(x > 590 && x < 830 && y > -1590 && y < -1390){
				windowSize = 2;
				program.c.setWindow(2);
				selection = 8;
			}
			else if(x > 840 && x < 1030 && y > -1590 && y < -1390){
				windowSize = 3;
				program.c.setWindow(3);
				selection = 8;
			}
			else if(x > 440 && x < 580 && y > -2000 && y < -1860){
				windowTypeSelected = 1;
				program.c.setWindowType(1);
				selection = 9;
			}
			else if(x > 660 && x < 820 && y > -2000 && y < -1860){
				windowTypeSelected = 2;
				program.c.setWindowType(2);
				selection = 9;
			}
			else if(x > 900 && x < 1060 && y > -2000 && y < -1860){
				windowTypeSelected = 3;
				program.c.setWindowType(3);
				selection = 9;
			}
			else if(x > 430 && x < 615 && y > -2570 && y < -2425){
				tempSelected = 1;
				program.c.setHeat(1);
				selection = 10;
			}
			else if(x > 615 && x < 855 && y > -2570 && y < -2425){
				tempSelected = 2;
				program.c.setHeat(2);
				selection = 10;
			}
			else if(x > 855 && x < 1060 && y > -2570 && y < -2425){
				tempSelected = 3;
				program.c.setHeat(3);
				selection = 10;
			}
			else if(x > 445 && x < 570 && y > -3115 && y < -2915){
				sysSizeSelected = 1;
				program.c.setSysSize(1);
				selection = 11;
			}
			else if(x > 600 && x < 760 && y > -3115 && y < -2915){
				sysSizeSelected = 2;
				program.c.setSysSize(2);
				selection = 11;
			}
			else if(x > 810 && x < 1040 && y > -3115 && y < -2915){
				sysSizeSelected = 3;
				program.c.setSysSize(3);
				selection = 11;
			}
			else if(x > 450 && x < 1030 && y > 420 && y < 580){

				program.actionResolver.createAlanDialog();
				selection = 4;

			}

		}
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
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
