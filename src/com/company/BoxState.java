package com.company;

public enum BoxState {
    INITIAL("#"),
    FLAG("?"),
    EMPTY("0"),
    HIDDEN_BOMB("#"),
    BOMB("X");

    private String value;

    private BoxState (String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
