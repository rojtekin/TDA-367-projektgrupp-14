package Model;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.dongbat.jbump.World;

public interface IModel {

    PlayerCharacter getPlayerCharacter();

    TiledMap getTiledMap();

    void setMap(String mapName);

    int getMapPixelWidth();

    int getMapPixelHeight();

    void initialize(String mapName);

    World<Entity> getWorld();
}
