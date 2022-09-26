package Model;

import com.dongbat.jbump.World;

public interface IModel {
    public void initialize();
    World<Entity> getWorld();
}
