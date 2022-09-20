package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;


public class View {
    private Model model;
    private Texture playerImage;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private static final int SCREEN_WIDTH = 800;
    private static final int SCREEN_HEIGHT = 480;

    Texture img;
    TiledMap tiledMap;
    TiledMapRenderer tiledMapRenderer;

    public View(Model model) {
        this.model = model;
    }

    public void initialize() {
        // Load an image
        playerImage = new Texture(Gdx.files.internal("characters/BlueSamurai-Idle.png"));

        camera = new OrthographicCamera();
        camera.setToOrtho(false, SCREEN_WIDTH, SCREEN_HEIGHT);

        tiledMap = new TmxMapLoader().load("tilemapthings/Test2ActualMap2.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);

        batch = new SpriteBatch();
    }

    public void update() {
        //ScreenUtils.clear(1, 1, 0.9f, 1);
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //tiledMapRenderer.render();
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();

        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(playerImage, model.getPlayerX(), model.getPlayerY());
        batch.end();
    }
    public void dispose () {
        playerImage.dispose();
        batch.dispose();
    }
}
