package view.forms;

import java.util.Scanner;

/**
 * Basic abstract class to represent view.forms
 */
public abstract class FormView {

    protected Scanner scanner;

    public FormView(Scanner scanner){
        this.scanner = scanner;
    }
    /**
     * Displays form to user
     */
    public abstract void printForm() throws Exception;

}
