package IO.FirstEx;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

public class KeywordsByte {
    private static String[] keywords;
    private static HashMap<String, Integer> dict = new HashMap<>();

    static {
        getKeywords();
        createAndCleanMap();
    }

    private static void getKeywords() {
        try (InputStream keywordsStream =
                     KeywordsByte.class.getResourceAsStream("/io/keywords")) {
            keywords = new String(keywordsStream.readAllBytes()).split("[ \n]");
        } catch (IOException e) {
            throw new RuntimeException("Smth wrong with keywords file", e);
        }
    }

    private static void createAndCleanMap() {
        for (String keyword : keywords)
            dict.put(keyword.trim(), 0);
    }

    public static HashMap<String, Integer> getKeywordsInfoForFile(String filename) {
        createAndCleanMap();
        try (InputStream input =
                     KeywordsByte.class.getResourceAsStream("/io/"+filename)) {
            byte[] file = input.readAllBytes();
            String[] text = new String(file).replaceAll("[;{}:\t]","").split("[ \n]");

            for (String word : text) {
                if (word.length() == 0)
                    continue;

                if (dict.containsKey(word.trim())) {
                    int temp = dict.get(word);
                    dict.put(word, temp + 1);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Smth wrong with " + filename + " file", e);
        }
        return dict;
    }

    public static void main(String[] args) {
        HashMap<String, Integer> mass = KeywordsByte.getKeywordsInfoForFile("ConsoleApplication.java");
        mass.forEach((key, value) -> System.out.printf("%s %d\n", key, value));
    }
}
