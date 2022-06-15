package model.users;

import model.adapters.JsonAdapter;


public class Administerer extends User {


    public Administerer(JsonAdapter adapter) {
        super(adapter);
    }

    @Override
    public boolean isPatient() {
        return false;
    }

    @Override
    public boolean isReceptionist() {
        return false;
    }

    @Override
    public boolean isAdministerer() {
        return true;
    }


}
