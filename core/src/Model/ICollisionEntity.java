package Model;

import com.dongbat.jbump.World;

public interface ICollisionEntity {

    void addCollision();

    void setWorld(World<Entity> world);

    void updatePosition();
}
