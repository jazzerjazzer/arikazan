package com.po.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.Net.HttpRequest;
import com.badlogic.gdx.Net.HttpResponse;
import com.badlogic.gdx.Net.HttpResponseListener;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.po.kazan.MainProgram;

public class NetAPITest implements Screen, HttpResponseListener {

	SpriteBatch batch;
	Texture texture;
	String text;
	BitmapFont font;
	HttpRequest httpRequest;

	MainProgram program;

	public boolean needsGL20 () {
		// just because the non pot, we could change the image instead...
		return true;
	}

	public NetAPITest(MainProgram program){
		this.program = program;
	}

	@Override
	public void dispose () {
		batch.dispose();

		font.dispose();
		if (texture != null) texture.dispose();
	}


	@Override
	public void handleHttpResponse (HttpResponse httpResponse) {

		final int statusCode = httpResponse.getStatus().getStatusCode();
		// We are not in main thread right now so we need to post to main thread for ui updates
		final byte[] rawImageBytes = httpResponse.getResult();
		Gdx.app.postRunnable(new Runnable() {
			public void run () {
				Pixmap pixmap = new Pixmap(rawImageBytes, 0, rawImageBytes.length);
				texture = new Texture(pixmap);
			}
		});

		if (statusCode != 200) {
			Gdx.app.log("NetAPITest", "An error ocurred since statusCode is not OK");
			return;
		}
	}


	@Override
	public void failed (Throwable t) {

		t.printStackTrace();
	}


	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		if (texture != null) {
			batch.begin();
			batch.draw(texture, 300, 300, 200, 200);
			batch.end();
		} else if (text != null) {
			batch.begin();
			font.drawMultiLine(batch, text, 10, Gdx.graphics.getHeight() - 10);
			batch.end();
		}
	}

	@Override
	public void show() {
		System.out.println("show start");
		batch = new SpriteBatch();
		font = new BitmapFont();

		if (texture != null) texture.dispose();
		texture = null;
		text = null;
	
		String url;
		String httpMethod = Net.HttpMethods.GET;
		String requestContent = null;
		url = "http://arikazan.com.tr/wp-content/uploads/2014/05/ack2-2.jpg";
	
		httpRequest = new HttpRequest(httpMethod);
		httpRequest.setUrl(url);
		httpRequest.setContent(requestContent);
		Gdx.net.sendHttpRequest(httpRequest, NetAPITest.this);
		
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
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

}