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
	
	public static boolean bodyInBounds(Body body)
	{
		UserData userData = (UserData) body.getUserData();
		switch (userData.getUserDataType())
		{
			case PLAYER:
			case OBJECT_ENEMY_CACTUS:
				return body.getPosition().x + userData.getWidth() / 2 > 0;
		}
		
		return true;
	}
	
	public static boolean bodyIsObejctCactusEnemy(Body body)
	{
		UserData userData = (UserData) body.getUserData();
		return userData != null && userData.getUserDataType() == UserDataType.OBJECT_ENEMY_CACTUS;
	}

}
