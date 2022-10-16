package model;

import com.dongbat.jbump.World;

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

    public PlayerWeapon(World<Entity> world){
        this.weaponDamage = weaponDamage;
        this.weaponRange = weaponRange;
        this.weaponWidth = weaponWidth;
        this.weaponSpeed = weaponSpeed;
        this.weaponAngle = weaponAngle;
        this.weaponRotations = weaponRotations;
        this.world = world;
    }

    //TODO
    //in playercharachter the swords position shall update with the move command like player does
    public void weaponSwing(int rotationStart,int rotationFinish, int animationpart, PlayerCharacter player){
        int degreedistance = Math.abs(rotationStart - rotationFinish);
        int degreerotation = degreedistance/weaponRotations;
        //lista entities
        if(animationpart <= weaponRotations) {
            setWeaponAngle(rotationStart*(degreerotation*animationpart));
            float point1 = getWeaponRange();
            float rotatedpoint1x = (float) (point1 * cos(getWeaponAngle()));
            float rotatedpoint1y = (float) (point1 * sin(getWeaponAngle()));
            rotatedpoint1x = rotatedpoint1x + player.getX();
            rotatedpoint1y = rotatedpoint1y + player.getY();

            //world.querySegmentWithCoords(player.getX(), player.getY(), rotatedpoint1x, rotatedpoint1y, ); //filter är cross, fixa i jbump.
            //lista entities? add if not already there world.querySegment(x1, y1, x2, y2, filter, items);
            //lista skada alla i listan
            //lista knuffa alla i listan
            weaponSwing(rotationStart, rotationFinish,animationpart,player);
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
}