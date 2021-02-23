package com.company;

public class Box {

    private int dimensionX;
    private int dimensionY;
    private String state;

    private final String INITIAL = "·";

    public Box (int dimensionX, int dimensionY, String state) {
        this.dimensionX = dimensionX;
        this.dimensionY = dimensionY;
        this.state = INITIAL;
    }
}
