package controller;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import controller.utility.Eventbus;
import controller.utility.ViewControllerEvent;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import view.View;

public class MenuScreenController extends InputAdapter {
    private final Eventbus viewEventBus;
    View view;

    public MenuScreenController(Eventbus viewEventBus) {
        this.viewEventBus = viewEventBus;
    }


    public void addPlayButtonClickListener(Button button) {
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                viewEventBus.emit(new ViewControllerEvent(ViewControllerEvent.EventType.VIEW_GAME_SCREEN));
                System.out.println("clicked");

            }
        });
    }

    public void addSettingsButtonClickListener(Button button) {
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

            }
        });
    }




}