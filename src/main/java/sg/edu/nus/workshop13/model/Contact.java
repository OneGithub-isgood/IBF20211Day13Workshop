package sg.edu.nus.workshop13.model;

import java.util.Random;

public class Contact {
    private String name;
    private String email;
    private int phoneNum;
    private String userID;

    public Contact() {
        String userIDString = "";
        Random randLogic = new Random();
        for (int hexChar = 0; hexChar < 8; hexChar++) {
            userIDString = userIDString + (Integer.toHexString(randLogic.nextInt(16))).toString();
        }
        this.userID = userIDString;
    }

    public Contact(String IDString) {
        this.userID = IDString;
    }

    public Contact(String IDString, String name, String email, int number) {
        this.userID = IDString;
        this.name = name;
        this.email = email;
        this.phoneNum = number;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhoneNum() {
        return this.phoneNum;
    }

    public void setPhoneNum(int number) {
        this.phoneNum = number;
    }

    public String getUserID() {
        return this.userID;
    }

    public void setUserID(String idString) {
        this.userID = idString;
    }
}
