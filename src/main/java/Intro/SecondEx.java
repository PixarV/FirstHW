package Intro;

import java.util.Scanner;
import static java.lang.Math.pow;

public class SecondEx {
        public static void main(String... args) {
                Scanner scanner = new Scanner(System.in);
                double eps = scanner.nextDouble();

                double a = 1; // n = 0
                int n = 1;

                while (a  > eps) {
                        a = 1 / pow(n + 1, 2);
                        System.out.println(a);
                        n++;
                }
                System.out.println(--n);
        }
}
