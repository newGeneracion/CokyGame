/**
 * Clase para manejar el splash al iniciar la aplicación
 * @help: http://www.pixnbgames.com/blog/libgdx/como-hacer-una-pantalla-de-inicio-o-splash-screen-en-libgdx/
 * @author user: JVillegas
 */

package com.nng.cokygame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ScreenSplash extends AbstractScreen{
	
	private SpriteBatch batch;
	private Texture     ttrSplash;
	
	public ScreenSplash() {
		super();
		this.batch = new SpriteBatch();
		this.ttrSplash = new Texture(Gdx.files.internal("img/IMAGE_SPLASH.png"));
	}
	
	public void render(float deltaTime) {
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		
		this.batch.begin();
			this.batch.draw(this.ttrSplash, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		this.batch.end();
		
	}
	
	public void dispose(){
		this.ttrSplash.dispose();
		this.batch.dispose();
	}

	@Override
	public void buildStage() {
	}

}
