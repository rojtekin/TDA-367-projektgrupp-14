package controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import model.*;

import java.util.Objects;

/**
 * A class responsible for updating the model in response to user input.
 */
public class Controller {
    private Model model;
    private IPlayerCharacter player;

    public Controller(Model model) {
        this.model = Objects.requireNonNull(model);
        this.player = Objects.requireNonNull(model.getPlayer());
    }

    public void update(IPlayerCharacter player) {
        model.getPlayer().setMoving(false);
        // User input
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) {
            player.move(Direction.LEFT);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) {
            player.move(Direction.RIGHT);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W)) {
            player.move(Direction.UP);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S)) {
            player.move(Direction.DOWN);
        }

        model.getPlayer().setSwinging(false);
        // sword attack

        //Left
        if(Gdx.input.isKeyJustPressed(Input.Keys.L)){
            player.weaponAttack(315,45,0);
            model.getPlayer().setSwinging(true);

        }
        //Up
        if(Gdx.input.isKeyJustPressed(Input.Keys.K)){
            player.weaponAttack(225,315,0);
            model.getPlayer().setSwinging(true);

        }
        //right
        if(Gdx.input.isKeyJustPressed(Input.Keys.J)){
            player.weaponAttack(135,225,0);
            model.getPlayer().setSwinging(true);
        }
        //Down
        if(Gdx.input.isKeyJustPressed(Input.Keys.I)){
            player.weaponAttack(45,135,0);
            model.getPlayer().setSwinging(true);
        }
    }
}
