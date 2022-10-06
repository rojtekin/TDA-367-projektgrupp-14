package model;

public class ModelFactory {
    public static Model makeModel(String mapName) {
        Model model = new Model();
        model.initialize(new TiledMapLoader(mapName));
        return model;
    }
}
