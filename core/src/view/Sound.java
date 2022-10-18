package view;

import model.Entity;
import model.Model;
import com.badlogic.gdx.Gdx;

public class Sound{
    private static final com.badlogic.gdx.audio.Music BACKGROUND_MUSIC = Gdx.audio.newMusic(Gdx.files.internal("Audio/Background music.ogg"));
    private static final com.badlogic.gdx.audio.Sound ENEMY_HIT = Gdx.audio.newSound(Gdx.files.internal("Audio/enemyHit.mp3"));
    private static final com.badlogic.gdx.audio.Sound SWORD_SWOOSH = Gdx.audio.newSound(Gdx.files.internal("Audio/sword-swoosh.mp3"));
    private static final com.badlogic.gdx.audio.Sound PLAYER_DEATH = Gdx.audio.newSound(Gdx.files.internal("Audio/player-death.mp3"));

    public void playGameMusic(){
        BACKGROUND_MUSIC.setLooping(true);
        BACKGROUND_MUSIC.play();
        BACKGROUND_MUSIC.setVolume(0.1f);
    }

    public void stopGameMusic(){
        BACKGROUND_MUSIC.dispose();
    }

    public void playEnemyHit(){
        ENEMY_HIT.play(); // TODO: check if the sounds isnt too loud later
    }

    public  void playSwordHit(){
        SWORD_SWOOSH.play(); // TODO: check if the sounds isnt too loud later
    }

    public void playPlayerDeathSound(){
        PLAYER_DEATH.play();
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
