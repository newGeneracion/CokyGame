package com.nng.cokygame.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.nng.cokygame.actors.Background;
import com.nng.cokygame.actors.EnemyCactusActor;
import com.nng.cokygame.actors.Ground;
import com.nng.cokygame.actors.Player;
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
	/*
	 * World fisic , gravity , listener of contacts 
	 * Mundo fisico, gravedad,  escuchador de contactos entre fixturas, render del mundo fisico
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
		super(new ScalingViewport(Scaling.stretch, VIEWPORT_WIDTH, VIEWPORT_HEIGHT, new OrthographicCamera(VIEWPORT_WIDTH, VIEWPORT_HEIGHT)));
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
		addActor(new Background());
		ground = new Ground(WorldUtils.createGround(world));
		addActor(ground);
		player = new Player(WorldUtils.createPlayer(world));
		addActor(player);
		renderer = new Box2DDebugRenderer();
		createObjectEnemyCactus();
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
	}
	
	public void updateBody(Body body) 
	{
		if (BodyUtils.bodyIsObejctCactusEnemy(body) && !(BodyUtils.bodyInBounds(body)))
		{
			if (!player.isHit())
			{
				createObjectEnemyCactus();
			}
			world.destroyBody(body);
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
		 * liberación de recursos
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
