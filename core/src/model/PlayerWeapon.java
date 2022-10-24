package model;

import com.dongbat.jbump.*;

import java.util.*;

import static java.lang.Math.*;

/**
 * PlayerWeapon is an abstract class that have the methods required for a weapon
 */
public abstract class PlayerWeapon {

    private float weaponSpeed;
    private float weaponDamage;
    private float weaponRange;
    private final float weaponWidth;
    private double weaponAngle;
    private final int weaponRotations;
    private final World<IEntity> world;
    private final CollisionFilter filter;

    /**
     * Constructor that subclasses of PlayerWeapon use.
     * @param world the world weapon exists in
     * @param weaponDamage Is the damage the weapon deals per hit
     * @param weaponRange Is the range the sword has
     * @param weaponWidth Is the swords width
     * @param weaponSpeed Will be how often the sword can be swung
     * @param weaponAngle The rotation of the sword
     * @param weaponRotations How many angles the swordswing contains
     */
    public PlayerWeapon(World<IEntity> world, float weaponDamage, float weaponRange, float weaponWidth, float weaponSpeed, float weaponAngle, int weaponRotations){
        this.weaponDamage = weaponDamage;
        this.weaponRange = weaponRange;
        this.weaponWidth = weaponWidth;
        this.weaponSpeed = weaponSpeed;
        this.weaponAngle = weaponAngle;
        this.weaponRotations = weaponRotations;
        this.world = world;
        filter = getFilter();
    }

    /**
     * Makes multiple segments that scan for enemies touching them and afterwards damages those enemies.
     * @param rotationStart The angle (degrees) the swordswing will start at
     * @param rotationFinish The angle (degrees) the swordswing will end at
     * @param currentWeaponRotation What part of the rotation it starts at
     * @param playerX The x position of the player that the sword swings around
     * @param playerY The y position of the player that the sword swings around
     * @param playerWidth The width of the player that the sword swings around
     * @param playerHeight The height of the player that the sword swings around
     * @param playerDamage The player's damage
     * @param faction The faction of the player
     */
    public void weaponSwing(int rotationStart, int rotationFinish, int currentWeaponRotation, float playerX, float playerY, float playerWidth, float playerHeight, float playerDamage, Faction faction){
        int degreeDistance = Math.abs(rotationStart - rotationFinish);
        double degreeRotation = degreeDistance/weaponRotations;
        while (currentWeaponRotation <= weaponRotations) {
            setWeaponAngle(toRadians(rotationStart + degreeRotation*currentWeaponRotation/3));
            float point1 = getWeaponRange();
            float rotatedPoint1x = (float) (point1 * cos(getWeaponAngle()));
            float rotatedPoint1y = (float) (point1 * sin(getWeaponAngle()));
            rotatedPoint1x = rotatedPoint1x + playerWidth/2 + playerX;
            rotatedPoint1y = rotatedPoint1y + playerHeight/2 + playerY;

            IntPoint collisionNormal = new IntPoint();
            collisionNormal.x = (int) Math.round(-1 *  cos(getWeaponAngle()));
            collisionNormal.y = (int) Math.round(-1 *  sin(getWeaponAngle()));

            Set<Entity> isKnown = new HashSet<>();
            ArrayList<ItemInfo> items = new ArrayList<>();
            List<Entity> entities = new LinkedList<>();
            world.querySegmentWithCoords((playerX+playerWidth/2), (playerY+playerHeight/2), rotatedPoint1x, rotatedPoint1y, filter, items);

            for (ItemInfo i : items) {
                entities.add((Entity) i.item.userData);
            }
            if (entities.size() > 0) {
                for (Entity entity : entities) {
                    if (!isKnown.contains((entity))) {
                        entity.beAttacked((weaponDamage + playerDamage), faction);
                        if (entity.getX() != playerX && entity.getY() != playerY) {
                            entity.pushBack(collisionNormal);
                        }
                        isKnown.add(entity);
                    }
                }
            }

            currentWeaponRotation += 1;
        }
        setWeaponAngle(((rotationStart-45)%360));
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