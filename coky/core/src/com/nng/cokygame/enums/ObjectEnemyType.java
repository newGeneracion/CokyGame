package com.nng.cokygame.enums;

import com.nng.cokygame.utils.Constants;

public enum ObjectEnemyType {
		
		OBJECT_CACTUS_1(1f, 1f, Constants.OBJECT_ENEMY_CACTUS_X, Constants.OBJECT_ENEMY_CACTUS_SHORT_Y, Constants.OBJECT_CACTUS_DENSITY),
		OBJECT_CACTUS_2(1f, 1f, Constants.OBJECT_ENEMY_CACTUS_X, Constants.OBJECT_ENEMY_CACTUS_SHORT_Y, Constants.OBJECT_CACTUS_DENSITY),
		OBJECT_CACTUS_3(1f, 1f, Constants.OBJECT_ENEMY_CACTUS_X, Constants.OBJECT_ENEMY_CACTUS_SHORT_Y, Constants.OBJECT_CACTUS_DENSITY),
		OBJECT_CACTUS_4(1f, 1f, Constants.OBJECT_ENEMY_CACTUS_X, Constants.OBJECT_ENEMY_CACTUS_SHORT_Y, Constants.OBJECT_CACTUS_DENSITY);
		
		private float width;
		private float height;
		private float x;
		private float y;
		private float density;
		
		ObjectEnemyType(float width, float height, float x, float y, float density)
		{
			this.width  = width;
			this.height = height;
			this.x = x;
			this.y =y;
			this.density = density;
		}
		
		public float getWidth() {
			return width;
		}
		
		public float getHeight() {
			return height;
		}
		
		public float getY() {
			return y;
		}
		
		public float getX() {
			return x;
		}
		
		public float getDensity() {
			return density;
		}
}
