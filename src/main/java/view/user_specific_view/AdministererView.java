package view.user_specific_view;

import view.InteractiveView;

import java.util.Scanner;

public class AdministererView extends InteractiveView {
    public AdministererView(Scanner scanner) {
        super(scanner);
        this.displayString = "Administerer Menu:\n1. Conduct Interview for on-site test\n2. Log out";
    }
}
