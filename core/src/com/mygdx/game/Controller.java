package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class Controller {
    private Model model;

    public Controller(Model model) {
        this.model = model;
    }

    public void update() {
        // User input
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            model.movePlayerLeft();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            model.movePlayerRight();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
            model.movePlayerUp();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            model.movePlayerDown();
        }
    }
}
