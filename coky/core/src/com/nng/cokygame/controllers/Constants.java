package com.nng.cokygame.controllers;

public class Constants {
	
	// variables generales para el juego
	public final static String VARIABLE_PRUEBA = "Prueba Jimmy marzo 1 de 2015";
	public final static float SCREEN_WIDTH     = 320.0f;
	public final static float SCREEN_HEIGHT    = 240.0f;
	public final static float SCREEN_SCALE     = 2;
	
	// var for world Box2d
	// pixel per meter radio
	public static final float PPM       = 100;
	public static final float GRAVITY_X = 0.0f;
	public static final float GRAVITY_Y = -9.81f;
	
	// category bits for bodys and/or fixtures
	public static final short BIT_GROUND = 0;
	public static final short BIT_PALYER = 2;
	public static final short BIT_ACORN  = 4;
	
	// time for screen Splash	
	public static final long SPLASH_MINIUM_MILLIS = 3000L;
}
