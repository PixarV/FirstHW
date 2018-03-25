package OOP.SecondEx;

public enum OfficeItem {
    SCISSORS(5.77),
    CLIP(0.5),
    PEN(2.3),
    PENCIL(2.1),
    RUBBER(1.1);

    private double price; // price

    OfficeItem(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }
}
