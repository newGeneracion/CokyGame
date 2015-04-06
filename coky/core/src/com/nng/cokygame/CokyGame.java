/**
 * help: http://www.pixnbgames.com/blog/libgdx/como-gestionar-varias-pantallas-en-libgdx/
 * help: http://www.pixnbgames.com/blog/libgdx/como-hacer-una-pantalla-de-inicio-o-splash-screen-en-libgdx/
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
	                                               ScreenManager.getInstance().showScreen(ScreenEnum.SCREEN_GAME);
	                                           }
	                                       }, (float)(Constants.SPLASH_MINIUM_MILLIS - splash_elapsed_time) / 1000f);
	                           } else {
	                        	   ScreenManager.getInstance().showScreen(ScreenEnum.SCREEN_GAME);
	                           }
	                       }
	                   });
	               }
	            }).start();
	    }
	 

}
