package Controller;

import de.gurkenlabs.litiengine.input.Input;

import java.awt.event.KeyEvent;

public final class PlayerInput {

    private PlayerInput() {
    }

    public static void init() {
        Input.keyboard().onKeyPressed(KeyEvent.VK_ESCAPE, e -> System.exit(0));
    }
}
