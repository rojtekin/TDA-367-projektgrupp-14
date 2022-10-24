package view;

import model.Direction;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import model.Entity;

import java.util.HashMap;
import java.util.Map;

public class ImageHandler {
    private final Map<Class<? extends Entity>, TextureRegion[][]> allEntityImages = new HashMap<>();

    /**
     * Loads a sprite sheet for the specified entity, splits it into multiple images
     * and returns a 2D array which contains them.
     * @param c a class that extends Entity
     * @return a 2D array with texture regions of the sprite sheet of the specified entity
     */
    private TextureRegion[][] getEntityImages(Class<? extends Entity> c) {
        Texture spriteSheet = new Texture(Gdx.files.internal("entities/" + c.getSimpleName() + "SpriteSheet.png"));
        int nColumnsSpriteSheet = 4;
        int nRowsSpriteSheet = 4;
        // Splits the sprite sheet into multiple images
        return TextureRegion.split(spriteSheet,
                spriteSheet.getWidth() / nColumnsSpriteSheet,
                spriteSheet.getHeight() / nRowsSpriteSheet);
    }

    /**
     * Loads the Texture for swordSwing
     * @return the texture for swordSing
     */
    protected Texture getSwordSwingImage(){
        Texture swordSwing = new Texture("Weapons/pixil-frame-0.png");
        return swordSwing;
    }

    /**
     * Gets images for the specified entity and puts them in the Map allEntityImages.
     * @param c a class that extends entity
     */
    private void loadEntityImages(Class<? extends Entity> c) {
        allEntityImages.put(c, getEntityImages(c));
    }

    /**
     * Returns an entity image based on the direction of the entity and the current frame in the walk animation.
     * @param c a class that extends Entity
     * @param currentFrame the current frame in the entity walk animation
     * @param direction the direction that the entity is facing
     * @return the texture region which matches the state of the entity
     */
    protected TextureRegion getEntityImage(Class<? extends Entity> c, Direction direction, int currentFrame) {
        if (!allEntityImages.containsKey(c)) {
            loadEntityImages(c);
        }
        TextureRegion[][] entityImages = allEntityImages.get(c);
        return entityImages[currentFrame][direction.ordinal()];
    }
}
