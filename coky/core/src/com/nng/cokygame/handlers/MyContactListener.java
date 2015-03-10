package com.nng.cokygame.handlers;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.nng.cokygame.controllers.Constants;

public class MyContactListener implements ContactListener {
	 
	private int numFootContats;
	
	public MyContactListener(){
		super();
	}
	
	@Override
	public void beginContact(Contact c) {
		
		Fixture fixtureA = c.getFixtureA();
		Fixture fixtureB = c.getFixtureB();
		
		if (fixtureA == null || fixtureB == null) return;
		
		if (fixtureA.getUserData().equals(Constants.USER_DATA_PLAYER_FOOT) ) numFootContats++;
		if (fixtureB.getUserData().equals(Constants.USER_DATA_PLAYER_FOOT) ) numFootContats++;
	}
	
	@Override
	public void endContact(Contact c){
		Fixture fixtureA = c.getFixtureA();
		Fixture fixtureB = c.getFixtureB();
		
		if (fixtureA == null || fixtureB == null) return;
		
		if (fixtureA.getUserData().equals(Constants.USER_DATA_PLAYER_FOOT) ) numFootContats--;
		if (fixtureB.getUserData().equals(Constants.USER_DATA_PLAYER_FOOT) ) numFootContats--;
	}
	
	@Override public void postSolve(Contact c, ContactImpulse ci){}
	@Override public void preSolve(Contact c, Manifold m){}
	
	public boolean getJump(){
		return  (numFootContats>0);
	}
	
}
