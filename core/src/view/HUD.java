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
import model.Model;

public class HUD {
    private final Model model;
    private Stage stage;
    private FitViewport stageViewport;
    private IPlayerCharacter player;
    private double currentHealth;
    private double maxHealth;

    private int outlineSize = 4;

    private int healthBarWidth = 200;
    private int healthBarHeight = 20;
    private int currentHealthBarWidth;

    private int experienceBarWidth = 400;
    private int experienceBarHeight = 10;
    private int currentExperienceBarWidth;

    private Image healthColor;

    //The code under is a way to make a certain color since libgdx somehow doesnt know rgb values needed
    //for a square (and also make it an actor so I can send it back and not draw directly).

    private final Texture red = new Texture(Gdx.files.internal("HudColors/Red00923f.png"));
    private final Texture yellow = new Texture(Gdx.files.internal("HudColors/Yellowf1c50c.png"));
    private final Texture green = new Texture(Gdx.files.internal("HudColors/Green00933b.png"));

    private final Texture black = new Texture(Gdx.files.internal("HudColors/Black.png"));
    private final NinePatch blackHealth = new NinePatch(black, 0, 0, 0, 0);

    // Couldnt use the same image twice.
    private final Image blackHealthBar = new Image(blackHealth);
    private final Image blackExperienceBar = new Image(blackHealth);

    // lyfta ut konfigurationsdatan till en enum. Singleton. Turtorial java enum turtorial. Enums kan ha konstant state
    private final Texture aqua = new Texture(Gdx.files.internal("HudColors/Aqua00ffff.png"));
    private final NinePatch emptyExperienceBar = new NinePatch(aqua, 0, 0, 0, 0);
    private final Image experienceBar = new Image(emptyExperienceBar);

    private final Texture experienceYellow = new Texture(Gdx.files.internal("HudColors/Xpyellowc8a72b.png"));
    private final NinePatch experienceYellowNinePatch = new NinePatch(experienceYellow, 0, 0, 0, 0);
    private final Image fullExperienceBar = new Image(experienceYellowNinePatch);


    private final Table table1 = new Table();
    private final Table table2 = new Table();
    private final Table table3 = new Table();
    private final Label.LabelStyle whiteTextColorAndFont = new Label.LabelStyle((new BitmapFont()), Color.WHITE);
    private Label healthBarLabel =  new Label("HP", whiteTextColorAndFont);
    private Label scoreLabel = new Label("Score: ", whiteTextColorAndFont);

    /**
     * Creates an instance of HUD
     * @param spriteBatch Libgdx class used for allowing things to be drawn or not.
     * @param model model is needed to health, score and experience.
     */
    public HUD(SpriteBatch spriteBatch, Model model) {
        stageViewport = new FitViewport(800, 480);
        stage = new Stage(stageViewport, spriteBatch);
        this.model = model;
        this.player = model.getPlayer();
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

        table1.add(blackHealthBar).size(healthBarWidth+ outlineSize, healthBarHeight + outlineSize).left().top().expandX().expandY().pad(8); //black underline hp bar

        table1.row(); //new row

        table1.add(blackExperienceBar).size(experienceBarWidth +outlineSize, experienceBarHeight+outlineSize).bottom().pad(8); //Black border experience

        stage.addActor(table1); //add the bottom layer to stage

        table2.setFillParent(true);

        pickHealthBarColor();
        pickCurrentHealthBarWidth();
        table2.add(getHealthColor()).size(currentHealthBarWidth, healthBarHeight).left().top().pad(10).expandX().expandY();
        //hp size is between 0 and 1
        table2.row();

        table2.add(experienceBar).size(experienceBarWidth, experienceBarHeight).pad(10);

        stage.addActor(table2);

        table3.setFillParent(true);

        table3.add(healthBarLabel).left().top().expandX().expandY().padLeft(12).padTop(10);
        scoreLabel.setText("Score: "+model.getCurrentScore());
        table3.add(scoreLabel).right().top().pad(10);
        table3.row();

        pickCurrentExperienceBarWidth();
        table3.add(fullExperienceBar).size(currentExperienceBarWidth, experienceBarHeight).bottom().pad(10).expandY().colspan(2);
        stage.addActor(table3);
        healthBarLabel.setText("HP " + (int)player.getCurrentHealth() + "/" + (int)player.getMaxHealth());
    }

    private void pickHealthBarColor(){
        currentHealth = player.getCurrentHealth();
        maxHealth = player.getMaxHealth();
        if(currentHealth <= maxHealth * 0.33) {
            NinePatch lowHp = new NinePatch(red, 0, 0, 0, 0);
            Image lowhp = new Image(lowHp);
            setHealthColor(lowhp);
        }
        else if(currentHealth <= maxHealth * 0.66) {
            NinePatch midHp = new NinePatch(yellow, 0, 0, 0, 0);
            Image midhp = new Image(midHp);
            setHealthColor(midhp);
        }
        else if(currentHealth <= maxHealth * 1) {
            NinePatch fullHp = new NinePatch(green, 0, 0, 0, 0);
            Image fullhp = new Image(fullHp);
            setHealthColor(fullhp);
        }
    }

    private void pickCurrentHealthBarWidth(){
        currentHealth = player.getCurrentHealth();
        maxHealth = player.getMaxHealth();
        float percentageHealthBarFilled = (float)(currentHealth / maxHealth);
        setCurrentHealthBarWidth((int)(healthBarWidth*percentageHealthBarFilled));
    }

    private void pickCurrentExperienceBarWidth(){
        float currentExperience = player.getExperience();
        float maxExperience = 100;
        float percentageExperienceBarFilled = (currentExperience / maxExperience);
        setCurrentExperienceBarWidth((int)(experienceBarWidth*percentageExperienceBarFilled));
    }

    private void setCurrentExperienceBarWidth(int barSize){
        this.currentExperienceBarWidth = barSize;
    }

    private void setHealthColor(Image healthColor) {
        this.healthColor = healthColor;
    }
    private Image getHealthColor() {
        return healthColor;
    }

    private void setCurrentHealthBarWidth(int barSize) {
        this.currentHealthBarWidth = barSize;
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