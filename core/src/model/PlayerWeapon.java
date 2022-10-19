package model;

import com.dongbat.jbump.*;
import view.WeaponSwingListener;

import java.util.*;

import static java.lang.Math.*;

public abstract class PlayerWeapon {

    private float weaponSpeed;
    private float weaponDamage;
    private float weaponRange;
    private float weaponWidth;
    private double weaponAngle;
    private int weaponRotations;
    private World<Entity> world;
    private CollisionFilter filter;
    private Set<Entity> isKnown = new HashSet<Entity>();
    private List<WeaponSwingListener> weaponSwingListeners = new ArrayList<>();

    public PlayerWeapon(World<Entity> world, float weaponDamage, float weaponRange, float weaponWidth, float weaponSpeed, float weaponAngle, int weaponRotations){
        this.weaponDamage = weaponDamage;
        this.weaponRange = weaponRange;
        this.weaponWidth = weaponWidth;
        this.weaponSpeed = weaponSpeed;
        this.weaponAngle = weaponAngle;
        this.weaponRotations = weaponRotations;
        this.world = world;
        filter = getFilter();
    }

    //TODO
    //in playercharachter the swords position shall update with the move command like player does
    public void weaponSwing(int rotationStart, int rotationFinish, int animationpart, PlayerCharacter player){
        int degreeDistance = Math.abs(rotationStart - rotationFinish);
        double degreeRotation = degreeDistance/weaponRotations;
        while (animationpart <= weaponRotations) {
            setWeaponAngle(toRadians(rotationStart + degreeRotation*animationpart/3)); //there will be a listener who prints out the sword when the angle changes
            float point1 = getWeaponRange();
            float rotatedPoint1x = (float) (point1 * cos(getWeaponAngle()));
            float rotatedPoint1y = (float) (point1 * sin(getWeaponAngle()));
            rotatedPoint1x = rotatedPoint1x + player.getWidth()/2 + player.getX();
            rotatedPoint1y = rotatedPoint1y + player.getHeight()/2 + player.getY();

            //world.querySegmentWithCoords(player.getX(), player.getY(), rotatedpoint1x, rotatedpoint1y, filter, null); //filter är cross, fixa i jbump.
            ArrayList<ItemInfo> items = new ArrayList<>();
            world.querySegmentWithCoords(player.getX(), player.getY(), rotatedPoint1x, rotatedPoint1y, filter, items);
            List<Entity> entities = new LinkedList<>();
            for (ItemInfo i : items) {
                entities.add((Entity) i.item.userData);
            }
            items.clear();
            if (entities.size() > 0) {
                Set<Entity> seen = new HashSet<Entity>();
                for (Entity entity : entities) {
                    if (!isKnown.contains((entity))) {
                        //gör skada;
                        entity.beAttacked((weaponDamage),"player");
                    }
                    seen.add(entity);
                }
                isKnown = seen;
                entities.clear();
            }

            animationpart += 1;
        }
    }
    public double getWeaponAngle() {
        return weaponAngle;
    }

    private void setWeaponAngle(double weaponAngle) {
        this.weaponAngle = weaponAngle;
    }

    public float getWeaponRange() {
        return weaponRange;
    }

    public float getWeaponWidth() {
        return weaponWidth;
    }

    public CollisionFilter getFilter() {
        CollisionFilter filter = new CollisionFilter() {
            @Override
            public Response filter(Item item, Item item2) {
                return Response.cross;
            }
        };
        return filter;
    }
}