package model;

import com.dongbat.jbump.Collision;
import com.dongbat.jbump.Collisions;
import com.dongbat.jbump.World;
import com.badlogic.gdx.maps.Map;
import model.monsters.*;
import java.awt.*;
import model.rewards.RewardSystem;
import view.ISoundObserver;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A class responsible for managing the logic of the game.
 */
public class Model implements IModelSubject {
    private final IMapCache mapCache;
    private final IPlayerCharacter player;
    private final List<Monster> monsters = new ArrayList<>();
    private final List<IEntity> entityList = new ArrayList<>();
    private final List<Point> spawnPoints;
    private static final int MAX_MONSTERS = 50;
    private int spawnPointsIndex = 0;
    private final RewardSystem rewardSystem = new RewardSystem();
    private final World<IEntity> world;
    private int currentScore = 0;
    private final List<ISoundObserver> soundObservers = new ArrayList<>();
    private boolean playerIsDead = false;

    public Model(IMapCache mapCache, IPlayerCharacter player, List<Point> spawnPoints) {
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
        playerHealthCheck();
    }

    /**
     * Checks if player has reached the threshold to level up and applies a reward accordingly if so.
     */
    private void levelUpCheckAndApply() {
        if (player.levelUpCheck()){
            player.reduceExperience();
            rewardSystem.applyReward(player, rewardSystem.getRandomReward(player));
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

    public boolean playerIsInMotion() {
        return player.isInMotion();
    }

    public ArrayList<IEntity> getEntities(){
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
    }

    public List<Monster> getMonsters() {
        return new ArrayList<>(monsters);
    }

    /**
     * Moves every monster.
     */
    private void moveMonsters() {
        for (Monster monster : monsters) {
            Collisions collisions = monster.moveTowardTarget(player.getX(), player.getY());
            checkCollisions(collisions, monster);
        }
    }

    /**
     * Goes through collisions and checks if a collision with the player character has occurred.
     * If it has, the player character is affected.
     * @param collisions the collisions which occurred when a living entity moved
     * @param livingEntity the living entity which moved
     */
    private void checkCollisions(Collisions collisions, ILivingEntity livingEntity) {
        for (int i = 0; i < collisions.size(); i++) {
            Collision collision = collisions.get(i);
            if (collisionWithPlayer(collision)) {
                player.beAttacked(livingEntity.getDamage(), livingEntity.getFaction());
                player.pushBack(collision.normal);
                notifyMonsterAttack();
            }
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

    private boolean collisionWithPlayer(Collision collision) {
        return collision.other.userData.equals(player);
    }

    public World<IEntity> getWorld() {
        return mapCache.getWorld();
    }

    /**
     * Removes a monster from the game and removes
     * its collisionBox from the world
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

    /**
     * Checks if a player's health is equal to or below zero and if
     * the method has been triggered previously. If not it plays a sound and
     * sets a boolean value to prevent the method from being triggered again.
     */
    public void playerHealthCheck() {
        if (!playerIsDead && player.getCurrentHealth() <= 0) {
            notifyPlayerDeath();
            playerIsDead = true;
        }
    }

    @Override
    public void addObserver(ISoundObserver observer) {
        player.getWeapon().addObserver(observer);
        soundObservers.add(observer);
    }

    @Override
    public void removeObserver(ISoundObserver observer) {
        player.getWeapon().removeObserver(observer);
        soundObservers.remove(observer);
    }

    @Override
    public void notifyPlayerDeath() {
        for (ISoundObserver o : soundObservers) {
            o.playPlayerDeathSound();
        }
    }

    @Override
    public void notifyMonsterAttack() {
        for (ISoundObserver o : soundObservers) {
            o.playEnemyHit();
        }
    }

}
