package model.users;

import model.adapters.JsonAdapter;

/**
 * Represents a customer in the system
 */
public class Patient extends User {

    public Patient(JsonAdapter adapter) {
        super(adapter);
    }

    @Override
    public boolean isPatient() {
        return true;
    }

    @Override
    public boolean isReceptionist() {
        return false;
    }

    @Override
    public boolean isAdministerer() {
        return false;
    }

}
