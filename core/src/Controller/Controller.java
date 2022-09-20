package Controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import Model.*;

public class Controller {
    private Model model;

    public Controller(Model model) {
        this.model = model;
    }

    public void update() {
        // User input
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) {
            PlayerCharacter.instance().moveLeft();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) {
            PlayerCharacter.instance().moveRight();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W)) {
            PlayerCharacter.instance().moveUp();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S)) {
            PlayerCharacter.instance().moveDown();
        }
    }
}
