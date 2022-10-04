package model;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.dongbat.jbump.World;

public interface IModel {

    PlayerCharacter getPlayerCharacter();

    TiledMap getMap();

    void setMap(String mapName);

    int getMapUnitWidth();

    int getMapUnitHeight();

    void initialize(String mapName);

    World<Entity> getWorld();

    void setPlayerMoving(boolean moving);
}
