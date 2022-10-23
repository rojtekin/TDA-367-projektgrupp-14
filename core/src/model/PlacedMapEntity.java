package model;

import com.dongbat.jbump.World;

/**
 * A class for static objects placed on the map. Used to assign collision to map objects.
 * These entities have no movement and no health, as they represent constant items in the
 * world that the player cannot directly interact with, such as walls.
 */
public class PlacedMapEntity extends Entity {
    public PlacedMapEntity(float x, float y, float height, float width, World<IEntity> world) {
        super(x, y, height, width, 0, world);
    }

    /**
     * When a static map entity is attacked nothing happens as these entities
     * can't take damage
     */
    @Override
    public void beAttacked(float damage, Faction faction) {
    }
}
