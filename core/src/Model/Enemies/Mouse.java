package Model.Enemies;

import Model.Enemy;
import com.badlogic.gdx.Gdx;

public class Mouse extends Enemy {

    public Mouse(int x, int y, int height, int width, int speed, double health, double damage) {
        super(x, y, height, width, speed, health, damage);
        setEntityName("Mouse");
        addSound(Gdx.audio.newSound(Gdx.files.internal("Enemies/Mouse/mouse-squeek.mp3")));
        
    }

}
