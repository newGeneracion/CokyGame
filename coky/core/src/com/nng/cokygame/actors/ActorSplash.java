package com.nng.cokygame.actors;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.AlphaAction;
import com.badlogic.gdx.scenes.scene2d.actions.DelayAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.nng.cokygame.enums.ScreenEnum;
import com.nng.cokygame.enums.ScreenManager;

public class ActorSplash extends Actor{
	
	private Game game;
	private AlphaAction alpha, alpha2;
	private DelayAction delay;
	private SequenceAction sequence;
	private Texture text;
	private Image ImgBr;
	
	public ActorSplash(Game game){
		this.game = game;
		this.init();
	}
	
	private void init(){
		
		this.sequence = new SequenceAction();
		
		this.alpha = new AlphaAction();
		this.alpha.setAlpha(0f);
		this.alpha.setDuration(1f);
		
		this.alpha2 = new AlphaAction();
		this.alpha2.setAlpha(1f);
		this.alpha2.setDuration(2f);
		
		this.delay = new DelayAction();
		this.delay.setDuration(1f);
		
		this.sequence.addAction(alpha);
		this.sequence.addAction(delay);
		this.sequence.addAction(alpha2);
		
		this.addAction(sequence);
		
		this.text  = new Texture(Gdx.files.internal("img/IMAGE_BLACK.png"));
		this.ImgBr = new Image(text);
		this.ImgBr.getColor().a = 1;
		
	}
	@Override
	public void draw(Batch batch, float parentAlpha){
		super.draw(batch, parentAlpha);
		this.ImgBr.getColor().a = this.getColor().a;
		this.ImgBr.draw(batch, parentAlpha);
		if (this.sequence.getActions().size == 0){
			ScreenManager.getInstance().showScreen(ScreenEnum.SCREEN_GAME);
			this.remove();
		}
	}
	
	public Game getGame()
	{
		return game;
	}

}
