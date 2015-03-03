package com.nng.cokygame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;

public class ScreenMainMenu extends AbstractScreen{
	
	private Texture iconPlay;
	ImageButton btnPlay;
	
	public ScreenMainMenu() {
		super();
		this.init();
	}
	
	private void init() {
		this.iconPlay = new Texture(Gdx.files.internal("img/ICON_PLAY.png"));
	}
	
	@Override
	public void buildStage() {

	}
	
	public void dispose(){
		super.dispose();
	}
	
}
