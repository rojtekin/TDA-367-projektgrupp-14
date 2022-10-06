package model;

import model.enemies.Cyclops;
import model.enemies.Mouse;

public class ModelFactory {
    public static Model makeModel(String mapName) {
        Model model = new Model();
        model.addEnemy(new Cyclops(100, 100, 1, 1, 1, model.getWorld()));
        model.addEnemy(new Cyclops(100, 200, 1, 1, 1, model.getWorld()));
        model.addEnemy(new Cyclops(200, 100, 1, 1, 1, model.getWorld()));
        model.addEnemy(new Mouse(200, 200,2, 1, 1, model.getWorld()));
        model.initialize(mapName);
        return model;
    }
}
