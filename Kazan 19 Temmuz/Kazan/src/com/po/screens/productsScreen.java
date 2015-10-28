package com.po.screens;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.Net.HttpRequest;
import com.badlogic.gdx.Net.HttpResponse;
import com.badlogic.gdx.Net.HttpResponseListener;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
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

public class productsScreen implements Screen, InputProcessor, HttpResponseListener, GestureListener {

	private MainProgram program;
	public OrthographicCamera camera;
	public SpriteBatch batch;

	private Texture bg, slider_en, slider_tr, mainHeader;
	private Texture productLabel;

	private int x, y, allTextures;
	private boolean sliderOn;
	private Vector3 tap = new Vector3(0,0,0);

	private Texture calibriLetterTexture;
	private BitmapFont calibriLetterFont;

	private Texture calibriCapitalTexture;
	private BitmapFont calibriCapitalFont;
	HttpRequest httpRequest;
	private ArrayList<Texture> productTextures;
	private ArrayList<ArrayList<Texture>> productImagesArrayList = new ArrayList<ArrayList<Texture>>();

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


	public productsScreen(MainProgram program) {
		this.program = program;
	}

	@Override
	public void render(float delta) {
		batch.setProjectionMatrix(camera.combined);
		batch.begin();

		batch.draw(bg,0,0);

		if(program.lang.equals("tr")){
			batch.draw(slider_tr,0,0);
			batch.draw(mainHeader,x,y + 1920 - 160);
			int k = 0;
			for(int i=1; i < program.rt.productsObjectsFromTxt.length+1; i++) {
				batch.draw(productLabel,x,y + 1920 - 160  - (500)*i);
				calibriCapitalFont.draw(batch, program.rt.productsObjectsFromTxt[i-1].getName() + " Serisi",x +100,y+ 1920 - 160  - (500)*i +450 );
				if(program.rt.productsObjectsFromTxt[i-1].getCatLocDesc()[0].getLocDesc().length() > 70){
					calibriLetterFont.draw(batch, program.rt.productsObjectsFromTxt[i-1].getCatLocDesc()[0].getLocDesc().substring(0,70),x +100,y+ 1920 - 160  - (500)*i +140 );
					if(program.rt.productsObjectsFromTxt[i-1].getCatLocDesc()[0].getLocDesc().length() > 140){
						calibriLetterFont.draw(batch, program.rt.productsObjectsFromTxt[i-1].getCatLocDesc()[0].getLocDesc().substring(70,140) + "...",x +100,y+ 1920 - 160  - (500)*i +100 );
					}else{
						calibriLetterFont.draw(batch, program.rt.productsObjectsFromTxt[i-1].getCatLocDesc()[0].getLocDesc().substring(70) + "...",x +100,y+ 1920 - 160  - (500)*i +100 );

					}
				}else{
					calibriLetterFont.draw(batch, program.rt.productsObjectsFromTxt[i-1].getCatLocDesc()[0].getLocDesc(),x +100,y+ 1920 - 160  - (500)*i +140 );					
				}

				if(allTextures == productTextures.size()){
					for (int j = 0 ; j < program.rt.productsObjectsFromTxt[i-1].getImageURLs().length; j++, k++){
						if(j==4)
							break;
						batch.draw(productTextures.get(k), x + j * 180 + 100, y + 1920 - 160 - 500*i + 200, 150, 150);
					}
				}
			}

		} else {
			batch.draw(slider_en,0,0);
			batch.draw(mainHeader,x,y + 1920 - 160);
		
			int k = 0;
			for(int i=1; i < program.rt.productsObjectsFromTxt.length+1; i++) {
				batch.draw(productLabel,x,y + 1920 - 160  - (500)*i);
				calibriCapitalFont.draw(batch, program.rt.productsObjectsFromTxt[i-1].getName() + " Series",x +100,y+ 1920 - 160  - (500)*i +450 );
				if(program.rt.productsObjectsFromTxt[i-1].getCatLocDesc()[1].getLocDesc().length() > 70){
					calibriLetterFont.draw(batch, program.rt.productsObjectsFromTxt[i-1].getCatLocDesc()[1].getLocDesc().substring(0,70),x +100,y+ 1920 - 160  - (500)*i +140 );
					if(program.rt.productsObjectsFromTxt[i-1].getCatLocDesc()[1].getLocDesc().length() > 140){
						calibriLetterFont.draw(batch, program.rt.productsObjectsFromTxt[i-1].getCatLocDesc()[1].getLocDesc().substring(70,140) + "...",x +100,y+ 1920 - 160  - (500)*i +100 );
					}else{
						calibriLetterFont.draw(batch, program.rt.productsObjectsFromTxt[i-1].getCatLocDesc()[1].getLocDesc().substring(70) + "...",x +100,y+ 1920 - 160  - (500)*i +100 );

					}
				}else{
					calibriLetterFont.draw(batch, program.rt.productsObjectsFromTxt[i-1].getCatLocDesc()[1].getLocDesc(),x +100,y+ 1920 - 160  - (500)*i +140 );					
				}

				if(allTextures == productTextures.size()){
					for (int j = 0 ; j < program.rt.productsObjectsFromTxt[i-1].getImageURLs().length; j++, k++){
						if(j==4)
							break;
						batch.draw(productTextures.get(k), x + j * 180 + 100, y + 1920 - 160 - 500*i + 200, 150, 150);
					}
				}
			}
		}
		
		System.out.println(productTextures.size());

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

		multiplexer.addProcessor(gd);
		multiplexer.addProcessor(ip);
		Gdx.input.setInputProcessor(multiplexer);
		Gdx.input.setCatchBackKey(true);

		bg = program.textures.bg;
		slider_en = program.textures.slider_en;
		slider_tr = program.textures.slider_tr;
		productLabel = program.textures.productLabel;
		mainHeader = program.textures.mainHeader;


		calibriLetterTexture = new Texture(Gdx.files.internal("calibri_0.png"));
		calibriLetterTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear); 
		calibriLetterFont = new BitmapFont(Gdx.files.internal("calibri.fnt"), new TextureRegion(calibriLetterTexture), false);
		calibriLetterFont.setColor(0,0,1,1);

