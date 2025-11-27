
package src.ui;

import java.util.Scanner;

public class Console {
    private static final Scanner scanner = new Scanner(System.in);

    public static void writeLine(Object message) {
        System.out.println(message);
    }

    public static String readLine(String promt) {
        System.out.println(promt);
        return scanner.nextLine();

    }

    public static int readLineInt(String promt) {
        System.out.println(promt);
        int value = scanner.nextInt();
        scanner.nextLine();
        return value;
    }

}
