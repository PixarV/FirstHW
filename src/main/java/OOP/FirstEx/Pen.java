package OOP.FirstEx;


import java.util.Objects;

public class Pen {
    public enum PenType {
        BALL_PEN,
        GEL_PEN,
        FOUNTAIN_PEN,
        DEFAULT // unknown writing object
    }
    private PenType penType;
    private int color;

    public Pen(PenType penType, int color) {
        this.penType = penType;
        this.color = color;
    }

    public Pen(int color) {
        this(PenType.DEFAULT, color);
    }

    public PenType getPenType() {
        return penType;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void write(String text) {

    }

    public void writeLine(int lineLength) {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pen pen = (Pen) o;
        return color == pen.color &&
                penType == pen.penType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(penType, color);
    }

    @Override
    public String toString() {
        return "Pen{" +
                "penType=" + penType +
                ", color=" + color +
                '}';
    }
}
