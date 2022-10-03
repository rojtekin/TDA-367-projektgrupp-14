package View;

import Model.Direction;
import Model.Entity;
import Model.PlayerCharacter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class ImageLoader {
    private String folder;
    private String direction;
    private String motionState;
    private Direction angleOfEntity;
    public Texture loadImage(Entity entity){
        angleOfEntity = entity.getDirection();
        if (entity.getInMotion()){
            motionState = "active";}
        else{
            motionState = "idle";}

        if (angleOfEntity == Direction.RIGHT){
            direction = "right";}
        else if (angleOfEntity == Direction.UP){
            direction = "up";}
        else if (angleOfEntity == Direction.LEFT){
            direction = "left";}
        else if (angleOfEntity == Direction.DOWN){
            direction = "down";
        }
        if (entity instanceof PlayerCharacter){
            folder = "Characters/";
        }
        else if(entity instanceof Model.Enemies.Enemy){
            folder = "Enemies/" + entity.getClass().getSimpleName() + "/";
        }

        Texture entityImage = new Texture(Gdx.files.internal(folder + entity.getEntityName() + "-"+ motionState + "-"+ direction + ".png"));

        return entityImage;
    }
}
