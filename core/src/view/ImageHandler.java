package view;

import model.Direction;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.HashMap;

public class ImageHandler {
    private HashMap<String, TextureRegion[][]> allEntityImages = new HashMap<>();

    /**
     * Loads a sprite sheet for the specified entity, splits it into multiple images
     * and returns a 2D array which contains them.
     * @param entityName the name of the entity
     * @return a 2D array with texture regions of the sprite sheet of the specified entity
     */
    private TextureRegion[][] getEntityImages(String entityName) {
        Texture spriteSheet = new Texture(Gdx.files.internal("entities/" + entityName + "SpriteSheet.png"));
        int nColumnsPlayerWalkSheet = 4;
        int nRowsPlayerWalkSheet = 4;
        // Splits the sprite sheet into multiple images
        return TextureRegion.split(spriteSheet,
                spriteSheet.getWidth() / nColumnsPlayerWalkSheet,
                spriteSheet.getHeight() / nRowsPlayerWalkSheet);
    }

    /**
     * Loads the entity images and puts them in the HashMap allEntityImages.
     */
    protected void loadEntityImages() {
        allEntityImages.put("Player", getEntityImages("Player"));
        allEntityImages.put("Mouse", getEntityImages("Mouse"));
        allEntityImages.put("Cyclops", getEntityImages("Cyclops"));
    }

    /**
     * Gets the entity image based on the direction of the entity and the current frame in the walk animation.
     * @param entityName the name of the entity
     * @param currentFrame the current frame in the entity walk animation
     * @param direction the direction that the entity is facing
     * @return the texture region which matches the state of the entity
     */
    protected TextureRegion getEntityImage(String entityName, Direction direction, int currentFrame) {
        TextureRegion[][] entityImages = allEntityImages.get(entityName);
        switch(direction) {
            case UP:
                return entityImages[currentFrame][1];
            case DOWN:
                return entityImages[currentFrame][0];
            case LEFT:
                return entityImages[currentFrame][2];
            case RIGHT:
                return entityImages[currentFrame][3];
            default:
                throw new AssertionError();
        }
    }
}
