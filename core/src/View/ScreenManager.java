package View;

import Utility.IEventListener;
import Utility.Eventbus;
import Utility.ViewControllerEvent;
import com.badlogic.gdx.Game;


public class ScreenManager implements IEventListener<ViewControllerEvent> {
    private final BaseScreen menuScreen;
    private final BaseScreen gameScreen;



    private final Game game;

    public ScreenManager(Game game, BaseScreen menuScreen, BaseScreen gameScreen, Eventbus eventbus) {
        this.game = game;
        this.menuScreen = menuScreen;
        this.gameScreen = gameScreen;
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

