package model;

import com.dongbat.jbump.Collision;
import com.dongbat.jbump.Collisions;
import com.dongbat.jbump.World;
import com.badlogic.gdx.maps.Map;
import model.enemies.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Model implements MovementListener {
    private PlayerCharacter player;
    private List<Enemy> enemyList = new ArrayList<>();
    private IMapLoader mapLoader;
    private List<Entity> entityList = new ArrayList<>();
    private final List<Point> spawnPoints = new ArrayList<>();
    private static final int MAX_ENEMIES = 8;
    private int spawnPointsIndex = 0;

    public Model(IMapLoader mapLoader, PlayerCharacter player) {
        this.mapLoader = mapLoader;
        this.player = player;
    }

    public void initialize() {
        setSpawnPoints();
    }

    public PlayerCharacter getPlayer(){
        return player;
    }

    public void update() {
        spawnEnemies();
        moveEnemies();
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

    private void moveEnemies() {
        for (Enemy enemy : enemyList) {
            enemy.moveTowardPlayer(player.getX(), player.getY());
        }
    }

    private void spawnEnemies() {
        while (enemyList.size() < MAX_ENEMIES) {
            spawnRandomEnemy(spawnPoints.get(spawnPointsIndex));
            if (spawnPointsIndex < spawnPoints.size() - 1) { spawnPointsIndex++; }
            else { spawnPointsIndex = 0; }
        }
    }

    private void setSpawnPoints() {
        int xLeft = 100;
        int xCenter = mapLoader.getMapUnitWidth() / 2;
        int xRight = mapLoader.getMapUnitWidth() - 100;
        int yBottom = 100;
        int yCenter = mapLoader.getMapUnitHeight() / 2;
        int yTop = mapLoader.getMapUnitHeight() - 100;

        spawnPoints.add(new Point(xLeft, yTop));
        spawnPoints.add(new Point(xCenter, yTop));
        spawnPoints.add(new Point(xRight, yTop));

        spawnPoints.add(new Point(xLeft, yCenter));
        spawnPoints.add(new Point(xRight, yCenter));

        spawnPoints.add(new Point(xLeft, yBottom));
        spawnPoints.add(new Point(xCenter, yBottom));
        spawnPoints.add(new Point(xRight, yBottom));
    }

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

    public World<Entity> getWorld() {
        return mapLoader.getWorld();
    }
}
