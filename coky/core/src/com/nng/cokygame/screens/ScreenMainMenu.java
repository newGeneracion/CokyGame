package com.nng.cokygame.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.nng.cokygame.enums.ScreenEnum;
import com.nng.cokygame.enums.ScreenManager;
import com.nng.cokygame.utils.Constants;


public class ScreenMainMenu extends AbstractScreen{
	
	private Stage stage;
	
	public ScreenMainMenu() 
	{
		super();
		this.init();
	}
	
	public ScreenMainMenu(Game game)
	{
		super(game);
		this.init();
	}
	
	private void init() 
	{
		
	}
	
	public void buildStage() {}
	
	public void resize(int width, int height)
	{
	 	super.resize( width, height );
        final float buttonX = ( width - Constants.BUTTON_WIDTH ) / 2;
        float currentY = 280f;
        stage = new Stage();
        stage.getViewport().setScreenSize(Constants.MENU_VIEWPORT_WIDTH, Constants.MENU_VIEWPORT_HEIGHT);
        Gdx.input.setInputProcessor(stage);
 
        // label "welcome"
        Label welcomeLabel = new Label( "Coky la Ardilla for Android", getSkin() );
        welcomeLabel.setX(( ( width - welcomeLabel.getWidth() ) / 2 ));
        welcomeLabel.setY( currentY + 100 );
        stage.addActor( welcomeLabel );
 
        // button "start game"
        final TextButton startGameButton = new TextButton( "Play", getSkin() );
        startGameButton.setX(buttonX);
        startGameButton.setY(currentY);
        startGameButton.setWidth(Constants.BUTTON_WIDTH);
        startGameButton.setHeight(Constants.BUTTON_HEIGHT); 
        startGameButton.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				ScreenManager.getInstance().showScreen(ScreenEnum.SCREEN_GAME);
			}
		});
        stage.addActor( startGameButton );
        
        // button "options"
        /*
        TextButton optionsButton = new TextButton( "Opciones", getSkin() );
        optionsButton.setX(buttonX);
        optionsButton.setY( currentY -= Constants.BUTTON_HEIGHT + Constants.BUTTON_SPACING );
        optionsButton.setWidth(Constants.BUTTON_WIDTH);
        optionsButton.setHeight(Constants.BUTTON_HEIGHT);
        stage.addActor( optionsButton );
        */
 
        // button "statistiques"
        TextButton statistiquesButton = new TextButton( "Estadisticas", getSkin() );
        statistiquesButton.setX(buttonX);
        statistiquesButton.setY(currentY -= Constants.BUTTON_HEIGHT + Constants.BUTTON_SPACING );
        statistiquesButton.setWidth(Constants.BUTTON_WIDTH);
        statistiquesButton.setHeight( Constants.BUTTON_HEIGHT);
        statistiquesButton.addListener(new ChangeListener(){
        	public void changed(ChangeEvent event, Actor actor) {
        		ScreenManager.getInstance().showScreen(ScreenEnum.SCREEN_STATISTICS);
        	};
        });
        stage.addActor( statistiquesButton );
        
        TextButton exitButton = new TextButton( "Salir", getSkin() );
        exitButton.setX(buttonX);
        exitButton.setY(currentY -= Constants.BUTTON_HEIGHT + Constants.BUTTON_SPACING );
        exitButton.setWidth(Constants.BUTTON_WIDTH);
        exitButton.setHeight( Constants.BUTTON_HEIGHT);
        exitButton.addListener(new ChangeListener(){
        	public void changed(ChangeEvent event, Actor actor) {
        		Gdx.app.exit();
        	}
        });
        stage.addActor(exitButton);
	}

	@Override
	public void render(float deltaTime) 
	{
		super.render(deltaTime);
		stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
		stage.draw();
	}
	
	public void dispose()
	{
		super.dispose();
		if (stage != null) stage.dispose();
		
	}
	
	
}
