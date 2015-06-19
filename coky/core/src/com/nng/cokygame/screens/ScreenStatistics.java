package com.nng.cokygame.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
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
//import com.nng.cokygame.utils.Constants;

public class ScreenStatistics extends AbstractScreen{
	
	private Stage stage;
	private Preferences prefs;
	private Table table = new Table();
	
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
	
	@Override
    public void show() {
		stage = new Stage(new StretchViewport(600,300));
		//stage.clear();
		
		if (prefs == null) prefs = Gdx.app.getPreferences("CokyGameEstadisticas");
		int distancia = prefs.getInteger("DISTANCIA_RECORRIDA");
		int objectos  = prefs.getInteger("OBJECTOS_ESQUIVADOS");
		
        Label welcomeLabel = new Label( "Estadisticas Coky la Ardilla", getSkin() );
        
        Label distanciaLabel = new Label("Distancia Total Recorrida: " + distancia + " mtrs", getSkin());
       
        Label objectLabel = new Label("Numero de objetos esquivados: " + objectos, getSkin());
 
        TextButton volverButton = new TextButton( "Volver", getSkin() );
             volverButton.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				ScreenManager.getInstance().showScreen(ScreenEnum.SCREEN_MAIN_MENU);
			}
        });
             
        table.add(welcomeLabel).padBottom(40).row();
        table.add(distanciaLabel).padBottom(20).row();
        table.add(objectLabel).padBottom(20).row();
        table.add(volverButton).size(550,60).padBottom(20).row();

        table.setFillParent(true);
        stage.addActor(table);
        
        Gdx.input.setInputProcessor(stage);
        
	}
	
	public void render(float deltaTime)
	{
		Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
		super.render(deltaTime);
		stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
		stage.draw();
	}
	
	public void resize(int width, int height)
	{
		/*super.resize( width, height );
		
		if (prefs == null) prefs = Gdx.app.getPreferences("CokyGameEstadisticas");
		int distancia = prefs.getInteger("DISTANCIA_RECORRIDA");
		int objectos  = prefs.getInteger("OBJECTOS_ESQUIVADOS");
		
		if (stage == null )stage = new Stage();
		stage.clear();
        final float buttonX = ( width - Constants.BUTTON_WIDTH ) / 2;
        float currentY = 400f;
        
        stage.getViewport().setScreenSize(Constants.MENU_VIEWPORT_WIDTH, Constants.MENU_VIEWPORT_HEIGHT);
        Gdx.input.setInputProcessor(stage);*/
 
      
        
	}

}
