package view;

import model.Entity;
import model.Model;
import com.badlogic.gdx.Gdx;

import java.util.ArrayList;
import java.util.List;

public class Sound{
    private static final com.badlogic.gdx.audio.Music BACKGROUND_MUSIC = Gdx.audio.newMusic(Gdx.files.internal("Audio/Background music.ogg"));
    private static final com.badlogic.gdx.audio.Sound ENEMY_HIT = Gdx.audio.newSound(Gdx.files.internal("Audio/enemyHit.mp3"));
    private static final com.badlogic.gdx.audio.Sound SWORD_SWOOSH = Gdx.audio.newSound(Gdx.files.internal("Audio/sword-swoosh.mp3"));
    private static final com.badlogic.gdx.audio.Sound PLAYER_DEATH = Gdx.audio.newSound(Gdx.files.internal("Audio/player-death.mp3"));

    /**
     * Plays the background music of the game, constantly looping.
     */
    public void playGameMusic(){
        BACKGROUND_MUSIC.setLooping(true);
        BACKGROUND_MUSIC.play();
        BACKGROUND_MUSIC.setVolume(0.1f);
    }

    /**
     * Stops playing the background music.
     */
    public void stopGameMusic(){
        BACKGROUND_MUSIC.dispose();
    }

    /**
     * Plays the sound associated with the enemy hitting something/someone.
     */
    public void playEnemyHit(){
        ENEMY_HIT.play(); // TODO: check if the sounds isnt too loud later
    }

    /**
     * Plays the sound of the sword swing.
     */
    public  void playSwordHit(){
        SWORD_SWOOSH.play(); // TODO: check if the sounds isnt too loud later
    }

    /**
     * Plays the player death sound.
     */
    public void playPlayerDeathSound(){
        PLAYER_DEATH.play();
    }

    public com.badlogic.gdx.audio.Sound getIdleSound(Entity entity) {
        com.badlogic.gdx.audio.Sound sound = Gdx.audio.newSound(Gdx.files.internal("Audio/"+ entity.getClass().getSimpleName() +"-idle-noise.mp3"));
        return sound;
    }

    /**
     * Continuously plays the idle-sounds of every living entity in the model.
     * @param model a reference to the model the sounds are associated with.
     * @param interval how long between each play of the idle sounds.
     */
    public void playIdleSoundsWithInterval(final Model model, long interval) {
        List<Class> entityClass = new ArrayList<>();
        for (final Entity entity : model.getEntities()) {
            if (!entityClass.contains(entity.getClass())) {
                entityClass.add(entity.getClass());
                Runnable toRun = new Runnable() {
                    public void run() {
                        com.badlogic.gdx.audio.Sound sound = getIdleSound(entity);
                        while (model.getEntities().contains(entity)) {
                            float distance = (float) Math.hypot(entity.getX() - model.getPlayer().getX(), entity.getY() - model.getPlayer().getY());
                            distance = 300 / (distance * entity.getWidth());
                            float remove_distance = distance * 0;
                            if (!model.isPaused()) {
                                sound.play(distance);
                            }
                            else  {
                                sound.play(remove_distance);
                            }
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

}
