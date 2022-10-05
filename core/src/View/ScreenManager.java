package View;

import Utility.IEventListener;
import Utility.Eventbus;
import Utility.ViewControllerEvent;
import com.badlogic.gdx.Game;


public class ScreenManager implements IEventListener<ViewControllerEvent> {
    private final BaseScreen menuScreen;
    private final BaseScreen gameScreen;

    private final BaseScreen settingScreen;

    private final BaseScreen gameOverScreen;


    private final Game game;

    public ScreenManager(Game game, BaseScreen menuScreen, BaseScreen gameScreen, BaseScreen settingScreen, BaseScreen gameOverScreen, Eventbus eventbus) {
        this.game = game;
        this.menuScreen = menuScreen;
        this.gameScreen = gameScreen;
        this.settingScreen = settingScreen;
        this.gameOverScreen = gameOverScreen;
        eventbus.listenFor(ViewControllerEvent.class, this);
        viewScreen(ScreenEnum.MAIN_MENU);
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
            case MAIN_MENU -> menuScreen;
            case GAME -> gameScreen;
            case SETTINGS -> settingScreen;
            case GAME_OVER -> gameOverScreen;
        };
    }

    @Override
    public void handle(ViewControllerEvent event) {
        switch (event.getEventType()) {
            case VIEW_MAIN_SCREEN -> viewScreen(ScreenEnum.MAIN_MENU);
            case VIEW_GAME_SCREEN -> viewScreen(ScreenEnum.GAME);
        }
    }
}

