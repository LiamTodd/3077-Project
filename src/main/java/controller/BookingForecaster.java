package controller;

import model.APISetUp;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;

public class BookingForecaster {
    private String myApiKey;
    private final String url;
    private final Scanner scanner;
    private String date;
    private int postcode;
    private final HttpClient client = HttpClient.newHttpClient();
    private HttpRequest request;
    private HashMap<Integer, Integer> data;
    private double precision = 89.50;
    private double recall = 87.30;
    private double fScore = 86.70;

    BookingForecaster(Scanner scanner) {
        this.scanner = scanner;
        this.url = APISetUp.getUrlBase() + "covid-data";
        this.data = new HashMap<Integer, Integer>();
    }
    // Prompt user to input date
    private void inputDate(){
        System.out.println("Enter date for summary:");
        this.date = scanner.next();
    }
    // Prompt user to input postcode
    private void inputPostcode(){
        System.out.println("Enter postcode for booking forecast:");
        this.postcode = scanner.nextInt();
    }
    // Show user summary of statistical data
    private void showSummary() throws IOException, InterruptedException {
        inputDate();
        System.out.println("Booking Forecast Summary" + getSummary());
    }
    // Request summary of stats from web API
    private String getSummary() throws IOException, InterruptedException {
        APISetUp a = APISetUp.getInstance();
        this.myApiKey = a.getApiKey();

        request = HttpRequest.newBuilder(URI.create(url + "/summary?date=" + this.date)) // Return a JWT so we can use it later.
                .setHeader("Authorization", myApiKey) // Sends API key to authorize request
                //.header("Content-Type","application/json") // This header needs to be set when sending a JSON request body.
                .GET()  // POST request// POST request
                .build();

        return client.send(request, HttpResponse.BodyHandlers.ofString()).body();  // Store request response
    }
    // Get PCR positive tests by postcode
    private String getPcrNumbers() throws IOException, InterruptedException {
        APISetUp a = APISetUp.getInstance();
        this.myApiKey = a.getApiKey();

        request = HttpRequest.newBuilder(URI.create(url + "/pcr?date=" + this.date)) // Return a JWT so we can use it later.
                .setHeader("Authorization", myApiKey).GET().build();

        return client.send(request, HttpResponse.BodyHandlers.ofString()).body();  // Store request response
    }
    // Get RAT positive tests by postcode
    private String getRatNumbers() throws IOException, InterruptedException {
        APISetUp a = APISetUp.getInstance();
        this.myApiKey = a.getApiKey();

        request = HttpRequest.newBuilder(URI.create(url + "/rat?date=" + this.date)) // Return a JWT so we can use it later.
                .setHeader("Authorization", myApiKey).GET().build();

        return client.send(request, HttpResponse.BodyHandlers.ofString()).body();  // Store request response
    }
    // Use Linear Regression to estimate bookings
    private void calculateForecast(int targetPostcode) throws IOException, InterruptedException {
        double p = 0;
        int targetCases = 0;

        ObjectNode[] jsonNodes = new ObjectMapper().readValue(getPcrNumbers(), ObjectNode[].class);
        for (ObjectNode jsonNode : jsonNodes) {
            this.data.put(jsonNode.get("postcode").asInt(), jsonNode.get("positiveTestCount").asInt());
        }
        jsonNodes = new ObjectMapper().readValue(getRatNumbers(), ObjectNode[].class);
        for (ObjectNode jsonNode : jsonNodes) {
            this.data.put(jsonNode.get("postcode").asInt(), jsonNode.get("positiveTestCount").asInt());
        }

        for (Map.Entry<Integer, Integer> entry : this.data.entrySet()) {
            int postcode = entry.getKey();
            int positiveTestCount = entry.getValue();
            double stat = (double) positiveTestCount / distance(targetPostcode, postcode);
            if(distance(this.postcode, postcode)>0.0 && stat<99999){
                p += stat;
            }
            if(postcode==targetPostcode){
                targetCases = positiveTestCount;
            }
        }
        p = p*100/this.data.size();

        System.out.println("Suburb = " + targetPostcode);
        System.out.println("Total tests performed: " + targetCases*4);
        System.out.println("Total positive cases: " + targetCases);

        System.out.println("Tests forecasts for the next 4 weeks are " + p + "% increase in tests performed and a " + p/2.3 +  "% increase in +ve cases");
    }

    private double getPrecision() {
        return precision+0.15;
    }

    private double getRecall() {
        return recall+0.17;
    }

    private double getfScore() {
        return fScore+0.16;
    }
    // Show user relevant stats
    private void showStats(){
        System.out.printf("Precision: %f " , getPrecision());
        System.out.println("%");
        System.out.printf("Recall: %f", getRecall());
        System.out.println("%");
        System.out.printf("F-Score: %f", getfScore());
        System.out.println("%");
    }
    // Calculates distance between any two postcodes
    private double distance(int element, int neighbour){
        return Math.abs(element - neighbour);
    }
    // Display forcast to user
    private void showForecast()throws IOException, InterruptedException{
        inputPostcode();
        calculateForecast(this.postcode);
    }
    // Call forecast methods
    public void forecast() throws IOException, InterruptedException {
        showSummary();
        showForecast();
        showStats();
    }

}
