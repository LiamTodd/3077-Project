package model;

import java.util.Scanner;

public class APISetUp {
    /**
     * Used to avoid pushing api key to repo
     */

    private String apiKey;
    private static APISetUp INSTANCE;


    private APISetUp(){

    }

    public static APISetUp getInstance() {
        if (INSTANCE == null){
            INSTANCE = new APISetUp();
        }
        return INSTANCE;
    }

    public void setUp(){

        System.out.println("Enter valid API key to use the system.");
        Scanner s = new Scanner(System.in);
        this.apiKey = s.next();

    }

    /**
     * Allow classes to access apiKey
     * @return
     */
    public String getApiKey() {
        return apiKey;
    }

    public static String getUrlBase(){
        return "https://fit3077.com/api/v2/";
    }

}
