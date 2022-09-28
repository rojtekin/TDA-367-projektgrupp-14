package Model;

import Model.Enemies.Enemy;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.dongbat.jbump.Item;
import com.dongbat.jbump.World;

import java.util.ArrayList;

public class Model implements IModel {
    private TiledMap tiledMap = new TmxMapLoader().load("Map/TestMap.tmx");
    private MapProperties prop = tiledMap.getProperties();
    private int mapWidth = prop.get("width", Integer.class);
    private int mapHeight = prop.get("height", Integer.class);
    private int tilePixelWidth = prop.get("tilewidth", Integer.class);
    private int tilePixelHeight = prop.get("tileheight", Integer.class);

    private int mapPixelWidth = mapWidth * tilePixelWidth;
    private int mapPixelHeight = mapHeight * tilePixelHeight;

    private PlayerCharacter player;
    private ArrayList<Enemy> enemyList = new ArrayList<>();
    private World<Entity> world = new World<>();

    public PlayerCharacter getPlayer() {
        return player;
    }

    public int getMapPixelHeight() {
        return mapPixelHeight;
    }

    public int getMapPixelWidth() {
        return mapPixelWidth;
    }

    //World contains the collisionboxes from JBump
    public World<Entity> getWorld() {
        return world;
    }

    public ArrayList<Enemy> getEnemies(){
        return this.enemyList;
    }

    public void initialize() {
        player = new PlayerCharacter(mapPixelWidth / 2, mapPixelHeight / 2, this);
    }

    public void updateEnemyList() {
        for (Entity e : enemyList) {
            world.add(new Item<Entity>(e), e.getX(), e.getY(), e.getWidth(), e.getHeight());
        }
    }
}
