package view;

import model.monsters.Mouse;
import model.Entity;
import model.Model;
import com.badlogic.gdx.Gdx;

public class Sound{
    private final String backgroundMusicPath = "Audio/Background music.ogg";

    public void initialize(){
        com.badlogic.gdx.audio.Music backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal(backgroundMusicPath));
        backgroundMusic.setLooping(true);
       // backgroundMusic.play();
        backgroundMusic.setVolume(0.1f);
    }

    public com.badlogic.gdx.audio.Sound getSound() { //TODO generalise for everything

        com.badlogic.gdx.audio.Sound sound = Gdx.audio.newSound(Gdx.files.internal("Audio/Mouse-squeek.mp3"));
        return sound;
    }

    public void playSounds(final Model model) {
        // occasionally runs the function getSound() if its entity exists. timer starts when entity "spawns".
        for (final Entity entity : model.getEntities()) {
            Runnable toRun = new Runnable() {
                    public void run() {
                        while (model.getEntities().contains(entity)){
                            float distance = (float) Math.hypot(entity.getX()-model.getPlayer().getX(), entity.getY()-model.getPlayer().getY());
                            distance = 100/distance;
                            getSound().play(distance);
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
