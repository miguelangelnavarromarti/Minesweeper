package com.company;

public class Box {

    private int rowPosition;
    private int columnPosition;
    private BoxState state;

    public Box (int rowPosition, int columnPosition) {
        this.rowPosition = rowPosition;
        this.columnPosition = columnPosition;
        this.state = BoxState.INITIAL;
    }

    public String getState() {
        return state.getValue();
    }

    public void setState(BoxState state) {
        this.state = state;
    }

}
