package com.nng.cokygame.enums;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

public class ScreenManager {
	
	private static ScreenManager instance;
	private Game game;
	
	public ScreenManager() {
		super();
	}
	
	public static ScreenManager getInstance() {
		if(instance==null) 
			instance = new ScreenManager();
		return instance;
	}
	
	public void initializate(Game game){
		this.game = game;
	}
	
	public void showScreen(ScreenEnum screenEnum){
		Screen currentScreen = game.getScreen();
		Screen newScreen = screenEnum.getScreen();
		game.setScreen(newScreen);
		if(currentScreen != null)
			currentScreen.dispose();
	}
	
	public Game getGame(){
		return game;
	}

}
