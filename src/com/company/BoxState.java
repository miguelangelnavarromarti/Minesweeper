package com.company;

public enum BoxState {
    INITIAL("#"),
    FLAG("?"),
    EMPTY("·"),
    HIDDEN_BOMB("#"),
    BOMB("0");

    private String value;

    private BoxState (String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
