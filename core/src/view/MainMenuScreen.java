package view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import controller.MenuScreenController;


public class MainMenuScreen extends AbstractScreen {


    final Skin skin;
    private final MenuScreenController menuScreenController;


    public MainMenuScreen(MenuScreenController menuScreenController) {
        this.menuScreenController = menuScreenController;
        this.skin = new Skin(Gdx.files.internal("Screen/Buttons/uiskin.json"));
    }



    @Override
    public void show() {
        Image img = new Image(new Texture("main_menu_background_image.png"));
        stage.getActors().insert(0, img);



        Table table = new Table(skin);
        table.center().top();
        table.setFillParent(true);


        TextButton newGame = new TextButton("New Game", skin);
        TextButton settings = new TextButton("Settings", skin);
        TextButton exit = new TextButton("Exit", skin);


        table.row();
        table.add(newGame).padTop(80).minWidth(250).minHeight(50);
        table.row();
        table.add(settings).padTop(100).minWidth(250).minHeight(50);
        table.row();
        table.add(exit).padTop(60).minWidth(250).minHeight(50);

        menuScreenController.addSettingsButtonClickListener(settings);
        menuScreenController.addPlayButtonClickListener(newGame);
        stage.addActor(table);

        stage.draw();


    }





    @Override
    public void render(float delta) {
        super.render(Gdx.graphics.getDeltaTime());

    }

    @Override
    public void resize(int width, int height) {

    }


    @Override
    public void dispose() {
    stage.dispose();
    batch.dispose();
    skin.dispose();
    }

}
