import java.util.Scanner;

public class FifthEx {
        public static void main(String... args) {
                Scanner scanner = new Scanner(System.in);
                int strings = scanner.nextInt();
                int columns = scanner.nextInt();

                for (int i = 0; i < strings; i++) {
                        for (int j = 0; j < columns; j++) {
                                if (i == j || j + i == columns - 1) {
                                        System.out.print(" 1 ");
                                        continue;
                                }
                                System.out.print(" 0 ");
                        }
                        System.out.println();
                }

        }
}
