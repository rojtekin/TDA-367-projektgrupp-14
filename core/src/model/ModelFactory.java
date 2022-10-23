package model;

import java.awt.*;
import java.util.List;

/**
 * A class responsible for creating a Model object.
 */
public class ModelFactory {
    public static Model makeModel(String mapName) {
        TiledMapCache mapData = new TiledMapCache();
        mapData.loadEnvironment(mapName);
        PlayerCharacter player = new PlayerCharacter(mapData.getMapUnitWidth() / 2, mapData.getMapUnitHeight() / 2, mapData.getWorld());

        List<Point> spawnPoints = mapData.getSpawnPoints();
        Model model = new Model(mapData, player, spawnPoints);
        model.initialize();
        return model;
    }
}
