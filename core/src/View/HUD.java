package View;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class HUD {
    private Stage stage;
    private FitViewport stageViewport;

    public HUD(SpriteBatch spriteBatch) {
        stageViewport = new FitViewport(800,480);
        stage = new Stage(stageViewport, spriteBatch);
        Table table = new Table();
        table.top();
        table.left();
        table.setFillParent(true);

        Label testupperLabel = new Label("UPP", new Label.LabelStyle((new BitmapFont()), Color.WHITE));
        Label testbottomLabel = new Label("DOWn", new Label.LabelStyle((new BitmapFont()), Color.WHITE));
        table.add(testupperLabel).expandX().padTop(10);

        table.row();
        //table.bottom();
        table.add(testbottomLabel).expandX().padBottom(10);

        stage.addActor(table);
}

    public Stage getStage() { return stage; }

    public void dispose(){
        stage.dispose();
    }
    }