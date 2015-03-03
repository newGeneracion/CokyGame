package com.nng.cokygame.handlers;

public class MyInput {
	
	public static final boolean[] keys;
	public static final boolean[] pkeys;
	
	public static final int NUM_KEYS = 2;
	public static final int BUTTON_JUMPER = 0;
	
	static {
		keys = new boolean[NUM_KEYS];
		pkeys = new boolean[NUM_KEYS];
	}
	
	public static void update(){
		for(int i = 0; i < NUM_KEYS; i++){
			pkeys[i] = keys[i];
		}
	}
	
	public static void setKey(int i, boolean b){ keys[i] = b;}
	public static boolean isDown(int i){return keys[i];} 
	public static boolean isPressed(int i){
		return keys[i] && !pkeys[i];
	}

}
