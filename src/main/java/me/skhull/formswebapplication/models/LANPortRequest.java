package me.skhull.formswebapplication.models;

import me.skhull.formswebapplication.Utilities.Utilities;

import java.io.Serializable;

// Java Bean

public class LANPortRequest implements Serializable {
    private String name;
    private String email;
    private String phone;
    private String pfNumber;
    private String description;
    private String unit;

    public LANPortRequest() {
    }

    public LANPortRequest(String name, String email, String phone, String description, String pfNumber, String unit) {
        this.pfNumber = pfNumber.trim().toUpperCase();
        this.unit = Utilities.capitalize(unit.trim());
        this.name = Utilities.capitalize(name.trim());
        this.email = email.replace("@iitk.ac.in", "").trim()+"@iitk.ac.in";
        this.phone = phone.trim();
        this.description = description.trim();
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getPfNumber() {
        return pfNumber;
    }

    public String getDescription() {
        return description;
    }

    public String getUnit() {
        return unit;
    }

    public void printRequestDetails() {
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Phone: " + phone);
        System.out.println("PF Number: " + pfNumber);
        System.out.println("Description: " + description);
        System.out.println("Unit: " + unit);
    }

    public boolean validateRequestDetails() {
        if (name == null || email == null || phone == null || pfNumber == null || description == null || unit == null ||
                name.isBlank() || email.isBlank() || phone.isBlank() || pfNumber.isBlank() || description.isBlank() ||
                unit.isBlank()) {
            return false;
        }

        return Utilities.validateEmail(email) && Utilities.validatePhone(phone) && Utilities.validatePfNumber(pfNumber);
    }
}
