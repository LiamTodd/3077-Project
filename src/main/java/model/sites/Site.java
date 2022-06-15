package model.sites;

import model.adapters.JsonToSiteAdapter;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.Map;

/**
 * Represents a testing site in the system
 */
public class Site {

    private String name;
    private String id;
    private String description;
    private String webUrl;
    private String phoneNumber;
    private String latitude;
    private String longitude;
    private String suburb;
    private String state;
    private String postcode;
    private String status;
    private String waitTime;
    private JsonNode siteTypes;
    private JsonNode testTypes;

    public Site(JsonToSiteAdapter adapter){
        Map<String, String> properties = adapter.getStringProperties();
        this.name = properties.get("name");
        this.id = properties.get("id");
        this.description = properties.get("description");
        this.webUrl = properties.get("webUrl");
        this.phoneNumber = properties.get("phoneNumber");
        this.latitude = properties.get("latitude");
        this.longitude = properties.get("longitude");
        this.suburb = properties.get("suburb");
        this.state = properties.get("state");
        this.postcode = properties.get("postcode");
        this.status = properties.get("status");
        this.waitTime = properties.get("waitTime");
        Map<String, JsonNode> nodeProperties = adapter.getNodeProperties();
        this.siteTypes = nodeProperties.get("siteTypes");
        this.testTypes = nodeProperties.get("testTypes");

    }

    /**
     * Returns neatly formatted data for the testing site
     * @return
     */
    @Override
    public String toString() {
        return name + ": " +
                "\n         id: " + id +
                "\n         description: " + description +
                "\n         status: " + status +
                "\n         wait time: " + waitTime +
                "\n         phoneNumber: " + phoneNumber +
                "\n         location: " + suburb +
                " " + state;
    }


    /**
     * necessary getters
     */


    public String getId() {
        return id;
    }

    public String getSuburb() {
        return suburb;
    }

    public JsonNode getSiteTypes(){ return siteTypes;}

    public JsonNode getTestTypes(){ return testTypes; }

}
