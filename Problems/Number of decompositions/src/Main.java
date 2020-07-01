import java.util.Scanner;

class Main {
    public static void decompose(int n) {
        decompose(n, n, "");
    }

    public static void decompose(int n, int min, String prefix) {
        if (n == 0) {
            System.out.println(prefix);
            return;
        }

        for (int i = 1; i <= Math.min(min, n); i++) {
            decompose(n - i, i, prefix + " " + i);
        }
    }


    public static void main(String[] args) {
        int n = new Scanner(System.in).nextInt();
        decompose(n);

    }
}