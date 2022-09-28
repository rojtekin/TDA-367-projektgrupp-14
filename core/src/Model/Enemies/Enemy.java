package Model.Enemies;

import Model.Entity;
import Model.IModel;
import com.dongbat.jbump.World;

public abstract class Enemy extends Entity {
    private float damage;
    private String imagePath = ""; // hur gör man att de måste finnas en?

    public Enemy(int x, int y, int height, int width, int speed, float health, float damage, World<Entity> world) {
        super(x, y, height, width, speed, health, world);
        this.damage = damage;
    }
    protected void setImagePath(String path){
        this.imagePath = path;
    }
    public String getImagePath(){
        return this.imagePath;
    }
}
