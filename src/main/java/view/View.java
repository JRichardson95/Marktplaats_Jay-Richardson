package view;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class View {
    private final BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
    public void printLine(){
        System.out.println("-----------------------");
    }

    public String readLine() {
        try {
            return read.readLine();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "Er ging iets mis met het invoeren";
    }
}
