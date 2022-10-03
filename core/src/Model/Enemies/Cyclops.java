package Model.Enemies;

import Model.Entity;
import com.dongbat.jbump.World;

public class Cyclops extends Enemy {

    public Cyclops(int x, int y, int speed, float health, float damage, World<Entity> world) {
        super(x, y, 32, 32, speed, health, damage, world,"Cyclops");
    }
}
