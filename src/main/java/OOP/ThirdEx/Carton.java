package OOP.ThirdEx;

public class Carton extends WritingSurfaceChanceryItem {
    private double density = 0.75;

    /**
     * Create empty colored carton with base density
     * @param color
     * @param width
     * @param height
     * @param density carton thickness
     */
    public Carton(int color, double width, double height, int density) {
        super(color, width, height);
        this.density = density;
    }

    /**
     * Create empty white carton with base density
     * @param width
     * @param height
     */
    public Carton(double width, double height) {
        super(0xFFFFFF, width, height);
    }

    public double getDensity() {
        return density;
    }

    public void setDensity(double density) {
        this.density = density;
    }

    /**
     * Draw lines on carton
     * @param numLines number of lines
     */
    @Override
    public void makeLined(int numLines) {
        if (density >= 1.5)
            this.numLines = numLines / 2;
        lined = (numLines > 0);
    }

}
