package Strings.ThirdEx;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class HtmlWithRegExp {
    public static void main(String[] args) {
//        Pattern img = Pattern.compile("<img");
//        Matcher m = img.
        ClassLoader classLoader = HtmlWithRegExp.class.getClassLoader();
        try (BufferedReader br =
                     new BufferedReader(
                             new FileReader(classLoader.getResource("text.html").getFile()))){

            ArrayList<String> lines = br.lines()
                    .collect(Collectors.toCollection(ArrayList::new));

            ArrayList<String> images = lines.stream()
                    .filter(line -> Pattern.matches(".*<img .*src=\".+\\.(jpg|png|gif).*", line))
                    .collect(Collectors.toCollection(ArrayList::new));

            int i = 0;
            for (String img : images) {

                if (!Pattern.matches(".*pic" + i + "\\..*",
                        img.replace("pic_home", "pic0"))) {
                    System.out.println("Pics aren't sequentially");
                    break;
                }
                i++;
            }

            Pattern rusImg = Pattern.compile(".*(рис\\.|рисун).*", Pattern.CASE_INSENSITIVE);
            lines.stream()
                    .filter(line -> rusImg.matcher(line).matches())
                    .forEach(System.out::println);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
