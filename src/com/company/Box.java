package com.company;

public class Box {

    private BoxRepresentation state;
    private boolean covered;
    private boolean flag;
    private boolean bomb;
    private int bombsAround;

    //CONSTRUCTOR

    public Box () {
        this.state = BoxRepresentation.INITIAL;
        this.covered = true;
        this.flag = false;
        this.bomb = false;
        this.bombsAround = 0;
    }

    //GETTERS

    public String getState() {
        return state.getValue();
    }

    public boolean isCovered() {
        return covered;
    }

    public boolean hasFlag() {
        return flag;
    }

    public boolean hasBomb() {
        return bomb;
    }

    public int getBombsAround() {
        return bombsAround;
    }

    //SETTERS

    public void setState(BoxRepresentation state) {
        this.state = state;
    }

    public void cover(boolean covered) {
        this.covered = covered;
    }

    public void putFlag(boolean flag) {
        this.flag = flag;
    }

    public void putBomb(boolean bomb) {
        this.bomb = bomb;
    }

    public void setBombsAround(int bombsAround) {
        this.bombsAround = bombsAround;
    }
}
