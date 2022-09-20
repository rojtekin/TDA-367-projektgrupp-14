package com.mygdx.game.Model;

public interface IModel {

    public int getPlayerX();

    public int getPlayerY();

    public void initialize();

    public void movePlayerLeft();

    public void movePlayerRight();

    public void movePlayerUp();

    public void movePlayerDown();
}
