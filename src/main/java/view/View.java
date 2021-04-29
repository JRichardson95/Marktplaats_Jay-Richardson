package view;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;

public class View {
    private static final BufferedReader read = new BufferedReader(new InputStreamReader(System.in));

    public static void print(String input){
        System.out.print(input);
    }
    public static void println(String input){
        System.out.println(input);
    }
    public static void printErr(String input){ System.err.println(input); }

    public static void printPointer(){
        final String ANSI_RESET = "\u001B[0m";
        final String ANSI_GREEN = "\u001B[32m";
        print(ANSI_GREEN+"> "+ANSI_RESET);
    }

    public static void divider(){
        System.out.println("--------------------------------------------");
    }

    public static String readLine() {
        try {
            return read.readLine();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "Ongeldige input";
    }

    public static int readInt(){
        try {
            return Integer.parseInt(read.readLine());
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return 0;
    }

    public static BigDecimal readBigDecimal(){
        try {
            return new BigDecimal(read.readLine());
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static void header(String titel){
        divider();
        System.out.println("        " + titel);
        divider();
    }

}
