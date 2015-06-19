package com.nng.cokygame.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.nng.cokygame.enums.ScreenEnum;
import com.nng.cokygame.enums.ScreenManager;



public class ScreenMainMenu extends AbstractScreen{
	
	private Stage stage;	
	//the use of tables makes it easier to sort objects.
	private Table table = new Table();
	
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
	
	
	@Override
    public void show() {
		
		stage = new Stage(new StretchViewport(600,300));
		
		 // label "welcome"
        Label welcomeLabel = new Label( "Coky la Ardilla for Android", getSkin() );
 
        // button "start game"
        final TextButton startGameButton = new TextButton( "Play", getSkin() );
        startGameButton.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				ScreenManager.getInstance().showScreen(ScreenEnum.SCREEN_GAME);
			}
		});
 
        // button "statistics"
        TextButton statistiquesButton = new TextButton( "Estadisticas", getSkin() );
        statistiquesButton.addListener(new ChangeListener(){
        	public void changed(ChangeEvent event, Actor actor) {
        		ScreenManager.getInstance().showScreen(ScreenEnum.SCREEN_STATISTICS);
        	};
        });
        
        TextButton exitButton = new TextButton( "Salir", getSkin() );
        exitButton.addListener(new ChangeListener(){
        	public void changed(ChangeEvent event, Actor actor) {
        		Gdx.app.exit();
        	}
        });
                      
        table.add(welcomeLabel).padBottom(40).row();
        table.add(startGameButton).size(550,60).padBottom(20).row();
        table.add(statistiquesButton).size(550,60).padBottom(20).row();
        table.add(exitButton).size(550,60).padBottom(20).row();

        table.setFillParent(true);
        stage.addActor(table);
        
        Gdx.input.setInputProcessor(stage);
	}
	
	
	public void resize(int width, int height)
	{
					
	 	//super.resize(width, height);
	 	
        //final float buttonX = ( width - Constants.BUTTON_WIDTH ) / 2;
        //float currentY = 280f;
        
        //stage.getViewport().setScreenSize(Constants.MENU_VIEWPORT_WIDTH, Constants.MENU_VIEWPORT_HEIGHT);
    }

	@Override
    public void hide()
	{
		//dispose();
	}
		
	@Override
	public void render(float deltaTime) 
	{
		Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
		super.render(deltaTime);
		stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
		stage.draw();
	}
	
	public void dispose()
	{
		//super.dispose();
		if (stage != null) stage.dispose();
		
	}
	
	
}
