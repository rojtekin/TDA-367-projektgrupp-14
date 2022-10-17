package view;

import model.Entity;
import model.Model;
import com.badlogic.gdx.Gdx;

public class Sound{
    private static final com.badlogic.gdx.audio.Music background_music = Gdx.audio.newMusic(Gdx.files.internal("Audio/Background music.ogg"));
    private static final com.badlogic.gdx.audio.Sound enemy_hit = Gdx.audio.newSound(Gdx.files.internal("Audio/enemyHit.mp3"));
    private static final com.badlogic.gdx.audio.Sound sword_swoosh = Gdx.audio.newSound(Gdx.files.internal("Audio/sword-swoosh.mp3"));
    private static final com.badlogic.gdx.audio.Sound player_death = Gdx.audio.newSound(Gdx.files.internal("Audio/player-death.mp3"));

    public void playGameMusic(){
        background_music.setLooping(true);
        background_music.play();
        background_music.setVolume(0.1f);
    }

    public void stopGameMusic(){
        background_music.dispose();
    }

    public void playEnemyHit(){
        enemy_hit.play(); // TODO: check if the sounds isnt too loud later
    }

    public  void playSwordHit(){
        sword_swoosh.play(); // TODO: check if the sounds isnt too loud later
    }

    public void playPlayerDeathSound(){
        player_death.play();
    }

    public com.badlogic.gdx.audio.Sound getIdleSound(Entity entity) {
        com.badlogic.gdx.audio.Sound sound = Gdx.audio.newSound(Gdx.files.internal("Audio/"+ entity.getClass().getSimpleName() +"-idle-noise.mp3"));
        return sound;
    }

    public void playIdleSoundsWithInterval(final Model model, long interval) {
        // occasionally runs the function getIdleSound() and plays the sound if its entity exists. timer starts when entity "spawns".
        for (final Entity entity : model.getEntities()) {
            Runnable toRun = new Runnable() {
                public void run() {
                    while (model.getEntities().contains(entity)){
                        float distance = (float) Math.hypot(entity.getX()-model.getPlayer().getX(), entity.getY()-model.getPlayer().getY());
                        distance = 300/(distance*entity.getWidth());
                        getIdleSound(entity).play(distance);
                        try {
                            Thread.sleep(interval);
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
