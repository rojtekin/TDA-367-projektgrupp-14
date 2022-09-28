package View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class HUD {
    private Stage stage;
    private FitViewport stageViewport;

    public HUD(SpriteBatch spriteBatch) {
        stageViewport = new FitViewport(800,480);
        stage = new Stage(stageViewport, spriteBatch);

        int score = 10;

        //The code under is a way to make a certain color since libgdx somehow doesnt know rgb values needed
        //for a square

        Texture black= new Texture(Gdx.files.internal("CSSforExpBar/Black.png"));
        NinePatch blackHp = new NinePatch(black, 0, 0, 0, 0);
        Image blackHpBar = new Image(blackHp);
        Image blackXpBar = new Image(blackHp);

        Texture aqua = new Texture(Gdx.files.internal("CSSforExpBar/Aqua00ffff.png"));
        NinePatch emptyxp = new NinePatch(aqua, 0, 0, 0, 0);
        Image xpbar = new Image(emptyxp);
        /*
        Texture aquamarine = new Texture(Gdx.files.internal("CSSforExpBar/Aquamarine7fffd4.png"));
        NinePatch fullexperience = new NinePatch(aquamarine, 0, 0, 0, 0);
        Image fullxpbar = new Image(fullexperience);
        */
        Texture green = new Texture(Gdx.files.internal("CSSforExpBar/Green00933b.png"));
        NinePatch fullHp= new NinePatch(green, 0, 0, 0, 0);
        Image fullhp = new Image(fullHp);

        Texture yellow = new Texture(Gdx.files.internal("CSSforExpBar/Yellowf1c50c.png"));
        NinePatch midHp = new NinePatch(yellow, 0, 0, 0, 0);
        Image midhp = new Image(midHp);

        Texture red = new Texture(Gdx.files.internal("CSSforExpBar/Red00923f.png"));
        NinePatch lowHp = new NinePatch(red, 0, 0, 0, 0);
        Image lowhp = new Image(lowHp);

        Texture xpyellow = new Texture(Gdx.files.internal("CSSforExpBar/Xpyellowc8a72b.png"));
        NinePatch xpyellow2 = new NinePatch(xpyellow, 0, 0, 0, 0);
        Image fullxpbar = new Image(xpyellow2);

        Label hpLabel = new Label("HP", new Label.LabelStyle((new BitmapFont()), Color.WHITE));
        Label scoreLabel = new Label("Score: " + Integer.toString(score), new Label.LabelStyle((new BitmapFont()), Color.WHITE));

        //3 tables. Id like to have one but stack is not working like I think it should. Currently
        // it acts as 3 layers, black under, secondary color, main color, text? as a fourth if I want
        // ugly coded maybe

        Table table1 = new Table();
        table1.setFillParent(true); //make the table the size of parent which equals screensize code will wor on all screens

        table1.add(blackHpBar).size(204,24).left().top().expandX().expandY().pad(8); //black underline hp bar

        table1.row(); //new row

        table1.add(blackXpBar).size(404,14).bottom().pad(8); //Black border experience

        stage.addActor(table1); //add the bottom layer to stage

        Table table2 = new Table();
        table2.setFillParent(true);

        //den här ska switcha från grön till gull till röd och även minska i size beroende på hp, funktion att göra
        table2.add(fullhp).size(200,20).left().top().pad(10).expandX().expandY();

        table2.row();

        table2.add(xpbar).size(400,10).pad(10);

        stage.addActor(table2);

        /*
        Texture aqua = new Texture(Gdx.files.internal("CSSforExpBar/Aqua00ffff.png"));
        NinePatch emptyxp = new NinePatch(aqua, 0, 0, 0, 0);
        Image xpbar = new Image(emptyxp);

        xpbar.setSize(200,20);

        Texture aquamarine = new Texture(Gdx.files.internal("CSSforExpBar/Aquamarine7fffd4.png"));
        NinePatch fullexperience = new NinePatch(aquamarine, 0, 0, 0, 0);
        Image fullxpbar = new Image(fullexperience);

        fullxpbar.setSize(100,20);

        Stack stack = new Stack();
        stack.add(xpbar);
        stack.add(fullxpbar);

        table.add(stack).colspan(2).pad(10);

         */

        Table table3 = new Table();
        table3.setFillParent(true);

        table3.add(hpLabel).left().top().expandX().expandY().padLeft(12).padTop(10);
        table3.add(scoreLabel).right().top().pad(10);

        table3.row();

        table3.add(fullxpbar).size(300,10).bottom().pad(10).expandY().colspan(2);
        stage.addActor(table3);

}

    public Stage getStage() { return stage; }

    public void dispose(){
        stage.dispose();
    }
    }