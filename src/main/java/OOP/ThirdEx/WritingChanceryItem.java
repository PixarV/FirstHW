package OOP.ThirdEx;

public abstract class WritingChanceryItem extends ChanceryItem {
    protected int color;

    public abstract void write(String text);
    public abstract void write(String text, WritingSurfaceChanceryItem surface);
}
