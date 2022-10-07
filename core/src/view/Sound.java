package view;

import model.enemies.Mouse;
import model.Entity;
import model.Model;
import com.badlogic.gdx.Gdx;

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
            if (entity instanceof Mouse) { // temporary bcs every entity plays same sound and it hurts my soul
                Runnable toRun = new Runnable() {
                    public void run() { //TODO: gör while loop implementation
                        while (model.getEntities().contains(entity)){
                            getSound().play();
                            try {
                                Thread.sleep(2000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                };
                new Thread(toRun).start();
            }
        }
    }
}
