package com.nng.cokygame.android;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.nng.cokygame.CokyGame;
import com.nng.cokygame.utils.Constants;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		config.useAccelerometer = Constants.ANDROID_USER_ACCELEROMETER;
		config.useCompass = Constants.ANDROID_USE_COMPASS;
		initialize(new CokyGame(), config);
	}
}
