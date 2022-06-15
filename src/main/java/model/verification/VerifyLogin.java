package model.verification;

import model.APISetUp;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;
/**
 * Class to verify login credentials
 */
public class VerifyLogin {
    private String userName;    // Represents the user's associated username used to login
    private Scanner scanner;   // Scanner to input usernames and passwords from the user
    private String jwt; // JSON Web Token
    private String myApiKey;
    private static final String usersLoginUrl = APISetUp.getUrlBase() + "user/login"; // Root URL for the web service. All web service request URLs start with this root URL.

    public VerifyLogin(Scanner scanner) {
        this.scanner = scanner;
    }
    /**
     * Prompts user to enter their username and password to login
     */
    private void promptUser(){
        System.out.print("Enter username:");
        userName = scanner.nextLine();
        System.out.print("Enter password:");
    }
    /**
     * Continues to prompt user and attempts to authenticate until JWT is generated
     * @return JSON Web Token authenticating user
     * @throws Exception
     */
    public String authenticate() throws Exception {
        while(jwt==null){
            promptUser();
            requestJWT();
        }
        return jwt;
    }

    /**
     * Sends API request to verify credentials and returns JWT
     * @throws Exception
     */
    private void requestJWT() throws Exception {

        APISetUp a = APISetUp.getInstance();
        this.myApiKey = a.getApiKey();

        // Creates the request body with the username and password
        String jsonString = "{" +
                "\"userName\":\"" + userName + "\"," +
                "\"password\":\"" + scanner.nextLine() + "\"" +
                "}";
        // Authenticating a user's credentials using the POST /user/login endpoint
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(URI.create(usersLoginUrl + "?jwt=true")) // Return a JWT, so we can use it later.
                .setHeader("Authorization", myApiKey)   // Sets API key as header to authorize request.
                .header("Content-Type","application/json") // This header needs to be set when sending a JSON request body.
                .POST(HttpRequest.BodyPublishers.ofString(jsonString))  // POST() method is used here, and the request body is supplied to it.
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString()); // Saves the client response

        System.out.println(request.uri());

        checkStatusCode(response);
    }
    /**
     * checks status code of API request
     * @param response whether the credentials are correct
     */
    private void checkStatusCode(HttpResponse<String> response) {
        if(response.statusCode()==200){ // Checks if the response is status code 200
            jwt = response.body();
            System.out.println("User authenticated. JWT stored." + jwt);
        }else {
            String[] message = response.body().split("\"");
            System.out.println("Response code: " + message[9]);
        }
    }

    public String getUserName() {
        return userName;
    }
}
