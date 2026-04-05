import java.util.Scanner;

public class SafeInput {

    public static String getNonZeroLenString(Scanner pipe, String prompt) {
        String retString = "";
        do {
            System.out.print("\n" + prompt + ": ");
            retString = pipe.nextLine();
        } while(retString.length() == 0);
        return retString;
    }

    public static int getInt(Scanner pipe, String prompt) {
        int retInt = 0;
        boolean done = false;
        String trash = "";
        do {
            System.out.print("\n" + prompt + ": ");
            if (pipe.hasNextInt()) {
                retInt = pipe.nextInt();
                pipe.nextLine();
                done = true;
            } else {
                trash = pipe.nextLine();
                System.out.println("You must enter an integer. You entered: " + trash);
            }
        } while (!done);
        return retInt;
    }

    public static double getDouble(Scanner pipe, String prompt) {
        double retDouble = 0;
        boolean done = false;
        String trash = "";
        do {
            System.out.print("\n" + prompt + ": ");
            if (pipe.hasNextDouble()) {
                retDouble = pipe.nextDouble();
                pipe.nextLine();
                done = true;
            } else {
                trash = pipe.nextLine();
                System.out.println("You must enter a double. You entered: " + trash);
            }
        } while (!done);
        return retDouble;
    }

    public static int getRangedInt(Scanner pipe, String prompt, int low, int high) {
        int retInt = 0;
        boolean done = false;
        String trash = "";
        do {
            System.out.print("\n" + prompt + " [" + low + "-" + high + "]: ");
            if (pipe.hasNextInt()) {
                retInt = pipe.nextInt();
                pipe.nextLine();
                if (retInt >= low && retInt <= high) {
                    done = true;
                } else {
                    System.out.println("Not in range. Please enter a value between " + low + " and " + high + ".");
                }
            } else {
                trash = pipe.nextLine();
                System.out.println("You must enter an integer. You entered: " + trash);
            }
        } while (!done);
        return retInt;
    }

    public static double getRangedDouble(Scanner pipe, String prompt, double low, double high) {
        double retDouble = 0;
        boolean done = false;
        String trash = "";
        do {
            System.out.print("\n" + prompt + " [" + low + "-" + high + "]: ");
            if (pipe.hasNextDouble()) {
                retDouble = pipe.nextDouble();
                pipe.nextLine();
                if (retDouble >= low && retDouble <= high) {
                    done = true;
                } else {
                    System.out.println("Not in range. Please enter a value between " + low + " and " + high + ".");
                }
            } else {
                trash = pipe.nextLine();
                System.out.println("You must enter a double. You entered: " + trash);
            }
        } while (!done);
        return retDouble;
    }

    // PART F: getYNConfirm
    public static boolean getYNConfirm(Scanner pipe, String prompt) {
        boolean retVal = false;
        boolean done = false;
        String input = "";
        do {
            System.out.print("\n" + prompt + " [Y/N]: ");
            input = pipe.nextLine();
            if (input.equalsIgnoreCase("Y")) {
                retVal = true;
                done = true;
            } else if (input.equalsIgnoreCase("N")) {
                retVal = false;
                done = true;
            } else {
                System.out.println("Invalid input. Please enter exactly Y or N.");
            }
        } while (!done);
        return retVal;
    }

    // PART G: getRegExString
    public static String getRegExString(Scanner pipe, String prompt, String regEx) {
        String input = "";
        boolean done = false;
        do {
            System.out.print("\n" + prompt + ": ");
            input = pipe.nextLine();
            if (input.matches(regEx)) {
                done = true;
            } else {
                System.out.println("Input does not match the required pattern.");
            }
        } while (!done);
        return input;
    }

    // PART H: prettyHeader
    public static void prettyHeader(String msg) {
        int totalWidth = 60;

        // Print top row
        for (int i = 0; i < totalWidth; i++) {
            System.out.print("*");
        }
        System.out.println();

        // Print middle row
        System.out.print("***");
        int spaceAvailable = totalWidth - 6;
        int paddingLeft = (spaceAvailable - msg.length()) / 2;
        int paddingRight = spaceAvailable - msg.length() - paddingLeft;

        for (int i = 0; i < paddingLeft; i++) {
            System.out.print(" ");
        }
        System.out.print(msg);
        for (int i = 0; i < paddingRight; i++) {
            System.out.print(" ");
        }
        System.out.print("***\n");

        // Print bottom row
        for (int i = 0; i < totalWidth; i++) {
            System.out.print("*");
        }
        System.out.println();
    }
}