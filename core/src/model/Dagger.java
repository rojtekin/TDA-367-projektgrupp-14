package model;

import com.dongbat.jbump.CollisionFilter;
import com.dongbat.jbump.Item;
import com.dongbat.jbump.Response;
import com.dongbat.jbump.World;

import java.util.ArrayList;

public class Dagger implements IWeapon {

    private float weaponDamage = 2;
    private World<IEntity> collisionWorld;

    public Dagger(World<IEntity> world) {
        this.collisionWorld = world;
    }

    public void attack(float x, float y, float playerDamage, Faction faction, Direction dir) {
        int xdir = 1;
        int ydir = 1;
        if (dir.x <= 0) {
            xdir = -1;
        }
        if (dir.y <= 0) {
            ydir = -1;
        }
        ArrayList<Item> hit = new ArrayList<>();
        collisionWorld.queryRect(x, y, x + (xdir*20), y + (ydir*10), crossFilter(), hit);
        for (Item<Entity> i : hit) {
            i.userData.beAttacked(playerDamage + weaponDamage, faction);
        }
    }

    public CollisionFilter crossFilter() {
        CollisionFilter cf = new CollisionFilter() {
            @Override
            public Response filter(Item item, Item other) {
                return Response.cross;
            }
        };
        return cf;
    }
}
