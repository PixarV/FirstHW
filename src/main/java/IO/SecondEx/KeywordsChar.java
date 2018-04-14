package IO.SecondEx;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import IO.FirstEx.KeywordsByte;

public class KeywordsChar {
    private static HashMap<String, Integer> dict = new HashMap<>();

    static {
        getKeywords();
    }

    private static void getKeywords() {
        try (BufferedReader reader = new BufferedReader(
                new FileReader(KeywordsChar.class.getResource("/io/keywords").getFile()))) {

            reader.lines().flatMap(line -> Arrays.stream(line.split(" ")))
                    .forEach(keyword -> dict.put(keyword.trim(), 0));

        } catch (IOException e) {
            throw new RuntimeException("Smh wrong with keywords file", e);
        } catch (NullPointerException e) {
            throw new RuntimeException("keywords doesn't exist in resources.", e);
        }
    }


    public static void getKeywordsInfoForFile(String filename) {
        KeywordsByte.createAndCleanMap();
        try (BufferedReader reader = new BufferedReader(
                new FileReader(KeywordsChar.class.getResource("/io/"+filename).getFile()))) {

            reader.lines().map(line -> line.replaceAll("[;{}:\t]",""))
                    .flatMap(line -> Arrays.stream(line.split(" ")))
                    .filter(word -> word.length() > 0 && dict.containsKey(word.trim()))
                    .forEach(word -> dict.put(word.trim(), dict.get(word.trim()) + 1));

        } catch (IOException e) {
            throw new RuntimeException("Smh wrong with " + filename + " file", e);
        } catch (NullPointerException e) {
            throw new RuntimeException(filename + " doesn't exist in resources.", e);
        }

        addKeywordsInfo();
    }

    private static void addKeywordsInfo() {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("answerChar"))) {
            writer.write(getStringFromMap());
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException("Smh wrong with answerChar file", e);
        }
    }

    public static String getStringFromMap() {
        StringBuilder temp = new StringBuilder();
        dict.forEach((key, value) -> {
            temp.append(key);
            temp.append(" ");
            temp.append(value);
            temp.append("\n");
        });
        return temp.toString();
    }


    public static void main(String[] args) {
        KeywordsChar.getKeywordsInfoForFile("ConsoleApplication.java");
    }

}

