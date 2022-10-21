package model;

import com.dongbat.jbump.Collision;
import com.dongbat.jbump.Collisions;
import com.dongbat.jbump.World;
import com.badlogic.gdx.maps.Map;
import model.monsters.*;
import java.awt.*;
import model.rewards.Reward;
import model.rewards.RewardSystem;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Model implements MovementListener {
    private IEnvironmentCache mapCache;
    private IPlayerCharacter player;
    private final List<Monster> monsters = new ArrayList<>();
    private final List<Entity> entityList = new ArrayList<>();
    private final List<Point> spawnPoints;
    private static final int MAX_MONSTERS = 50;
    private int spawnPointsIndex = 0;
    private final RewardSystem rewardSystem = new RewardSystem();
    private World<IEntity> world;
    private int currentScore = 0;

    public Model(IEnvironmentCache mapCache, PlayerCharacter player, List<Point> spawnPoints) {
        this.mapCache = Objects.requireNonNull(mapCache);
        this.world = mapCache.getWorld();
        this.player = Objects.requireNonNull(player);
        this.spawnPoints = Objects.requireNonNull(spawnPoints);
    }

    /**
     * @return playerCharacter connected to Model
     */
    public IPlayerCharacter getPlayer(){
        return player;
    }

    public void update() {
        spawnMonsters();
        moveMonsters();
        levelUpCheckAndApply();
        despawnDeadNPCs();
        System.out.println(getPlayer().getExperience());
        System.out.println(getPlayer().getPerkList());
    }

    /**
     * Checks if player has reached the threshold to level up and applies a reward accordingly if so.
     */
    private void levelUpCheckAndApply() {
        if (player.levelUpCheck()){
            player.reduceExperience();
            player = rewardSystem.applyReward(getPlayer(), Reward.SPEED_DEVIL);
            player.increaseLevel();
        }
    }

    /**
     * Goes through the list of enemies checks if they need to be removed and adds experience & score
     * O(n)
     */
    public void despawnDeadNPCs() {
        for (int i = 0; i < getMonsters().size(); i++) {
            if (getMonsters().get(i).getCurrentHealth() <= 0) {
                getPlayer().gainExperience(getMonsters().get(i).getExperience());
                setCurrentScore(getCurrentScore()+getMonsters().get(i).getScore());
                despawn(getMonsters().get(i));
            }
        }
    }

    public Map getMap() {
        return mapCache.getMap();
    }

    public Direction getPlayerDirection() {
        return player.getDirection();
    }

    public boolean playerIsMoving() {
        return player.isMoving();
    }

    public ArrayList<Entity> getEntities(){
        return new ArrayList<>(entityList);
    }

    /**
     * Allows manually adding of a monster to the model
     * @param monster the monster to be added
     */
    public void addMonster(Monster monster) {
        if (monster.getWorld() != world) {
            monster.setWorld(world);
        }
        monsters.add(monster);
        entityList.add(monster);
        monster.addMovementListener(this);
    }

    public List<Monster> getMonsters() {
        return new ArrayList<>(monsters);
    }

    private void moveMonsters() {
        for (Monster monster : monsters) {
            monster.moveTowardPlayer(player.getX(), player.getY());
        }
    }

    /**
     * Adds monsters at different spawn points until the maximum number of monsters is reached.
     */
    private void spawnMonsters() {
        while (monsters.size() < MAX_MONSTERS) {
            spawnRandomMonster(spawnPoints.get(spawnPointsIndex));
            if (spawnPointsIndex < spawnPoints.size() - 1) { spawnPointsIndex++; }
            else { spawnPointsIndex = 0; }
        }
    }

    /**
     * Adds a random monster at the specified spawn point.
     * @param spawnPoint the spawn point where a random monster should spawn
     */
    private void spawnRandomMonster(Point spawnPoint) {
        if (Math.random() < 0.75) {
            addMonster(new Cyclops(spawnPoint.x, spawnPoint.y, getWorld()));
        }
        else {
            addMonster(new Mouse(spawnPoint.x, spawnPoint.y, getWorld()));
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

    private boolean collisionWithPlayer(Collision collision) {
        return collision.other.userData.equals(player);
    }

    public World<IEntity> getWorld() {
        return mapCache.getWorld();
    }

    public void initialize() {
        rewardSystem.initialize(this);
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

    /**
     * @return returns the score the player has acquired
     */
    public int getCurrentScore() {
        return currentScore;
    }

    public void setCurrentScore(int currentScore) {
        this.currentScore = currentScore;
    }
}
