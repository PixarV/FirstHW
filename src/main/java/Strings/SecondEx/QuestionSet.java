package Strings.SecondEx;

import java.util.*;

public class QuestionSet {
    private static ResourceBundle bundle;

    private QuestionSet(){}

    public static void printQuestions(Locale locale) {
        bundle = ResourceBundle.getBundle("questions", locale);
        Iterator<String> keys = bundle.getKeys().asIterator();

        keys.forEachRemaining(key->System.out.printf("%s. %s\n",
                key, bundle.getString(key).split("#")[0]));
    }


    public static void main(String... args) {
        System.out.println("Выберите язык (ru или en):");
        Scanner scanner = new Scanner(System.in);
        String lang = scanner.nextLine().toLowerCase();
        String inputNum;

        switch (lang) {
            case "ru":
                QuestionSet.printQuestions(new Locale("ru", "RU"));
                inputNum = "Введите номер вопроса:";
                break;
            case "en":
                QuestionSet.printQuestions(new Locale("en", "UK"));
                inputNum = "Enter the number of the question:";
                break;
            default:
                System.out.println("Введеная локаль не поддержиается.");
                return;
        }

        System.out.println(inputNum);
        int num = scanner.nextInt();
        System.out.println(bundle.getString(Integer.toString(num)).split("#")[1]);
    }
}
