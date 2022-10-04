package Model;

import Model.Enemies.*;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Rectangle;
import com.dongbat.jbump.Item;
import com.dongbat.jbump.World;


import java.util.ArrayList;
import java.util.List;

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
    private ArrayList<Entity> entityList = new ArrayList<>();
    private World<Entity> world = new World<>();
    private List<Enemy> enemyList = new ArrayList<>();

    public PlayerCharacter getPlayerCharacter() {
        return player;
    }

    public TiledMap getTiledMap() {
        return tiledMap;
    }

    /**
     * Loads a new map
     * @param mapName name of map to be loaded
     */
    public void setMap(String mapName) {
        tiledMap = new TmxMapLoader().load("Map/" + mapName + ".tmx");
        this.world = new World<>();
        importMapProperties();
        importMapCollision();
    }
    public PlayerCharacter getPlayer(){
        return player ;
    }
    public void update() {
        for (Enemy enemy : enemyList) {
            enemy.moveTowardPlayer(player.getX(), player.getY());
        }
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

    public int getMapPixelHeight() {
        return mapPixelHeight;
    }

    public int getMapPixelWidth() {
        return mapPixelWidth;
    }

    public World<Entity> getWorld() {
        return world;
    }

    public ArrayList<Entity> getEntities(){
        return new ArrayList<Entity>(entityList);
    }
    /**
     * Loads a specified map and creates a playercharacter
     * @param mapName the filename of the map
     */
    public void initialize(String mapName) {
        tiledMap = new TmxMapLoader().load("Map/" + mapName + ".tmx");
        importMapProperties();
        importMapCollision();
        player = new PlayerCharacter(mapPixelWidth / 2, mapPixelHeight / 2, world);
        entityList.add(player);
        //Mouse mouse1 = new Mouse(50,50,16,16,2,1,1, world); //temporary
        //entityList.add(mouse1);
    }

    /**
     * Imports the height and width of the current map
     */
    private void importMapProperties() {
        prop = tiledMap.getProperties();
        mapWidth = prop.get("width", Integer.class);
        mapHeight = prop.get("height", Integer.class);
        tilePixelWidth = prop.get("tilewidth", Integer.class);
        tilePixelHeight = prop.get("tileheight", Integer.class);
        mapPixelWidth = mapWidth * tilePixelWidth;
        mapPixelHeight = mapHeight * tilePixelHeight;
    }

    /**
     * Loads all objects in the collision layer of the map
     * to the world. Does not support polygons.
     */
    private void importMapCollision() {
        objects = tiledMap.getLayers().get("collision").getObjects();
        for (RectangleMapObject o : objects.getByType(RectangleMapObject.class)) {
            Rectangle r = o.getRectangle();
            PlacedMapEntity st = new PlacedMapEntity(r.x, r.y, r.height, r.width, world, "");
            world.add(new Item<Entity>(st), r.x, r.y, r.width, r.height);
        }
    }

    public void addEnemy(Enemy enemy) {
        enemyList.add(enemy);
    }

    public List<Enemy> getEnemyList() {
        return new ArrayList<>(enemyList);
    }
}
