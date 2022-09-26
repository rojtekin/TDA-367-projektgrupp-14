package View;

import Model.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;


public class View {
    private Model model;
    private TextureRegion playerImage;
    private Texture playerWalkSheet;
    private TextureRegion[][] playerWalkFrames;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private static final int SCREEN_WIDTH = 800;
    private static final int SCREEN_HEIGHT = 480;

    private TiledMap tiledMap;
    private TiledMapRenderer tiledMapRenderer;

    public View(Model model) {
        this.model = model;
    }

    public void initialize() {
        loadPlayerImages();

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

        //makes camera follow PlayerCharacter (keeps the player in the center of the screen)
        camera.position.set(PlayerCharacter.instance().getX() + PlayerCharacter.instance().getWidth() / 2, PlayerCharacter.instance().getY() + PlayerCharacter.instance().getHeight() / 2, 0);

        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        updatePlayerImage();
        batch.draw(playerImage, PlayerCharacter.instance().getX(), PlayerCharacter.instance().getY());
        batch.end();
    }
    public void dispose () {
        playerWalkSheet.dispose();
        batch.dispose();
        tiledMap.dispose();
    }

    private void loadPlayerImages() {
        playerWalkSheet = new Texture(Gdx.files.internal("characters/BlueSamurai-Walk.png"));
        int nColumnsPlayerWalkSheet = 4;
        int nRowsPlayerWalkSheet = 4;
        // Splits the player character walk sheet into multiple frames
        playerWalkFrames = TextureRegion.split(playerWalkSheet,
                playerWalkSheet.getWidth() / nColumnsPlayerWalkSheet,
                playerWalkSheet.getHeight() / nRowsPlayerWalkSheet);
    }

    private void updatePlayerImage() {
        Direction playerDirection = model.getPlayerDirection();
        switch(playerDirection) {
            case UP:
                playerImage = playerWalkFrames[0][1];
                break;
            case DOWN:
                playerImage = playerWalkFrames[0][0];
                break;
            case LEFT:
                playerImage = playerWalkFrames[0][2];
                break;
            case RIGHT:
                playerImage = playerWalkFrames[0][3];
                break;
        }
    }
}
