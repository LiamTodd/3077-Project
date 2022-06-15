package model.bookings;

import com.fasterxml.jackson.databind.JsonNode;
import model.adapters.JsonToBookingAdapter;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.ArrayList;
import java.util.Map;

/**
 * Class to represent a booking Object
 */
public class Booking {
    private String customerId;
    private String testingSiteId;
    private String startTime;
    private ObjectNode additionalInfo;
    private String id;
    private String notes;
    private String smsPin;
    private String status;
    private JsonNode revertStack;
    private String additionalInfoString;


    public Booking(String customerId, String testingSiteId, String startTime, String notes, ObjectNode additionalInfo) {
        this.customerId = customerId;
        this.testingSiteId = testingSiteId;
        this.startTime = startTime;
        this.notes = notes;
        this.additionalInfo = additionalInfo;
    }


    public Booking(JsonToBookingAdapter adapter) {
        Map<String, String> properties = adapter.getStringProperties();
        this.customerId = properties.get("customerId");
        this.testingSiteId = properties.get("testingSiteId");
        this.startTime = properties.get("startTime");
        this.notes = properties.get("notes");
        this.id = properties.get("id");
        this.smsPin = properties.get("smsPin");
        this.status = properties.get("status");
        Map<String, JsonNode> nodeProperties = adapter.getNodeProperties();
        this.revertStack = nodeProperties.get("revertStack");
        this.additionalInfoString = this.revertStack.toString();

    }
    @Override
    public String toString() {
        return "Booking with ID: " + id +
                "\n         customerId: " + customerId +
                "\n         testingSiteId: " + testingSiteId +
                "\n         startTime: " + startTime +
                "\n         modificationHistory: " + additionalInfoString +
                "\n         notes: " + notes +
                "\n         smsPin: " + smsPin +
                "\n         status: " + status;
    }

    public String toNotificationString(){
        return "Booking with ID: " + id +
                "\n         updated status: " + status;
    }
    public String getSmsPin(){
        return this.smsPin;
    }
    public String getStartTime() {
        return startTime;
    }
    public String getStatus(){
        return this.status;
    }
    public String getTestingSiteId() {
        return this.testingSiteId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getId(){return this.id;};
    public void setTestingSiteId(String testingSiteId) {
        this.testingSiteId = testingSiteId;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String modifiedBookingStringToJson(){
        return "{" +
                "\"customerId\":\"" + customerId + "\"," +
                "\"testingSiteId\":\"" + testingSiteId + "\"," +
                "\"startTime\":\"" + startTime + "\"," +
                "\"status\":\"" + "MODIFIED" + "\"," +
                "\"notes\":\"" + notes
                +"\"," + additionalInfoString
                + "}" ;
    }

    public void setAdditionalInfoAsString(ArrayList<String> additionalInfoArray){

        String res = "\"additionalInfo\":" + "{" +
                "\"revertStack\":" + "[";
        for (int i = 0; i < additionalInfoArray.size(); i++){
            res += additionalInfoArray.get(i);
            if (i < additionalInfoArray.size() - 1){
                res += ",";
            }
        }
        res += "]}";
        additionalInfoString = res;
    }

    public JsonNode getRevertStack() {
        return revertStack;
    }

    /**
     * Takes Booking data and formats in appropriate JSON format
     */
    public String stringToJson(){
        return "{" +
                "\"customerId\":\"" + customerId + "\"," +
                "\"testingSiteId\":\"" + testingSiteId + "\"," +
                "\"startTime\":\"" + startTime + "\"," +
                "\"notes\":\"" + notes + "\"," +
                "\"additionalInfo\":" + additionalInfo +
                "}";
    }
}
