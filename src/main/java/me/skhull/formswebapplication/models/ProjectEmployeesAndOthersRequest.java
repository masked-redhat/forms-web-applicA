package me.skhull.formswebapplication.models;

import me.skhull.formswebapplication.Utilities.Utilities;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.Objects;

// Java Bean

public class ProjectEmployeesAndOthersRequest implements Serializable {
    private String firstName;
    private String middleName;
    private String surname;
    private String pfNumber;
    private String projectNumber;
    private String projectInvestigatorFullName;
    private String projectInvestigatorAddress;
    private String typeAccount;
    private String isFreshAccount;
    private String oldLoginID;
    private String oldPfNumber;
    private String contactPersonal;
    private String contactOffice;
    private int amt;
    private String date;

    public ProjectEmployeesAndOthersRequest() {
    }

    public ProjectEmployeesAndOthersRequest(String firstName, String middleName, String surname, String pfNumber, String projectNumber, String projectInvestigatorFullName, String projectInvestigatorAddress, String typeAccount, String isFreshAccount, String oldLoginID, String oldPfNumber, String contactPersonal, String contactOffice, String amt, String date) {
        this.firstName = Utilities.capitalize(firstName.trim());
        this.middleName = Utilities.capitalize(middleName.trim());
        this.surname = Utilities.capitalize(surname.trim());
        this.pfNumber = pfNumber.trim().toUpperCase();
        this.projectNumber = projectNumber.trim();
        this.projectInvestigatorFullName = Utilities.capitalize(projectInvestigatorFullName.trim());
        this.projectInvestigatorAddress = Utilities.capitalize(projectInvestigatorAddress.trim());
        this.typeAccount = Utilities.capitalize(typeAccount.trim());
        this.isFreshAccount = Utilities.capitalize(isFreshAccount.trim());
        this.oldLoginID = oldLoginID.trim();
        this.oldPfNumber = oldPfNumber.trim();
        this.contactPersonal = contactPersonal.trim();
        this.contactOffice = contactOffice.trim();
        this.amt = amt.trim().isEmpty() ?0:Integer.parseInt(amt.trim());
        this.date = date.trim();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getSurname() {
        return surname;
    }

    public String getPfNumber() {
        return pfNumber;
    }

    public String getProjectNumber() {
        return projectNumber;
    }

    public String getProjectInvestigatorFullName() {
        return projectInvestigatorFullName;
    }

    public String getProjectInvestigatorAddress() {
        return projectInvestigatorAddress;
    }

    public String getTypeAccount() {
        return typeAccount;
    }

    public String isFreshAccount() {
        return isFreshAccount;
    }

    public String getOldLoginID() {
        return oldLoginID;
    }

    public String getOldPfNumber() {
        return oldPfNumber;
    }

    public String getContactPersonal() {
        return contactPersonal;
    }

    public String getContactOffice() {
        return contactOffice;
    }

    public int getAmt() {
        return amt;
    }

    public String getDate() {
        return date;
    }

    public void printRequestDetails(){
        System.out.println("First Name: " + firstName);
        System.out.println("Middle Name: " + middleName);
        System.out.println("Surname: " + surname);
        System.out.println("Pf Number: " + pfNumber);
        System.out.println("Project Number: " + projectNumber);
        System.out.println("Project Investigator Full Name: " + projectInvestigatorFullName);
        System.out.println("Project Investigator Address: " + projectInvestigatorAddress);
        System.out.println("Type Account: " + typeAccount);
        System.out.println("Fresh Account: " + isFreshAccount);
        System.out.println("Old Login ID: " + oldLoginID);
        System.out.println("Old Pf Number: " + oldPfNumber);
        System.out.println("Contact Personal: " + contactPersonal);
        System.out.println("Contact Office: " + contactOffice);
        System.out.println("Amount: " + amt);
        System.out.println("Date of Birth: " + date);
    }

    public boolean validateRequestDetails(){
        if (firstName==null || pfNumber==null || projectNumber==null || projectInvestigatorFullName==null || projectInvestigatorAddress==null || typeAccount==null || contactPersonal==null || contactOffice==null || amt<0){
            return false;
        }

        if (!(Objects.equals(isFreshAccount, "New") || Objects.equals(isFreshAccount, "Renew"))){return false;}

        if (!(Objects.equals(typeAccount, "Web") || Objects.equals(typeAccount, "Email") || Objects.equals(typeAccount, "Shell"))){return false;}

        return Utilities.validatePfNumber(pfNumber) && Utilities.validatePfNumber(oldPfNumber) && Utilities.validatePhone(contactPersonal) && Utilities.validatePhone(contactOffice);
    }

}
