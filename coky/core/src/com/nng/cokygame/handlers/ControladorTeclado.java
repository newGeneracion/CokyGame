package com.nng.cokygame.handlers;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;

public class ControladorTeclado extends InputAdapter{
	
	private PLAYER_ESTATE estadoJugador;
	
	public ControladorTeclado() {
		estadoJugador = PLAYER_ESTATE.STATIC;
	}
	
	public boolean keyDown(int keyCode){
		
		switch(keyCode){
			case Input.Keys.E:
				MyInput.setKey(MyInput.BUTTON_GAME_EXIT, true);
				break;
			case Input.Keys.SPACE:
				if (estadoJugador == PLAYER_ESTATE.MOVING)
					MyInput.setKey(MyInput.BUTTON_PLAYER_JUMPER, true);
				else
					MyInput.setKey(MyInput.BUTTON_PLAYER_RUN, true);
				break;
			case Input.Keys.R:
				MyInput.setKey(MyInput.BUTTON_GAME_RESTART, true);
				break;		 
		}
		return true;
	}
	
	public boolean keyUp(int keyCode){
		switch(keyCode){
		    case Input.Keys.E:
			    MyInput.setKey(MyInput.BUTTON_GAME_EXIT, false);
			    break;
			case Input.Keys.SPACE:
				if (estadoJugador == PLAYER_ESTATE.MOVING){ 
					MyInput.setKey(MyInput.BUTTON_PLAYER_JUMPER, false);
		        }else {
					MyInput.setKey(MyInput.BUTTON_PLAYER_RUN, false);
					estadoJugador = PLAYER_ESTATE.MOVING;
				}
				break;
			case Input.Keys.R:
				MyInput.setKey(MyInput.BUTTON_GAME_RESTART, false);
				break;		 
		}
		return true;
	}
	
	private enum PLAYER_ESTATE {
		STATIC, MOVING
	}
	
	

}
