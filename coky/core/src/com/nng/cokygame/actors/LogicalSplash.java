package com.nng.cokygame.actors;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.nng.cokygame.screens.ScreenSplash;
import com.nng.cokygame.utils.Constants;

public class LogicalSplash extends Table{
	
	private Game game;
	private Texture text;
	private ActorSplash as;
	
	public LogicalSplash(Game game, ScreenSplash screen ){
		
		this.text = new Texture(Gdx.files.internal("img/IMAGE_SPLASH.png"));
		this.setBounds(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
		this.setClip(true);
		this.game = game;
		
		this.as = new ActorSplash(this.game);
		this.addActor(as);
		
	}
	
	public void act(float deltaTime){
		super.act(deltaTime);
	}
	
	public void draw(SpriteBatch batch, float parentAlpha){
		batch.setColor(Color.WHITE);
		batch.draw(text, 0, 0);
		super.draw(batch, parentAlpha);
	}
}
