package controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import model.*;

public class Controller {
    private Model model;
    private IControllable player;

    public Controller(Model model) {
        this.model = model;
        this.player = model.getPlayer();
    }


    public void update() {
        model.setPlayerMoving(false);
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

        // sword attack
        //Left
        if(Gdx.input.isKeyPressed(Input.Keys.L)){
            player.weaponAttack(315,45,0);
        }
        //Up
        if(Gdx.input.isKeyPressed(Input.Keys.I)){
            player.weaponAttack(45,135,0);
        }
        //right
        if(Gdx.input.isKeyPressed(Input.Keys.J)){
            player.weaponAttack(135,225,0);
        }
        //Down
        if(Gdx.input.isKeyPressed(Input.Keys.K)){
            player.weaponAttack(225,315,0);
        }
    }
}
