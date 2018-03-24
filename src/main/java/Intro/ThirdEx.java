package Intro;

import java.util.Scanner;
import static java.lang.Math.tan;

public class ThirdEx {
        public static void main(String... args) {
                Scanner scanner = new Scanner(System.in);
                double a, b , h;
                a = scanner.nextDouble();
                b = scanner.nextDouble();
                h = scanner.nextDouble();

                if (b < a) { // instead of throwing an exception
                        double temp = b;
                        b = a;
                        a = temp;
                }

                double start = a;
                while (start <= b) {
                        System.out.printf("%.8f  | %.8f\n", start, func(start));
                        start += h;
                }
        }

        private static double func(double x) {
                return tan(2 * x) - 3;
        }
}
