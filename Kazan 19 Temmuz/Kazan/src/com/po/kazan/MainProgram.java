package com.po.kazan;

import java.io.IOException;
import java.util.Locale;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.po.screens.TextureDownloadTest;
import com.po.screens.splashScreen;

public class MainProgram extends Game {

	public Textures textures = new Textures();
	public jsonRequests json = new jsonRequests();
	public Calculation c = new Calculation();
	
	public String lang = "en";
	
	public boolean resultLocationAcquired = false;
	public boolean resultLocationFinal = false;
	public ReadTxt rt;
	public boolean disclaimerIsShown = false;
	public boolean toastShown = false;
	public boolean storeyDialogEntered = false;
	
	GL10 gl;
	public ActionResolver actionResolver;
	MainProgram(ActionResolver actionResolver)
	{
		this.actionResolver = actionResolver;
	}
	
	public MainProgram() {

	}

	@Override
	public void create() {
		
		Texture.setEnforcePotImages(false);
		
		gl = Gdx.app.getGraphics().getGL10();
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();
		
		lang = Locale.getDefault().getDisplayLanguage();
		
		if(lang.equals("Türkçe"))
			lang = "tr";
				
		this.setScreen(new splashScreen(this));
	}

	@Override
	public void dispose() {
	}

	@Override
	public void render() {	
		super.render();
	}
	
}
