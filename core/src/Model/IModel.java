package Model;

import com.dongbat.jbump.World;

public interface IModel {

    int getMapPixelWidth();

    int getMapPixelHeight();

    void initialize();

    World<Entity> getWorld();
}
