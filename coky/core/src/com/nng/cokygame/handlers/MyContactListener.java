package com.nng.cokygame.handlers;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.nng.cokygame.stages.StageGame;
import com.nng.cokygame.utils.BodyUtils;

public class MyContactListener implements ContactListener {
	
	private StageGame stageGame;
	
	public MyContactListener()
	{
		super();
	}
	
	public MyContactListener(StageGame stageGame)
	{
		super();
		this.stageGame = stageGame;
	}
	
	@Override
	public void beginContact(Contact c)
	{
		
		Body bodyA = c.getFixtureA().getBody();
		Body bodyB = c.getFixtureB().getBody();
		
		if (bodyA ==  null || bodyB == null) return;
		
		if ((BodyUtils.bodyIsPlayer(bodyA) && BodyUtils.bodyIsObejctCactusEnemy(bodyB)) ||
		    (BodyUtils.bodyIsPlayer(bodyB) && BodyUtils.bodyIsObejctCactusEnemy(bodyA)))
	    {
		    stageGame.getPlayer().hit();
	    }
		if ((BodyUtils.bodyIsGround(bodyA) && BodyUtils.bodyIsPlayer(bodyB)) ||
		    (BodyUtils.bodyIsGround(bodyB) && BodyUtils.bodyIsPlayer(bodyA)))
	    {
			stageGame.getPlayer().landed();
		}
		
	}
	
	@Override
	public void endContact(Contact c){
		
		Body bodyA = c.getFixtureA().getBody();
		Body bodyB = c.getFixtureB().getBody();
		
		if (bodyA ==  null || bodyB == null) return;
		
	}
	
	@Override public void postSolve(Contact c, ContactImpulse ci){}
	@Override public void preSolve(Contact c, Manifold m){}
	
	
}
