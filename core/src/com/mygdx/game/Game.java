package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class Game extends ApplicationAdapter {
	private Texture playerImage;
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Player player;
	private static final int SCREEN_WIDTH = 800;
	private static final int SCREEN_HEIGHT = 480;

	@Override
	public void create () {
		// Load an image
		playerImage = new Texture(Gdx.files.internal("characters/BlueSamurai-Idle.png"));

		camera = new OrthographicCamera();
		camera.setToOrtho(false, SCREEN_WIDTH, SCREEN_HEIGHT);

		batch = new SpriteBatch();
		player = new Player(SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2, 32, 32);
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 1, 0.9f, 1);
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.draw(playerImage, player.getX(), player.getY());
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
