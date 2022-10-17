package model;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class ModelFactory {
    public static Model makeModel(String mapName) {
        TiledMapLoader mapLoader = new TiledMapLoader(mapName);
        PlayerCharacter player = new PlayerCharacter(mapLoader.getMapUnitWidth() / 2, mapLoader.getMapUnitHeight() / 2, mapLoader.getWorld());

        int xLeft = 100;
        int xCenter = mapLoader.getMapUnitWidth() / 2;
        int xRight = mapLoader.getMapUnitWidth() - 100;
        int yBottom = 100;
        int yCenter = mapLoader.getMapUnitHeight() / 2;
        int yTop = mapLoader.getMapUnitHeight() - 100;
        List<Point> spawnPoints = Arrays.asList(new Point(xLeft, yTop), new Point(xCenter, yTop), new Point(xRight, yTop),
                                                new Point(xLeft, yCenter), new Point(xRight, yCenter),
                                                new Point(xLeft, yBottom), new Point(xCenter, yBottom), new Point(xRight, yBottom));

        Model model = new Model(mapLoader, player, spawnPoints);
        model.initialize();
        return model;
    }
}
