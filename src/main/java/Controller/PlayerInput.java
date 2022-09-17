package Controller;

import Model.IControllable;
import de.gurkenlabs.litiengine.input.Input;

import java.awt.event.KeyEvent;

public final class PlayerInput {

    //There is only ever one character controlled by the player
    private static IControllable playerCharacter;

    private PlayerInput() {
    }


    public static void init(IControllable c) {
        setPlayerCharacter(c);
        Input.keyboard().onKeyPressed(KeyEvent.VK_ESCAPE, e -> System.exit(0));
        Input.keyboard().onKeyPressed(KeyEvent.VK_W, e -> playerCharacter.moveUp());
        Input.keyboard().onKeyPressed(KeyEvent.VK_S, e -> playerCharacter.moveDown());
        Input.keyboard().onKeyPressed(KeyEvent.VK_A, e -> playerCharacter.moveLeft());
        Input.keyboard().onKeyPressed(KeyEvent.VK_D, e -> playerCharacter.moveRight());
    }

    //Sets the entity to be currently controlled by the user
    public static void setPlayerCharacter(IControllable c) {
        playerCharacter = c;
    }

}
