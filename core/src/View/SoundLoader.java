package View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class SoundLoader {
    public Sound loadSound(){ //TODO generalise for everything

        Sound sound = Gdx.audio.newSound(Gdx.files.internal("Enemies/Mouse/mouse-squeek.mp3"));
        return sound;
    }
}
