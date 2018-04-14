package IO.FirstEx;

import java.io.*;
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
            throw new RuntimeException("Smh wrong with keywords file", e);
        } catch (NullPointerException e) {
            throw new RuntimeException("keywords doesn't exist in resources.", e);
        }
    }

    private static void createAndCleanMap() {
        for (String keyword : keywords)
            dict.put(keyword.trim(), 0);
    }

    public static void getKeywordsInfoForFile(String filename) {
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
            throw new RuntimeException("Smh wrong with " + filename + " file", e);
        } catch (NullPointerException e) {
            throw new RuntimeException(filename + " doesn't exist in resources.", e);
        }

        addKeywordsInfo();
    }

    private static void addKeywordsInfo() {
        try(OutputStream output = new FileOutputStream("answer")) {
            byte[] bytes = getStringFromMap().getBytes();
            output.write(bytes);
            output.flush();
        } catch (IOException e) {
            throw new RuntimeException("Smh wrong with answer file", e);
        }
    }

    private static String getStringFromMap() {
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
        KeywordsByte.getKeywordsInfoForFile("ConsoleApplication.java");
    }

}
