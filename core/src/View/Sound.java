package View;

import Model.Enemies.Mouse;
import Model.Entity;
import Model.Model;
import com.badlogic.gdx.Gdx;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;


public class Sound{
    public com.badlogic.gdx.audio.Sound getSound() { //TODO generalise for everything

        com.badlogic.gdx.audio.Sound sound = Gdx.audio.newSound(Gdx.files.internal("Enemies/Mouse/mouse-squeek.mp3"));
        return sound;
    }

    public void playSounds(final Model model) {
        // occasionally runs the function getSound() if its entity exists. timer starts when entity "spawns".
        for (final Entity entity : model.getEntities()) {
            if (entity instanceof Mouse) {
                ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
                Runnable toRun = new Runnable() {
                    Entity current = entity;
                    public void run() {
                        if (model.getEntities().contains(current)){
                            getSound().play();
                        }
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                };
                ScheduledFuture<?> handle = scheduler.scheduleAtFixedRate(toRun, 1, 1, TimeUnit.SECONDS);
            }
            if (!model.getEntities().contains(entity)) {
                Thread.currentThread().stop();
            }
        }
          /*  ArrayList<com.badlogic.gdx.audio.Sound> currentSounds = entity.getSounds();
            for (com.badlogic.gdx.audio.Sound sound : currentSounds) {
                sound.play(1); // make 1 into something else so we can change it in setting later
            }*/
    }

    public void stopSounds(Model model) {
        for (Entity entity : model.getEntities()) {
            ArrayList<com.badlogic.gdx.audio.Sound> currentSounds = entity.getSounds();
            for (com.badlogic.gdx.audio.Sound sound : currentSounds)
                sound.stop();
        }
    }
}
