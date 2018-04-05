package OOP.FourthEx;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class Item {
    private static ArrayList<Item> items = new ArrayList<>();

    private String name;
    private double price;

    public Item(String name, double price) {
        this.name = name;
        this.price = price;
        items.add(this);
    }

    public static class SortByPrice implements Comparator<Item> {
        @Override
        public int compare(Item o1, Item o2) {
            if (o1.price == o2.price) return 0;
            return (o1.price >= o2.price) ? 1 : -1;
        }
    }

    public static void sort(Comparator<Item> comparator) {
        items.sort(comparator);
        items.forEach(System.out::println);
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public static void main(String[] args) {
        String[] stupidRandomNames = {"gg", "js", "12m3", "dd4", "gg"};

        for (String name : stupidRandomNames)
            new Item(name, new Random().nextDouble()*100);

        System.out.println("First sort:");
        sort(new SortByPrice());

        System.out.println("Second sort:");
        sort(Comparator.comparing(Item::getName));

        System.out.println("Third sort:");
        sort((o1, o2) -> {
            int mainCompare = o1.getName().compareTo(o2.getName());
            if (mainCompare == 0)
                return new SortByPrice().compare(o1, o2);
            return mainCompare;
        });

    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
