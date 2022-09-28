package Model;

import Model.Enemies.Enemy;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Rectangle;
import com.dongbat.jbump.Item;
import com.dongbat.jbump.World;


import java.util.ArrayList;

public class Model implements IModel {
    private TiledMap tiledMap;
    private MapProperties prop;
    private int mapWidth;
    private int mapHeight;
    private int tilePixelWidth;
    private int tilePixelHeight;
    private int mapPixelWidth;
    private int mapPixelHeight;
    private MapObjects objects;
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
    public World<Entity> getWorld() {
        return world;
    }
    public ArrayList<Enemy> getEnemies(){
        return this.enemyList;
    }

    public void initialize(String mapName) {
        tiledMap = new TmxMapLoader().load("Map/" + mapName + ".tmx");
        importMapProperties();
        importMapCollision();
        player = new PlayerCharacter(mapPixelWidth / 2, mapPixelHeight / 2, world);
    }

    public void importMapProperties() {
        prop = tiledMap.getProperties();
        mapWidth = prop.get("width", Integer.class);
        mapHeight = prop.get("height", Integer.class);
        tilePixelWidth = prop.get("tilewidth", Integer.class);
        tilePixelHeight = prop.get("tileheight", Integer.class);
        mapPixelWidth = mapWidth * tilePixelWidth;
        mapPixelHeight = mapHeight * tilePixelHeight;
        objects = tiledMap.getLayers().get(3).getObjects();
    }

    public void importMapCollision() {
        for (RectangleMapObject o : objects.getByType(RectangleMapObject.class)) {
            Rectangle r = o.getRectangle();
            StaticObjectEntity st = new StaticObjectEntity(r.x, r.y, r.height, r.width, world);
            world.add(new Item<Entity>(st), r.x, r.y, r.width, r.height);
        }
    }

    public void updateEnemyList() {
        for (Entity e : enemyList) {
            world.add(new Item<>(e), e.getX(), e.getY(), e.getWidth(), e.getHeight());
        }
    }
}
