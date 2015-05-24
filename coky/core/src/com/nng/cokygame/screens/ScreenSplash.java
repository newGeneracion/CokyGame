/**
 * Clase para manejar el splash al iniciar la aplicación
 * @help: http://www.pixnbgames.com/blog/libgdx/como-hacer-una-pantalla-de-inicio-o-splash-screen-en-libgdx/
 * @author user: JVillegas
 */

package com.nng.cokygame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nng.cokygame.actors.ActorSplash;
import com.nng.cokygame.actors.LogicalSplash;
import com.nng.cokygame.enums.ScreenManager;

public class ScreenSplash extends AbstractScreen{
	
	private SpriteBatch batch;
	private Texture     ttrSplash;
	private LogicalSplash ls;
	private ScreenManager screenManager;
	private ActorSplash  actorSplash;
	
	public ScreenSplash() {
		super();
		this.batch = new SpriteBatch();
		this.ttrSplash = new Texture(Gdx.files.internal("img/IMAGE_SPLASH.png"));
		this.screenManager = new ScreenManager();
		this.ls = new LogicalSplash(screenManager.getGame(), this);
		this.addActor(ls);
		actorSplash = new ActorSplash(getGame());
		this.addActor(actorSplash);
	}
	@Override
	public void render(float deltaTime) {
		super.render(deltaTime);	
		this.batch.begin();
			this.batch.draw(this.ttrSplash, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		this.batch.end();
		this.draw();
		
	}
	
	public void dispose(){
		super.dispose();
		this.ttrSplash.dispose();
		this.batch.dispose();
	}

	@Override
	public void buildStage() {
	}
	
	@Override
	public void act(float delta) {
		super.act();
	}
	
	@Override
	public void draw() {
		super.draw();
	}

}
