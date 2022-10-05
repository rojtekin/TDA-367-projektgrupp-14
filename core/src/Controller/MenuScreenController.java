package Controller;

import Utility.Eventbus;
import Utility.ViewControllerEvent;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class MenuScreenController extends InputAdapter {
    private final Eventbus viewEventBus;

    public MenuScreenController(Eventbus viewEventBus) {
        this.viewEventBus = viewEventBus;
    }


    public void addPlayButtonClickListener(Button button) {
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                viewEventBus.emit(new ViewControllerEvent(ViewControllerEvent.EventType.VIEW_GAME_SCREEN));

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
