package Strings.FirstEx;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CrazyLogger {
    private StringBuilder journal = new StringBuilder();
    private DateTimeFormatter formatter;

    public CrazyLogger() {
        formatter = DateTimeFormatter.ofPattern("dd-MM-YYYY : hh-mm");
        journal.append(LocalDateTime.now().format(formatter));
        journal.append(" - Initialization of journal\n");
    }

    public StringBuilder getJournal() {
        return journal;
    }

    public void addLine(String message) {
        String dateTime = LocalDateTime.now().format(formatter);
        journal.append(dateTime);
        journal.append(" - ");
        journal.append(message);
        journal.append("\n");
    }

    public void find(String message) {
        String[] lines = journal.toString().split("\n");
        for (String line : lines)
            if (line.contains(message))
                System.out.println(line);
    }

    public void find(LocalDateTime start, LocalDateTime end) {
        String[] lines = journal.toString().split("\n");

        for (String line : lines) {
            String[] log = line.split(" - ");
            LocalDateTime logTime = LocalDateTime.from(formatter.parse(log[0]));
            if (logTime.isAfter(start) && logTime.isBefore(end))
                System.out.println(line);

            if (logTime.isAfter(end)) break;
        }
    }


}
