package controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import model.*;

import java.util.Objects;

/**
 * A class responsible for updating the model in response to user input.
 */
public class Controller {
    private final IPlayerCharacter player;

    public Controller(IPlayerCharacter player) {
        this.player = Objects.requireNonNull(player);
    }

    public void update() {
        player.setInMotion(false);
        // User input
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) {
            player.move(Direction.LEFT);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) {
            player.move(Direction.RIGHT);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W)) {
            player.move(Direction.UP);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S)) {
            player.move(Direction.DOWN);
        }

        player.setSwinging(false);
        // sword attack

        //Left
        if (Gdx.input.isKeyJustPressed(Input.Keys.L)) {
            player.weaponAttack(315, 45);
            player.setSwinging(true);

        }
        //Up
        if (Gdx.input.isKeyJustPressed(Input.Keys.K)) {
            player.weaponAttack(225, 315);
            player.setSwinging(true);

        }
        //right
        if (Gdx.input.isKeyJustPressed(Input.Keys.J)) {
            player.weaponAttack(135, 225);
            player.setSwinging(true);
        }
        //Down
        if (Gdx.input.isKeyJustPressed(Input.Keys.I)) {
            player.weaponAttack(45, 135);
            player.setSwinging(true);
        }
    }
}

