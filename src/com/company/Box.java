package com.company;

public class Box {

    private int rowPosition;
    private int columnPosition;
    private String state;

    private final String INITIAL = "#";
    private final String FLAG = "?";
    private final String NOTHING = "·";
    private final String HIDDEN_BOMB = "#";
    private final String BOMB = "0";

    public Box (int rowPosition, int columnPosition) {
        this.rowPosition = rowPosition;
        this.columnPosition = columnPosition;
        this.state = INITIAL;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

}
