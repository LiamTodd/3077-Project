package model.adapters;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.Map;

/**
 * Implements adapter pattern to adapt API response data into easy-to-use data format
 */
public abstract class JsonAdapter {

    protected ObjectNode dataNode;

    /**
     * Constructs adapter from JSON API response data
     * @param dataNode JSON API response data
     */
    public JsonAdapter(ObjectNode dataNode){
        this.dataNode = dataNode;
    }

    /**
     * Returns string properties of the JSON being adapted
     * @return A Map containing the string properties of the JSON being adapted
     */
    public abstract Map<String, String> getStringProperties();


    /**
     * Provides logic to access and format String data from the JSON Data
     * @param key The key for the JSON data being accessed
     * @return The String data accessed from the JSON data via the key
     */
    protected String adaptJsonString(String key){
        return dataNode.findValue(key).toString().replaceAll("^\"|\"$", "");
    }

    /**
     * Provides logic to find nested JSON data within the JSON data being adapted
     * @param key The key for the JSON data being accessed
     * @return The nested JSON data accessed from the JSON data via the key
     */
    protected JsonNode adaptJsonNode(String key){
        return dataNode.findValue(key);
    }

}
