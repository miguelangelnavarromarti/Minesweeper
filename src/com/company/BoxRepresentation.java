package com.company;

public enum BoxRepresentation {
    INITIAL("#"),
    FLAG("?"),
    EMPTY("0"),
    HIDDEN_BOMB("#"),
    BOMB("X");

    private String value;

    private BoxRepresentation(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
