package com.mygdx.game.Model;

public class Model implements IModel {
    private Player player;
    private static final int SCREEN_WIDTH = 800;
    private static final int SCREEN_HEIGHT = 480;

    @Override
    public void initialize() {
        player = new Player(SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2, 32, 32, 5);
    }

    @Override
    public void movePlayerLeft() {
        player.setX(player.getX() - player.getSpeed());
        // Player cannot go off-screen
        if(player.getX() < 0) { player.setX(0); }
    }

    @Override
    public void movePlayerRight() {
        player.setX(player.getX() + player.getSpeed());
        // Player cannot go off-screen
        if(player.getX() > SCREEN_WIDTH - player.getWidth()) player.setX(SCREEN_WIDTH - player.getWidth());
    }

    @Override
    public void movePlayerUp() {
        player.setY(player.getY() + player.getSpeed());
        // Player cannot go off-screen
        if(player.getY() + player.getHeight() > SCREEN_HEIGHT) player.setY(SCREEN_HEIGHT - player.getHeight());
    }

    @Override
    public void movePlayerDown() {
        player.setY(player.getY() - player.getSpeed());
        // Player cannot go off-screen
        if(player.getY() < 0) { player.setY(0); }
    }

    @Override
    public int getPlayerX() {
        return player.getX();
    }

    @Override
    public int getPlayerY() {
        return player.getY();
    }
}
