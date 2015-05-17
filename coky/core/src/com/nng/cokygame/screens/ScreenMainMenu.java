package com.nng.cokygame.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.nng.cokygame.enums.ScreenEnum;
import com.nng.cokygame.enums.ScreenManager;


public class ScreenMainMenu extends AbstractScreen{
	
	private static final float BUTTON_WIDTH = 300f;
	private static final float BUTTON_HEIGHT = 60f;
	private static final float BUTTON_SPACING = 10f;
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
        final float buttonX = ( width - BUTTON_WIDTH ) / 2;
        float currentY = 280f;
        stage = new Stage();
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
        startGameButton.setWidth(BUTTON_WIDTH);
        startGameButton.setHeight(BUTTON_HEIGHT);
        stage.addActor( startGameButton );
        
        startGameButton.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				ScreenManager.getInstance().showScreen(ScreenEnum.SCREEN_GAME);
			}
		});
        
        // button "options"
        TextButton optionsButton = new TextButton( "Opciones", getSkin() );
        optionsButton.setX(buttonX);
        optionsButton.setY( currentY -= BUTTON_HEIGHT + BUTTON_SPACING );
        optionsButton.setWidth(BUTTON_WIDTH);
        optionsButton.setHeight(BUTTON_HEIGHT);
        stage.addActor( optionsButton );
 
        // button "hall of fame"
        TextButton hallOfFameButton = new TextButton( "Estadisticas", getSkin() );
        hallOfFameButton.setX(buttonX);
        hallOfFameButton.setY(currentY -= BUTTON_HEIGHT + BUTTON_SPACING );
        hallOfFameButton.setWidth(BUTTON_WIDTH);
        hallOfFameButton.setHeight( BUTTON_HEIGHT);
        stage.addActor( hallOfFameButton );
	}
	

	
	@Override
	public void render(float deltaTime) 
	{
		super.render(deltaTime);
		Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
		stage.draw();
		// Table.drawDebug(stage);
	}
	
	public void dispose()
	{
		stage.dispose();
		super.dispose();
	}
	
	
}
