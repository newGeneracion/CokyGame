package com.nng.cokygame.actors;

import com.badlogic.gdx.physics.box2d.Body;
import com.nng.cokygame.box2d.GroundUserData;

public class Ground extends ActorGame {
	
	public Ground(Body body)
	{
		super(body);
	}
	
	public GroundUserData getUserData()
	{
		return (GroundUserData) userData;
	}
	
}
