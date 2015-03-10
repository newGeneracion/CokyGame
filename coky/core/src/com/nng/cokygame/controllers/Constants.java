package com.nng.cokygame.controllers;

public class Constants {
	
	// variables generales para el juego
	public final static String VARIABLE_PRUEBA = "Prueba Jimmy marzo 1 de 2015";
	public final static float SCREEN_WIDTH     = 320.0f;
	public final static float SCREEN_HEIGHT    = 240.0f;
	public final static float SCREEN_SCALE     = 2;
	public final static float GAME_STEP        = 1/60f;
	
	// var for world Box2d
	// pixel per meter radio
	public static final float PPM       = 100;
	public static final float GRAVITY_X = 0.0f;
	public static final float GRAVITY_Y = -9.81f;
	
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
}
