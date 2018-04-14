import java.io.File;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class IOOperations {
    private IOOperations(){};

    static ArrayList<String> read(String filename) {
        ArrayList<String> lines = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File(filename))){
            while (scanner.hasNextLine())
                lines.add(scanner.nextLine());
        } catch (FileNotFoundException e) {
            System.out.printf("Error. %s does't exist.\n", filename);
        }
        return lines;
    }

    static void write(String filename, ArrayList<String> lines) {
        try (FileOutputStream outputStream = new FileOutputStream(filename, true)) {
            for(String line : lines)
                outputStream.write(line.getBytes());
        } catch (IOException e) {
            System.out.printf("Error. Don't use %s, please!.\n", filename);
        }
    }

    static boolean cleanFile(String filename) {
        try (FileOutputStream outputStream = new FileOutputStream(filename)) {
            outputStream.write("".getBytes());
        } catch (IOException e) {
            System.out.printf("Error. Don't use %s, please!.\n", filename);
            return false;
        }
        return true;
    }
}
