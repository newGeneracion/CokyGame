package com.nng.cokygame.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.nng.cokygame.actors.Background;
import com.nng.cokygame.actors.EnemyCactusActor;
import com.nng.cokygame.actors.Ground;
import com.nng.cokygame.actors.Player;
import com.nng.cokygame.enums.ScreenEnum;
import com.nng.cokygame.enums.ScreenManager;
import com.nng.cokygame.handlers.ControladorTouch;
import com.nng.cokygame.handlers.MyContactListener;
import com.nng.cokygame.utils.BodyUtils;
import com.nng.cokygame.utils.Constants;
import com.nng.cokygame.utils.WorldUtils;

public class StageGame extends Stage{
	
	/*
	 * acumulador del tiempo delta
	 */
	private float accumulator;
	
	// private static final int VIEWPORT_WIDTH = 20;
	// private static final int VIEWPORT_HEIGHT = 13;
	
	private static final int VIEWPORT_WIDTH  = (int)Constants.SCREEN_WIDTH;
	private static final int VIEWPORT_HEIGHT = (int)Constants.SCREEN_HEIGHT;
	
	private Skin skin;
	private Window pause;
	private Window gameOver;
	/*
	 * World fisic , gravity , listener of contacts 
	 * Mundo fisico, gravedad,  escuchador de contactos entre fixturas, render del mundo fisico
	 */
	private World world;
	private OrthographicCamera camera;
	private OrthographicCamera cameraHUD;
	private SpriteBatch batch;
	private BitmapFont font;
	private Box2DDebugRenderer renderer;
	private MyContactListener myContactListener;
	private ControladorTouch controladorTouch;
	private int distanciaRecorrida;
	private int objectosEsquivados;
	private Preferences preferences;
	/*
	 * Actor para el suelo
	 */
	private Ground ground;
	/*
	 * Actor para el jugador
	 */
	private Player player;
		
	public StageGame()
	{
		super(new ScalingViewport(Scaling.stretch, VIEWPORT_WIDTH, VIEWPORT_HEIGHT, new OrthographicCamera(VIEWPORT_WIDTH, VIEWPORT_HEIGHT)));
		this.init();
	}
	
