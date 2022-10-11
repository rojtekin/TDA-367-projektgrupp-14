package model;

public class PlayerWeapon {

    private float weaponDamage;
    private float weaponRange;

    private float weaponWidth;
    private float weaponSpeed;

    private float weaponAngle;

    public PlayerWeapon(float weaponDamage,float weaponSpeed, float weaponRange, float weaponWidth){
        this.weaponDamage = weaponDamage;
        this.weaponRange = weaponRange;
        this.weaponWidth = weaponWidth;
        this.weaponSpeed = weaponSpeed;
    }

    public void weaponSwing(int rotationStart,int rotationFinish, int animationpart){
        if(animationpart > 0) {

        }
        //rotationstart, kolla kollision på svärdet som går från spelarens centrum
        //x0 är alltid spelarens utgångspunktx
        //x1 är x0 + weaponwidth vid starten, kommer sedan ändras.
        //y0 är utgångspunnkty
        //y1 är y0 + weaponRange vid starten, kommer att ändras.
    }
    public float getWeaponAngle() {
        return weaponAngle;
    }

    public void setWeaponAngle(float weaponAngle) {
        this.weaponAngle = weaponAngle;
    }

    public float getWeaponRange() {
        return weaponRange;
    }

    public float getWeaponWidth() {
        return weaponWidth;
    }
}
