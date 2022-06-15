package view.forms.bookingform;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import view.forms.FormView;
import view.forms.questionaire.InterviewFormView;
import model.bookings.Booking;
import model.bookings.BookingManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Scanner;

/**
 * Base class to represent all sorts of Booking view.forms
 */
public class BookingFormView extends FormView {
    private final BookingManager bookingManager;
    private String siteId;
    private String userId;
    private String startTime;
    private String testDate;

    private String pin;


    public String getStartTime() {
        return startTime;
    }

    public String getTestDate() {
        return testDate;
    }

    public String  getPin() {
        return pin;
    }

    public String setPin(String  pin) {
        return this.pin = pin;
    }

    public String getNotes() {
        return notes;
    }

    public ObjectNode getAdditionalInfo() {
        return additionalInfo;
    }

    public String getUserId() {
        return userId;
    }

    public String getBookingId() {
        return bookingId;
    }

    private String notes;
    private ObjectNode additionalInfo;
    private String testRecommended = "";
    private final ObjectNode node = new ObjectMapper().createObjectNode();

    private String bookingId;

    public BookingFormView(Scanner scanner) throws Exception {
        super(scanner);
        this.bookingManager = BookingManager.getInstance();
    }

    /**
     * Displays form to user
     */
    public void printForm() throws Exception {
    }
    /*
      Checks if the given booking is in the future and thus eligble to be modified
     */
    public boolean isBookingFuture(String bookingID) throws Exception {
        //Booking booking = getBookingManager().getBookingById(bookingID).get(0);
        // TODO: check if booking is in the future
        // Extract date time of booking
        String booking = getBookingManager().getBookingById(bookingID).get(0).getStartTime();
        System.out.println(booking);

        // compare to timeNow
        //if(booking.getStartTime()>Instant.now())
        int bookingYear = Integer.parseInt(booking.substring(0,4));
        int bookingMonth = Integer.parseInt(booking.substring(5, 7));
        int bookingDay = Integer.parseInt(booking.substring(8,10));
        GregorianCalendar bookingDate = new GregorianCalendar(bookingYear, bookingMonth, bookingDay);
        GregorianCalendar todaysDate = new GregorianCalendar();
//        String timeNow = Instant.now().toString();
        return todaysDate.compareTo(bookingDate)<0;
    }

    /**
     * Books covid test based on given info
     * @throws IOException
     * @throws InterruptedException
     */
    void bookTest() throws Exception {
        System.out.println("Now booking test for user based on given info...");
        Booking booking = new Booking(getUserId(), siteId, dateFormatter(testDate, startTime), notes, additionalInfo); // Create new booking

        if(bookingManager.postBooking(booking.stringToJson())){ // check if booking was successful
            System.out.print("Booking created:");
            System.out.println(booking.stringToJson());  // Display booking created
        }else{
            System.out.println("Booking failed!");
        }
    }
    /**
     * Fill in form to get recommended covid test
     */
    void inputTestRecommended() throws Exception {
        System.out.println("Questionaire...");

        InterviewFormView interviewForm = new InterviewFormView(scanner);
        interviewForm.printForm(); // Ask patient questions about symptons
        String testRecommended = interviewForm.getTestAdvised(); // store test advised

        additionalInfo = node.put("i", testRecommended); // put into additional info
        additionalInfo = node.put("revertStack", "[]"); // put into additional info

    }
    /**
     * Prompts user to input notes, if any
     */
    protected void inputNotes() {
        System.out.println("Enter any notes:");
        this.notes = scanner.nextLine();
    }
    /**
     * Prompts user to input date and time of their test
     */
    protected void inputDateTime() {
        System.out.println("Enter date in the format (YYYY-MM-DD) to book:");
        this.testDate = scanner.nextLine();
        System.out.println("Enter time in the format (HH:MM) to book:");
        this.startTime = scanner.nextLine();
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setTestDate(String testDate) {
        this.testDate = testDate;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    /**
     * Prompts user to enter user ID of person they are booking for
     */
    protected void inputUserId() {
        System.out.println("Enter user ID of person the booking is for:");

        this.userId = scanner.nextLine();

    }
    /**
     * Prompts user to enter site ID they wish to book at
     */
    void inputSiteId() {
        System.out.println("Enter site ID of testing site to book at:");
        this.siteId = scanner.nextLine();
    }

    /**
     * Prompts user to enter Booking ID they wish to modify
     */
    protected void inputBookingID() {

        this.bookingId = scanner.nextLine();
        scanner.next();
    }

    protected void inputSMSPIN() {

        this.pin = scanner.nextLine();
        scanner.next();
    }

    /**
     * Returns date and time in appropriate format
     * @param bookingDate Booking date
     * @param bookingTime Booking time
     * @return formatted date time
     */
    protected String dateFormatter(String bookingDate, String bookingTime){
        //YYYY-MM-DDTHH:MM:SS.079Z
        return bookingDate + "T" + bookingTime + ":00.079Z";
        //return Instant.now().toString();
    }

    public BookingManager getBookingManager() {
        return this.bookingManager;
    }

    public ObjectNode getNode() {
        return this.node;
    }

    public String getSiteId() {
        return this.siteId;
    }

    public String getTestRecommended() {
        return this.testRecommended;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public void setAdditionalInfo(ObjectNode additionalInfo) {
        this.additionalInfo = additionalInfo;
    }
    /*
        Modifies the time and venue of an existing booking
     */
    protected void modifyBooking() throws Exception {
        String bookingID = getBookingId();
        System.out.println(bookingID);

        Booking booking = getBookingManager().getBookingById(bookingID).get(0);  // Old booking info
        // TODO: store the old booking as an additional object
        String oldData = "{" + "\"startTime\":\"" + booking.getStartTime() + "\"," +
                "\"testingSiteId\":\"" + booking.getTestingSiteId() + "\"}" ;

        JsonNode revertStack =  booking.getRevertStack();
        ArrayList<String> tempStack = new ArrayList<>();

        for (JsonNode previousState:revertStack
             ) {
            tempStack.add(previousState.toString());
        }
        tempStack.add(oldData);

        booking.setAdditionalInfoAsString(tempStack);
        booking.setTestingSiteId(getSiteId());

        System.out.println("request sent: " + booking.modifiedBookingStringToJson());
        getBookingManager().modifyBooking(getBookingId(), booking.modifiedBookingStringToJson());


    }
    /**
     * Asks user if they need or already have a RAT test kit
     * @throws IOException
     * @throws InterruptedException
     */
    private void askRAT() throws Exception {
        System.out.println("Enter site ID of the site where you want to collect a RAT test from OR hit 'enter' if you already have a RAT test kit.");
        setSiteId(scanner.nextLine()); //Gets site from user

        if (getSiteId() != null){ // If user presses enter, they already have a RAT.

            setAdditionalInfo(getNode().put("revertStack", "[]")); // add QR code to additional info
//            if(getVerifyJWT().verify(this.getJWT())){
//                bookTest();
//            }
            bookTest();
            System.out.println("Your QR code has been sent");
        }
        else{
            System.out.println("We hope your RAT test turns out negative! No QR code has been sent.");
        }
    }
}
