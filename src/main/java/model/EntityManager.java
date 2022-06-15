package model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

/**
 * An abstract class which manages sets of entities in the system
 */
public abstract class EntityManager {

    private String myApiKey;
    protected static String url;

    /**
     * Finds all entities of a certain type
     * @return An ArrayList of all entities of a certain type in the system
     * @throws Exception if the API response is invalid when retrieving the entities
     */
    protected ArrayList findAllEntities() throws Exception {

        APISetUp a = APISetUp.getInstance();
        this.myApiKey = a.getApiKey();

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest
                .newBuilder(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        // This request is authenticated (API key attached in the Authorization header), and will return the JSON array object containing all users.
        client = HttpClient.newHttpClient();
        request = HttpRequest
                .newBuilder(URI.create(url))
                .setHeader("Authorization", myApiKey)
                .GET()
                .build();

        response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Error checking for this sample code. You can check the status code of your request, as part of performing error handling in your assignment.
        if (response.statusCode() != 200) {
            throw new Exception();
        }

        ObjectNode[] jsonNodes = new ObjectMapper().readValue(response.body(), ObjectNode[].class);

        addEntities(jsonNodes);
        ArrayList entities = addEntities(jsonNodes);

        return entities;

    }

    protected abstract ArrayList addEntities(ObjectNode[] entities) throws Exception;

}

