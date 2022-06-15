package view;

import java.util.Scanner;

public abstract class InteractiveView extends View{
    public InteractiveView(Scanner scanner) {
        super(scanner);
    }
    public String getUserInput() {
        this.showDisplay();
        String input = scanner.next(); // Take user input
        scanner.nextLine();
        return input;
    }
}
