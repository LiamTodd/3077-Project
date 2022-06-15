package view;

import java.util.Scanner;

public class ShowBookingsView extends InteractiveView {
    public ShowBookingsView(Scanner scanner) {
        super(scanner);
        this.displayString = "1. View all\n2. View by site ID\n3. View cancelled \n4. View non-cancelled\n5. Go back";
    }

}
