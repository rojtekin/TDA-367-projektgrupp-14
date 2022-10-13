package controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import model.*;

import java.util.Objects;

public class Controller {
    private Model model;
    private IPlayerCharacter player;

    public Controller(Model model) {
        this.model = Objects.requireNonNull(model);
       // this.player = Objects.requireNonNull(model.getPlayer());
    }

    public void update(IPlayerCharacter player) {
        model.getPlayer().setMoving(false);
        // User input
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) {
            player.move(Direction.LEFT, player.getSpeed());
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) {
            player.move(Direction.RIGHT, player.getSpeed());
        }
        if(Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W)) {
            player.move(Direction.UP, player.getSpeed());
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S)) {
            player.move(Direction.DOWN, player.getSpeed());
        }
    }
}
