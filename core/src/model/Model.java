package model;

import com.dongbat.jbump.Collision;
import com.dongbat.jbump.Collisions;
import com.dongbat.jbump.World;
import com.badlogic.gdx.maps.Map;
import model.enemies.*;
import model.rewards.Reward;
import model.rewards.RewardSystem;

import java.util.ArrayList;
import java.util.List;

public class Model implements MovementListener {
    private IMapLoader mapLoader;
    private IPlayerCharacter player;
    private List<Enemy> enemyList = new ArrayList<>();
    private List<Entity> entityList = new ArrayList<>();
    private RewardSystem rewardSystem = new RewardSystem();
    private World<IEntity> world = new World<>();

    public IPlayerCharacter getPlayer(){
        return player;
    }

    public void update() {
        moveEnemies();
        if (rewardSystem.levelUpChecker(player)){
        player = rewardSystem.applyReward(player, Reward.SPEED_DEVIL);
        System.out.println(player.getSpeed());
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
        return new ArrayList<Entity>(entityList);
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

    public World<IEntity> getWorld() {
        return mapLoader.getWorld();
    }

    /**
     * Loads a specified map and creates a playercharacter
     * @param mapLoader object that loads a map of a specific type
     */
    public void initialize(IMapLoader mapLoader) {
        rewardSystem.initialize(world);
        this.mapLoader = mapLoader;
        player = new PlayerCharacter(mapLoader.getMapUnitWidth() / 2, mapLoader.getMapUnitHeight() / 2, mapLoader.getWorld());
        player.gainExperience(100);
    }
}
