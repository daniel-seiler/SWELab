package iwwwdnw.spielzug.impl;

public enum Colour {
    RED,
    YELLOW,
    GREEN,
    BLUE,
    CYAN,
    BLACK;


    public static Colour of(int index) {
        switch (index) {
            case 0:
                return RED;
            case 1:
                return YELLOW;
            case 2:
                return GREEN;
            case 3:
                return BLUE;
            case 4:
                return CYAN;
            case 5:
                return BLACK;
        }
        return null;
    }
}
