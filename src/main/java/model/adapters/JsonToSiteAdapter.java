package model.adapters;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.HashMap;
import java.util.Map;

/**
 * Used to adapt the JSON API response for a testing site into an easily accessible data type for the Site class to use
 */
public class JsonToSiteAdapter extends JsonAdapter {
    public JsonToSiteAdapter(ObjectNode dataNode) {
        super(dataNode);
    }

    /**
     * Returns string properties of the JSON being adapted, in key-value pairs for the Site class to use
     * @return A Map containing the string properties of the JSON being adapted
     */
    @Override
    public Map<String, String> getStringProperties() {
        Map<String, String> properties = new HashMap();
        properties.put("name", adaptJsonString("name"));
        properties.put("id", adaptJsonString("id"));
        properties.put("description", adaptJsonString("description"));
        properties.put("websiteUrl", adaptJsonString("websiteUrl"));
        properties.put("phoneNumber", adaptJsonString("phoneNumber"));
        properties.put("latitude", adaptJsonString("latitude"));
        properties.put("longitude", adaptJsonString("longitude"));
        properties.put("suburb", adaptJsonString("suburb"));
        properties.put("state", adaptJsonString("state"));
        properties.put("postcode", adaptJsonString("postcode"));
        properties.put("waitTime", adaptJsonString("waitTime"));
        properties.put("status", adaptJsonString("openStatus"));
        return properties;
    }

    /**
     * Returns the nested JSON node properties of the JSON being adapted, in key-value pairs for the Site class to use
     * @return A Map containing the nested JSON properties of the JSON being adapted
     */
    public Map<String, JsonNode> getNodeProperties() {
        Map<String, JsonNode> properties = new HashMap();
        properties.put("siteTypes", adaptJsonNode("siteTypes"));
        properties.put("testTypes", adaptJsonNode("testTypes"));
        return properties;
    }
}
