package model;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class ModelFactory {
    public static Model makeModel(String mapName) {
        TiledEnvironmentCache mapData = new TiledEnvironmentCache();
        mapData.loadEnvironment(mapName);
        PlayerCharacter player = new PlayerCharacter(mapData.getMapUnitWidth() / 2, mapData.getMapUnitHeight() / 2, mapData.getWorld());

        int xLeft = 100;
        int xCenter = mapData.getMapUnitWidth() / 2;
        int xRight = mapData.getMapUnitWidth() - 100;
        int yBottom = 100;
        int yCenter = mapData.getMapUnitHeight() / 2;
        int yTop = mapData.getMapUnitHeight() - 100;
        List<Point> spawnPoints = Arrays.asList(new Point(xLeft, yTop), new Point(xCenter, yTop), new Point(xRight, yTop),
                                                new Point(xLeft, yCenter), new Point(xRight, yCenter),
                                                new Point(xLeft, yBottom), new Point(xCenter, yBottom), new Point(xRight, yBottom));

        Model model = new Model(mapData, player, spawnPoints);
        model.initialize();
        return model;
    }
}
