package com.nng.cokygame.handlers;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;

public class MyInputProcessor extends InputAdapter{
	
	public boolean keyDown(int key){
		if (key == Input.Keys.SPACE) 
			MyInput.setKey(MyInput.BUTTON_JUMPER, true);
		return true;
	}
	
	public boolean keyUp(int key){
		if (key == Input.Keys.SPACE)
			MyInput.setKey(MyInput.BUTTON_JUMPER, false);
		return true;
	}

}
