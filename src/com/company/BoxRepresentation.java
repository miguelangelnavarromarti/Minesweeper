package com.company;

public enum BoxRepresentation {
    COVERED("#"),
    FLAG("?"),
    BOMB("X"),
    ZERO("0"),
    ONE("1"),
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8");

    private String value;

    private BoxRepresentation(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
