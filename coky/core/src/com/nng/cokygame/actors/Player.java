package com.nng.cokygame.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.nng.cokygame.box2d.PlayerUserData;
import com.nng.cokygame.utils.Constants;


public class Player extends ActorGame
{
	
	private boolean jumping;
	private boolean hit;
	private TextureRegion textureRegion;
	
	public Player(Body body)
	{
		super(body);
		jumping = false;
		textureRegion =new TextureRegion(new Texture(Gdx.files.internal(Constants.PLAYER_IMAGE_PATH_1)));
	}
	
	public PlayerUserData getUserData()
	{
		return (PlayerUserData) userData;
	}
	
	public void jump()
	{
		if(!(jumping || hit)) {
			body.applyLinearImpulse(getUserData().getJumpingLinearImpulse(), body.getWorldCenter(), true);
			jumping = true;
		}
	}
	
	public void landed()
	{
		jumping = false;
	}
	
	public void hit()
	{
		body.applyAngularImpulse(getUserData().getHitAngularImpulse(), true);
		hit = true;
	}
	
	public boolean isHit()
	{
		return hit;
	}
	
	@Override 
	public void draw(Batch batch, float parentAlpha)
	{
		super.draw(batch, parentAlpha);
		batch.draw(textureRegion, screenRectangle.x, screenRectangle.y + screenRectangle.height/3, screenRectangle.width, screenRectangle.height* 3 / 4);
	}

}

