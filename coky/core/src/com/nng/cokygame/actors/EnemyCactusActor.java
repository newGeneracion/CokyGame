package com.nng.cokygame.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.nng.cokygame.box2d.ObjectEnemyUserData;
import com.nng.cokygame.utils.Constants;

public class EnemyCactusActor extends ActorGame
{

	private float stateTime;
	private TextureRegion textureRegion; 

	public EnemyCactusActor(Body body)
	{
		super(body);
		textureRegion = new TextureRegion(new Texture(Gdx.files.internal(Constants.OBJECT_CACTUS_IMAGE_PATH_1)));
	}
	
	public ObjectEnemyUserData getUserData()
	{
		return (ObjectEnemyUserData) userData;
	}
	
	public void draw (Batch batch, float parentAlpha)
	{
		super.draw(batch, parentAlpha);
		batch.draw(textureRegion, screenRectangle.x, screenRectangle.y + screenRectangle.height/3, screenRectangle.width, screenRectangle.height* 3 / 4);
	}
	
	public void act(float delta) 
	{
		super.act(delta);
		body.setLinearVelocity(getUserData().getLinearVelocity());
	}
	

}
