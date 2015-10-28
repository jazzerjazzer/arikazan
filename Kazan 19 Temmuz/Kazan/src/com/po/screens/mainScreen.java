package com.po.screens;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.po.kazan.Calculation;
import com.po.kazan.MainProgram;

public class mainScreen implements Screen, InputProcessor {

	private MainProgram program;
	public OrthographicCamera camera;
	public SpriteBatch batch;
	
	private Texture bg, slider_en, slider_tr;
	private Texture mainLogo_en, mainLogo_tr, mainHeader, mainRecordElement;
	private Texture mainRecordHeader_en, mainRecordHeader_tr;
	
	private int x, y;
	private boolean sliderOn;
	private Vector3 tap = new Vector3(0,0,0);
	FileHandle saveFile = Gdx.files.absolute("/data/data/com.po.kazan/files/saveFinal.txt");
	
	private Texture calibriPartTexture;
	private BitmapFont calibriPartBitmap;
	
	private ArrayList<Calculation> savedCalculations;
	
	public mainScreen(MainProgram program) {
		this.program = program;
		program.c = new Calculation();
	}

	@Override
	public void render(float delta) {

		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		
		batch.draw(bg,0,0);
		
		if(program.lang.equals("tr")){
			batch.draw(slider_tr,0,0);
			batch.draw(mainHeader,x,y + 1920 - 160);
			batch.draw(mainLogo_tr,x,y + 1920 - 160 - 800);
			batch.draw(mainRecordHeader_tr,x,y + 1920 - 160 - 800 - 80);
		} else {
			batch.draw(slider_en,0,0);
			batch.draw(mainHeader,x,y + 1920 - 160);
			batch.draw(mainLogo_en,x,y + 1920 - 160 - 800);
			batch.draw(mainRecordHeader_en,x,y + 1920 - 160 - 800 - 80);
		}
		
		for(int i=1; i<6; i++){
			batch.draw(mainRecordElement,x,y + 1920 - 160 - 800 - 80 - (200)*i);
			if(i-1 < savedCalculations.size())
				calibriPartBitmap.draw(batch, savedCalculations.get(i-1).name, x + 100, y + 1920 - 160 - 800 - 80 - (200)*i + 150);
				
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
		
		Gdx.input.setInputProcessor(this);
        Gdx.input.setCatchBackKey(true);
	

		bg = program.textures.bg;
		slider_en = program.textures.slider_en;
		slider_tr = program.textures.slider_tr;
		mainLogo_en = program.textures.mainLogo_en;
		mainLogo_tr = program.textures.mainLogo_tr;
		mainHeader = program.textures.mainHeader;
		mainRecordElement = program.textures.mainRecordElement;
		mainRecordHeader_en = program.textures.mainRecordHeader_en;
		mainRecordHeader_tr = program.textures.mainRecordHeader_tr;
		
		x = 0; y = 0;
		
		String temp = saveFile.readString();
		
		String[] records = temp.split("\\r?\\n"); 
			
		savedCalculations = new ArrayList<Calculation>();
		
		for (int i = 0; i < records.length; i++){
			if(!records[i].contains("#"))
				break;
			String[] tempRecords = records[i].split("#");
				
			savedCalculations.add(new Calculation(tempRecords[11], Integer.parseInt(tempRecords[0]), Integer.parseInt(tempRecords[1]), 
					Integer.parseInt(tempRecords[2]), Integer.parseInt(tempRecords[3]), Integer.parseInt(tempRecords[4]), Integer.parseInt(tempRecords[5]), Integer.parseInt(tempRecords[6]), 
							Integer.parseInt(tempRecords[7]), Integer.parseInt(tempRecords[8]), Integer.parseInt(tempRecords[9]), Integer.parseInt(tempRecords[10])));
			
		}
		
		calibriPartTexture = new Texture(Gdx.files.internal("cal_part_0.png"));
		calibriPartTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear); 
		calibriPartBitmap = new BitmapFont(Gdx.files.internal("cal_part.fnt"), new TextureRegion(calibriPartTexture), false);
		
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
			
			if (Gdx.input.isKeyPressed(Keys.BACK)){
				
				Gdx.app.exit();
			}
			
			if(x < 200 && x > 0 && y < 1920 && y > 1750){
				sliderOn = !sliderOn;
			}
			
			if(x > 120 && x < 960 && y < 1760 && y > 1100){
			
				System.out.println("--------------**** main screen geçiþi-----------");
				FileHandle fh = Gdx.files.absolute("/data/data/com.po.kazan/files/hwlocation.txt");
				System.out.println(fh.readString());
				System.out.println("--------------**** end of main screen geçiþ-----------");
				
				
				program.setScreen(new calculationScreen(program));
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
				
				if( y < 880 && y > 680 && savedCalculations.size() >= 1){
					program.c = savedCalculations.get(savedCalculations.size()-1);
					//program.c.calculate();
					program.setScreen(new resultScreen(program));
				}
				if( y < 680 && y > 480 && savedCalculations.size() >= 2){
					program.c = savedCalculations.get(savedCalculations.size()-2);	
					program.setScreen(new resultScreen(program));
				}
				if( y < 480 && y > 280  && savedCalculations.size() >= 3){
					program.c = savedCalculations.get(savedCalculations.size()-3);
					program.setScreen(new resultScreen(program));
				}
				if( y < 280 && y > 80  && savedCalculations.size() >= 4){
					program.c = savedCalculations.get(savedCalculations.size()-4);
					program.setScreen(new resultScreen(program));
				}
			}
		}
	}
	
	@Override
	public boolean keyDown(int keycode) {
		if(keycode == Keys.BACK){
			
			Gdx.app.exit();
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
