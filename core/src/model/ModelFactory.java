package model;

public class ModelFactory {
    public static Model makeModel(String mapName) {
        TiledMapLoader mapLoader = new TiledMapLoader(mapName);
        PlayerCharacter player = new PlayerCharacter(mapLoader.getMapUnitWidth() / 2, mapLoader.getMapUnitHeight() / 2, mapLoader.getWorld());
        Model model = new Model(mapLoader, player);
        model.initialize();
        return model;
    }
}
