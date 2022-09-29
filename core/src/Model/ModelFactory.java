package Model;

public class ModelFactory {
    public static Model makeModel(String mapName) {
        Model model = new Model();
        model.initialize(mapName);
        return model;
    }
}
