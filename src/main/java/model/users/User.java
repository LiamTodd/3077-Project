package model.users;

import model.adapters.JsonAdapter;

import java.util.Map;

/**
 * This class represents a Person in the system
 */

public abstract class User {
    private String givenName;
    private String familyName;
    private String id;
    private String userName;
    private String phoneNumber;
    private String worksAt;

    /**
     * Constructs a User using an Adapter to convert raw API response data
     * @param adapter The adapter for the API's response
     */
    public User(JsonAdapter adapter){
        Map<String, String> properties = adapter.getStringProperties();
        this.userName = properties.get("userName");
        this.givenName = properties.get("givenName");
        this.familyName = properties.get("familyName");
        this.id = properties.get("id");
        this.phoneNumber = properties.get("phoneNumber");
        this.worksAt = properties.get("worksAt");
    }


    /**
     * Displays formatted data for the User
     * @return
     */
    @Override
    public String toString() {
        return "Person{" +
                "givenName='" + givenName + '\'' +
                ", familyName='" + familyName + '\'' +
                ", id='" + id + '\'' +
                ", userName='" + userName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }

    public abstract boolean isPatient();
    public abstract boolean isReceptionist();
    public abstract boolean isAdministerer();

    /**
     * Returns the username of the user
     * @return The user name of the user
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Returns the id of the user
     * @return the id of the user
     */
    public String getId(){
        return id;
    }

    public String getWorksAt(){
        return worksAt;
    }

}
