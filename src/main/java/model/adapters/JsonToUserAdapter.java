package model.adapters;

import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.HashMap;
import java.util.Map;

/**
 * Used to adapt the API response for a User into an easily accessible format for the User class hierarchy
 */
public class JsonToUserAdapter extends JsonAdapter {
    public JsonToUserAdapter(ObjectNode dataNode) {
        super(dataNode);
    }

    /**
     * Returns string properties of the JSON being adapted, in key-value pairs for the User class to use
     * @return A Map containing the string properties of the JSON being adapted
     */
    @Override
    public Map<String, String> getStringProperties() {
        Map<String, String> properties = new HashMap();
        properties.put("userName", adaptJsonString("userName"));
        properties.put("givenName", adaptJsonString("givenName"));
        properties.put("familyName", adaptJsonString("familyName"));
        properties.put("id", adaptJsonString("id"));
        properties.put("phoneNumber", adaptJsonString("phoneNumber"));
        try {
            properties.put("worksAt", adaptJsonString("worksAt"));
        }
        catch (Exception ignored){

        }
        return properties;
    }
}
