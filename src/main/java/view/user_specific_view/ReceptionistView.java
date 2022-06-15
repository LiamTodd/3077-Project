package view.user_specific_view;

import view.InteractiveView;

import java.util.Scanner;

public class ReceptionistView extends InteractiveView {
    public ReceptionistView(Scanner scanner) {
        super(scanner);
        this.displayString = "Receptionist Menu:\n1. On-site booking\n2. View bookings\n3. Cancel a booking\n4. Delete a booking\n5. Modify a booking\n6. Revert a booking\n7. View notifications\n8. Log out";
    }

}
