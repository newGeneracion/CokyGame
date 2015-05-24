package com.nng.cokygame.screens;

import com.badlogic.gdx.ScreenAdapter;
import com.nng.cokygame.stages.StageGame;

public class ScreenGame extends ScreenAdapter 
{	
	
	private StageGame stageGame;
	
	public ScreenGame()
	{
		super();
		stageGame = new StageGame();
	}
	
	public void render(float deltaTime)
	{
		super.render(deltaTime);
		if (!stageGame.getPlayer().isHit())
			stageGame.act();
		stageGame.draw();
		
	}
	
	public void resize(int width, int height)
	{
		super.resize(width, height);
		stageGame.resize(20, 13);
	}
	
}

