package controller;

import java.util.Scanner;

public abstract class Controller {
    protected Scanner scanner;
    public Controller(Scanner scanner){
        this.scanner = scanner;
    }
    public abstract void controlSystem() throws Exception;
    protected abstract boolean interpretUserInput(String input) throws Exception;
}
