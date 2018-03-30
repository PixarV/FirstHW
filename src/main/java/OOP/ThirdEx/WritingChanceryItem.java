package OOP.ThirdEx;

public abstract class WritingChanceryItem extends ChanceryItem {
    protected int color;

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public abstract void write(String text, WritingSurfaceChanceryItem surface);
}
