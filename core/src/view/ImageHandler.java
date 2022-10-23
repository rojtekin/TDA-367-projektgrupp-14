package view;

import model.Direction;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import model.Entity;
import model.PlayerCharacter;
import model.monsters.Cyclops;
import model.monsters.Mouse;

import java.util.HashMap;

public class ImageHandler {
    private final HashMap<Class<? extends Entity>, TextureRegion[][]> allEntityImages = new HashMap<>();

    /**
     * Loads a sprite sheet for the specified entity, splits it into multiple images
     * and returns a 2D array which contains them.
     * @param c a class that extends Entity
     * @return a 2D array with texture regions of the sprite sheet of the specified entity
     */
    private TextureRegion[][] getEntityImages(Class<? extends Entity> c) {
        Texture spriteSheet = new Texture(Gdx.files.internal("entities/" + c.getSimpleName() + "SpriteSheet.png"));
        int nColumnsPlayerWalkSheet = 4;
        int nRowsPlayerWalkSheet = 4;
        // Splits the sprite sheet into multiple images
        return TextureRegion.split(spriteSheet,
                spriteSheet.getWidth() / nColumnsPlayerWalkSheet,
                spriteSheet.getHeight() / nRowsPlayerWalkSheet);
    }

    protected Texture getSwordSwingImage(){
        Texture swordSwing = new Texture("Weapons/pixil-frame-0.png");
        return swordSwing;
    }

    /**
     * Loads the entity images and puts them in the HashMap allEntityImages.
     */
    protected void loadEntityImages() {
        allEntityImages.put(PlayerCharacter.class, getEntityImages(PlayerCharacter.class));
        allEntityImages.put(Mouse.class, getEntityImages(Mouse.class));
        allEntityImages.put(Cyclops.class, getEntityImages(Cyclops.class));
    }

    /**
     * Gets the entity image based on the direction of the entity and the current frame in the walk animation.
     * @param c a class that extends Entity
     * @param currentFrame the current frame in the entity walk animation
     * @param direction the direction that the entity is facing
     * @return the texture region which matches the state of the entity
     */
    protected TextureRegion getEntityImage(Class<? extends Entity> c, Direction direction, int currentFrame) {
        TextureRegion[][] entityImages = allEntityImages.get(c);
        return switch (direction) {
            case UP -> entityImages[currentFrame][1];
            case DOWN -> entityImages[currentFrame][0];
            case LEFT -> entityImages[currentFrame][2];
            case RIGHT -> entityImages[currentFrame][3];
        };
    }
}
