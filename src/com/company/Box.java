package com.company;

public class Box {

    private BoxState state;

    public Box () {
        this.state = BoxState.INITIAL;
    }

    public String getState() {
        return state.getValue();
    }

    public void setState(BoxState state) {
        this.state = state;
    }

}
