package com.nng.cokygame.actors;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.nng.cokygame.box2d.UserData;

public abstract class ActorGame extends Actor
{
	protected Body body;
	protected UserData userData;
	
	public ActorGame()
	{
		super();
	}
	
	public ActorGame(Body body)
	{
		super();
		this.body = body;
		this.userData = (UserData) body.getUserData();
	}
	
	protected abstract UserData getUserData();
		
}
