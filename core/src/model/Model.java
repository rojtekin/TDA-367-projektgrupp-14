package model;

import com.dongbat.jbump.Collision;
import com.dongbat.jbump.Collisions;
import com.dongbat.jbump.World;
import com.badlogic.gdx.maps.Map;
import model.enemies.*;
import java.util.ArrayList;
import java.util.List;

public class Model implements MovementListener {
    private PlayerCharacter player;
    private List<Enemy> enemyList = new ArrayList<>();
    private IArenaLoader mapLoader;
    private List<Entity> entityList = new ArrayList<>();

    public PlayerCharacter getPlayerCharacter() {
        return player;
    }

    public PlayerCharacter getPlayer(){
        return player;
    }

    public void update() {
        moveEnemies();
        checkEnemyHealth();
    }

    /**
     * Goes through the list of enemies checks if they need to be removed
     * O(n)
     */
    public void checkEnemyHealth() {
        for (int i = 0; i < getEnemyList().size(); i++) {
            if (getEnemyList().get(i).getCurrentHealth() <= 0) {
                despawn(getEnemyList().get(i));
            }
        }
    }

    public Map getMap() {
        return mapLoader.getMap();
    }

    public Direction getPlayerDirection() {
        return player.getDirection();
    }

    public boolean playerIsMoving() {
        return player.isMoving();
    }

    public void setPlayerMoving(boolean moving) {
        player.setMoving(moving);
    }

    public ArrayList<Entity> getEntities(){
        return new ArrayList<>(entityList);
    }

    public void addEnemy(Enemy enemy) {
        enemyList.add(enemy);
        enemy.addMovementListener(this);
    }

    public List<Enemy> getEnemyList() {
        return new ArrayList<>(enemyList);
    }

    void moveEnemies() {
        for (Enemy enemy : enemyList) {
            enemy.moveTowardPlayer(player.getX(), player.getY());
        }
    }

    @Override
    public void onMovement(Collisions collisions) {
        for (int i = 0; i < collisions.size(); i++) {
            Collision collision = collisions.get(i);
            if (collisionWithPlayer(collision)) {
                player.pushBack(collision.normal);
            }
        }
    }

    private boolean collisionWithPlayer(Collision collision) { return collision.other.userData.equals(player); }

    public World<Entity> getWorld() {
        return mapLoader.getWorld();
    }

    /**
     * Loads a specified map and creates a playercharacter
     * @param mapLoader object that loads a map of a specific type
     */
    public void initialize(IArenaLoader mapLoader) {
        this.mapLoader = mapLoader;
        player = new PlayerCharacter(mapLoader.getMapUnitWidth() / 2, mapLoader.getMapUnitHeight() / 2, mapLoader.getWorld());
        entityList.add(player);
        //Mouse mouse1 = new Mouse(50,50,16,16,2,1,1, mapLoader.getWorld()); //temporary
        //entityList.add(mouse1);
    }

    /**
     * Removes an Enemy from the game and removes
     * its collisionbox from the world
     * @param enemy Enemy to be removed
     */
    public void despawn(Enemy enemy) {
        enemyList.remove(enemy);
        enemy.removeCollision();
    }
}
