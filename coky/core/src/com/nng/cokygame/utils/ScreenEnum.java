package com.nng.cokygame.utils;

import com.nng.cokygame.screens.AbstractScreen;
import com.nng.cokygame.screens.ScreenCredits;
import com.nng.cokygame.screens.ScreenGame;
import com.nng.cokygame.screens.ScreenMainMenu;
import com.nng.cokygame.screens.ScreenSplash;

public enum ScreenEnum {
	
	SCREEN_SPLASH {
		public ScreenSplash getScreen(){
			return new ScreenSplash();
		}
	},
	SCREEN_MAIN_MENU {
		public AbstractScreen getScreen(){
			return new ScreenMainMenu();
		}
	}, 
	SCREEN_GAME {
		public AbstractScreen getScreen(){
			return new ScreenGame();
		}
		
	},
	SCREEN_CREDITS {
		public AbstractScreen getScreen(){
			return new ScreenCredits();
		}
	};
	
	public abstract AbstractScreen getScreen();
}
