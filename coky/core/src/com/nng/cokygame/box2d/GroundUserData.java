package com.nng.cokygame.box2d;

import com.nng.cokygame.enums.UserDataType;

public class GroundUserData extends UserData 
{
	public GroundUserData(float width, float height)
	{
		super(width, height);
		userDataType = UserDataType.GROUND;		
	}

}
