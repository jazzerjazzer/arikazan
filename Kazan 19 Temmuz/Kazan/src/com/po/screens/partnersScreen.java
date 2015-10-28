package com.po.screens;

import java.util.ArrayList;
import java.util.Collections;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
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
import com.po.kazan.contacts;

public class partnersScreen implements Screen, InputProcessor, GestureListener {

	private MainProgram program;
	public OrthographicCamera camera;
	public SpriteBatch batch;

	private Texture bg, slider_en, slider_tr, mainHeader;
	private Texture partner_selected, partner_unselected, partnerInfo;

	private int x, y;
	private boolean sliderOn;
	private Vector3 tap = new Vector3(0,0,0);

	private ArrayList <String> countries = new ArrayList <String>();
	private int countrySelect=0;
	private ArrayList<contacts> c = new ArrayList<contacts>();
	
	private Texture calibriBasTexture;
	private BitmapFont calibriBasBitmap;
	
	private Texture calibriPartTexture;
	private BitmapFont calibriPartBitmap;
	
	
	
	InputMultiplexer multiplexer = new InputMultiplexer();

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

	public partnersScreen(MainProgram program) {
		this.program = program;
	}

	@Override
	public void render(float delta) {
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		

		if(program.lang.equals("tr")){
			batch.draw(slider_tr,0,0);
			batch.draw(bg,0,y-5780);
			batch.draw(mainHeader,x,y + 1920 - 160);
			for(int i=1; i<countries.size()+1; i++){
	
				if(countrySelect != 0){
					if(countrySelect == i){
						batch.draw(partner_selected,x,y + 1920 - 160  - (160)*i);
						calibriBasBitmap.draw(batch, countries.get(i-1), x + 50 ,y+1920-160 - 160*i + 100);
						// ayrýntýlarý çiz
						for(int j=0; j<c.size(); j++){
							batch.draw(partnerInfo,x,y + 1920 - 160  - (160)*i - 520*(j+1));
							//kalLetterFont.draw(batch, c.get(j).getCity(), x+100, y +100 + 1920 - 160  - (160)*i - 500*(j+1));
						
							calibriBasBitmap.draw(batch, c.get(j).getName(), x+100, y + 1920 - 160 - (160)*i - 520*(j+1) + 460);
						
							if(!c.get(j).getAdress().equals("-1"))
								calibriPartBitmap.draw(batch, c.get(j).getAdress(), x+100, y + 1920 - 160 - (160)*i - 520*(j+1) + 385);
							
							if(!c.get(j).getCity().equals("-1"))
								calibriPartBitmap.draw(batch, c.get(j).getCity(), x+100, y + 1920 - 160 - (160)*i - 520*(j+1) + 350);
							
							//if(!c.get(j).getCountry().equals("-1"))
								//calibriPartBitmap.draw(batch, c.get(j).getCountry(), x+200, y + 1920 - 160 - (160)*i - 520*(j+1) + 355);
							
							if(!c.get(j).getPhone().equals("-1"))
								calibriPartBitmap.draw(batch, c.get(j).getPhone(), x+200, y + 1920 - 160 - (160)*i - 520*(j+1) + 275);
							
							if(!c.get(j).getEmail().equals("-1"))
								calibriPartBitmap.draw(batch, c.get(j).getEmail(), x+200, y + 1920 - 160 - (160)*i - 520*(j+1) + 175);
							
							if(!c.get(j).getWebsite().equals("-1"))
								calibriPartBitmap.draw(batch, c.get(j).getWebsite(), x+200, y + 1920 - 160 - (160)*i - 520*(j+1) + 75);
						}
					} 
					if(countrySelect < i){
						batch.draw(partner_unselected,x,y + 1920 - 160  - (160)*i - 520*c.size());
						calibriBasBitmap.draw(batch, countries.get(i-1), x + 50 ,y+1920-160 - 160*i + 100 - 520*c.size());
					} else {
						batch.draw(partner_unselected,x,y + 1920 - 160  - (160)*i);
						calibriBasBitmap.draw(batch, countries.get(i-1), x + 50 ,y+1920-160 - 160*i + 100);
					}
					
				} else {
					batch.draw(partner_unselected,x,y + 1920 - 160  - (160)*i);
					calibriBasBitmap.draw(batch, countries.get(i-1), x + 50 ,y+1920-160 - 160*i + 100);
				}

			}

		} else {
			batch.draw(slider_en,0,0);
			batch.draw(bg,0,y-5780);
			batch.draw(mainHeader,x,y + 1920 - 160);
			
			for(int i=1; i<countries.size()+1; i++){
	
				if(countrySelect != 0){
					if(countrySelect == i){
						batch.draw(partner_selected,x,y + 1920 - 160  - (160)*i);
						calibriBasBitmap.draw(batch, countries.get(i-1), x + 50 ,y+1920-160 - 160*i + 100);
						// ayrýntýlarý çiz
						for(int j=0; j<c.size(); j++){
							batch.draw(partnerInfo,x,y + 1920 - 160  - (160)*i - 520*(j+1));
							//kalLetterFont.draw(batch, c.get(j).getCity(), x+100, y +100 + 1920 - 160  - (160)*i - 500*(j+1));
						
							calibriBasBitmap.draw(batch, c.get(j).getName(), x+100, y + 1920 - 160 - (160)*i - 520*(j+1) + 460);
						
							if(!c.get(j).getAdress().equals("-1"))
								calibriPartBitmap.draw(batch, c.get(j).getAdress(), x+100, y + 1920 - 160 - (160)*i - 520*(j+1) + 385);
							
							if(!c.get(j).getCity().equals("-1"))
								calibriPartBitmap.draw(batch, c.get(j).getCity(), x+100, y + 1920 - 160 - (160)*i - 520*(j+1) + 350);
							
							//if(!c.get(j).getCountry().equals("-1"))
								//calibriPartBitmap.draw(batch, c.get(j).getCountry(), x+200, y + 1920 - 160 - (160)*i - 520*(j+1) + 355);
							
							if(!c.get(j).getPhone().equals("-1"))
								calibriPartBitmap.draw(batch, c.get(j).getPhone(), x+200, y + 1920 - 160 - (160)*i - 520*(j+1) + 275);
							
							if(!c.get(j).getEmail().equals("-1"))
								calibriPartBitmap.draw(batch, c.get(j).getEmail(), x+200, y + 1920 - 160 - (160)*i - 520*(j+1) + 175);
							
							if(!c.get(j).getWebsite().equals("-1"))
								calibriPartBitmap.draw(batch, c.get(j).getWebsite(), x+200, y + 1920 - 160 - (160)*i - 520*(j+1) + 75);
						}
					} 
					if(countrySelect < i){
						batch.draw(partner_unselected,x,y + 1920 - 160  - (160)*i - 520*c.size());
						calibriBasBitmap.draw(batch, countries.get(i-1), x + 50 ,y+1920-160 - 160*i + 100 - 520*c.size());
					} else {
						batch.draw(partner_unselected,x,y + 1920 - 160  - (160)*i);
						calibriBasBitmap.draw(batch, countries.get(i-1), x + 50 ,y+1920-160 - 160*i + 100);
					}
					
				} else {
					batch.draw(partner_unselected,x,y + 1920 - 160  - (160)*i);
					calibriBasBitmap.draw(batch, countries.get(i-1), x + 50 ,y+1920-160 - 160*i + 100);
				}

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
		Gdx.input.setCatchBackKey(true);

		countrySelect = 0;
		
		bg = program.textures.bg;
		slider_en = program.textures.slider_en;
		slider_tr = program.textures.slider_tr;
		partner_selected = program.textures.partner_selected;
		partner_unselected = program.textures.partner_unselected;
		partnerInfo = program.textures.partnerInfo;
		mainHeader = program.textures.mainHeader;
		x = 0; y = 0;

		multiplexer.addProcessor(gd);
		multiplexer.addProcessor(ip);
		Gdx.input.setInputProcessor(multiplexer);
		Gdx.input.setCatchBackKey(true);
		
		
		for (int i = 0; i < program.rt.contactsObjectsFromTxt.length; i++){
			if(!countries.contains(program.rt.contactsObjectsFromTxt[i].getCountry())){
				countries.add(program.rt.contactsObjectsFromTxt[i].getCountry());
			}
		}

		Collections.sort(countries.subList(0,countries.size()));
		
		calibriBasTexture = new Texture(Gdx.files.internal("cal_bas_0.png"));
		calibriBasTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear); 
		calibriBasBitmap = new BitmapFont(Gdx.files.internal("cal_bas.fnt"), new TextureRegion(calibriBasTexture), false);
		
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

			y -= this.y; 
			
			if (Gdx.input.isKeyPressed(Keys.BACK)){
				
				program.setScreen(new mainScreen(program));
			}
			

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
			}else {

				if(x > 800 && x < 1080){
					/*
					if(countries.size()-countrySelect == y / 160+2)
						countrySelect = 0;
					else{
						countrySelect = y / 160+2;

						String tempName = countries.get(countries.size()-1-countrySelect);
						c = new ArrayList<contacts>();
						for(int i = 0; i < program.rt.contactsObjectsFromTxt.length; i++){
							
							if(program.rt.contactsObjectsFromTxt[i].getCountry().equals(tempName)){
								c.add(program.rt.contactsObjectsFromTxt[i]);
							}
						}

						countrySelect = countries.size()-1-countrySelect;
					}
					System.out.println("country sel: "+countrySelect);
					System.out.println(c.size());
					*/

					if(countrySelect == 0){
						int selected = (1920 - y)/160;
						System.out.println("selected: " + selected);
						countrySelect = selected;
						String name = countries.get(countrySelect-1);
						c = new ArrayList<contacts>();
						for(int i=0; i<program.rt.contactsObjectsFromTxt.length; i++){
							if(program.rt.contactsObjectsFromTxt[i].getCountry().equals(name)){
								c.add(program.rt.contactsObjectsFromTxt[i]);
							}
						}
					} else {
						/*
						String name = countries.get(countrySelect-1);
						int contacts = 0;
						
						for(int i=0; i<program.rt.contactsObjectsFromTxt.length; i++){
							if(program.rt.contactsObjectsFromTxt[i].getCountry().equals(name)){
								contacts++;
							}
						}
						System.out.println(" contacts: " + contacts);

						int selected = (1920 - 520*contacts - y)/160;
						countrySelect = selected;
						*/
						int selected = (1920 - y) / 160;
						
						if(countrySelect > selected){
							
							System.out.println("Yukardaki");
							System.out.println("Country Select " + countrySelect);
							System.out.println("Selected " + selected);
							
							countrySelect = selected;

							String name = countries.get(countrySelect-1);
							c = new ArrayList<contacts>();
							for(int i=0; i<program.rt.contactsObjectsFromTxt.length; i++){
								if(program.rt.contactsObjectsFromTxt[i].getCountry().equals(name)){
									c.add(program.rt.contactsObjectsFromTxt[i]);
								}
							}
							
						} else if(countrySelect < selected) {
							
							y += c.size()*520;
							selected = (1920 - y) / 160;
							
							System.out.println("AßaÛÝdaki: " + y);
							
							countrySelect = selected;

							String name = countries.get(countrySelect-1);
							c = new ArrayList<contacts>();
							for(int i=0; i<program.rt.contactsObjectsFromTxt.length; i++){
								if(program.rt.contactsObjectsFromTxt[i].getCountry().equals(name)){
									c.add(program.rt.contactsObjectsFromTxt[i]);
								}
							}
							
						} else {
							c = new ArrayList<contacts>();
							countrySelect = 0;
						}
						
						
						
					}
					System.out.println("selected dÝßarda: " + countrySelect);
					System.out.println(" partners tÝk: " + x + " " + y);
					
				}
			}
		}
	}

	@Override
	public boolean keyDown(int keycode) {

		if(keycode == Keys.BACK){
			
			program.setScreen(new mainScreen(program));
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
		
		this.y += (-2)*deltaY;

		if(this.y < 0)
			this.y = 0;

		if(this.y > (2400 + c.size()*520 - 1920))
			this.y = (2400 + c.size()*520 - 1920);

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

}
