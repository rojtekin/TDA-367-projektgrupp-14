package com.mygdx.game;

public class Model {
    private Player player;
    private static final int SCREEN_WIDTH = 800;
    private static final int SCREEN_HEIGHT = 480;

    public Model(Player player) {
        this.player = player;
    }

    public void movePlayerLeft() {
        player.setX(player.getX() - player.getSpeed());
        // Player cannot go off-screen
        if(player.getX() < 0) { player.setX(0); }
    }

    public void movePlayerRight() {
        player.setX(player.getX() + player.getSpeed());
        // Player cannot go off-screen
        if(player.getX() > SCREEN_WIDTH - player.getWidth()) player.setX(SCREEN_WIDTH - player.getWidth());
    }

    public void movePlayerUp() {
        player.setY(player.getY() + player.getSpeed());
        // Player cannot go off-screen
        if(player.getY() + player.getHeight() > SCREEN_HEIGHT) player.setY(SCREEN_HEIGHT - player.getHeight());
    }

    public void movePlayerDown() {
        player.setY(player.getY() - player.getSpeed());
        // Player cannot go off-screen
        if(player.getY() < 0) { player.setY(0); }
    }

    public int getPlayerX() {
        return player.getX();
    }

    public int getPlayerY() {
        return player.getY();
    }
}
