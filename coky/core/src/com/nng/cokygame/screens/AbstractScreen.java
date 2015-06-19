package com.nng.cokygame.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.nng.cokygame.utils.Constants;

public abstract class AbstractScreen extends Stage implements Screen{
	
	// protected OrthographicCamera camera;
	
	protected Game game;
	protected Skin skin;
	
	protected AbstractScreen(){
		//this.camera = new OrthographicCamera();
		//this.camera.setToOrtho(false, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
		super( new StretchViewport(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT, new OrthographicCamera()) );
	}
	
	protected AbstractScreen(Game game)
	{
		super( new StretchViewport(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT, new OrthographicCamera()) );
		this.game = game;
	}
	
	protected Game getGame()
	{
		return game;
	}
	
	public abstract void buildStage();
	
	public void render(float deltaTime){
		
		Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        super.act(deltaTime);
        super.draw();
	}
	
	public void show(){
		// Gdx.input.setInputProcessor(this);
	}
	

	public void resize(int width, int height) {
		this.getViewport().update(width, height, true);
	}
	
	@Override public void hide() {}
    @Override public void pause() {}
    @Override public void resume() {}
    
    protected Skin getSkin()
    {
    	if (skin == null)
    	{
    		FileHandle skinFile = Gdx.files.internal("skin/uiskin.json");
    		skin = new Skin(skinFile);
    	}
    	return skin;
    }
    
    public void dipose()
    {
    	if (skin != null) skin.dispose();
    }

}
