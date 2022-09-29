package View;

import Model.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.SoundLoader;
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
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.*;


public class View {
    private Model model;
    private float timeSincePlayerWalkFrameChanged = 0f;
    private int currentPlayerWalkFrame = 0;
    private HashMap<String, TextureRegion[][]> allEntityImages = new HashMap<String, TextureRegion[][]>();
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
        loadEntityImages();

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
        updatePlayerWalkFrame();
        batch.draw(getPlayerImage(currentPlayerWalkFrame), model.getPlayer().getX(), model.getPlayer().getY());
        drawAllEntities(model);
        batch.end();


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
        batch.dispose();
        tiledMap.dispose();
    }

    /**
     * Loads a sprite sheet for the specified entity, splits it into multiple images
     * and returns a 2D array which contains them.
     * @param entityName the name of the entity
     * @return a 2D array with texture regions of the sprite sheet of the specified entity
     */
    private TextureRegion[][] getEntityImages(String entityName) {
        Texture spriteSheet = new Texture(Gdx.files.internal("entities/" + entityName + "SpriteSheet.png"));
        int nColumnsPlayerWalkSheet = 4;
        int nRowsPlayerWalkSheet = 4;
        // Splits the sprite sheet into multiple images
        return TextureRegion.split(spriteSheet,
                spriteSheet.getWidth() / nColumnsPlayerWalkSheet,
                spriteSheet.getHeight() / nRowsPlayerWalkSheet);
    }

    /**
     * Loads the entity images and puts them in the HashMap allEntityImages.
     */
    private void loadEntityImages() {
        allEntityImages.put("Player", getEntityImages("Player"));
        allEntityImages.put("Mouse", getEntityImages("Mouse"));
        allEntityImages.put("Cyclops", getEntityImages("Cyclops"));
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
     * Gets the player image based on the direction of the player and the current frame in the walk animation.
     * @param currentFrame the current frame in the player walk animation
     */
    private TextureRegion getPlayerImage(int currentFrame) {
        Direction playerDirection = model.getPlayerDirection();
        TextureRegion[][] playerWalkFrames = allEntityImages.get("Player");
        switch(playerDirection) {
            case UP:
                return playerWalkFrames[currentFrame][1];
            case DOWN:
                return playerWalkFrames[currentFrame][0];
            case LEFT:
                return playerWalkFrames[currentFrame][2];
            case RIGHT:
                return playerWalkFrames[currentFrame][3];
            default:
                throw new AssertionError();
        }
    }

    /**
     * Gets the entity image based on the direction of the entity and the current frame in the walk animation.
     * @param entityName the name of the entity
     * @param currentFrame the current frame in the entity walk animation
     * @param direction the direction that the entity is facing
     * @return the texture region which matches the state of the entity
     */
    private TextureRegion getEntityImage(String entityName, Direction direction, int currentFrame) {
        TextureRegion[][] entityImages = allEntityImages.get(entityName);
        switch(direction) {
            case UP:
                return entityImages[currentFrame][1];
            case DOWN:
                return entityImages[currentFrame][0];
            case LEFT:
                return entityImages[currentFrame][2];
            case RIGHT:
                return entityImages[currentFrame][3];
            default:
                throw new AssertionError();
        }
    }
}
