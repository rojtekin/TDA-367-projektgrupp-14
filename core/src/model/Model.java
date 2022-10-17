package model;

import com.dongbat.jbump.Collision;
import com.dongbat.jbump.Collisions;
import com.dongbat.jbump.World;
import com.badlogic.gdx.maps.Map;
import model.enemies.*;

import java.awt.*;
import model.rewards.Reward;
import model.rewards.RewardSystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Model implements MovementListener {
    private IMapLoader mapLoader;
    private IPlayerCharacter player;
    private List<Enemy> enemyList = new ArrayList<>();
    private List<Entity> entityList = new ArrayList<>();
    private final List<Point> spawnPoints;
    private static final int MAX_ENEMIES = 8;
    private int spawnPointsIndex = 0;
    private RewardSystem rewardSystem = new RewardSystem();
    private World<IEntity> world = new World<>();

    public Model(IMapLoader mapLoader, PlayerCharacter player, List<Point> spawnPoints) {
        this.mapLoader = Objects.requireNonNull(mapLoader);
        this.player = Objects.requireNonNull(player);
        this.spawnPoints = Objects.requireNonNull(spawnPoints);
    }

    public PlayerCharacter getPlayer(){
    public IPlayerCharacter getPlayer(){
        return player;
    }

    public void update() {
        spawnEnemies();
        moveEnemies();
        if (rewardSystem.levelUpChecker(player)){
        player = rewardSystem.applyReward(player, Reward.SPEED_DEVIL);
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

    private void addEnemy(Enemy enemy) {
        enemyList.add(enemy);
        entityList.add(enemy);
        enemy.addMovementListener(this);
    }

    public List<Enemy> getEnemyList() {
        return new ArrayList<>(enemyList);
    }

    private void moveEnemies() {
        for (Enemy enemy : enemyList) {
            enemy.moveTowardPlayer(player.getX(), player.getY());
        }
    }

    /**
     * Adds enemies at different spawn points until the maximum number of enemies is reached.
     */
    private void spawnEnemies() {
        while (enemyList.size() < MAX_ENEMIES) {
            spawnRandomEnemy(spawnPoints.get(spawnPointsIndex));
            if (spawnPointsIndex < spawnPoints.size() - 1) { spawnPointsIndex++; }
            else { spawnPointsIndex = 0; }
        }
    }

    /**
     * Adds a random enemy at the specified spawn point.
     * @param spawnPoint the spawn point where a random enemy should spawn
     */
    private void spawnRandomEnemy(Point spawnPoint) {
        if (Math.random() < 0.75) {
            addEnemy(new Cyclops(spawnPoint.x, spawnPoint.y,1,1,1, getWorld()));
        }
        else {
            addEnemy(new Mouse(spawnPoint.x, spawnPoint.y,2,1,1, getWorld()));
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
