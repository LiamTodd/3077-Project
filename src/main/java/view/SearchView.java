package view;

import java.util.Scanner;

public class SearchView extends InteractiveView {

    public SearchView(Scanner scanner){
        super(scanner);
        this.displayString = "1. Search by suburb\n2. Search by test type\n3. Search by facility type\n4. Go back";
    }

}
