package com.nng.cokygame.handlers;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.nng.cokygame.stages.StageGame;

public class ControladorTouch extends InputAdapter {
	
	private StageGame stageGame;
	private Rectangle screenRightSide;
	private Vector3   touchPoint;
	
	public ControladorTouch() 
	{
		super();
	}
	
	public ControladorTouch(StageGame stageGame)
	{
		super();
		this.stageGame = stageGame;
		touchPoint = new Vector3();
		screenRightSide = new Rectangle(this.stageGame.getCamera().viewportWidth / 2, 0, this.stageGame.getCamera().viewportWidth, this.stageGame.getCamera().viewportHeight);
	}
	
	@Override
	public boolean touchDown(int x, int y, int pointer, int button)
	{
		translateScreenToWorldCoordinates(x, y);
		if (rightSideTouched(touchPoint.x, touchPoint.y)) stageGame.getPlayer().jump();
		return super.touchDown(x, y, pointer, button);
	}
	
	private void translateScreenToWorldCoordinates(int x, int y)
	{
		this.stageGame.getCamera().unproject(touchPoint.set(x, y, 0));
	}
	
	private boolean rightSideTouched(float x, float y)
	{
		return screenRightSide.contains(x, y);
	}

}
