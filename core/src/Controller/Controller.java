package Controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import Model.*;

public class Controller {
    private IModel model;
    private IControllable player;

    public Controller(IModel model, IControllable player) {
        this.model = model;
        this.player = player;
    }

    public void update() {
        // User input
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) {
            player.moveLeft();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) {
            player.moveRight();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W)) {
            player.moveUp();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S)) {
            player.moveDown();
        }
    }
}
