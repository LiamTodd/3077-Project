package controller;

import view.ShowNotificationsView;

import java.util.Scanner;

public class ShowNotificationsController extends Controller{

    private String receptionistEmployerSite;

    public ShowNotificationsController(Scanner scanner, String receptionistEmployerSite) {
        super(scanner);
        this.receptionistEmployerSite = receptionistEmployerSite;
    }

    @Override
    public void controlSystem() throws Exception {
        ShowNotificationsView view = new ShowNotificationsView(scanner, receptionistEmployerSite);
        view.updateDisplay();
    }

    @Override
    protected boolean interpretUserInput(String input) {
        return false;
    }
}
