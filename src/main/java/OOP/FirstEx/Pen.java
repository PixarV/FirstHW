package OOP.FirstEx;

import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        writeLine(10);
        System.out.println(text);
        writeLine(10);
    }

    public void writeLine(int lineLength) {
        if (lineLength <= 0) return;

        String line = Stream.generate(() -> "-")
                .limit(lineLength)
                .collect(Collectors.joining());

        System.out.println(line);
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

    public static void main(String... args) {
        Pen pen = new Pen(PenType.GEL_PEN, 100);
        pen.write("text");
    }
}
