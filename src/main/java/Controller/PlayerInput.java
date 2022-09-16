package Controller;


import Model.PlayerCharacter;
import de.gurkenlabs.litiengine.input.Input;
import de.gurkenlabs.litiengine.input.KeyboardEntityController;
import de.gurkenlabs.litiengine.physics.IMovementController;

import java.awt.event.KeyEvent;

public final class PlayerInput {

    private PlayerInput() {
    }

    public static void init() {
        // make the game exit upon pressing ESCAPE (by default there is no such key binding and the window needs to be shutdown otherwise, e.g. ALT-F4 on Windows)
        Input.keyboard().onKeyPressed(KeyEvent.VK_ESCAPE, e -> System.exit(0));
        Input.keyboard().onKeyPressed(KeyEvent.VK_UP, e -> PlayerCharacter.instance().moveUp());
        Input.keyboard().onKeyPressed(KeyEvent.VK_DOWN, e -> PlayerCharacter.instance().moveDown());
        Input.keyboard().onKeyPressed(KeyEvent.VK_LEFT, e -> PlayerCharacter.instance().moveLeft());
        Input.keyboard().onKeyPressed(KeyEvent.VK_RIGHT, e -> PlayerCharacter.instance().moveRight());


    }
}
