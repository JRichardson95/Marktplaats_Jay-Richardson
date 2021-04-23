package view;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class View {
    private final static BufferedReader read = new BufferedReader(new InputStreamReader(System.in));

    public static void print(String input){
        System.out.print(input);
    }
    public static void println(String input){
        System.out.println(input);
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
        return "Er ging iets mis met het invoeren";
    }

    public static void header(String titel){
        divider();
        System.out.println("        " + titel);
        divider();
    }

}
