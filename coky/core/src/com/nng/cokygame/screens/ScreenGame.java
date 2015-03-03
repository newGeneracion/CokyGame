package com.nng.cokygame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.nng.cokygame.controllers.Constants;

public class ScreenGame extends AbstractScreen {
	
	private SpriteBatch batch;
	private Texture     img;
	private World       world;
	private Box2DDebugRenderer box2dRenderer;
	private Vector2 gravity;
	private OrthographicCamera camera;
	private Body bodyPlayer;
	private Body bodyGround;
	
	public ScreenGame(){
		super();
		this.init();
	}
	
	private void init(){
		
		this.gravity = new Vector2(Constants.GRAVITY_X, Constants.GRAVITY_Y);
		this.world   = new World(gravity, true);
		this.box2dRenderer = new Box2DDebugRenderer();
		
		this.camera  = new OrthographicCamera();
		this.camera.setToOrtho(false, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
		this.camera.position.set(Constants.SCREEN_HEIGHT/2f, Constants.SCREEN_WIDTH/2f, 0);
		
		// create player
		BodyDef bDefPlayer = new BodyDef();
		bDefPlayer.angle   = 0f;
		bDefPlayer.type = BodyDef.BodyType.DynamicBody;
		bDefPlayer.position.set(0, 50);
		
		PolygonShape boxPlayer = new PolygonShape();
		boxPlayer.setAsBox(10, 10);
	
		FixtureDef fDefPlayer = new FixtureDef();
		fDefPlayer.shape       = boxPlayer;
		fDefPlayer.isSensor    = false;
		fDefPlayer.restitution = 0f;
		fDefPlayer.density     = 0f;
		fDefPlayer.friction    = 0f;
		
		bodyPlayer = world.createBody(bDefPlayer);
		bodyPlayer.createFixture(fDefPlayer);
		boxPlayer.dispose();
		
		// create ground
		BodyDef bDefGround = new BodyDef();
		bDefGround.type  = BodyDef.BodyType.StaticBody;
		bDefGround.angle = 0;
		bDefGround.position.set(0, 0);
		
		
		PolygonShape boxGround = new PolygonShape();
		boxGround.setAsBox(Constants.SCREEN_WIDTH, 10f);
		
		FixtureDef fDefGround = new FixtureDef();
		fDefGround.shape    = boxGround;
		fDefGround.isSensor = false;
		fDefGround.restitution = 0f;
		fDefGround.density  = 10f;
		fDefGround.friction = 0.2f;
		
		bodyGround = world.createBody(bDefGround);
		bodyGround.createFixture(fDefGround);
		boxGround.dispose();
	}
	
	public void buildStage(){
		this.batch = new SpriteBatch();
		this.img   = new Texture("badlogic.jpg");
	}
	
	public void render(float deltaTime) {
		super.render(deltaTime);
		this.updateWorld(deltaTime);
		this.renderWorld(deltaTime);
	}
	
	private void updateWorld(float deltaTime){
		world.step(deltaTime, 6, 2);
	}
	
	private void renderWorld(float deltaTime){
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		box2dRenderer.render(world, camera.combined);
	}
	
	public void dispose(){
		super.dispose();
		box2dRenderer.dispose();
		world.dispose();
	}
	
}

/*public class ScreenGame extends ScreenAdapter{
	
	private SpriteBatch batch;
	private Texture     img;
	private CokyGame    game;
	
	public ScreenGame(CokyGame game){
		this.game = game;
	}
	
	@Override
	public void show() {
		super.show();
		this.init();
	}
	
	private void init(){
        this.batch = new SpriteBatch();
		this.img   = new Texture("badlogic.jpg");
	}

	@Override
	public void render(float deltaTime) {
		super.render(deltaTime);
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		this.batch.begin();
		this.batch.draw(this.img, 0, 0);
		this.batch.end();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

	@Override
	public void pause() {
		super.pause();		
	}

	@Override
	public void resume() {
		super.resume();		
	}
    
	public void hide() {
		super.hide();
	}

	public void dispose() {
		super.dispose();
	}

}*/
