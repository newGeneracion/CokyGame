package com.nng.cokygame.utils;

import java.util.Random;

import com.nng.cokygame.enums.ObjectEnemyType;

public class RandomEnemyObjectUtils 
{
	
	public static ObjectEnemyType getRandomObjectEnemyType() 
	{
		RandomEnum<ObjectEnemyType> randomEnum = new RandomEnum<ObjectEnemyType>(ObjectEnemyType.class);
		return randomEnum.random();
	}
	
	private static class RandomEnum<E extends Enum> 
	{
		private static final Random RND = new Random();
		private final E[] values;
		
		public RandomEnum(Class<E> token) {
			values = token.getEnumConstants();
		}
		
		public E random() {
			return values[RND.nextInt(values.length)];
		}
	}

}
