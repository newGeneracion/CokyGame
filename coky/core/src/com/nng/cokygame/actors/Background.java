package com.nng.cokygame.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.nng.cokygame.utils.Constants;

public class Background extends Actor 
{
	
	private final TextureRegion textureRegion;
	private Rectangle textureRegionBounds1;
	private Rectangle textureRegionBounds2;
	private final int SPEED = 100;
	
	public Background()
	{
		textureRegion = new TextureRegion(new Texture(Gdx.files.internal(Constants.BACKGROUND_IMAGE_PATH_1)));
		textureRegionBounds1 = new Rectangle(0 - Constants.SCREEN_WIDTH / 2, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT );
		textureRegionBounds2 = new Rectangle(Constants.SCREEN_WIDTH / 2, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
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
		batch.draw(textureRegion, textureRegionBounds1.x, textureRegionBounds2.y, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
		batch.draw(textureRegion, textureRegionBounds2.x, textureRegionBounds2.y, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
	}
	
	private boolean leftBoundsReached(float deltaTime)
	{
		return (textureRegionBounds2.x - (deltaTime * SPEED)) <= 0;
	}
	
	private void updateXBounds(float deltaTime) 
	{
		textureRegionBounds1.x += deltaTime * SPEED;
		textureRegionBounds2.x += deltaTime * SPEED;		
	}
	
	private void resetBounds()
	{
		textureRegionBounds1 = textureRegionBounds2;
		textureRegionBounds2 = new Rectangle(Constants.SCREEN_WIDTH, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
	}

}
