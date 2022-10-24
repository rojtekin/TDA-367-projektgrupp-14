package model;

import com.dongbat.jbump.*;
import view.ISoundObserver;

import java.util.*;

import static java.lang.Math.*;

public abstract class PlayerWeapon implements IWeaponSubject {

    private float weaponSpeed;
    private float weaponDamage;
    private float weaponRange;
    private final float weaponWidth;
    private double weaponAngle;
    private final int weaponRotations;
    private final World<IEntity> world;
    private final CollisionFilter filter;
    private final List<ISoundObserver> soundObservers = new ArrayList<>();

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

    //in playercharachter the swords position shall update with the move command like player does
    public void weaponSwing(int rotationStart, int rotationFinish, int currentWeaponRotation, PlayerCharacter player){
        int degreeDistance = Math.abs(rotationStart - rotationFinish);
        double degreeRotation = degreeDistance/weaponRotations;
        while (currentWeaponRotation <= weaponRotations) {
            setWeaponAngle(toRadians(rotationStart + degreeRotation*currentWeaponRotation/3)); //there will be a listener who prints out the sword when the angle changes
            float point1 = getWeaponRange();
            float rotatedPoint1x = (float) (point1 * cos(getWeaponAngle()));
            float rotatedPoint1y = (float) (point1 * sin(getWeaponAngle()));
            rotatedPoint1x = rotatedPoint1x + player.getWidth()/2 + player.getX();
            rotatedPoint1y = rotatedPoint1y + player.getHeight()/2 + player.getY();

            IntPoint collisionNormal = new IntPoint();
            collisionNormal.x = (int) Math.round(-1 *  cos(getWeaponAngle()));
            collisionNormal.y = (int) Math.round(-1 *  sin(getWeaponAngle()));

            Set<Entity> isKnown = new HashSet<>();
            ArrayList<ItemInfo> items = new ArrayList<>();
            List<Entity> entities = new LinkedList<>();
            //world.querySegmentWithCoords(player.getX(), player.getY(), rotatedpoint1x, rotatedpoint1y, filter, null); //filter är cross, fixa i jbump.
            world.querySegmentWithCoords((player.getX()+player.getWidth()/2), (player.getY()+player.getHeight()/2), rotatedPoint1x, rotatedPoint1y, filter, items);

            for (ItemInfo i : items) {
                entities.add((Entity) i.item.userData);
            }
            if (entities.size() > 0) {
                for (Entity entity : entities) {
                    if (!isKnown.contains((entity))) {
                        //gör skada;
                        entity.beAttacked((weaponDamage + player.getDamage()),Faction.PLAYER);
                        if (entity != player) {
                            entity.pushBack(collisionNormal);
                        }
                        isKnown.add(entity);
                    }
                }
            }

            currentWeaponRotation += 1;
        }
        notifyWeaponswing();
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

    @Override
    public void addObserver(ISoundObserver observer) {
        soundObservers.add(observer);
    }

    @Override
    public void removeObserver(ISoundObserver observer) {
        soundObservers.remove(observer);
    }

    @Override
    public void notifyWeaponswing() {
        for (ISoundObserver o : soundObservers) {
            o.playSwordHit();
        }
    }
}