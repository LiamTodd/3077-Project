package model.adapters;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.HashMap;
import java.util.Map;

public class JsonToBookingAdapter extends JsonAdapter{
    /**
     * Constructs adapter from JSON API response data
     *
     * @param dataNode JSON API response data
     */
    public JsonToBookingAdapter(ObjectNode dataNode) {
        super(dataNode);
    }

    @Override
    public Map<String, String> getStringProperties() {
        Map<String, String> properties = new HashMap();
        properties.put("id", adaptJsonString("id"));
        properties.put("notes", adaptJsonString("notes"));
        properties.put("smsPin", adaptJsonString("smsPin"));
        properties.put("customerId", adaptJsonNode("customer").get("id").textValue());
        properties.put("testingSiteId", adaptJsonNode("testingSite").get("id").textValue());
        properties.put("startTime", adaptJsonString("startTime"));
        properties.put("status", adaptJsonString("status"));
        return properties;
    }

    /**
     * Returns the nested JSON node properties of the JSON being adapted, in key-value pairs for the Site class to use
     * @return A Map containing the nested JSON properties of the JSON being adapted
     */
    public Map<String, JsonNode> getNodeProperties() {
        Map<String, JsonNode> properties = new HashMap();
        properties.put("revertStack", adaptJsonNode("revertStack"));
        return properties;
    }

}
