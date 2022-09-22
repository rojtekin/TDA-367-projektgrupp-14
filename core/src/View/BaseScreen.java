package View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;

public abstract class BaseScreen extends Stage implements Screen {
    final Batch batch = new SpriteBatch();
    private final InputMultiplexer multiplexer = new InputMultiplexer();
    BaseScreen() {
        super(new FitViewport(800, 400, new OrthographicCamera(800, 400)));
        multiplexer.addProcessor(this);
        Gdx.input.setInputProcessor(multiplexer);
    }
    void addToMultiplexer(InputProcessor newProcessor) {
        multiplexer.addProcessor(newProcessor);
    }
    abstract void setBackgroundImage();

    @Override
    public void show() {
        Gdx.input.setInputProcessor(multiplexer);
    }

    @Override
        public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        super.act(delta);
        super.draw();
        Gdx.input.setInputProcessor(multiplexer);
    }

    @Override
    public void resize(int width, int height) {
        getViewport().update(width, height, true);
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

