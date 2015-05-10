package com.nng.cokygame.box2d;

import com.badlogic.gdx.math.Vector2;
import com.nng.cokygame.enums.UserDataType;
import com.nng.cokygame.utils.Constants;

public class PlayerUserData extends UserData
{
	
	private Vector2 jumpingLinearImpulse;
	private Vector2 runningPosition;
	
	
	public PlayerUserData()
	{
		super();
	}
	
	public PlayerUserData(float width, float height)
	{
		super(width, height);
		jumpingLinearImpulse = Constants.PLAYER_JUMPING_LINEAR_IMPULSE;
		runningPosition = new Vector2(Constants.PLAYER_X, Constants.PLAYER_Y);
		userDataType = UserDataType.PLAYER;
	}
	
	public void setJumpingLinearImpulse(Vector2 jumpingLinearImpulse)
	{
		this.jumpingLinearImpulse = jumpingLinearImpulse;
	}
	
	public Vector2 getJumpingLinearImpulse()
	{
		return jumpingLinearImpulse;
	}
	
	public Vector2 getRunningPosition()
	{
		return runningPosition;
	}
	
	public float getHitAngularImpulse()
	{
		return Constants.PLAYER_HIT_ANGULAR_IMPULSE;
	}
	
}
