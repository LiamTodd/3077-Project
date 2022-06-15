package model.bookings;

import com.fasterxml.jackson.databind.node.ObjectNode;
import model.APISetUp;
import model.EntityManager;
import model.adapters.JsonToBookingAdapter;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

/**
 * Class to create and manage Bookings
 */
public class BookingManager extends EntityManager {
    private String myApiKey;
    private final HttpClient client = HttpClient.newHttpClient();
    private HttpRequest request;
    private HttpResponse<String> response;
    private static BookingManager INSTANCE;
    private ArrayList<Booking> allBookings;
    private static final String CANCELLED_STATUS = "CANCELLED";
    private static final String DEFAULT_STATUS = "INITIATED";
    private static final String MODIFIED_STATUS = "MODIFIED";

    private BookingManager() throws Exception {
        this.url = APISetUp.getUrlBase() + "booking";
        this.allBookings = this.findAllEntities();
    }
    /**
     * Ensures that only a single instance is present in the system
     */
    public static BookingManager getInstance() throws Exception {
        if (INSTANCE == null){
            INSTANCE = new BookingManager();
        }
        return INSTANCE;
    }
    // Gets all bookings from web API
    public ArrayList<Booking> getAllBookings() {
        return allBookings;
    }
    /**
     * Creates individual bookings and posts to API
     * @param booking String representing a booking
     * @return true if success else false
     * @throws Exception if API request is invalid
     */
    public boolean postBooking(String booking) throws Exception {
        APISetUp a = APISetUp.getInstance();
        this.myApiKey = a.getApiKey();

        request = HttpRequest.newBuilder(URI.create(url)) // Return a JWT so we can use it later.
                .setHeader("Authorization", myApiKey) // Sends API key to authorize request
                .header("Content-Type","application/json") // This header needs to be set when sending a JSON request body.
                .POST(HttpRequest.BodyPublishers.ofString(booking))  // POST request
                .build();

        response = client.send(request, HttpResponse.BodyHandlers.ofString());  // Store request response

        return checkStatusCode();
    }
    /**
     * Cancels Booking by modifying status to CANCELLED from initiated
     */
    public boolean cancelBooking(String bookingId) throws IOException, InterruptedException {
        return modifyBooking(bookingId, "{" +"\"status\":\"" + CANCELLED_STATUS + "\"}");
    }
    // Changes status of a booking to modified
    public boolean modBooking(String bookingId) throws IOException, InterruptedException {
        return modifyBooking(bookingId, "{" +"\"status\":\"" + MODIFIED_STATUS + "\"}");
    }
    /**
     * Modifies the venue or time of a booking
     */
    public boolean modifyBooking(String bookingID, String booking) throws IOException, InterruptedException {
        APISetUp a = APISetUp.getInstance();
        this.myApiKey = a.getApiKey();

        request = HttpRequest.newBuilder()
                .uri(URI.create(url + "/" + bookingID))
                .setHeader("Authorization", myApiKey)
                .method("PATCH", HttpRequest.BodyPublishers.ofString(booking))
                .header("Content-Type", "application/json")
                .build();

        response = client.send(request, HttpResponse.BodyHandlers.ofString());  // Store request response
        return response.statusCode() == 200;
    }
    /// Delete booking specified via web API request
    public boolean deleteBooking(String bookingID) throws IOException, InterruptedException {
        APISetUp a = APISetUp.getInstance();
        this.myApiKey = a.getApiKey();

        request = HttpRequest.newBuilder(URI.create(url + "/" + bookingID)) // Return a JWT so we can use it later.
                .setHeader("Authorization", myApiKey) // Sends API key to authorize request
                //.header("Content-Type","application/json") // This header needs to be set when sending a JSON request body.
                .DELETE()  // POST request
                .build();

        response = client.send(request, HttpResponse.BodyHandlers.ofString());  // Store request response

        System.out.println("Response code: " + response.statusCode());
        return response.statusCode()==204;
    }
    /**
     * Checks status of API call
     * @return
     */
    private boolean checkStatusCode() {
        if(response.statusCode() == 201){
            System.out.println("PIN: 6264");
            return true;
        }
        return false;
    }
    // Add new bookings
    @Override
    protected ArrayList addEntities(ObjectNode[] jsonNodes) {
        ArrayList entities = new ArrayList();
        for (ObjectNode node : jsonNodes){
            Booking booking = new Booking(new JsonToBookingAdapter(node));
            entities.add(booking);
        }
        return entities;
    }
    // Get booking by site ID
    public ArrayList<Booking> getBookingBySiteId(String siteId) throws Exception {
        ArrayList<Booking> res = new ArrayList<>();
        this.allBookings = findAllEntities();
        for (Booking booking : allBookings){
            String id = booking.getTestingSiteId();
            if (id.replaceAll("^\"|\"$", "").equals(siteId)) {
                res.add(booking);
            }
        }
        return res;
    }
    // Get booking by pin
    public ArrayList<Booking> getBookingBySmsPin(String smsPin) throws Exception {
        ArrayList<Booking> res = new ArrayList<>();
        this.allBookings = findAllEntities();
        for (Booking booking : allBookings){
            String pin = booking.getSmsPin();
            if (pin.replaceAll("^\"|\"$", "").equals(smsPin)) {
                res.add(booking);
            }
        }
        return res;
    }
    // Get booking by booking ID
    public ArrayList<Booking> getBookingById(String idNum) throws Exception {
        ArrayList<Booking> res = new ArrayList<>();
        this.allBookings = findAllEntities();
        for (Booking booking : allBookings){
            String id = booking.getId();
            if (id.replaceAll("^\"|\"$", "").equals(idNum)) {
                res.add(booking);
            }
        }
        return res;
    }
    // Get all cancelled bookings
    public ArrayList<Booking> getCancelledBookings() throws Exception {
        ArrayList<Booking> res = new ArrayList<>();
        this.allBookings = findAllEntities();
        for (Booking booking : allBookings){
            if (booking.getStatus().equals(CANCELLED_STATUS)) {
                res.add(booking);
            }
        }
        return res;
    }
    // Get all active bookings
    public ArrayList<Booking> getNonCancelledBookings() throws Exception {
        ArrayList<Booking> res = new ArrayList<>();
        this.allBookings = findAllEntities();
        for (Booking booking : allBookings){
            if (!booking.getStatus().equals(CANCELLED_STATUS)) {
                res.add(booking);
            }
        }
        return res;
    }
    // Get all bookings that are modified
    public ArrayList<Booking> getModifiedBookings() throws Exception {
        ArrayList<Booking> res = new ArrayList<>();
        this.allBookings = findAllEntities();
        for (Booking booking : allBookings){
            if (booking.getStatus().equals(MODIFIED_STATUS)) {
                res.add(booking);
            }
        }
        return res;
    }
}
