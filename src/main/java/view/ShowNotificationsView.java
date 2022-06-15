package view;

import model.bookings.Booking;
import model.bookings.BookingManager;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class ShowNotificationsView extends View implements DisplayOnlyView{

    private String receptionistEmployerSite;
    public ShowNotificationsView(Scanner scanner, String receptionistEmployerSite) {
        super(scanner);
        this.receptionistEmployerSite = receptionistEmployerSite;
    }

    public void updateDisplay() throws Exception {
        ArrayList<Booking> modifiedBookings = BookingManager.getInstance().getModifiedBookings();
        System.out.println("\nModified bookings at the site you work at: ");
        displayBookings(filterRelevantBookings(modifiedBookings), "Modified Booking: ");

        ArrayList<Booking> cancelledBookings = BookingManager.getInstance().getCancelledBookings();
        System.out.println("\nCancelled bookings at the site you work at: ");
        displayBookings(filterRelevantBookings(cancelledBookings), "Cancelled Booking: ");
    }

    private void displayBookings(ArrayList<Booking> bookings, String message){
        if (bookings.size() == 0){
            System.out.println("None to show.");
            return;
        }
        for (Booking booking: bookings){
            System.out.println(message + booking.toNotificationString());
        }
    }

    private ArrayList<Booking> filterRelevantBookings(ArrayList<Booking> bookings){
        ArrayList<Booking> res = new ArrayList<>();
        for (Booking booking: bookings){
            if (Objects.equals(booking.getTestingSiteId(), receptionistEmployerSite)){
                res.add(booking);
            }
        }
        return res;
    }

}
