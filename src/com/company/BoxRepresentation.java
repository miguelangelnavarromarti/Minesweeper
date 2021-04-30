package com.company;

public enum BoxRepresentation {
    COVERED("[ " + Color.WHITE + "#" + Color.RESET + " ]"),
    FLAG("[ " + Color.YELLOW + "B" + Color.RESET + " ]"),
    BOMB("[ " + Color.RED + "X" + Color.RESET + " ]"),
    ZERO("[ " + Color.CYAN + "0" + Color.RESET + " ]"),
    ONE("[ " + Color.CYAN + "1" + Color.RESET + " ]"),
    TWO("[ " + Color.BLUE + "2" + Color.RESET + " ]"),
    THREE("[ " + Color.PURPLE + "3" + Color.RESET + " ]"),
    FOUR("[ " + Color.PURPLE + "4" + Color.RESET + " ]"),
    FIVE("[ " + Color.PURPLE + "5" + Color.RESET + " ]"),
    SIX("[ " + Color.PURPLE + "6" + Color.RESET + " ]"),
    SEVEN("[ " + Color.PURPLE + "7" + Color.RESET + " ]"),
    EIGHT("[ " + Color.PURPLE + "8" + Color.RESET + " ]");

    private String value;

    private BoxRepresentation(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
