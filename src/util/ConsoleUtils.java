package util;

import java.util.Scanner;

public class ConsoleUtils {

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";



    public static String getString(String message){
        System.out.print(message);
        String str = new Scanner(System.in).nextLine().trim();
        if (str.isBlank() || str.isEmpty()) {
            printError("Ошибка! Вы ничего не ввели");
            return getString(message);
        }
        return str;
    }

    public static int getInteger(String message) {
        String strNum = getString(message);

        int number;
        try {
            number = Integer.parseInt(strNum);
        } catch (NumberFormatException e) {
            printError("Ошибка! Вы ввели не число.");
            return getInteger(message);
        }

        return number;
    }


    public static void printError(String message) {
        System.out.println(ANSI_RED + message + ANSI_RESET);
    }

}