	private void init()
	{
		accumulator = 0f;
		setWorld();
		setCamera();
		setTouch();
		
		FileHandle skinFile = Gdx.files.internal("skin/uiskin.json");
		skin = new Skin(skinFile);
		
		// independent pause menu
		TextButton continueButton = new TextButton("continue", skin);
		continueButton.setWidth(Constants.BUTTON_WIDTH);
        continueButton.setHeight(Constants.BUTTON_HEIGHT);
		continueButton.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				pause.setVisible(false);
				super.clicked(event, x, y);
			}
		});
		
		pause = new Window("PAUSE", skin);
		pause.padTop(64);
		pause.add(continueButton).row();
		pause.add(new TextButton("Exit", skin));
		pause.setSize(VIEWPORT_WIDTH / 1.5f, VIEWPORT_HEIGHT / 1.5f);
		pause.setPosition(VIEWPORT_WIDTH / 2 - pause.getWidth() / 2, VIEWPORT_HEIGHT / 2 - pause.getHeight() / 2);
		pause.setKeepWithinStage(false);
		pause.setMovable(false);
		pause.setVisible(false);
		addActor(pause);		
	}
	
	private void setWorld()
	{
		world = WorldUtils.createWorld();
		myContactListener = new MyContactListener(this);
		world.setContactListener(myContactListener);
		addActor(new Background());
		ground = new Ground(WorldUtils.createGround(world));
		addActor(ground);
		player = new Player(WorldUtils.createPlayer(world));
		addActor(player);
		renderer = new Box2DDebugRenderer();
		createObjectEnemyCactus();
		distanciaRecorrida = 0;
		objectosEsquivados = 0;
	}
	
	private void createObjectEnemyCactus()
	{
		EnemyCactusActor enemyCactus = new EnemyCactusActor(WorldUtils.createEnemyCactus(world));
		addActor(enemyCactus);
	}
	
	private void setCamera()
	{
		camera = new OrthographicCamera(VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
		camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0f);
		camera.update();
		cameraHUD = new OrthographicCamera(VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
		cameraHUD.position.set(cameraHUD.viewportWidth / 2f, cameraHUD.viewportHeight / 2f, 0f );
		cameraHUD.update();
		batch = new SpriteBatch();
		font  = new BitmapFont();
		font.setColor(Color.BLACK);
		font.setScale(1);
	}
	
	private void setTouch()
	{
		controladorTouch = new ControladorTouch(this);
		InputMultiplexer inputMultiplexer = new InputMultiplexer();
		inputMultiplexer.addProcessor(this);
		inputMultiplexer.addProcessor(controladorTouch);
		Gdx.input.setInputProcessor(inputMultiplexer);
	}
	
	@Override
	public void act(float deltaTime)
	{
		super.act(deltaTime);
		
		Array<Body> bodies = new Array<Body>(world.getBodyCount());
		world.getBodies(bodies);
		for (Body body: bodies) {
			updateBody(body);
		}
		accumulator += deltaTime;
		while ( accumulator >= deltaTime) 
		{
			world.step(Constants.GAME_STEP, 6, 2);
			accumulator -= Constants.GAME_STEP;
		}
		distanciaRecorrida += deltaTime * 60;
	}
	
	public void updateBody(Body body) 
	{
		if (BodyUtils.bodyIsObejctCactusEnemy(body) && !(BodyUtils.bodyInBounds(body)))
		{
			if (!player.isHit())
			{
				createObjectEnemyCactus();
			}
			objectosEsquivados++;
			world.destroyBody(body);
		}
	}
	
	@Override
	public void draw()
	{
		super.draw();
		renderer.render(world, camera.combined);
		batch.setProjectionMatrix(cameraHUD.combined);
		String mdr = Integer.toString(distanciaRecorrida)+ " m";
		float mpw = font.getBounds(mdr).width;
		float mph = font.getBounds(mdr).height;
		batch.begin();
		font.draw(batch, mdr, VIEWPORT_WIDTH - mpw - 10, VIEWPORT_HEIGHT - mph);
		batch.end();
	}
	
	@Override
	public void dispose()
	{
		/*
		 * liberación de recursos
		 */
		super.dispose();
		renderer.dispose();
		world.dispose();
		font.dispose();
		batch.dispose();
	}
	
	public void resize(int width, int height) 
	{
		camera.viewportWidth = width;
		camera.viewportHeight = height;
		camera.update();
	}
	
	public Player getPlayer() 
	{
		return player;		
	}
	
	public OrthographicCamera getCamera()
	{
		return camera;
	}

	public void printGameOver()
	{
		saveGame();
		gameOver = new Window("Game Over", skin);
		// independent window gameOver
		final TextButton restartGameButton = new TextButton( "Restart", skin);
		restartGameButton.addListener(new ClickListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				gameOver.setVisible(false);
				init();
				return super.touchDown(event, x, y, pointer, button);
			};
		});
		final TextButton exitGameButton = new TextButton("Exit", skin);
		exitGameButton.addListener(new ClickListener(){
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				ScreenManager.getInstance().showScreen(ScreenEnum.SCREEN_MAIN_MENU);
				return super.touchDown(event, x, y, pointer, button);
			};
		});
		String mdr = "Distancia recorrida: " + getDistanciaRecorrida();
		String moe = "Objectos esquivados: " + getObjetosEsquivados();
		final Label distacia = new Label(mdr, skin);
		distacia.setAlignment(Align.center);
		distacia.setWrap(true);
		final Label objectos = new Label(moe, skin);
		objectos.setAlignment(Align.center);
		objectos.setWrap(true);
		gameOver.setSize(VIEWPORT_WIDTH / 1.5f, VIEWPORT_HEIGHT / 1.5f);
		gameOver.setPosition(VIEWPORT_WIDTH / 2 - pause.getWidth() / 2, VIEWPORT_HEIGHT / 2 - pause.getHeight() / 2);
		gameOver.setMovable(false);
		gameOver.setVisible(true);
		gameOver.setLayoutEnabled(true);
		gameOver.add(distacia).width(Constants.BUTTON_WIDTH / 1.5f).row();
		gameOver.add(objectos).width(Constants.BUTTON_WIDTH / 1.5f).row();
		gameOver.add(restartGameButton).width(Constants.BUTTON_WIDTH / 1.5f).row();
		gameOver.add(exitGameButton).width(Constants.BUTTON_WIDTH / 1.5f).row();
		addActor(gameOver);
	}
	
	public String getDistanciaRecorrida() {
		return String.valueOf(distanciaRecorrida) + " m";
	}
	
	public String getObjetosEsquivados() {
		return String.valueOf(objectosEsquivados);
	}
	
	public void saveGame()
	{
		/**
		 * file path: C:/Users/user/.prefs
		 */
		if (preferences == null)
			preferences = Gdx.app.getPreferences("CokyGameEstadisticas");
		int distancia = preferences.getInteger("DISTANCIA_RECORRIDA");
		int objectos  = preferences.getInteger("OBJECTOS_ESQUIVADOS");
		distancia += distanciaRecorrida;
		objectos  += objectosEsquivados;
		preferences.putInteger("DISTANCIA_RECORRIDA", distancia);
		preferences.putInteger("OBJECTOS_ESQUIVADOS", objectos);
		preferences.flush();
	}
}
