package view;

import com.badlogic.gdx.graphics.g2d.Sprite;
import model.*;
import model.monsters.Monster;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import application.Time;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


/**
 * A class responsible for presenting a part of the model to the user.
 */
public class View implements ISoundObserver {
    private HUD hud;
    private final Model model;
    private float timeWhenPlayerWalkFrameChanged = 0f;
    private int currentPlayerWalkFrame = 0;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private static final int SCREEN_WIDTH = 800;
    private static final int SCREEN_HEIGHT = 480;

    private TiledMap tiledMap;
    private TiledMapRenderer tiledMapRenderer;
    private final ImageHandler imageHandler = new ImageHandler();
    private final SoundHandler soundHandler = new SoundHandler();

    private final Sprite swordSprite = new Sprite(imageHandler.getSwordSwingImage());

    private Set<IEntity> isKnown = new HashSet<>();
    private Set<IEntity> seen = new HashSet<>();

    /**
     * Constructs a view
     * @param model game model
     */
    public View(Model model) {
        this.model = Objects.requireNonNull(model);
    }

    /**
     * initializes images, tiledMap, sound, camera and SpriteBatch (drawing board)
     */
    public void initialize() {
        soundHandler.playGameMusic();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, SCREEN_WIDTH, SCREEN_HEIGHT);

        tiledMap = (TiledMap) model.getMap();
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);

        batch = new SpriteBatch();
        hud = new HUD(batch, model);
    }

    /**
     * updates View
     * draws underlayer, tiledMap,
     */
    public void update() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();

        centerCameraOnPlayer();

        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        updatePlayerWalkFrame();
        drawEntities();

        checkWeaponSwing();

        batch.end();

        hud.update();
        batch.setProjectionMatrix(hud.getStage().getCamera().combined);
        hud.getStage().draw();

        playIdleSounds();
    }

    private void playIdleSounds() {
        ArrayList<IEntity> entities = model.getEntities();
        for (IEntity entity : entities){
            if (!isKnown.contains((entity))){
                soundHandler.playIdleSoundsWithInterval(model, 2000);
            }
            seen.add(entity);
        }
        isKnown = seen;
    }

    private void centerCameraOnPlayer() {
        camera.position.set(model.getPlayer().getX() + model.getPlayer().getWidth() / 2, model.getPlayer().getY() + model.getPlayer().getHeight() / 2, 0);
    }

    private void drawPlayer() {
        batch.draw(imageHandler.getEntityImage(PlayerCharacter.class, model.getPlayerDirection(), currentPlayerWalkFrame), model.getPlayer().getX(), model.getPlayer().getY());
    }

    private void drawEnemies() {
        for (Monster monster : model.getMonsters()) {
            batch.draw(imageHandler.getEntityImage(monster.getClass(), monster.getDirection(), 0),
                    monster.getX(), monster.getY());
        }
    }

    private void drawEntities() {
        drawPlayer();
        drawEnemies();
    }

    /**
     * automatically disposes hud, batch and tiledMap when screen gets closed
     */
    public void dispose () {
        hud.dispose();
        batch.dispose();
        tiledMap.dispose();
    }

    /**
     * Changes the player walk frame to the next frame in the walk animation after a certain time interval if the player is moving.
     * Otherwise, the frame is set to the first frame of the walk animation.
     */
    private void updatePlayerWalkFrame() {
        if (model.playerIsInMotion()) {
            float timeSincePlayerWalkFrameChanged = Time.getInstance().getTicks() - timeWhenPlayerWalkFrameChanged;
            if (timeSincePlayerWalkFrameChanged > 12) {
                currentPlayerWalkFrame++;
                if (currentPlayerWalkFrame > 3) { currentPlayerWalkFrame = 0; }
                timeWhenPlayerWalkFrameChanged = Time.getInstance().getTicks();
            }
        }
        else {
            currentPlayerWalkFrame = 0;
            timeWhenPlayerWalkFrameChanged = Time.getInstance().getTicks();
        }
    }

    /**
     * checks if sword has been swung and if swung calls for it to be drawn
     */
    private void checkWeaponSwing() {
        if (model.getPlayer().isSwinging()) {
            drawWeaponSwing();
        }
    }
    /**
     * draws the weaponSwing
     */
    private void drawWeaponSwing() {
        batch.draw(swordSprite, model.getPlayer().getX(), model.getPlayer().getY(), model.getPlayer().getHeight() / 2, model.getPlayer().getWidth() / 2, 64, 64, 1, 1, (float) model.getPlayer().getWeapon().getWeaponAngle());
    }

    @Override
    public void playEnemyHit() {
        soundHandler.playEnemyHit();
    }

    @Override
    public void playSwordHit() {
        soundHandler.playSwordSwing();
    }

    @Override
    public void playPlayerDeathSound() {
        soundHandler.playPlayerDeathSound();
    }
}
