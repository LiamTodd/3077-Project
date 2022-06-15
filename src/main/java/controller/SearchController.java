package controller;

import model.sites.Site;
import model.sites.SitesManager;
import view.SearchView;

import java.util.ArrayList;
import java.util.Scanner;

public class SearchController extends Controller{
    public SearchController(Scanner scanner){
        super(scanner);
    }

    @Override
    public void controlSystem() throws Exception {

        SearchView view = new SearchView(scanner);
        boolean flag = true;

        while(flag){
            // use View to update UI
            String input = view.getUserInput();
            //interpret user input
            flag = interpretUserInput(input);
        }

    }

    /**
     * This method interprets the user's input and conducts different logic depending on their selections
     * @param input
     * @return
     * @throws Exception
     */
    protected boolean interpretUserInput(String input) throws Exception {
        boolean flag = true;
        ArrayList<Site> sites = null;
        if (!input.equals("1") && !input.equals("2") && !input.equals("3") && !input.equals("4")){ // Validate input
            System.out.println("Invalid input");
        } else if (input.equals("1")){ // Ask for suburb
            System.out.println("Which suburb would you like to search? ");
            input = scanner.nextLine();
            sites = SitesManager.getInstance().getSitesBySuburb(input); // Filter test sites by suburb

        } else if (input.equals("2")){ // Ask for test type
            System.out.println("What type of test would you like to search for? ");
            input = scanner.nextLine();
            sites = SitesManager.getInstance().getSitesByTestType(input); // Filter by test type
        }
        else if (input.equals("3")){ // Ask for testing site type
            System.out.println("What type of testing site would you like to search for? ");
            input = scanner.nextLine();
            sites = SitesManager.getInstance().getSitesBySiteType(input); // Filter by testing site type
        } else{
            flag = false;
        }

        if (!input.equals("4")){
            showSites(sites); // Select test type given by the user and update UI
        }
        return flag;
    }


    /**
     * Prints sites found by user's search
     * @param sites list of all sites filtered
     */
    private void showSites(ArrayList<Site> sites) {
        if (sites == null || sites.size() == 0){ // No sites returned
            System.out.println("Sorry, no sites found.");
        }
        else{
            for (int i = 0; i< sites.size(); i++) { // Iterate through sites
                System.out.println(String.valueOf(i+1) + ". " + sites.get(i).toString()); // Display each site option to user
            }

        }
    }
}
