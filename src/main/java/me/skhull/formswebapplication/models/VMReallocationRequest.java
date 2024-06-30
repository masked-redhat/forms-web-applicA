package me.skhull.formswebapplication.models;

import me.skhull.formswebapplication.Utilities.Utilities;

import java.io.Serializable;
import java.sql.Array;
import java.util.Arrays;

// Java Bean

public class VMReallocationRequest implements Serializable {
    private String userName;
    private String pfNumber;
    private String department;
    private String email;
    private String mobileNumber;
    private String vmIp;
    private String vmFullDomainName;
    private String[] services;
    private String vmPurpose;

    public VMReallocationRequest() {
    }

    public VMReallocationRequest(String userName, String pfNumber, String department, String email, String mobileNumber, String vmIp, String vmFullDomainName, String[] services, String vmPurpose) {
        this.userName = Utilities.capitalize(userName.trim());
        this.pfNumber = pfNumber.trim();
        this.department = Utilities.capitalize(department.trim());
        this.email = email.replace("@iitk.ac.in", "").trim()+"@iitk.ac.in";
        this.mobileNumber = mobileNumber.trim();
        this.vmIp = vmIp.trim();
        this.vmFullDomainName = vmFullDomainName.trim();
        this.services = services;
        this.vmPurpose = vmPurpose.trim();
    }

    public String getUserName() {
        return userName;
    }

    public String getPfNumber() {
        return pfNumber;
    }

    public String getDepartment() {
        return department;
    }

    public String getEmail() {
        return email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public String getVmIp() {
        return vmIp;
    }

    public String getVmFullDomainName() {
        return vmFullDomainName;
    }

    public String[] getServices() {
        return services;
    }

    public String getVmPurpose() {
        return vmPurpose;
    }

    public void printRequestDetails() {
        System.out.println("User Name: " + userName);
        System.out.println("P.F. Number: " + pfNumber);
        System.out.println("Department: " + department);
        System.out.println("Email: " + email);
        System.out.println("Mobile Number: " + mobileNumber);
        System.out.println("VM IP: " + vmIp);
        System.out.println("VM Full Domain Name: " + vmFullDomainName);
        System.out.println("Services: " + Arrays.toString(services));
        System.out.println("VM Purpose: " + vmPurpose);
    }

    public boolean validateRequestDetails() {
        if (userName == null || pfNumber == null || department == null || email == null || mobileNumber == null || vmIp == null || services == null || vmPurpose == null ||
                userName.isBlank() || pfNumber.isBlank() || department.isBlank() || email.isBlank() || mobileNumber.isBlank() || vmIp.isBlank() || vmPurpose.isBlank()) {
            return false;
        }

        return Utilities.validateEmail(email) && Utilities.validatePhone(mobileNumber) && Utilities.validatePfNumber(pfNumber) && Utilities.validateIp(vmIp);
    }
}
