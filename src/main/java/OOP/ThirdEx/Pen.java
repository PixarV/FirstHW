package OOP.ThirdEx;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Pen extends WritingChanceryItem{
    public Pen(int color) {
        this.color = color;
    }

    /**
     * Write text on the surface
     * @param text
     * @param surface paper or carton
     */
    @Override
    public void write(String text, WritingSurfaceChanceryItem surface) {
        System.out.println(text);

        if(surface.isLined()) {
            char symbol = surface.getLineSymbol();
            writeLine((int) surface.getWidth(), "" + symbol);
        }
    }

    /**
     * Write line from symbol '-' with preset length
     * @param lineLength
     */
    private void writeLine(int lineLength, String symbol) {
        if (lineLength <= 0) return;

        String line = Stream.generate(() -> symbol)
                .limit(lineLength)
                .collect(Collectors.joining());

        System.out.println(line);
    }
}
