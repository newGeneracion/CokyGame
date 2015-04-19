package com.nng.cokygame.utils;

import com.badlogic.gdx.math.Vector2;

public class Constants {
	
	// variables generales para el juego
	public final static String GAME_VERSION    = "1.0";
	public final static float SCREEN_WIDTH     = 800.0f;
	public final static float SCREEN_HEIGHT    = 480.0f;
	public final static float SCREEN_SCALE     = 2;
	public final static float GAME_STEP        = 1/300f;
	
	// config for android 
	public static final boolean ANDROID_USE_COMPASS = false;
	public static final boolean ANDROID_USER_ACCELEROMETER = false;
	
	// var for world Box2d
	// pixel per meter radio
	public static final float PPM       = 100;
	public static final float WORLD_GRAVITY_X = 0.0f;
	public static final float WORLD_GRAVITY_Y = -10f;
	public static final float WORLD_TO_SCREEN = 32.0f;
	
	// category bits for bodys and/or fixtures
	// NOTA MENTAL: no tomar como referencia  el 0, ya que no sirve
	public static final short BIT_GROUND = 1;
	public static final short BIT_PLAYER = 2;
	public static final short BIT_PLAYER_FOOT = 4;
	public static final short BIT_ACORN  = 8;
	
	// var for userData 
	public static final String USER_DATA_PLAYER = "player";
	public static final String USER_DATA_PLAYER_FOOT = "player_foot";
	public static final String USER_DATA_GROUND = "ground";
	public static final String USER_DATA_ACORN  = "acorn";
	
	// time for screen Splash	
	public static final long SPLASH_MINIUM_MILLIS = 3000L;
	
	
	// info for ground
	public static final float GROUND_X = 0;
	public static final float GROUND_Y = 0;
	public static final float GROUND_WIDTH   = 50f;
	public static final float GROUND_HEIGHT  = 2f;
	public static final float GROUND_DENSITY = 0f;
	public static final float GROUND_FRICTION = 0f;
	public static final float GROUND_RESTITUTION = 0f;
	
	// info for player
	public static final float PLAYER_X = 2;
	public static final float PLAYER_Y = GROUND_Y + GROUND_HEIGHT - 0.5f;
	public static final float PLAYER_WIDTH = 1f;
	public static final float PLAYER_HEIGHT = 1f;
	public static final float PLAYER_DENSITY = 0.5f;
	public static final float PLAYER_FRICTION = 0f;
	public static final float PLAYER_RESTITUTION = 0f;
	public static final float PLAYER_GRAVITY_SCALE = 7f;
	public static final Vector2 PLAYER_JUMPING_LINEAR_IMPULSE = new Vector2(0, 13f);
	public static final float PLAYER_HIT_ANGULAR_IMPULSE = 10f;
	
	// info for images
	public static final String BACKGROUND_IMAGE_PATH_1 = "img/BACKGROUND_IMAGE_2.png";
	public static final String BACKGROUND_IMAGE_PATH_2 = "";
	public static final String BACKGORUND_IMAGE_PATH_3 = "";
	
	public static final String GROUND_IMAGE_PATH_1 = "img/GROUND_1.png";
	public static final String GROUND_IMAGE_PATH_2 = "";
	
	public static final String PLAYER_IMAGE_PATH_1 = "img/PLAYER_1.png";
}
