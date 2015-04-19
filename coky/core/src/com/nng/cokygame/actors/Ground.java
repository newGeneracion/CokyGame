package com.nng.cokygame.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.nng.cokygame.box2d.GroundUserData;
import com.nng.cokygame.utils.Constants;

public class Ground extends ActorGame 
{
	
	private final TextureRegion textureRegion;
	private Rectangle textureRegionBounds1;
	private Rectangle textureRegionBounds2;
	private int SPEED = 10;
	
	public Ground(Body body)
	{
		super(body);
		textureRegion = new TextureRegion(new Texture(Gdx.files.internal(Constants.GROUND_IMAGE_PATH_1)));
		textureRegionBounds1 = new Rectangle(0 - getUserData().getWidth() / 2, 0, getUserData().getWidth(), getUserData().getHeight());
		textureRegionBounds2 = new Rectangle(getUserData().getWidth()/2, 0, getUserData().getWidth(), getUserData().getHeight());
	}
	
	public GroundUserData getUserData()
	{
		return (GroundUserData) userData;
	}
	
	@Override
	public void act(float deltaTime)
	{
		super.act(deltaTime);
		if (leftBoundsReached(deltaTime))
			resetBounds();
		else
			updateXBounds(-deltaTime);
	}
	
	@Override 
	public void draw(Batch batch, float parentAlpha)
	{
		super.draw(batch, parentAlpha);
		batch.draw(textureRegion, textureRegionBounds1.x, screenRectangle.y, screenRectangle.getWidth(),
                screenRectangle.getHeight());
        batch.draw(textureRegion, textureRegionBounds2.x, screenRectangle.y, screenRectangle.getWidth(),
                screenRectangle.getHeight());
	}
	
	public boolean leftBoundsReached(float deltaTime)
	{
		return (textureRegionBounds2.x - transformToScreen(deltaTime * SPEED )) <= 0;
	}
	
	public void updateXBounds(float deltaTime)
	{
		textureRegionBounds1.x += transformToScreen(deltaTime * SPEED);
		textureRegionBounds2.x += transformToScreen(deltaTime * SPEED); 
	}
	
	private void resetBounds()
	{
		textureRegionBounds1 = textureRegionBounds2;
		textureRegionBounds2 = new Rectangle(textureRegionBounds1.x + screenRectangle.width, 0 , screenRectangle.width, screenRectangle.height);
	}
	
}
