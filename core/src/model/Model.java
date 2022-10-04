package model;

import model.enemies.*;
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
    private final String MAPWIDTH = "width";
    private final String MAPHEIGHT = "height";
    private final String TILEUNITWIDTH = "tilewidth";
    private final String TILEUNITHEIGHT = "tileheight";
    private final String COLLISIONLAYER = "collision";

    private TiledMap map;
    private MapProperties prop;
    private int mapWidth;
    private int mapHeight;
    private int tileUnitWidth;
    private int tileUnitHeight;
    private int mapUnitWidth;
    private int mapUnitHeight;
    private MapObjects objects;
    private PlayerCharacter player;
    private List<Entity> entityList = new ArrayList<>();

    /**
     * World is a JBump object that
     * keeps track of all collisionboxes
     * and gives feedback about collision
     * Always empty when a new map is initialised
     * and then populated
     */
    private World<Entity> world;

    public PlayerCharacter getPlayerCharacter() {
        return player;
    }

    public TiledMap getMap() {
        return map;
    }

    /**
     * Loads a new map and populates a new world with data from the map
     * @param mapName name of map to be loaded
     */
    public void setMap(String mapName) {
        map = new TmxMapLoader().load("Map/" + mapName + ".tmx");
        this.world = new World<>();
        importMapProperties();
        importMapCollision();
    }
    public PlayerCharacter getPlayer(){
        return player ;
    }
    public void update(){

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

    public int getMapUnitHeight() {
        return mapUnitHeight;
    }

    public int getMapUnitWidth() {
        return mapUnitWidth;
    }

    public World<Entity> getWorld() {
        return world;
    }

    public ArrayList<Entity> getEntities(){
        return new ArrayList<>(entityList);
    }
    /**
     * Loads a specified map and creates a playercharacter
     * @param mapName the filename of the map
     */
    public void initialize(String mapName) {
        setMap(mapName);
        importMapProperties();
        importMapCollision();
        player = new PlayerCharacter(mapUnitWidth / 2, mapUnitHeight / 2, world);
        entityList.add(player);
        Mouse mouse1 = new Mouse(50,50,16,16,2,1,1, world); //temporary
        entityList.add(mouse1);
    }

    /**
     * Imports the height and width of the current map for placement purposes.
     * Tmx map sizes are defined in length of tiles, and tiles are defined
     * as a length of pixels that can vary between different maps.
     * Therefore the total length is number of tiles * number of pixels in a tile.
     */
    private void importMapProperties() {
        prop = map.getProperties();
        mapWidth = prop.get(MAPWIDTH, Integer.class);
        mapHeight = prop.get(MAPHEIGHT, Integer.class);
        tileUnitWidth = prop.get(TILEUNITWIDTH, Integer.class);
        tileUnitHeight = prop.get(TILEUNITHEIGHT, Integer.class);
        mapUnitWidth = mapWidth * tileUnitWidth;
        mapUnitHeight = mapHeight * tileUnitHeight;
    }

    /**
     * Loads all objects in the collision layer of the map
     * to the world. Does not support polygons.
     */
    private void importMapCollision() {
        objects = map.getLayers().get(COLLISIONLAYER).getObjects();
        for (RectangleMapObject o : objects.getByType(RectangleMapObject.class)) {
            Rectangle r = o.getRectangle();
            PlacedMapEntity st = new PlacedMapEntity(r.x, r.y, r.height, r.width, world);
            world.add(new Item<>(st), r.x, r.y, r.width, r.height);
        }
    }
}
