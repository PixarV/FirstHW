package OOP.FifthEx;

public enum Discipline {
    MATH(false),
    ART(true),
    DATABASES(false),
    ENGLISH(true);

    private boolean isInteger;

    private Discipline(boolean isInteger) {
        this.isInteger = isInteger;
    }

    public boolean isInteger() {
        return isInteger;
    }
}
