package view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;


public class AbstractScreen implements Screen {
    protected OrthographicCamera camera;
    protected static final int SCREEN_WIDTH = 900;
    protected static final int SCREEN_HEIGHT = 480;
    protected SpriteBatch batch;
    protected final Stage stage;

    public AbstractScreen( ) {
        FitViewport stageViewport = new FitViewport(SCREEN_WIDTH, SCREEN_HEIGHT);
        batch = new SpriteBatch();
        stage = new Stage(stageViewport, batch);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, SCREEN_WIDTH, SCREEN_HEIGHT);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
        stage.act(delta );
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
