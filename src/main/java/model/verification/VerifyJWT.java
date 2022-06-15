package model.verification;

import model.APISetUp;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
/**
 * Class to verify if JWT is still valid or had expired
 */
public class VerifyJWT {
    private String myApiKey;
    private static final String usersVerifyTokenUrl = APISetUp.getUrlBase() + "user/verify-token";
    private HttpResponse<String> response;

    /**
     * Verifies JWT
     * @param JWT JSON Web Token to verify
     * @return whether JWT is still valid
     * @throws Exception
     */
    public boolean verify(String JWT) throws Exception {

        APISetUp a = APISetUp.getInstance();
        this.myApiKey = a.getApiKey();

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder(URI.create(usersVerifyTokenUrl)) // Return a JWT so we can use it later.
                .setHeader("Authorization", myApiKey)  // Sets API key as header to authorize request.
                .header("Content-Type","application/json") // This header needs to be set when sending a JSON request body.
                .POST(HttpRequest.BodyPublishers.ofString(JWT)) // POST JWT verification request
                .build();

        response = client.send(request, HttpResponse.BodyHandlers.ofString()); // Saves the client response

        return checkStatusCode();
    }
    /**
     * checks status code of API request
     * @return whether the JWT is valid
     */
    private boolean checkStatusCode() {
        if(response.statusCode()==200){ // Checks if the response is status code 200
            System.out.println("JWT still valid");
            return true;
        }else{
            System.out.println("JWT is invalid, expired or key is not valid");
            System.out.println("Full JSON response: " + response.body()); // This endpoint does not return a JSON response upon success.
            return false;
        }
    }

}
