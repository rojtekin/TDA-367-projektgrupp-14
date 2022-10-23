package model;

import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Rectangle;
import com.dongbat.jbump.Item;
import com.dongbat.jbump.World;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Responsible for loading tile based maps and holding information relevant for logic
 * such as map proportions for spawn locations and for importing map objects for collision
 * Also holds the "world" object which handles collisionboxes
 */
public class TiledMapCache implements IMapCache {
    //Constants for layer names in map. We can't control how the layers are named
    //since Tiled allows you to name and number layers however you want. Therefore
    //the mapmaker must comply with these constants or else the program won't be able
    //to find the appropriate layers.
    private final String MAPWIDTH = "width";
    private final String MAPHEIGHT = "height";
    private final String TILEUNITWIDTH = "tilewidth";
    private final String TILEUNITHEIGHT = "tileheight";
    private final String COLLISIONLAYER = "collision";
    private final String SPAWNLAYER = "spawn";

    private TiledMap map;
    private MapProperties prop;
    private int mapWidth;
    private int mapHeight;
    private int tileUnitWidth;
    private int tileUnitHeight;
    private int mapUnitWidth;
    private int mapUnitHeight;
    private List<Point> spawnPoints;
    private MapObjects collisionObjects;
    private MapObjects spawnObjects;

    private World<IEntity> world;

    /**
     * World is a JBump object that keeps track of all collisionboxes
     * and gives feedback about collision. World is always empty when
     * a new map is initialised and then populated from the maps object layer.
     * Uses spatial partitioning and Axis Aligned Bounding Boxes
     * to detect collisions.
     * @return reference to JBump world object
     */
    public World<IEntity> getWorld() {
        return world;
    }

    public TiledMap getMap() {
        return map;
    }

    public int getMapUnitHeight() {
        return mapUnitHeight;
    }

    public int getMapUnitWidth() {
        return mapUnitWidth;
    }

    public List<Point> getSpawnPoints() {
        return spawnPoints;
    }

    /**
     * Creates an empty environment that can be loaded in the model
     * Useful for testing without a map
     */
    public TiledMapCache() {
        mapUnitHeight = 0;
        mapUnitWidth = 0;
        world = new World<>();
        defaultSpawnPoints();
    }

    private List<Point> defaultSpawnPoints() {
        int xLeft = 300;
        int xCenter = getMapUnitWidth() / 2;
        int xRight = getMapUnitWidth() - 200;
        int yBottom = 300;
        int yCenter = getMapUnitHeight() / 2;
        int yTop = getMapUnitHeight() - 200;
        return spawnPoints = Arrays.asList(new Point(xLeft, yTop), new Point(xCenter, yTop), new Point(xRight, yTop),
                new Point(xLeft, yCenter), new Point(xRight, yCenter),
                new Point(xLeft, yBottom), new Point(xCenter, yBottom), new Point(xRight, yBottom));

    }

    /**
     * Loads a new map and populates a new world with data from the map
     * @param mapName name of map to be loaded
     */
    public void loadEnvironment(String mapName) {
        map = new TmxMapLoader().load("Map/" + mapName + ".tmx");
        this.world = new World<>();
        readMapSize();
        importCollisionLayer();
        importSpawnPoints();
    }

    /**
     * Imports the height and width of the current map for placement purposes.
     * Tmx map sizes are defined in length of tiles, and tiles are defined
     * as a length of pixels that can vary between different maps.
     * Therefore, the total length is number of tiles * number of pixels in a tile.
     */
    private void readMapSize() {
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
     * to the world. Does not support polygons due to the game
     * using Axis-Aligned Bounding Boxes for its collision.
     */
    private void importCollisionLayer() {
        if (map.getLayers().get(COLLISIONLAYER) != null) {
            collisionObjects = map.getLayers().get(COLLISIONLAYER).getObjects();
            for (RectangleMapObject o : collisionObjects.getByType(RectangleMapObject.class)) {
                Rectangle r = o.getRectangle();
                PlacedMapEntity st = new PlacedMapEntity(r.x, r.y, r.height, r.width, world);
                world.add(new Item<>(st), r.x, r.y, r.width, r.height);
            }
        }
    }

    /**
     * Fetches a list of spawnpoints from a map if it has a baked in spawnlayer, otherwise uses default spawnpoints
     */
    private void importSpawnPoints() {
        if (map.getLayers().get(SPAWNLAYER) != null) {
            spawnObjects = map.getLayers().get(SPAWNLAYER).getObjects();
            spawnPoints = new ArrayList<>();
            for (RectangleMapObject o : spawnObjects.getByType(RectangleMapObject.class)) {
                Rectangle r = o.getRectangle();
                int rX = (int) r.getX() + ( (int) Math.abs(r.getWidth()) - (int) Math.abs(r.getX()) / 2);
                int rY = (int) r.getY() + ( (int) Math.abs(r.getHeight() - (int) Math.abs(r.getY())) / 2);
                spawnPoints.add(new Point(rX, rY));
            }
        }
    }
}
