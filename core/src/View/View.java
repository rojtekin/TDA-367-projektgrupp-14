package View;

import Model.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.SoundLoader;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class View {
    private Model model;
    private Texture playerImage;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private static final int SCREEN_WIDTH = 800;
    private static final int SCREEN_HEIGHT = 480;

    private TiledMap tiledMap;
    private TiledMapRenderer tiledMapRenderer;
    private ImageLoader imageLoader = new ImageLoader();
    private Sound soundLoader = new Sound();
    private int i = 0;

    public View(Model model) {
        this.model = model;
    }

    public void initialize() {
        // Load images

        camera = new OrthographicCamera();
        camera.setToOrtho(false, SCREEN_WIDTH, SCREEN_HEIGHT);

        tiledMap = new TmxMapLoader().load("Map/Test2ActualMap2.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);

        batch = new SpriteBatch();
    }

    public void update() {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();

        centerCameraOnPlayer(); //makes the camera follow PlayerCharacter (keeps the player in the center of the screen)

        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        drawAllEntities(model);
        batch.end();

        soundLoader.playSounds(model);

    }

    private void centerCameraOnPlayer() {
        camera.position.set(model.getPlayer().getX() + model.getPlayer().getWidth() / 2, model.getPlayer().getY() + model.getPlayer().getHeight() / 2, 0);
    }

    private void drawAllEntities(Model model) {
        for (Entity entity : model.getEntities()){
            batch.draw(imageLoader.loadImage(entity), entity.getX(), entity.getY());
        }
    }

    public void dispose () {
        playerImage.dispose();
        batch.dispose();
        tiledMap.dispose();
    }
}
