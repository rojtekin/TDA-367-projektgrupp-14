package model;

import com.dongbat.jbump.*;

import java.util.ArrayList;
import java.util.Collections;

public class Dagger implements IWeapon {

    private float weaponDamage = 2;
    private World<IEntity> collisionWorld;

    public Dagger(World<IEntity> world) {
        this.collisionWorld = world;
    }

    public void attack(float initX, float initY, float playerDamage, Faction faction, Direction dir) {
        Item<IEntity> hurtBox = new Item<>(null);
        Response.Result result;
        float x;
        float width;
        float y;
        float height;
        if (dir == Direction.DOWN) {
            x = initX + 8; width = initX + 24; y = initY; height = initY - 10;
            collisionWorld.add(hurtBox,x, y, width, height);
            result = collisionWorld.check(hurtBox, x, y, crossFilter());
            collisionHelper(result.projectedCollisions, playerDamage, faction);
        } else if (dir == Direction.UP) {
            x = initX + 8; y = initY + 32; width = initX + 24; height = initY + 42;
            collisionWorld.add(hurtBox,x, y, width, height);
            result = collisionWorld.check(hurtBox, x, y, crossFilter());
            collisionHelper(result.projectedCollisions, playerDamage, faction);
        } else if (dir == Direction.RIGHT) {
            x = initX + 32; y = initY + 8; width = initX + 42; height = initY + 24;
            collisionWorld.add(hurtBox,x, y, width, height);
            result = collisionWorld.check(hurtBox, x, y, crossFilter());
            collisionHelper(result.projectedCollisions, playerDamage, faction);
        } else if (dir == Direction.LEFT) {
            x = initX; y = initY + 8; width = initX -10; height = initY + 24;
            collisionWorld.add(hurtBox,x, y, width, height);
            result = collisionWorld.check(hurtBox, x, y, crossFilter());
            collisionHelper(result.projectedCollisions, playerDamage, faction);
        }
        collisionWorld.remove(hurtBox);
    }

    private void collisionHelper(Collisions collisions, float playerDamage, Faction faction) {
        for (int i = 0; i < collisions.size(); i++) {
            Item<Entity> touched = collisions.get(i).other;
            touched.userData.beAttacked(playerDamage + weaponDamage, faction);
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
