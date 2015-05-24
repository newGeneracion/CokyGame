package com.nng.cokygame.enums;

import com.badlogic.gdx.Screen;
import com.nng.cokygame.screens.AbstractScreen;
import com.nng.cokygame.screens.ScreenCredits;
import com.nng.cokygame.screens.ScreenGame;
import com.nng.cokygame.screens.ScreenMainMenu;
import com.nng.cokygame.screens.ScreenSplash;
import com.nng.cokygame.screens.ScreenStatistics;

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
		public Screen getScreen(){
			return new ScreenGame();
		}
	},
	SCREEN_STATISTICS {
		public AbstractScreen getScreen(){
			return new ScreenStatistics();
		}
	},
	SCREEN_CREDITS {
		public AbstractScreen getScreen(){
			return new ScreenCredits();
		}
	};
	
	public abstract Screen getScreen();
}
