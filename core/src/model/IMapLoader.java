package model;

import com.badlogic.gdx.maps.Map;
import com.dongbat.jbump.World;

/**
 * An interface that should allow for different implementations of maps, not just TiledMaps
 * but also potentially things like spreadsheets or just image files. For 2D games, tiles are
 * at this time ubiquitous and considered the standard but this might change in the future
 */
public interface IMapLoader {

    World<Entity> getWorld();

    Map getMap();

    void setMap(String mapName);

    int getMapUnitWidth();

    int getMapUnitHeight();

}
