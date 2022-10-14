package view;

import model.PlayerCharacter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class HUD {
    private Stage stage;
    private FitViewport stageViewport;
    private int score = 10; //temporary, it should take it from model
    private PlayerCharacter player;
    private double currenthp;
    private double maxhp;

    private int hpsize;

    private Image HpColor;

    //The code under is a way to make a certain color since libgdx somehow doesnt know rgb values needed
    //for a square (and also make it an actor so I can send it back and not draw directly).

    private Texture red = new Texture(Gdx.files.internal("HudColors/Red00923f.png"));
    private Texture yellow = new Texture(Gdx.files.internal("HudColors/Yellowf1c50c.png"));
    private Texture green = new Texture(Gdx.files.internal("HudColors/Green00933b.png"));

    private Texture black = new Texture(Gdx.files.internal("HudColors/Black.png"));
    private NinePatch blackHp = new NinePatch(black, 0, 0, 0, 0);
    private Image blackHpBar = new Image(blackHp);
    private Image blackXpBar = new Image(blackHp);

    // lyfta ut konfigurationsdatan till en enum. Singleton. Turtorial java enum turtorial. Enums kan ha konstant state
    private Texture aqua = new Texture(Gdx.files.internal("HudColors/Aqua00ffff.png"));
    private NinePatch emptyxp = new NinePatch(aqua, 0, 0, 0, 0);
    private Image xpbar = new Image(emptyxp);

    private Texture xpyellow = new Texture(Gdx.files.internal("HudColors/Xpyellowc8a72b.png"));
    private NinePatch xpyellow2 = new NinePatch(xpyellow, 0, 0, 0, 0);
    private Image fullxpbar = new Image(xpyellow2);

    private Label hpLabel;
    private Label scoreLabel = new Label("Score: " + score, new Label.LabelStyle((new BitmapFont()), Color.WHITE));


    public HUD(SpriteBatch spriteBatch,PlayerCharacter player) {
        stageViewport = new FitViewport(800, 480);
        stage = new Stage(stageViewport, spriteBatch);
        this.player = player;
    }

    public void update() {
        //3 tables. Id like to have one but stack is not working like I think it should. Currently
        // it acts as 3 layers, black under, secondary color, main color, text? as a fourth if I want
        // ugly coded maybe

        stage.clear(); //Prevents memory leak where new tables are continuously added to stage
        Table table1 = new Table();
        table1.setFillParent(true); //make the table the size of parent which equals screensize code will wor on all screens

        table1.add(blackHpBar).size(204, 24).left().top().expandX().expandY().pad(8); //black underline hp bar

        table1.row(); //new row

        table1.add(blackXpBar).size(404, 14).bottom().pad(8); //Black border experience

        stage.addActor(table1); //add the bottom layer to stage

        Table table2 = new Table();
        table2.setFillParent(true);

        //den här ska switcha från grön till gull till röd och även minska i size beroende på hp, funktion att göra
        //table2.add(fullhp).size(200,20).left().top().pad(10).expandX().expandY();
        pickhpcolor();
        pickhpsize();
        table2.add(getHpColor()).size(hpsize, 20).left().top().pad(10).expandX().expandY();
        //hp size is between 0 and 1
        table2.row();

        table2.add(xpbar).size(400, 10).pad(10);

        stage.addActor(table2);

        Table table3 = new Table();
        table3.setFillParent(true);

        table3.add(hpLabel).left().top().expandX().expandY().padLeft(12).padTop(10);
        table3.add(scoreLabel).right().top().pad(10);

        table3.row();

        table3.add(fullxpbar).size(300, 10).bottom().pad(10).expandY().colspan(2);
        stage.addActor(table3);
        hpLabel = new Label("HP " + (int)player.getCurrentHealth() + "/" + (int)player.getMaxHealth(), new Label.LabelStyle((new BitmapFont()), Color.WHITE));
    }

    private void pickhpcolor(){
        currenthp = player.getCurrentHealth();
        maxhp = player.getMaxHealth();
        if(currenthp <= maxhp * 0.33) {
            NinePatch lowHp = new NinePatch(red, 0, 0, 0, 0);
            Image lowhp = new Image(lowHp);
            setHpColor(lowhp);
        }
        if(currenthp <= maxhp * 0.66) {
            NinePatch midHp = new NinePatch(yellow, 0, 0, 0, 0);
            Image midhp = new Image(midHp);
            setHpColor(midhp);
        }
        if(currenthp <= maxhp * 1) {
            NinePatch fullHp = new NinePatch(green, 0, 0, 0, 0);
            Image fullhp = new Image(fullHp);
            setHpColor(fullhp);
        }
    }

    private void pickhpsize(){
        currenthp = player.getCurrentHealth();
        maxhp = player.getMaxHealth();
        float hpsize = (float)(currenthp/maxhp);
        setHpSize((int)(200*hpsize));
    }

    private void setHpColor(Image hpColor) {
        HpColor = hpColor;
    }
    private Image getHpColor() {
        return HpColor;
    }

    private void setHpSize(int hpsize) {
        this.hpsize = hpsize;
    }

    public Stage getStage() { return stage; }

    public void dispose(){
        stage.dispose();
        red.dispose(); //alla textures måste disposas
        xpyellow.dispose();
        yellow.dispose();
        green.dispose();
        red.dispose();
        aqua.dispose();
        black.dispose();
    }
    }