package View;

import Model.Enemy;
import Model.Entity;
import Model.PlayerCharacter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class ImageLoader {
    private String folder;
    private String direction;
    private String motionState;
    private int angleOfEntity;
    public Texture loadImage(Entity entity){
        angleOfEntity = entity.getAngle();
        if (entity.getInMotion()){
            motionState = "active";}
        else{
            motionState = "idle";}

        if (angleOfEntity == 0){
            direction = "right";}
        else if (angleOfEntity == 90){
            direction = "up";}
        else if (angleOfEntity == 180){
            direction = "left";}
        else if (angleOfEntity == 270){
            direction = "down";
        }

        if (entity instanceof PlayerCharacter){
            folder = "Characters/";
        }
        else if(entity instanceof Enemy){
            folder = "Enemies/" + entity.getClass().getSimpleName() + "/";
        }

        Texture entityImage = new Texture(Gdx.files.internal(folder + entity.getEntityName() + "-"+ motionState + "-"+ direction + ".png"));

        return entityImage;
    }
}
