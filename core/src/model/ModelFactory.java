package model;

import model.enemies.*;

public class ModelFactory {
    public static Model makeModel(IMapLoader mapLoader) {
        Model model = new Model();
        model.initialize(mapLoader);
        model.addEnemy(new Cyclops(100, 100, 1, 1, 1, model.getWorld()));
        model.addEnemy(new Cyclops(100, 200, 1, 1, 1, model.getWorld()));
        model.addEnemy(new Cyclops(200, 100, 1, 1, 1, model.getWorld()));
        model.addEnemy(new Mouse(200, 200,2, 1, 1, model.getWorld()));
        return model;
    }
}
