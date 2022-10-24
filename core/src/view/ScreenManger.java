package view;

import com.badlogic.gdx.Game;

/**
 * Decides which screen will be used.
 */
public class ScreenManger  {
    private final BaseScreen menuScreen;
    private final BaseScreen gameScreen;

    private final BaseScreen settingScreen;

    private final BaseScreen gameOverScreen;

    private final Game game;

    /**
     * Initializes ScreenManager
     * @param menuScreen menu screen
     * @param gameScreen ingame screen
     * @param settingScreen settings screen
     * @param gameOverScreen game over screen
     * @param game The application that runs the playble loop of the game
     */
    public ScreenManger(BaseScreen menuScreen, BaseScreen gameScreen, BaseScreen settingScreen, BaseScreen gameOverScreen, Game game) {
        this.menuScreen = menuScreen;
        this.gameScreen = gameScreen;
        this.settingScreen = settingScreen;
        this.gameOverScreen = gameOverScreen;
        this.game = game;
        viewScreen(ScreenEnum.MENU);
    }

    private void viewScreen(ScreenEnum screenEnum) {
        BaseScreen currentScreen = getScreen(screenEnum);
        if (currentScreen != null) {
            currentScreen.setBackgroundImage();
            game.setScreen(currentScreen);
        }
    }

    private BaseScreen getScreen(ScreenEnum screenEnum) {
        return switch (screenEnum) {
            case MENU -> menuScreen;
            case GAME -> gameScreen;
            case SETTINGS -> settingScreen;
            case GAME_OVER -> gameOverScreen;
        };
    }

}

