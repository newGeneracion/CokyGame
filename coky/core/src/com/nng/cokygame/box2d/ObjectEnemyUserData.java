package com.nng.cokygame.box2d;

import com.badlogic.gdx.math.Vector2;
import com.nng.cokygame.enums.UserDataType;
import com.nng.cokygame.utils.Constants;

public class ObjectEnemyUserData extends UserData
{
		private Vector2 linearVelocity;
		
		
		public ObjectEnemyUserData(float width, float height)
		{
			super(width, height);
			userDataType = UserDataType.OBJECT_ENEMY_CACTUS;
			linearVelocity = Constants.OBJECT_CACTUS_LINEAR_VELOCITY;
		}
		
		public void setLinearVelocity(Vector2 linearVelocity)
		{
			this.linearVelocity = linearVelocity;
		}
		
		public Vector2 getLinearVelocity()
		{
			return linearVelocity;
		}

}
