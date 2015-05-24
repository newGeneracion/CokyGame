package com.nng.cokygame.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.nng.cokygame.enums.ScreenEnum;
import com.nng.cokygame.enums.ScreenManager;
import com.nng.cokygame.utils.Constants;

public class ScreenStatistics extends AbstractScreen{
	
	private Stage stage;
	private Preferences prefs;
	
	public void buildStage() {}
	
	public ScreenStatistics() 
	{
		super();
		this.init();
	}
	
	public ScreenStatistics(Game game) 
	{
		super(game);
		this.init();
	}
	
	private void init()
	{
	}
	
	public void render(float deltaTime)
	{
		super.render(deltaTime);
		stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
		stage.draw();
	}
	
	public void resize(int width, int height)
	{
		super.resize( width, height );
		
		if (prefs == null) prefs = Gdx.app.getPreferences("CokyGameEstadisticas");
		int distancia = prefs.getInteger("DISTANCIA_RECORRIDA");
		int objectos  = prefs.getInteger("OBJECTOS_ESQUIVADOS");
		
		if (stage == null )stage = new Stage();
		stage.clear();
        final float buttonX = ( width - Constants.BUTTON_WIDTH ) / 2;
        float currentY = 400f;
        
        stage.getViewport().setScreenSize(Constants.MENU_VIEWPORT_WIDTH, Constants.MENU_VIEWPORT_HEIGHT);
        Gdx.input.setInputProcessor(stage);
 
        // label "welcome"
        Label welcomeLabel = new Label( "Estadisticas Coky la Ardilla", getSkin() );
        welcomeLabel.setX(( ( width - welcomeLabel.getWidth() ) / 2 ));
        welcomeLabel.setY( currentY -= 40);
        stage.addActor( welcomeLabel );
        
        Label distanciaLabel = new Label("Distancia Total Recorrida: " + distancia + " mtrs", getSkin());
        distanciaLabel.setX(( ( width - distanciaLabel.getWidth() ) / 2 ));
        distanciaLabel.setY(currentY -= 40);
        stage.addActor(distanciaLabel);
        
        Label objectLabel = new Label("Numero de objetos esquivados: " + objectos, getSkin());
        objectLabel.setX(( ( width - objectLabel.getWidth() ) / 2 ));
        objectLabel.setY(currentY -= 40);
        stage.addActor(objectLabel);
 
        // button "volver"
        TextButton volverButton = new TextButton( "Volver", getSkin() );
        volverButton.setX(buttonX);
        volverButton.setY(currentY -= 40 + Constants.BUTTON_SPACING + Constants.BUTTON_HEIGHT / 2);
        volverButton.setWidth(Constants.BUTTON_WIDTH);
        volverButton.setHeight( Constants.BUTTON_HEIGHT);
        volverButton.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				ScreenManager.getInstance().showScreen(ScreenEnum.SCREEN_MAIN_MENU);
			}
        });
        stage.addActor( volverButton );
        
	}

}
