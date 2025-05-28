package piramide;

public class Piramide {
    public static void main(String[] args) {
        int base = 10;

        for (int i = 0; i < base-1; i++) {
            System.out.print(" ");
        }
        System.out.print("0");
        for (int i = 0; i < base; i++) {
            for (int j = 0; j < base-i; j++) {
                System.out.print(" ");
            }
            for (int j = 0; j < (2*i-1); j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
}
