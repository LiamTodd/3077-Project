package view.user_specific_view;

import view.InteractiveView;

import java.util.Scanner;

public class PatientView extends InteractiveView {
    public PatientView(Scanner scanner) {
        super(scanner);
        this.displayString = "Customer Menu:\n1. Search for testing sites\n2. Book a Home Test\n3. Check Booking\n4. Modify Booking\n5. Cancel Booking\n6. Revert Booking\n7. Log out";
    }

}