		calibriCapitalTexture = new Texture(Gdx.files.internal("calibri_capital_header_0.png"));
		calibriCapitalTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear); 
		calibriCapitalFont = new BitmapFont(Gdx.files.internal("calibri_capital_header.fnt"), new TextureRegion(calibriCapitalTexture), false);
		calibriLetterFont.setColor(0,0,1,1);
		
		String url;
		String httpMethod = Net.HttpMethods.GET;
		String requestContent = null;
		httpRequest = new HttpRequest(httpMethod);
		httpRequest.setContent(requestContent);

		allTextures = 0;
		for(int i=1; i < program.rt.productsObjectsFromTxt.length+1; i++) {
			allTextures += program.rt.productsObjectsFromTxt[i-1].getImageURLs().length;
		}
		
		for (int i = 0; i < program.rt.productsObjectsFromTxt.length; i++){
			
			productTextures = new ArrayList<Texture>();
			for (int j = 0; j < program.rt.productsObjectsFromTxt[i].getImageURLs().length; j++ ){
				
				url = program.rt.productsObjectsFromTxt[i].getImageURLs()[j];
				httpRequest.setUrl(url);
				Gdx.net.sendHttpRequest(httpRequest, productsScreen.this);
				System.out.println(j + " url: " + url);
			}
		}
			
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

				program.setScreen(new calculationScreen(program));
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
			} else {
				
				if(x<1080 && x > 900){
				y -= this.y;
				int selectedProduct = (1920 - 160 - y) / 500;
				String pdfURL = program.rt.productsObjectsFromTxt[selectedProduct].getPdfURLs()[0];
				program.actionResolver.displayPDF(pdfURL);
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
	public void handleHttpResponse(HttpResponse httpResponse) {

		final int statusCode = httpResponse.getStatus().getStatusCode();
		// We are not in main thread right now so we need to post to main thread for ui updates
		final byte[] rawImageBytes = httpResponse.getResult();
		Gdx.app.postRunnable(new Runnable() {
			public void run () {
				Pixmap pixmap = new Pixmap(rawImageBytes, 0, rawImageBytes.length);
				productTextures.add(new Texture(pixmap));
			}
		});

		if (statusCode != 200) {
			Gdx.app.log("NetAPITest", "An error ocurred since statusCode is not OK");
			return;
		}

	}

	@Override
	public void failed(Throwable t) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean pan(float x, float y, float deltaX, float deltaY) {

		this.y += (-2)*deltaY;

		if(this.y < 0)
			this.y = 0;

		if(this.y > (program.rt.productsObjectsFromTxt.length-1)*500 - 1260)
			this.y = (program.rt.productsObjectsFromTxt.length-1)*500- 1260;

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
