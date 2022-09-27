package Model.Enemies;

import Model.Entity;
import Model.IModel;
import com.dongbat.jbump.Item;
import com.dongbat.jbump.World;

public class Mouse extends Enemy {

    public Mouse(int x, int y, int height, int width, int speed, float health, float damage, IModel model) {
        super(x, y, height, width, speed, health, damage, model);
        setImagePath("Enemies/mouse-idle-down.png");
        addCollision();
    }
}
