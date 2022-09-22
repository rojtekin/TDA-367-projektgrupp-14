package View;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

final class MainScreen extends BaseScreen {

    MainScreen(){
        super();
        createPlayButton();
        createSettingsButton();

    }

    private void createSettingsButton() {
    }

    private void createPlayButton() {
    }

    @Override
    void setBackgroundImage() {
        com.badlogic.gdx.scenes.scene2d.ui.Image img = new Image(new Texture("mainScreen_image.png"));
    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {

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
