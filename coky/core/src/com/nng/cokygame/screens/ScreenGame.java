package com.nng.cokygame.screens;

import static com.nng.cokygame.controllers.Constants.PPM;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.nng.cokygame.controllers.Constants;
import com.nng.cokygame.handlers.MyContactListener;
import com.nng.cokygame.handlers.MyInput;
import com.nng.cokygame.handlers.ControladorTeclado;

public class ScreenGame extends AbstractScreen {
	
	/* ######################################################################
	 * ############################# ATRIBUTOS ##############################
	 * ######################################################################*/
	
	/*
	 * Mundo fisico, gravedar,  escuchador de contactos entre fixturas, render del mundo fisico
	 */
	private World world;
	private Box2DDebugRenderer box2dRenderer;
	private MyContactListener myContactListener;
	private Vector2 gravity;
	
	/*
	 * Camara del Juego
	 */
	private OrthographicCamera camera;
	/*
	 * cuerpo en box2d del jugador
	 */
	private Body bodyPlayer;
	/*
	 * Cuerpo en box2d del suelo
	 */
	private Body bodyGround;
	/*
	 * interfaz que permite detectar cuando dos fixturas han hecho contacto entre si
	 */
	
	/*
	 * acumulador del tiempo delta
	 */
	private float accum;
	
	public ScreenGame(){
		super();
		this.init();
	}
	
	private void init(){
		
		this.gravity = new Vector2(Constants.GRAVITY_X, Constants.GRAVITY_Y);
		this.world   = new World(gravity, true);
		this.myContactListener = new MyContactListener();
		Gdx.input.setInputProcessor(new ControladorTeclado());
		this.world.setContactListener(myContactListener);
		this.box2dRenderer = new Box2DDebugRenderer();
		
		this.camera  = new OrthographicCamera();
		this.camera.setToOrtho(false, Constants.SCREEN_WIDTH / PPM, Constants.SCREEN_HEIGHT / PPM);
		// this.camera.position.set(Constants.SCREEN_HEIGHT/2f, Constants.SCREEN_WIDTH/2f, 0);
		
		
		/*
		 * Creación del cuerpo del jugador = fixturedef + bodydef
		 */
		BodyDef bDefPlayer = new BodyDef();
		// bDefPlayer.angle   = 0f;
		bDefPlayer.type = BodyDef.BodyType.DynamicBody;
		// bDefPlayer.linearVelocity.set(1f, 0);
		bDefPlayer.position.set(10 / PPM, 20 / PPM);
		
		PolygonShape boxPlayer = new PolygonShape();
		boxPlayer.setAsBox(10 / PPM, 10 / PPM);
	
		FixtureDef fDefPlayer = new FixtureDef();
		fDefPlayer.shape      = boxPlayer;
	    // fDefPlayer.isSensor    = false;
		fDefPlayer.filter.categoryBits = Constants.BIT_PLAYER;
		fDefPlayer.filter.maskBits     = Constants.BIT_GROUND;
		fDefPlayer.restitution = 0f;
		fDefPlayer.density     = 0f;
		fDefPlayer.friction    = 0f;
		
		bodyPlayer = world.createBody(bDefPlayer);
		bodyPlayer.createFixture(fDefPlayer).setUserData(Constants.USER_DATA_PLAYER);
		
		// create foot sensor for player
		boxPlayer.setAsBox(10 / PPM, 2 / PPM, new Vector2(0, -10/ PPM), 0);
		fDefPlayer.shape = boxPlayer;
		fDefPlayer.isSensor = true;
		fDefPlayer.filter.categoryBits = Constants.BIT_PLAYER;
		fDefPlayer.filter.maskBits     = Constants.BIT_GROUND;
		bodyPlayer.createFixture(fDefPlayer).setUserData(Constants.USER_DATA_PLAYER_FOOT);
		
		boxPlayer.dispose();
		
		/*
		 * Creación del suelo = fixturedef + bodydef
		 */
		BodyDef bDefGround = new BodyDef();
		bDefGround.type  = BodyDef.BodyType.StaticBody;
		bDefGround.angle = 0;
		bDefGround.position.set(0, 0);
		
		
		PolygonShape boxGround = new PolygonShape();
		boxGround.setAsBox(Constants.SCREEN_WIDTH / PPM, 10f / PPM);
		
		FixtureDef fDefGround = new FixtureDef();
		fDefGround.shape    = boxGround;
		fDefGround.isSensor = false;
		fDefGround.filter.categoryBits = Constants.BIT_GROUND;
		fDefGround.filter.maskBits     = Constants.BIT_PLAYER;
		fDefGround.restitution = 0f;
		fDefGround.density  = 0f;
		fDefGround.friction = 0f;
		
		bodyGround = world.createBody(bDefGround);
		bodyGround.createFixture(fDefGround).setUserData(Constants.USER_DATA_GROUND);
		boxGround.dispose();
		
		this.accum = 0;
	}
	
	public void buildStage(){}
	
	public void render(float deltaTime) {
		accum += deltaTime;
		while(accum > Constants.GAME_STEP) {
			accum -= deltaTime;
			super.render(Constants.GAME_STEP);
			world.step(Constants.GAME_STEP, 6, 2);
			/*
			 *  Metodo para leer la entrada del usuario
			 */
			this.handleInput(Constants.GAME_STEP);
			/*
			 *  Metodo para la actualización del mundo
			 */
			this.updateWorld(Constants.GAME_STEP);
			/*
			 *  Metodo para la renderizacion del mundo
			 */
			this.renderWorld(Constants.GAME_STEP);
			/*
			 * Actualiza la entrada de teclas para detectar si se esta
			 * presionando una tecla
			 */
			MyInput.update();
		}
	}
	
	private void handleInput(float deltaTime){
		
		/*
		 * Manejar los estados del jugador
		 */
		if (MyInput.isPressed(MyInput.BUTTON_PLAYER_JUMPER) && myContactListener.getJump()) bodyPlayer.applyForceToCenter(0, 200, true);
		if (MyInput.isPressed(MyInput.BUTTON_PLAYER_RUN)) bodyPlayer.setLinearVelocity(1, 0f);
		/*
		 * Manejar los estados del juego
		 */
		if (MyInput.isPressed(MyInput.BUTTON_GAME_RESTART)) this.init();
		if (MyInput.isPressed(MyInput.BUTTON_GAME_EXIT)) Gdx.app.exit();
		
	}
	
	private void updateWorld(float deltaTime){
		
	}
	
	private void renderWorld(float deltaTime){
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		box2dRenderer.render(world, camera.combined);
	}
	
	public void dispose(){
		/*
		 * liberación de recursos
		 */
		super.dispose();
		box2dRenderer.dispose();
		world.dispose();
	}
	
}

