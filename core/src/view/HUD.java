package view;

import model.IPlayerCharacter;
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
    private IPlayerCharacter player;
    private double currentHealth;
    private double maxHealth;

    private int healthBarSize;

    private Image healthColor;

    //The code under is a way to make a certain color since libgdx somehow doesnt know rgb values needed
    //for a square (and also make it an actor so I can send it back and not draw directly).

    private final Texture red = new Texture(Gdx.files.internal("HudColors/Red00923f.png"));
    private final Texture yellow = new Texture(Gdx.files.internal("HudColors/Yellowf1c50c.png"));
    private final Texture green = new Texture(Gdx.files.internal("HudColors/Green00933b.png"));

    private final Texture black = new Texture(Gdx.files.internal("HudColors/Black.png"));
    private final NinePatch blackHealth = new NinePatch(black, 0, 0, 0, 0);
    private final Image blackHealthBar = new Image(blackHealth);
    private final Image blackExperienceBar = new Image(blackHealth);

    // lyfta ut konfigurationsdatan till en enum. Singleton. Turtorial java enum turtorial. Enums kan ha konstant state
    private final Texture aqua = new Texture(Gdx.files.internal("HudColors/Aqua00ffff.png"));
    private final NinePatch emptyExperienceBar = new NinePatch(aqua, 0, 0, 0, 0);
    private final Image experienceBar = new Image(emptyExperienceBar);

    private final Texture experienceYellow = new Texture(Gdx.files.internal("HudColors/Xpyellowc8a72b.png"));
    private final NinePatch experienceYellowNinePatch = new NinePatch(experienceYellow, 0, 0, 0, 0);
    private final Image fullExperienceBar = new Image(experienceYellowNinePatch);

    private Label healthBarLabel;

    private final Table table1 = new Table();
    private final Table table2 = new Table();
    private final Table table3 = new Table();
    private final Label.LabelStyle whiteTextColorAndFont = new Label.LabelStyle((new BitmapFont()), Color.WHITE);
    private Label scoreLabel = new Label("Score: " + score, whiteTextColorAndFont);

    public HUD(SpriteBatch spriteBatch, IPlayerCharacter player) {
        stageViewport = new FitViewport(800, 480);
        stage = new Stage(stageViewport, spriteBatch);
        this.player = player;
    }

    public void update() {
        //3 tables. Id like to have one but stack is not working like I think it should. Currently
        // it acts as 3 layers, black under, secondary color, main color, text? as a fourth if I want
        // ugly coded maybe
        table1.clear();
        table2.clear();
        table3.clear();

        stage.clear(); //Prevents memory leak where new tables are continuously added to stage
        table1.setFillParent(true); //make the table the size of parent which equals screensize code will wor on all screens

        table1.add(blackHealthBar).size(204, 24).left().top().expandX().expandY().pad(8); //black underline hp bar

        table1.row(); //new row

        table1.add(blackExperienceBar).size(404, 14).bottom().pad(8); //Black border experience

        stage.addActor(table1); //add the bottom layer to stage

        table2.setFillParent(true);

        //den här ska switcha från grön till gull till röd och även minska i size beroende på hp, funktion att göra
        //table2.add(fullhp).size(200,20).left().top().pad(10).expandX().expandY();
        pickHealthBarColor();
        pickHealthBarSize();
        table2.add(getHealthColor()).size(healthBarSize, 20).left().top().pad(10).expandX().expandY();
        //hp size is between 0 and 1
        table2.row();

        table2.add(experienceBar).size(400, 10).pad(10);

        stage.addActor(table2);

        table3.setFillParent(true);

        table3.add(healthBarLabel).left().top().expandX().expandY().padLeft(12).padTop(10);
        table3.add(scoreLabel).right().top().pad(10);

        table3.row();

        table3.add(fullExperienceBar).size(300, 10).bottom().pad(10).expandY().colspan(2);
        stage.addActor(table3);
        healthBarLabel = new Label("HP " + (int)player.getCurrentHealth() + "/" + (int)player.getMaxHealth(), whiteTextColorAndFont);
    }

    private void pickHealthBarColor(){
        currentHealth = player.getCurrentHealth();
        maxHealth = player.getMaxHealth();
        if(currentHealth <= maxHealth * 0.33) {
            NinePatch lowHp = new NinePatch(red, 0, 0, 0, 0);
            Image lowhp = new Image(lowHp);
            setHealthColor(lowhp);
        }
        if(currentHealth <= maxHealth * 0.66) {
            NinePatch midHp = new NinePatch(yellow, 0, 0, 0, 0);
            Image midhp = new Image(midHp);
            setHealthColor(midhp);
        }
        if(currentHealth <= maxHealth * 1) {
            NinePatch fullHp = new NinePatch(green, 0, 0, 0, 0);
            Image fullhp = new Image(fullHp);
            setHealthColor(fullhp);
        }
    }

    private void pickHealthBarSize(){
        currentHealth = player.getCurrentHealth();
        maxHealth = player.getMaxHealth();
        float hpsize = (float)(currentHealth / maxHealth);
        setHpSize((int)(200*hpsize));
    }

    private void setHealthColor(Image healthColor) {
        this.healthColor = healthColor;
    }
    private Image getHealthColor() {
        return healthColor;
    }

    private void setHpSize(int hpsize) {
        this.healthBarSize = hpsize;
    }

    public Stage getStage() { return stage; }

    public void dispose(){
        stage.dispose();
        red.dispose(); //alla textures måste disposas
        experienceYellow.dispose();
        yellow.dispose();
        green.dispose();
        red.dispose();
        aqua.dispose();
        black.dispose();
    }
}