package model.users;

import model.adapters.JsonAdapter;

/**
 * Represents a Receptionist User
 */
public class Receptionist extends User {


    public Receptionist(JsonAdapter adapter) {
        super(adapter);
    }

    @Override
    public boolean isPatient() {
        return false;
    }

    @Override
    public boolean isReceptionist() {
        return true;
    }

    @Override
    public boolean isAdministerer() {
        return false;
    }


}
