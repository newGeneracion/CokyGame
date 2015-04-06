package com.nng.cokygame.utils;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.nng.cokygame.box2d.GroundUserData;
import com.nng.cokygame.box2d.PlayerUserData;

public class WorldUtils {
	
	public static World createWorld()
	{
		return new World(new Vector2(Constants.WORLD_GRAVITY_X, Constants.WORLD_GRAVITY_Y), true);
	}
	
	public static Body createGround(World world)
	{
		BodyDef bodydef = new BodyDef();
		bodydef.type = BodyDef.BodyType.StaticBody;
		bodydef.position.set(new Vector2(Constants.GROUND_X, Constants.GROUND_Y));
		bodydef.angle  = 0f;
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(Constants.GROUND_WIDTH / 2, Constants.GROUND_HEIGHT/2);
		FixtureDef fixdef = new FixtureDef();
		fixdef.density = Constants.GROUND_DENSITY;
		fixdef.friction = Constants.GROUND_FRICTION;
		fixdef.restitution = Constants.GROUND_RESTITUTION;
		fixdef.shape = shape;
		Body body = world.createBody(bodydef);
		body.createFixture(fixdef);
		body.setUserData(new GroundUserData());
		shape.dispose();
		return body;
	}
	
	public static Body createPlayer(World world)
	{
		BodyDef bodydef = new BodyDef();
		bodydef.type = BodyDef.BodyType.DynamicBody;
		bodydef.position.set(new Vector2(Constants.PLAYER_X, Constants.PLAYER_Y));
		bodydef.angle  = 0f;
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(Constants.PLAYER_WIDTH / 2, Constants.PLAYER_HEIGHT/2);
		FixtureDef fixdef = new FixtureDef();
		fixdef.density = Constants.PLAYER_DENSITY;
		fixdef.friction = Constants.PLAYER_FRICTION;
		fixdef.restitution = Constants.PLAYER_RESTITUTION;
		fixdef.shape = shape;
		Body body = world.createBody(bodydef);
		body.createFixture(fixdef);
		body.setGravityScale(Constants.PLAYER_GRAVITY_SCALE);
		body.resetMassData();
		body.setUserData(new PlayerUserData(Constants.PLAYER_WIDTH, Constants.PLAYER_HEIGHT));
		shape.dispose();
		return body;
	}

}
