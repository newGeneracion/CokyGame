package com.nng.cokygame.box2d;

import com.nng.cokygame.enums.UserDataType;

public abstract class UserData {
	
	protected UserDataType userDataType;
	protected float width;
	protected float height;
	
	public UserData()
	{
	}
	
	public UserData(float width, float height)
	{
		this.width = width;
		this.height = height;
	}
	
	public void setWidth(float width)
	{
		this.width = width;
	}
	
	public float getWidth()
	{
		return width;
	}
	
	public void setHeight(float height)
	{
		this.height = height;
	}
	
	public float getHeight()
	{
		return height;
	}
	
	public UserDataType getUserDataType()
	{
		return userDataType;
	}

}
