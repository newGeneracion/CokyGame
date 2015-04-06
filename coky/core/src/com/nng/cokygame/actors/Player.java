package com.nng.cokygame.actors;

import com.badlogic.gdx.physics.box2d.Body;
import com.nng.cokygame.box2d.PlayerUserData;


public class Player extends ActorGame
{
	
	private boolean jumping;
	
	public Player(Body body)
	{
		super(body);
		jumping = false;
	}
	
	public PlayerUserData getUserData()
	{
		return (PlayerUserData) userData;
	}
	
	public void jump()
	{
		if(!jumping) {
			body.applyLinearImpulse(getUserData().getJumpingLinearImpulse(), body.getWorldCenter(), true);
			jumping = true;
		}
	}
	
	public void landed()
	{
		jumping = false;
	}

}

