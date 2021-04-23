package src.com.company;

import src.com.company.Color;

public enum BoxRepresentation {
    COVERED("[ " + Color.WHITE + "#" + Color.RESET + " ]"),
    FLAG("[ " + Color.YELLOW + "B" + Color.RESET + " ]"),
    BOMB("[ " + Color.RED + "X" + Color.RESET + " ]"),
    ZERO("[ " + Color.CYAN + "0" + Color.RESET + " ]"),
    ONE("[ " + Color.CYAN + "1" + Color.RESET + " ]"),
    TWO("[ " + Color.CYAN + "2" + Color.RESET + " ]"),
    THREE("[ " + Color.BLUE + "3" + Color.RESET + " ]"),
    FOUR("[ " + Color.BLUE + "4" + Color.RESET + " ]"),
    FIVE("[ " + Color.BLUE + "5" + Color.RESET + " ]"),
    SIX("[ " + Color.BLUE + "6" + Color.RESET + " ]"),
    SEVEN("[ " + Color.BLUE + "7" + Color.RESET + " ]"),
    EIGHT("[ " + Color.BLUE + "8" + Color.RESET + " ]");

    private String value;

    private BoxRepresentation(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
