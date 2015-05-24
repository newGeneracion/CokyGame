/**
 * help: http://www.pixnbgames.com/blog/libgdx/como-gestionar-varias-pantallas-en-libgdx/
 * help: http://www.pixnbgames.com/blog/libgdx/como-hacer-una-pantalla-de-inicio-o-splash-screen-en-libgdx/
 * help: http://marketplace.eclipse.org/content/json-editor-plugin
 * help: https://code.google.com/p/steigert-libgdx/source/browse/trunk/tyrian-game/src/com/blogspot/steigert/tyrian/screens/MenuScreen.java
 * @author user: JVillegas
 */

package com.nng.cokygame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Timer;
import com.nng.cokygame.enums.ScreenEnum;
import com.nng.cokygame.enums.ScreenManager;
import com.nng.cokygame.utils.Constants;

public class CokyGame extends Game {

	 public void create () {
		 
		 	ScreenManager.getInstance().initializate(this);
		 	// ScreenManager.getInstance().showScreen(ScreenEnum.SCREEN_SPLASH);
		 	ScreenManager.getInstance().showScreen(ScreenEnum.SCREEN_SPLASH);
		 	
	        final long splash_start_time = System.currentTimeMillis();
	        new Thread(new Runnable() {
	               @Override
	               public void run() {
	 
	                   Gdx.app.postRunnable(new Runnable() {
	                       public void run() {
	                           // ... carga de datos, fuentes tipograficas, sonidos, imagenes, recursos de internacionalización
	 
	                           // Se muestra el menu principal tras la SpashScreen
	                           long splash_elapsed_time = System.currentTimeMillis() - splash_start_time;
	                           if (splash_elapsed_time < Constants.SPLASH_MINIUM_MILLIS) {
	                               Timer.schedule(
	                                       new Timer.Task() {
	                                           @Override
	                                           public void run() {
	                                               // ScreenManager.getInstance().showScreen(ScreenEnum.SCREEN_GAME);
	                                        	   ScreenManager.getInstance().showScreen(ScreenEnum.SCREEN_MAIN_MENU);
	                                           }
	                                       }, (float)(Constants.SPLASH_MINIUM_MILLIS - splash_elapsed_time) / 700f);
	                           } else {
	                        	   ScreenManager.getInstance().showScreen(ScreenEnum.SCREEN_GAME);
	                           }
	                       }
	                   });
	               }
	            }).start();
	            
	    }
	 

}
