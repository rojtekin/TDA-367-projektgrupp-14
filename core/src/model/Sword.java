package model;

public class Sword extends PlayerWeapon{
    private float weaponSpeed;
    private float weaponDamage;
    private float weaponRange;
    private float weaponWidth;
    private float weaponAngle;

    public Sword(){
        this.weaponSpeed = 1;
        this.weaponDamage = 10;
        this.weaponRange = 30;
        this.weaponWidth = 12;
    }
}
