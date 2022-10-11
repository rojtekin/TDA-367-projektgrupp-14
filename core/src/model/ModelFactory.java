package model;

public class ModelFactory {
    public static Model makeModel(IMapLoader mapLoader) {
        Model model = new Model();
        model.initialize(mapLoader);
        return model;
    }
}
