package view;

import controller.MenuScreenController;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

final class MenuScreen extends BaseScreen {
    private final MenuScreenController menuScreenController;
    private final String ScreenAssetsRoot = "Screen/Buttons/";
    MenuScreen(MenuScreenController menuScreenController){
        super();
        this.menuScreenController = menuScreenController;
        createPlayButton();
        createSettingsButton();

    }

    private void createSettingsButton() {
        TextureAtlas settingsButtonAtlas = new TextureAtlas(Gdx.files.internal(ScreenAssetsRoot + "/SettingButton.atlas")); // Load atlas file from skin
        Skin settingsButtonSkin = new Skin(Gdx.files.internal(ScreenAssetsRoot + "/SettingButton.json"), settingsButtonAtlas); // Create skin object
        Button settingsButton = new Button(settingsButtonSkin);
        settingsButton.setPosition(832, 400);
        menuScreenController.addSettingsButtonClickListener(settingsButton);
        addActor(settingsButton);
    }

    private void createPlayButton() {
        TextureAtlas playButtonAtlas = new TextureAtlas(Gdx.files.internal(ScreenAssetsRoot + "PlayButton.atlas"));
        Skin playButtonSkin = new Skin(Gdx.files.internal(ScreenAssetsRoot + "/PlayButton.json"), playButtonAtlas);
        Button playButton = new Button(playButtonSkin);
        playButton.setPosition(832, 500);
        menuScreenController.addSettingsButtonClickListener(playButton);
        addActor(playButton);

    }

    @Override
    void setBackgroundImage() {
        com.badlogic.gdx.scenes.scene2d.ui.Image img = new Image(new Texture("mainScreen_image.png"));
    }

    @Override
    public void render(float delta) {
        super.render(Gdx.graphics.getDeltaTime());

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
