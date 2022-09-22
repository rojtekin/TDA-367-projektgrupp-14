package Model;

public class ModelFactory {
    public static Model makeModel() {
        Model model = new Model();
        model.initialize();
        return model;
    }
}
