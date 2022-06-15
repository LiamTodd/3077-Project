package view;

import java.util.Scanner;

public abstract class View {
    protected Scanner scanner;
    protected String displayString;
    public View(Scanner scanner){
        this.scanner = scanner;
    }


    /**
     * This method updates the UI with the pre-login menu
     * @return
     */
    protected void showDisplay(){
        System.out.println(displayString);
    };
}
