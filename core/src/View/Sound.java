package View;

import Model.Entity;
import Model.Model;
import com.badlogic.gdx.Gdx;

import java.util.ArrayList;


public class Sound {
    public com.badlogic.gdx.audio.Sound getSound(){ //TODO generalise for everything

        com.badlogic.gdx.audio.Sound sound = Gdx.audio.newSound(Gdx.files.internal("Enemies/Mouse/mouse-squeek.mp3"));
        return sound;
    }

    public void playSounds(Model model){;
        for (Entity entity: model.getEntities()){
            ArrayList<com.badlogic.gdx.audio.Sound> currentSounds = entity.getSounds();
            for (com.badlogic.gdx.audio.Sound sound : currentSounds)
                sound.play(1); // make 1 into something else so we can change it in setting later
        }
    }


}
