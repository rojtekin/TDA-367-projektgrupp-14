package Model.Enemies;

import Model.Entity;
import Model.IModel;
import com.dongbat.jbump.World;

public abstract class Enemy extends Entity {
    private float damage;
    private String imagePath = ""; // hur gör man att de måste finnas en?

    public Enemy(int x, int y, int height, int width, int speed, float health, float damage, IModel model) {
        super(x, y, height, width, speed, health, model);
        this.damage = damage;
    }
    protected void setImagePath(String path){
        this.imagePath = path;
    }
    public String getImagePath(){
        return this.imagePath;
    }
}
