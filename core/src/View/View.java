package View;

import Model.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class View {
    private IModel model;
    private Texture playerImage;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private static final int SCREEN_WIDTH = 800;
    private static final int SCREEN_HEIGHT = 480;

    public View(IModel model) {
        this.model = model;
    }

    public void initialize() {
        // Load an image
        playerImage = new Texture(Gdx.files.internal("characters/BlueSamurai-Idle.png"));

        camera = new OrthographicCamera();
        camera.setToOrtho(false, SCREEN_WIDTH, SCREEN_HEIGHT);

        batch = new SpriteBatch();
    }

    public void update() {
        ScreenUtils.clear(1, 1, 0.9f, 1);
        //makes camera follow PlayerCharacter (keeps the player in the center of the screen)
        camera.position.set(PlayerCharacter.instance().getX() + PlayerCharacter.instance().getWidth() / 2, PlayerCharacter.instance().getY() + PlayerCharacter.instance().getHeight() / 2, 0);

        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(playerImage, PlayerCharacter.instance().getX(), PlayerCharacter.instance().getY());
        batch.end();
    }
    public void dispose () {
        playerImage.dispose();
        batch.dispose();
    }
}
