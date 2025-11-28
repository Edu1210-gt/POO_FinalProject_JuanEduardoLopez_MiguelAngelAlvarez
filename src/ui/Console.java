
package src.ui;

import java.util.Scanner;

/**
 * Utility class that centralizes all console input and output operations.
 * It provides a controlled and uniform interface for reading and writing data
 * from/to the standard console, abstracting direct handling of
 * {@code System.in} and {@code System.out}.
 *
 * This class works as a wrapper over {@link java.util.Scanner} and
 * {@link System#out}, aiming to:
 * <ul>
 * <li>Ensure safe and validated reading of textual input.</li>
 * <li>Validate and convert numeric input for integers and doubles.</li>
 * <li>Prevent common input-handling issues such as empty entries,
 * invalid numbers, or scanner closures.</li>
 * <li>Provide consistent output formatting through utility methods.</li>
 * </ul>
 *
 * By encapsulating input/output logic, this class reduces repeated code,
 * increases readability in client classes, and avoids misuse of the Scanner
 * across the application.
 */
public class Console {
    // Single shared Scanner instance used for all console input
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Prints a message to the console followed by a newline.
     * This method accepts any type of object, converting it to its
     * string representation.
     */
    public static void writeLine(Object message) {
        /**
         * Prompts the user for a text input. Ensures that the user does not
         * enter an empty value. If an empty string is provided, an error message
         * is shown and the input is requested again.
         *
         * @param prompt The message displayed before reading input.
         * @return A non-empty string entered by the user.
         */
        System.out.println(message);
    }

    public static String readLine(String promt) {
        while (true) {
            System.out.println(promt);
            String input = scanner.nextLine().trim();

            if (!input.isEmpty()) {
                return input;
            }

            System.out.println(" Error: Este campo no puede estar vacío. Intente de nuevo.");
        }
    }

    public static int readLineInt(String message) {
        while (true) {
            try {
                System.out.print(message);
                // Check if there's an available line, meaning the scanner hasn't already closed
                // If it has closed, throw an exception
                // NoSuchElementException instead of returning an empty line.
                if (!scanner.hasNextLine()) {
                    System.out.println("Input error. Try again.");
                    continue;
                }
                // check if the line is not empty
                String input = scanner.nextLine().trim();
                // Check if the entry is empty
                if (input.isEmpty()) {
                    System.out.println("You must enter a number. Try again.");
                    continue;
                }

                return Integer.parseInt(input);
                // verify if the input is a valid number
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Try again.");
            }
        }
    }

    /**
     * Prompts the user to enter a decimal number. The method validates the
     * input and ensures that it is a valid {@code double}. If the input cannot
     * be parsed, an error message is shown and the user is prompted again.
     *
     * @param prompt Message displayed before reading input.
     * @return A valid decimal number entered by the user.
     */
    public static double readLineDouble(String prompt) {
        while (true) {
            try {
                System.out.println(prompt);
                String input = scanner.nextLine().trim();
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println(" Error: Debe ingresar un número decimal válido.");
            }
        }
    }

}
