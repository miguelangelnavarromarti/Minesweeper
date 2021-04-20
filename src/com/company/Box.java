package com.company;

public class Box {

    private BoxRepresentation state;
    private boolean covered;
    private boolean flag;
    private boolean bomb;
    private int bombsAround;

    //CONSTRUCTOR

    public Box () {
        this.state = BoxRepresentation.COVERED;
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
        putRepresentation();
    }

    public void putFlag(boolean flag) {
        this.flag = flag;
        putRepresentation();
    }

    public void putBomb(boolean bomb) {
        this.bomb = bomb;
        putRepresentation();
    }

    public void setBombsAround(int bombsAround) {
        this.bombsAround = bombsAround;
        putRepresentation();
    }

    private void putRepresentation() {
        if (isCovered()) {
            if (hasFlag()) {
                setState(BoxRepresentation.FLAG);
            } else {
                setState(BoxRepresentation.COVERED);
            }
        } else {
            if (hasBomb()) {
                setState(BoxRepresentation.BOMB);
            } else {
                switch (getBombsAround()) {
                    case 0 :
                        setState(BoxRepresentation.ZERO);
                        break;
                    case 1 :
                        setState(BoxRepresentation.ONE);
                        break;
                    case 2 :
                        setState(BoxRepresentation.TWO);
                        break;
                    case 3 :
                        setState(BoxRepresentation.THREE);
                        break;
                    case 4 :
                        setState(BoxRepresentation.FOUR);
                        break;
                    case 5 :
                        setState(BoxRepresentation.FIVE);
                        break;
                    case 6 :
                        setState(BoxRepresentation.SIX);
                        break;
                    case 7 :
                        setState(BoxRepresentation.SEVEN);
                        break;
                    case 8 :
                        setState(BoxRepresentation.EIGHT);
                        break;
                }
            }
        }
    }
}

