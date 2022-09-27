package Model;

import Model.Enemies.Mouse;

import java.util.ArrayList;

public class Model implements IModel {
    private static final int SCREEN_WIDTH = 800;
    private static final int SCREEN_HEIGHT = 480;
    private ArrayList<Enemy> enemyList;

    public void initialize() {
        PlayerCharacter.instance();
    }
    public ArrayList<Enemy> getEnemies(){
        return this.enemyList;
    }

    public Direction getPlayerDirection() {
        return PlayerCharacter.instance().getDirection();
    }

    public boolean playerIsMoving() {
        return PlayerCharacter.instance().isMoving();
    }

    public void setPlayerMoving(boolean moving) {
        PlayerCharacter.instance().setMoving(moving);
    }
}
