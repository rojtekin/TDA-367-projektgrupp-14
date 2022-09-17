package Controller;

import Model.PlayerCharacter;
import de.gurkenlabs.litiengine.input.Input;

import java.awt.event.KeyEvent;

public final class PlayerInput {

    private PlayerInput() {
    }

    public static void init() {
        Input.keyboard().onKeyPressed(KeyEvent.VK_ESCAPE, e -> System.exit(0));
        Input.keyboard().onKeyPressed(KeyEvent.VK_W, e -> PlayerCharacter.instance().moveUp());
        Input.keyboard().onKeyPressed(KeyEvent.VK_S, e -> PlayerCharacter.instance().moveDown());
        Input.keyboard().onKeyPressed(KeyEvent.VK_A, e -> PlayerCharacter.instance().moveLeft());
        Input.keyboard().onKeyPressed(KeyEvent.VK_D, e -> PlayerCharacter.instance().moveRight());
    }
}
