/**
 * function: este archivo tiene la funcionalidad de cargar todos los recursos
 *           del juego (texturas, musica, sonidos)
 * @author user: JVillegas
 */

package com.nng.cokygame.resources;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public class AssetsManager {
	
	private HashMap<String, Texture> textures;
	private HashMap<String, Music>   music;
	private HashMap<String, Sound>   sound;
	
	public AssetsManager() {
		this.init();
	}
	
	private void init(){
		textures = new HashMap<String, Texture>();
		music    = new HashMap<String, Music>();
		sound    = new HashMap<String, Sound>();
	}
	
	public void loadTexture(String path, String key){
		Texture tex = new Texture(Gdx.files.internal(path));
		textures.put(key, tex);
	}
	
	public void loadMusic(String path, String key){
		Music mus = Gdx.audio.newMusic(Gdx.files.internal(path));
		music.put(key, mus);
	}
	
	public void loadSound(String path, String key){
		Sound son = Gdx.audio.newSound(Gdx.files.internal(path));
		sound.put(key, son);
	}
	
	public Texture getTexture(String key){
		return textures.get(key);
	}
	
	public Music getMusic(String key){
		return music.get(key);
	}
	
	public Sound getSound(String key){
		return sound.get(key);
	}
	
	public void disposeTexture(String key){
		Texture tex = textures.get(key);
		if(tex != null) tex.dispose();
	}
	
	public void disposeMusic(String key) {
		Music mus = music.get(key);
		if(mus != null) mus.dispose();
	}
	
	public void disposeSound(String key) {
		Sound son = sound.get(key);
		if(son != null) son.dispose();
	}
	
	public void disposeAll() {
		// code for dispose all recurses
	}

}
