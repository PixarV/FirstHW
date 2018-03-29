package OOP.ThirdEx;

public class Paper extends WritingSurfaceChanceryItem {
    private boolean crumple;

    /**
     * Create empty colored paper
     * @param color
     * @param width
     * @param height
     */
    public Paper(int color, double width, double height) {
        super(color, width, height);
    }

    /**
     * Create empty white paper
     * @param width
     * @param height
     */
    public Paper(double width, double height) {
        this(0xFFFFFF, width, height);
    }


    public void setLined(boolean lined) {
        this.lined = lined;
    }

    public boolean isCrumple() {
        return crumple;
    }

    /**
     * Draw lines on paper
     * @param numLines number of lines
     */
    @Override
    public void makeLined(int numLines) {
        lined = true;
        this.numLines = numLines;
    }

    /**
     * Create a "ball" from paper
     */
    public void makeCrumple() {
        numLines = 0;
        crumple = true;
    }

    /**
     * Try to smooth out a paper
     */
    public void expand() {
        if (crumple) {
            crumple = false;
            int num = (int) getHeight();
            makeLined(num);
        }
    }
}
