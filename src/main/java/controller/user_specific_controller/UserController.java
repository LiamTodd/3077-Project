package controller.user_specific_controller;

import controller.Controller;

import java.util.Scanner;

/**
 * This class represents a Person in the system
 */

public abstract class UserController extends Controller {
    private String worksAt;

    public UserController(){
        super(new Scanner(System.in));
    }

    public void setWorksAt(String worksAt) {
        this.worksAt = worksAt;
    }


    public String getWorksAt(){
        return worksAt;
    }

}
