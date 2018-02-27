import java.util.Scanner;

public class FourthEx {
        public static void main(String[] args) {

                Scanner scanner = new Scanner(System.in);
                int n = scanner.nextInt();

                double[] nums = new double[n];
                for (int i = 0; i < n; i++) {
                        nums[i] = scanner.nextDouble();
                }

                int start = 0;
                int end = n-1;
                double max = nums[start] + nums[end];
                while (start < end) {
                        max = (nums[start] + nums[end] > max) ? nums[start] + nums[end] : max;
                        start++;
                        end--;
                }
                System.out.println(max);
        }
}
