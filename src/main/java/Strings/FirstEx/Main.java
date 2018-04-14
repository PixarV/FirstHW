package Strings.FirstEx;

public class Main {
    public static void main(String... args) {
        CrazyLogger crazyLogger = new CrazyLogger();
        crazyLogger.addLine("first");
        crazyLogger.addLine("second");
        crazyLogger.addLine("first...fff");
        System.out.println(crazyLogger.getJournal());
        crazyLogger.find("firs");
    }
}
