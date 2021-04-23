package view;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class View {
    private final static BufferedReader read = new BufferedReader(new InputStreamReader(System.in));

    public static void printLine(){
        System.out.println("-----------------------");
    }

    public static String readLine() {
        try {
            return read.readLine();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "Er ging iets mis met het invoeren";
    }
}
