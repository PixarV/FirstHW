package IO.ThirdEx;

import IO.FirstEx.KeywordsByte;

import java.io.*;

public class EncodingProblem {
    public static void changeCharsetToFile(String filename) {
        String text;
        try (InputStream input = new FileInputStream(filename)) {
            text = new String(input.readAllBytes());
        } catch (IOException e) {
            throw new RuntimeException("Smh wrong with " + filename + " file", e);
        }

        try (OutputStreamWriter output = new OutputStreamWriter(
                new FileOutputStream(filename), "UTF-16")) {
            output.write(text, 0, text.length());
        } catch (IOException e) {
            throw new RuntimeException("Smh wrong with " + filename + " file", e);
        }
    }

    public static void main(String[] args) {
        changeCharsetToFile("textAboutUnicode");
    }
}
