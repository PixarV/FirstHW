package OOP.SecondEx;

import java.util.*;

public class Workplace {
    private EnumMap<OfficeItem, Integer> table;
    private String nickname;

    /**
     * Create empty workplace which is assigned to the "nickname"
     * @param nickname employee's nickname
     */
    Workplace(String nickname) {
        this.nickname = nickname;
        table = new EnumMap<OfficeItem, Integer>(OfficeItem.class);
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public EnumMap<OfficeItem, Integer> getTable() {
        return table.clone();
    }

    /**
     * Add set of office items to the workplace
     * @param items set
     */
    public void addItems(OfficeItem... items) {
        for (OfficeItem item : items) {
            int count = table.containsKey(item) ? table.get(item) + 1 : 1;
            table.put(item, count);
        }
    }

    /**
     * Delete set of office items from the workplace
     * @param items set
     */
    public void deleteItems(OfficeItem... items) {
        for (OfficeItem item : items) {
            if (table.containsKey(item)) {
                int count = table.get(item) > 0 ? table.get(item) - 1 : 0;
                table.put(item, count);
            }
        }
    }

    /**
     * Calculate summary cost for workplace
     * @return cost
     */
    public double countCost() {
        double cost = 0;
        for (Map.Entry<OfficeItem, Integer> item : table.entrySet()) {
            double price = item.getKey().getPrice();
            int count = item.getValue();
            cost += price*count;
        }

        return cost;
    }

    /**
     * Demonstrates table's content
     */
    public void show() {
        for (Map.Entry<OfficeItem, Integer> item : table.entrySet())
            System.out.printf("%s: %d\n", item.getKey(), item.getValue());
    }

    public static void main(String[] args) {
        Workplace work = new Workplace("ritt");

        work.addItems(OfficeItem.CLIP,
                OfficeItem.PENCIL,
                OfficeItem.PENCIL,
                OfficeItem.SCISSORS);
    }
}
