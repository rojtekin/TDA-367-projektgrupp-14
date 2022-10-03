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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


public class View {
    private HUD hud;
    private Model model;
    private TextureRegion playerImage;
    private Texture playerWalkSheet;
    private TextureRegion[][] playerWalkFrames;
    private float timeSincePlayerWalkFrameChanged = 0f;
    private int currentPlayerWalkFrame = 0;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private static final int SCREEN_WIDTH = 800;
    private static final int SCREEN_HEIGHT = 480;

    private TiledMap tiledMap;
    private TiledMapRenderer tiledMapRenderer;
    private ImageLoader imageLoader = new ImageLoader();
    private Sound soundLoader = new Sound();
    private int i = 0;

    private Set<Entity> isKnown = new HashSet<Entity>();

    public View(Model model) {
        this.model = model;
    }

    public void initialize() {
        loadPlayerImages();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, SCREEN_WIDTH, SCREEN_HEIGHT);

        tiledMap = model.getTiledMap();
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);

        batch = new SpriteBatch();
        hud = new HUD(batch, model.getPlayer());
    }

    public void update() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();

        centerCameraOnPlayer(); //makes the camera follow PlayerCharacter (keeps the player in the center of the screen)

        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        updatePlayerWalkFrame();
        updatePlayerImage(currentPlayerWalkFrame);
        batch.draw(playerImage, model.getPlayer().getX(), model.getPlayer().getY());
        drawAllEntities(model);
        batch.end();

        hud.update();
        batch.setProjectionMatrix(hud.getStage().getCamera().combined);
        //hud.getStage().act(delta);
        hud.getStage().draw();

        // do something when a entity spawns
        ArrayList<Entity> entities = model.getEntities();
        Set<Entity> seen = new HashSet<Entity>();
        for (Entity entity : entities){
            if (!isKnown.contains((entity))){
                soundLoader.playSounds(model);
            }
            seen.add(entity);
        }
        isKnown = seen;
    }

    private void centerCameraOnPlayer() {
        camera.position.set(model.getPlayer().getX() + model.getPlayer().getWidth() / 2, model.getPlayer().getY() + model.getPlayer().getHeight() / 2, 0);
    }

    private void drawAllEntities(Model model) {
        for (Entity entity : model.getEntities()){
            if (!(entity instanceof PlayerCharacter))
            batch.draw(imageLoader.loadImage(entity), entity.getX(), entity.getY());
        }
    }

    public void dispose () {
        hud.dispose();
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

    /**
     * Changes the player walk frame to the next frame in the walk animation after a certain time interval if the player is moving.
     * Otherwise, the frame is set to the first frame of the walk animation.
     */
    public void updatePlayerWalkFrame() {
        if (model.playerIsMoving()) {
            timeSincePlayerWalkFrameChanged += Gdx.graphics.getDeltaTime();
            if (timeSincePlayerWalkFrameChanged > 0.2) {
                currentPlayerWalkFrame++;
                if (currentPlayerWalkFrame > 3) { currentPlayerWalkFrame = 0; }
                timeSincePlayerWalkFrameChanged = 0f;
            }
        }
        else {
            currentPlayerWalkFrame = 0;
            timeSincePlayerWalkFrameChanged = 0f;
        }
    }


    /**
     * Updates the player image based on the direction of the player and the current frame in the walk animation.
     * @param currentFrame the current frame in the player walk animation
     */
    private void updatePlayerImage(int currentFrame) {
        Direction playerDirection = model.getPlayerDirection();
        switch(playerDirection) {
            case UP:
                playerImage = playerWalkFrames[currentFrame][1];
                break;
            case DOWN:
                playerImage = playerWalkFrames[currentFrame][0];
                break;
            case LEFT:
                playerImage = playerWalkFrames[currentFrame][2];
                break;
            case RIGHT:
                playerImage = playerWalkFrames[currentFrame][3];
                break;
        }
    }
}
