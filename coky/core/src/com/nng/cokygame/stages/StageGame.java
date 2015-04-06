package com.nng.cokygame.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.nng.cokygame.actors.Ground;
import com.nng.cokygame.actors.Player;
import com.nng.cokygame.handlers.ControladorTouch;
import com.nng.cokygame.handlers.MyContactListener;
import com.nng.cokygame.utils.Constants;
import com.nng.cokygame.utils.WorldUtils;

public class StageGame extends Stage{
	
	/*
	 * acumulador del tiempo delta
	 */
	private float accumulator;
	
	private static final int VIEWPORT_WIDTH = 20;
	private static final int VIEWPORT_HEIGHT = 13;
	
	/*
	 * Mundo fisico, gravedar,  escuchador de contactos entre fixturas, render del mundo fisico
	 */
	private World world;
	private OrthographicCamera camera;
	private Box2DDebugRenderer renderer;
	private MyContactListener myContactListener;
	private ControladorTouch controladorTouch;
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
		super();
		this.init();
	}
	
	private void init()
	{
		accumulator = 0f;
		setWorld();
		setCamera();
		setTouch();
	}
	
	private void setWorld()
	{
		world = WorldUtils.createWorld();
		myContactListener = new MyContactListener(this);
		world.setContactListener(myContactListener);
		ground = new Ground(WorldUtils.createGround(world));
		addActor(ground);
		player = new Player(WorldUtils.createPlayer(world));
		addActor(player);
		renderer = new Box2DDebugRenderer();
	}
	
	
	private void setCamera()
	{
		camera = new OrthographicCamera(VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
		camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0f);
		camera.update();
	}
	
	private void setTouch()
	{
		//Gdx.input.setInputProcessor(this);
		controladorTouch = new ControladorTouch(this);
		Gdx.input.setInputProcessor(controladorTouch);
	}
	
	@Override
	public void act(float deltaTime)
	{
		super.act(deltaTime);
		accumulator += deltaTime;
		while ( accumulator >= deltaTime) 
		{
			world.step(Constants.GAME_STEP, 6, 2);
			accumulator -= Constants.GAME_STEP;
		}
	}
	
	@Override
	public void draw()
	{
		super.draw();
		renderer.render(world, camera.combined);
	}
	
	@Override
	public void dispose()
	{
		/*
		 * liberaci�n de recursos
		 */
		super.dispose();
		renderer.dispose();
		world.dispose();
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
	
	
}
