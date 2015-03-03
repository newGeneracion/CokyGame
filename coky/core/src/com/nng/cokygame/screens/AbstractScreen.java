package com.nng.cokygame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.nng.cokygame.controllers.Constants;

public abstract class AbstractScreen extends Stage implements Screen{
	
	// protected OrthographicCamera camera;
	
	protected AbstractScreen(){
		//this.camera = new OrthographicCamera();
		//this.camera.setToOrtho(false, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
		super( new StretchViewport(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT, new OrthographicCamera()) );
	}
	
	public abstract void buildStage();
	
	public void render(float deltaTime){
		
		Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        super.act(deltaTime);
        super.draw();
	}
	
	public void show(){
		Gdx.input.setInputProcessor(this);
	}
	
	public void resize(int width, int height) {
		this.getViewport().update(width, height, true);
	}
	
	@Override public void hide() {}
    @Override public void pause() {}
    @Override public void resume() {}

}
