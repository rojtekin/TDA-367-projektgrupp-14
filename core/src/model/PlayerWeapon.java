package model;

public abstract class PlayerWeapon {

    private float weaponSpeed;
    private float weaponDamage;
    private float weaponRange;
    private float weaponWidth;
    private float weaponAngle;
    private int weaponRotations;

    public PlayerWeapon(){
        this.weaponDamage = weaponDamage;
        this.weaponRange = weaponRange;
        this.weaponWidth = weaponWidth;
        this.weaponSpeed = weaponSpeed;
        this.weaponAngle = weaponAngle;
        this.weaponRotations = weaponRotations;
        //weaponpositionx =
        //weaponpositiony =
    }

    //TODO
    //in playercharachter the swords position shall update with the move command like player does
    public void weaponSwing(int rotationStart,int rotationFinish, int animationpart){
        int degreedistance = Math.abs(rotationStart - rotationFinish);
        int degreerotation = degreedistance/weaponRotations;
        //lista entities
        if(animationpart <= weaponRotations) {
            //p1 = getplayerposition and degreerotation*animationpart*trigonometrisaker
            //p2 = x1 + weaponwidth, y1 + weaponheight and things from over
            //lista entities? add if not already there world.querySegment(x1, y1, x2, y2, filter, items);
            //lista skada alla i listan
            //lista knuffa alla i listan


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