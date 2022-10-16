package model;

import model.monsters.*;

public class ModelFactory {
    public static Model makeModel(IEnvironmentCache mapLoader) {
        Model model = new Model();
        model.initialize(mapLoader);
        model.addEnemy(new Cyclops(100, 100, 1, 10, 10, model.getWorld()));
        model.addEnemy(new Cyclops(100, 200, 1, 10, 10, model.getWorld()));
        model.addEnemy(new Cyclops(200, 100, 1, 10, 10, model.getWorld()));
        model.addEnemy(new Mouse(200, 200,2, 10, 5, model.getWorld()));
        return model;
    }
}
