package model;

import com.dongbat.jbump.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

public abstract class PlayerWeapon {

    private float weaponSpeed;
    private float weaponDamage;
    private float weaponRange;
    private float weaponWidth;
    private float weaponAngle;
    private int weaponRotations;
    private World<Entity> world;
    private CollisionFilter filter;
    private Set<Entity> isKnown = new HashSet<Entity>();

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
    public void weaponSwing(int rotationStart,int rotationFinish, int animationpart, PlayerCharacter player){
        int degreedistance = Math.abs(rotationStart - rotationFinish);
        int degreerotation = degreedistance/weaponRotations;
        while (animationpart <= weaponRotations) {
            setWeaponAngle(rotationStart*(degreerotation*animationpart)); //there will be a listener who prints out the sword when the angle changes
            float point1 = getWeaponRange();
            float rotatedpoint1x = (float) (point1 * cos(getWeaponAngle()));
            float rotatedpoint1y = (float) (point1 * sin(getWeaponAngle()));
            rotatedpoint1x = rotatedpoint1x + player.getX();
            rotatedpoint1y = rotatedpoint1y + player.getY();

            //world.querySegmentWithCoords(player.getX(), player.getY(), rotatedpoint1x, rotatedpoint1y, filter, null); //filter är cross, fixa i jbump.
            ArrayList<Entity> entities = world.querySegmentWithCoords(player.getX(), player.getY(), rotatedpoint1x, rotatedpoint1y, filter, null);
            Set<Entity> seen = new HashSet<Entity>();
            for (Entity entity : entities){
                if (!isKnown.contains((entity))){
                    //gör skada;
                }
                seen.add(entity);
            }
            isKnown = seen;
            //lista entities? add if not already there world.querySegment(x1, y1, x2, y2, filter, items);
            //lista skada alla i listan
            //lista knuffa alla i listan
            animationpart += 1;
        }
    }
    public float getWeaponAngle() {
        return weaponAngle;
    }

    private void setWeaponAngle(float weaponAngle) {
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