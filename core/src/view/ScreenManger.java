package view;

import com.badlogic.gdx.Game;
import application.Application;
import controller.utility.Eventbus;
import controller.utility.IEventListener;
import controller.utility.ViewControllerEvent;


public class ScreenManger implements IEventListener<ViewControllerEvent> {
    private final AbstractScreen menuScreen;
    private final AbstractScreen gameScreen;


    private final Application game;

    public ScreenManger(Application game, AbstractScreen menuScreen, AbstractScreen gameScreen, Eventbus eventbus) {

        this.game = game;
        this.menuScreen = menuScreen;
        this.gameScreen = gameScreen;
        eventbus.listenFor(ViewControllerEvent.class, this);
        viewScreen(ScreenEnum.MENU);
    }

    private void viewScreen(ScreenEnum screenEnum) {
        AbstractScreen currentScreen = getScreen(screenEnum);
        if (currentScreen != null) {
            game.setScreen(currentScreen);
        }
    }

    private AbstractScreen getScreen(ScreenEnum screenEnum) {
        return switch (screenEnum) {
            case MENU -> menuScreen;
            case GAME -> gameScreen;

        };
    }

    @Override
    public void handle(ViewControllerEvent event) {
        switch (event.getEventType()) {
            case VIEW_MAIN_SCREEN -> viewScreen(ScreenEnum.MENU);
            case  VIEW_GAME_SCREEN -> viewScreen(ScreenEnum.GAME);
        }
    }
}


