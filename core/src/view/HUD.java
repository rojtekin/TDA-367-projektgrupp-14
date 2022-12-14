package view;

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
    private final Stage stage;
    private final FitViewport stageViewport;

    private final static int OUTLINE_SIZE = 4;

    private final static int HEALTH_BAR_WIDTH = 200;
    private final static int HEALTH_BAR_HEIGHT = 20;
    private int currentHealthBarWidth;

    private final static int EXPERIENCE_BAR_WIDTH = 400;
    private final static int EXPERIENCE_BAR_HEIGHT = 10;
    private int currentExperienceBarWidth;

    private Image healthColor;

    private final Texture red = new Texture(Gdx.files.internal("HudColors/Red00923f.png"));
    private final Texture yellow = new Texture(Gdx.files.internal("HudColors/Yellowf1c50c.png"));
    private final Texture green = new Texture(Gdx.files.internal("HudColors/Green00933b.png"));

    private final Texture black = new Texture(Gdx.files.internal("HudColors/Black.png"));
    private final NinePatch blackHealthNinePatch = new NinePatch(black, 0, 0, 0, 0);

    // Couldnt use the same image twice.
    private final Image blackHealthBar = new Image(blackHealthNinePatch);
    private final Image blackExperienceBar = new Image(blackHealthNinePatch);

    private final Texture aqua = new Texture(Gdx.files.internal("HudColors/Aqua00ffff.png"));
    private final NinePatch emptyExperienceBarNinePatch = new NinePatch(aqua, 0, 0, 0, 0);
    private final Image experienceBar = new Image(emptyExperienceBarNinePatch);

    private final Texture experienceYellow = new Texture(Gdx.files.internal("HudColors/Xpyellowc8a72b.png"));
    private final NinePatch experienceYellowNinePatch = new NinePatch(experienceYellow, 0, 0, 0, 0);
    private final Image fullExperienceBar = new Image(experienceYellowNinePatch);


    private final Table table1 = new Table();
    private final Table table2 = new Table();
    private final Table table3 = new Table();
    private final Table table4 = new Table();
    private static final Label.LabelStyle whiteTextColorAndFont = new Label.LabelStyle((new BitmapFont()), Color.WHITE);
    private final Table gamePausedTable = new Table();
    private final Table gameOverTable = new Table();

    private final Label healthBarLabel =  new Label("HP: ", whiteTextColorAndFont);
    private final Label scoreLabel = new Label("Score: ", whiteTextColorAndFont);
    private final Label perkLabel = new Label("Perk: ", whiteTextColorAndFont);
    private final Label experienceLabel = new Label("XP: ", whiteTextColorAndFont);
    private final Label statsLabel = new Label("Level: ", whiteTextColorAndFont);

    private final static String PAUSE_LABEL_TEXT = "THE GAME IS PAUSED";
    private final Label pauseLabel = new Label(PAUSE_LABEL_TEXT, new Label.LabelStyle((new BitmapFont()), Color.WHITE));
    private final static String PAUSE_INFO_LABEL_TEXT = "To Resume, Please Press ESC";
    private final Label pauseInfoLabel = new Label(PAUSE_INFO_LABEL_TEXT , new Label.LabelStyle((new BitmapFont()), Color.WHITE));
    private final static String HOW_TO_PLAY_LABEL_TEXT = "How to play:";
    private final Label howToPlayLabel = new Label(HOW_TO_PLAY_LABEL_TEXT , new Label.LabelStyle((new BitmapFont()), Color.WHITE));
    private final static String TO_MOVE_LABEL_TEXT = "To move: use arrows or W,A,S,D";
    private final Label toMoveLabel = new Label(TO_MOVE_LABEL_TEXT, new Label.LabelStyle((new BitmapFont()), Color.WHITE));
    private final static String TO_ATTACK_LABEL_TEXT = "To Attack: use I,J,K,L";
    private final Label toAttackLabel = new Label(TO_ATTACK_LABEL_TEXT , new Label.LabelStyle((new BitmapFont()), Color.WHITE));

    private final Label gameOverLabel = new Label("" , new Label.LabelStyle((new BitmapFont()), Color.WHITE));
    private final Label finalScore = new Label("", new Label.LabelStyle((new BitmapFont()), Color.WHITE));
    /**
     * Creates an instance of HUD
     * @param spriteBatch Libgdx class used for allowing things to be drawn or not.
     * @param model model is needed to health, score and experience.
     */
    public HUD(SpriteBatch spriteBatch, Model model) {
        stageViewport = new FitViewport(800, 480);
        stage = new Stage(stageViewport, spriteBatch);
        this.model = model;
    }

    /**
     * updates everything shown by the HUD and puts it in Stage.
     */
    public void update(boolean gamePaused) {
        //3 tables. Id like to have one but stack is not working like I think it should. Currently
        // it acts as 3 layers, black under, secondary color, main color, text? as a fourth if I want

        pickHealthBarColor();
        pickCurrentHealthBarWidth();
        pickCurrentExperienceBarWidth();

        table1.clear();
        table2.clear();
        table3.clear();
        table4.clear();
        gamePausedTable.clear();
        gameOverTable.clear();

        stage.clear(); //Prevents memory leak where new tables are continuously added to stage
        table1.setFillParent(true); //make the table the size of parent which equals screensize code will wor on all screens

        table1.add(blackHealthBar).size(HEALTH_BAR_WIDTH + OUTLINE_SIZE, HEALTH_BAR_HEIGHT + OUTLINE_SIZE).left().top().expandX().expandY().pad(8); //black underline hp bar

        table1.row(); //new row

        table1.add(blackExperienceBar).size(EXPERIENCE_BAR_WIDTH + OUTLINE_SIZE, EXPERIENCE_BAR_HEIGHT + OUTLINE_SIZE).bottom().pad(8); //Black border experience

        stage.addActor(table1); //add the bottom layer to stage

        table2.setFillParent(true);

        table2.add(getHealthColor()).size(currentHealthBarWidth, HEALTH_BAR_HEIGHT).left().top().pad(10).expandX().expandY();
        //hp size is between 0 and 1
        table2.row();

        table2.add(experienceBar).size(EXPERIENCE_BAR_WIDTH, EXPERIENCE_BAR_HEIGHT).pad(10);

        stage.addActor(table2);

        table3.setFillParent(true);
        healthBarLabel.setText("HP " + (int) model.getPlayer().getCurrentHealth() + "/" + (int) model.getPlayer().getMaxHealth());
        table3.add(healthBarLabel).left().top().expandX().expandY().padLeft(12).padTop(10);

        if (!model.getPlayer().getPerkList().isEmpty()) {
            perkLabel.setText("Perk: " + model.getPlayer().getPerkList().get(0));
            table3.add(perkLabel).top().padTop(10);
        }

        scoreLabel.setText("Score: " + model.getCurrentScore());
        table3.add(scoreLabel).right().top().pad(10);
        table3.row();


        table3.add(fullExperienceBar).size(currentExperienceBarWidth, EXPERIENCE_BAR_HEIGHT).bottom().pad(10).expandY().colspan(3);
        stage.addActor(table3);

        table4.setFillParent(true);

        statsLabel.setText("Level: " + model.getPlayer().getLevel()  +'\n' + "Damage: " +  model.getPlayer().getDamage() + '\n' + "Speed: " + model.getPlayer().getSpeed());
        table4.add(statsLabel).padLeft(10).padTop(40).left();

        table4.row();
        experienceLabel.setText("Experience: "+ model.getPlayer().getExperience() + " / " + model.getPlayer().getExperienceThreshold());
        table4.add(experienceLabel).expandX().expandY().bottom().padBottom(25);
        stage.addActor(table4);


        setShowPauseScreen(gamePaused);

        gamePausedTable.center();
        gamePausedTable.setFillParent(true);
        gamePausedTable.add(pauseLabel).padBottom(1).expandX();
        gamePausedTable.row();
        gamePausedTable.add(pauseInfoLabel).padBottom(70).expandX();
        gamePausedTable.row();
        gamePausedTable.add(howToPlayLabel).padTop(10).expandX();
        gamePausedTable.row();
        gamePausedTable.add(toMoveLabel).padBottom(10).expandX();
        gamePausedTable.row();
        gamePausedTable.add(toAttackLabel).padBottom(50).expandX();
        gamePausedTable.row();

        pauseLabel.setFontScale(5f);
        pauseInfoLabel.setFontScale(2f);
        howToPlayLabel.setFontScale(2f);
        toMoveLabel.setFontScale(1.7f);
        toAttackLabel.setFontScale(1.7f);

        stage.addActor(gamePausedTable);


        createGameOverTable();
        stage.addActor(gameOverTable);

    }

    private void setShowPauseScreen(boolean gamePaused) {
        if (!gamePaused) {
            pauseLabel.setText("");
            pauseInfoLabel.setText("");
            howToPlayLabel.setText("");
            toMoveLabel.setText("");
            toAttackLabel.setText("");
        }
        else{
            pauseLabel.setText(PAUSE_LABEL_TEXT);
            pauseInfoLabel.setText(PAUSE_INFO_LABEL_TEXT);
            howToPlayLabel.setText(HOW_TO_PLAY_LABEL_TEXT);
            toMoveLabel.setText(TO_MOVE_LABEL_TEXT);
            toAttackLabel.setText(TO_ATTACK_LABEL_TEXT);
        }
    }

    private Table createGameOverTable() {
        gameOverTable.center();
        gameOverTable.setFillParent(true);
        gameOverTable.add(gameOverLabel).padBottom(60).expandX();
        gameOverTable.row();
        gameOverTable.add(finalScore).padTop(30).expandX();
        gameOverTable.row();
        gameOverLabel.setFontScale(4f);
        finalScore.setFontScale(3f);
        return gameOverTable;
    }

    public void showGameOverTable() {
        finalScore.setText("Your score is: " + model.getCurrentScore());
        gameOverLabel.setText("Game Over");

    }

    /**
     * When called it checks the players hp and sets HealthColor to the new color depending on current hp
     */
    private void pickHealthBarColor(){
        float currentHealth = model.getPlayer().getCurrentHealth();
        float maxHealth = model.getPlayer().getMaxHealth();
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

    /**
     * When called sets the currentHealthBarWidth based on current hp
     */
    private void pickCurrentHealthBarWidth(){
        float currentHealth = model.getPlayer().getCurrentHealth();
        float maxHealth = model.getPlayer().getMaxHealth();
        float percentageHealthBarFilled = (currentHealth / maxHealth);
        setCurrentHealthBarWidth((int)(HEALTH_BAR_WIDTH *percentageHealthBarFilled));
    }

    /**
     * When called sets the currentExperienceBarWidth based on current experience
     */
    private void pickCurrentExperienceBarWidth(){
        float currentExperience = model.getPlayer().getExperience();
        float maxExperience = 100;
        float percentageExperienceBarFilled = (currentExperience / maxExperience);
        setCurrentExperienceBarWidth((int)(EXPERIENCE_BAR_WIDTH *percentageExperienceBarFilled));
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

    /**
     * disposes stage and colors automatically when screen is closed
     */
    public void dispose(){
        stage.dispose();
        red.dispose(); //alla textures m??ste disposas
        experienceYellow.dispose();
        yellow.dispose();
        green.dispose();
        red.dispose();
        aqua.dispose();
        black.dispose();
    }
}