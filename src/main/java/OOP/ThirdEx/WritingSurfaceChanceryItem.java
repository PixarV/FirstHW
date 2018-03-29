package OOP.ThirdEx;

public abstract class WritingSurfaceChanceryItem extends ChanceryItem {
    private int color;
    private double width;
    private double height;
    private char lineSymbol;
    protected int numLines;
    protected boolean lined;

    protected WritingSurfaceChanceryItem(int color, double width, double height) {
        this.color = color;
        this.width = width;
        this.height = height;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }


    public char getLineSymbol() {
        return lineSymbol;
    }

    public void setLineSymbol(char lineSymbol) {
        this.lineSymbol = lineSymbol;
    }

    public boolean isLined() {
        return lined;
    }

    public abstract void makeLined(int numLines);
}
