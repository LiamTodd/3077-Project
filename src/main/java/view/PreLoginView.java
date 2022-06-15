package view;

import java.util.Scanner;

public class PreLoginView extends InteractiveView{

    public PreLoginView(Scanner scanner) {
        super(scanner);
        this.displayString = "Welcome to COVID test registration system.\n1. Login\n2. Search for a testing site\n3. Booking Forecast\n4. Quit";
    }

}
