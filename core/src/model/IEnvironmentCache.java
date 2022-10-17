package model;

import com.badlogic.gdx.maps.Map;
import com.dongbat.jbump.World;

/**
 * An interface that should allow for different implementations of maps, not just TiledMaps
 * but also potentially things like spreadsheets or just image files. For 2D games, tiles are
 * at this time ubiquitous and considered the standard but this might change in the future
 */
public interface IEnvironmentCache {

    /**
     * Gets the JBump "world" which is used for moving entities and registering collisions
     * @return the JBump world
     */
    World<IEntity> getWorld();

    /**
     * @return the map used in the model
     */
    Map getMap();

    /**
     * Used to load a map into the model for extracting logic related data
     * @param mapName the name of the map to be loaded
     */
    void loadEnvironment(String mapName);

    /**
     * Useful for knowing where to place objects and entities
     * @return the width of the map
     */
    int getMapUnitWidth();

    /**
     * Useful for knowing where to place objects and entities
     * @return the height of the map
     */
    int getMapUnitHeight();

}
