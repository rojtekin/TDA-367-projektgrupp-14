package model;

import com.dongbat.jbump.Collision;
import com.dongbat.jbump.Collisions;
import com.dongbat.jbump.World;
import com.badlogic.gdx.maps.Map;
import model.monsters.*;
import model.rewards.Reward;
import model.rewards.RewardSystem;
import java.util.ArrayList;
import java.util.List;

public class Model implements MovementListener {
    private IPlayerCharacter player;
    private List<Monster> monsters = new ArrayList<>();
    private List<Entity> entityList = new ArrayList<>();
    private IEnvironmentCache mapLoader;
    private RewardSystem rewardSystem = new RewardSystem();
    private World<IEntity> world = new World<>();

    public IPlayerCharacter getPlayer(){
        return player;
    }

    public void update() {
        moveEnemies();
        if (rewardSystem.levelUpChecker(player)){
        player = rewardSystem.applyReward(player, Reward.SPEED_DEVIL);
        }
        despawnDeadNPCs();
    }

    /**
     * Goes through the list of enemies checks if they need to be removed
     * O(n)
     */
    public void despawnDeadNPCs() {
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

    public void addEnemy(Monster monster) {
        monsters.add(monster);
        monster.addMovementListener(this);
    }

    public List<Monster> getEnemyList() {
        return new ArrayList<>(monsters);
    }

    void moveEnemies() {
        for (Monster monster : monsters) {
            monster.moveTowardPlayer(player.getX(), player.getY());
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

    public World<IEntity> getWorld() {
        return mapLoader.getWorld();
    }

    /**
     * Loads a specified map and creates a playercharacter
     * @param mapLoader object that loads a map of a specific type
     */
    public void initialize(IEnvironmentCache mapLoader) {
        rewardSystem.initialize(world);
        this.mapLoader = mapLoader;
        player = new PlayerCharacter(mapLoader.getMapUnitWidth() / 2, mapLoader.getMapUnitHeight() / 2, mapLoader.getWorld());
        player.gainExperience(100);
    }

    /**
     * Removes a monster from the game and removes
     * its collisionbox from the world
     * @param monster Enemy to be removed
     */
    public void despawn(Monster monster) {
        monsters.remove(monster);
        monster.removeCollision();
    }
}
