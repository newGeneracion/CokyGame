package com.nng.cokygame.actors;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.nng.cokygame.box2d.UserData;
import com.nng.cokygame.utils.Constants;

public abstract class ActorGame extends Actor
{
	protected Body body;
	protected UserData userData;
	protected Rectangle screenRectangle;
	protected float stateTime;
	
	public ActorGame()
	{
		super();
	}
	
	public ActorGame(Body body)
	{
		super();
		this.body = body;
		this.userData = (UserData) body.getUserData();
		this.screenRectangle = new Rectangle();
	}
	
	@Override 
	public void act(float deltaTime)
	{
		super.act(deltaTime);
		if (body.getUserData() != null)
			updateRectangle();
		else
			remove();
	}

	private void updateRectangle()
	{
		screenRectangle.x      = transformToScreen(body.getPosition().x - userData.getWidth() / 2);
		screenRectangle.y      = transformToScreen(body.getPosition().y - userData.getHeight() / 2);
		screenRectangle.width  = transformToScreen(userData.getWidth());
		screenRectangle.height = transformToScreen(userData.getHeight()); 
	}
	
	protected float transformToScreen(float n)
	{
		return Constants.WORLD_TO_SCREEN * n;
	}
	
	protected float getTime()
	{
		return stateTime;
	}
	
	protected abstract UserData getUserData();
		
}
