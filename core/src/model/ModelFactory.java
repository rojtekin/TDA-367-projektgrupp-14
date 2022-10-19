package model;

import java.awt.*;
import java.util.List;

public class ModelFactory {
    public static Model makeModel(String mapName) {
        TiledEnvironmentCache mapData = new TiledEnvironmentCache();
        mapData.loadEnvironment(mapName);
        PlayerCharacter player = new PlayerCharacter(mapData.getMapUnitWidth() / 2, mapData.getMapUnitHeight() / 2, mapData.getWorld());

        List<Point> spawnPoints = mapData.getSpawnPoints();
        Model model = new Model(mapData, player, spawnPoints);
        model.initialize();
        return model;
    }
}
