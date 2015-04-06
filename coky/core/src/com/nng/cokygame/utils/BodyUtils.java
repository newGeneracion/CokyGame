package com.nng.cokygame.utils;

import com.badlogic.gdx.physics.box2d.Body;
import com.nng.cokygame.box2d.UserData;
import com.nng.cokygame.enums.UserDataType;

public class BodyUtils 
{
	
	public static boolean bodyIsPlayer(Body body)
	{
		UserData userData = (UserData) body.getUserData();
		return userData.getUserDataType() == UserDataType.PLAYER;
	}
	
	public static boolean bodyIsGround(Body body)
	{
		UserData userData = (UserData) body.getUserData();
		return userData.getUserDataType() == UserDataType.GROUND;
	}

}
