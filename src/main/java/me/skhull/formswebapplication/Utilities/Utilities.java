package me.skhull.formswebapplication.Utilities;

import me.skhull.formswebapplication.database.DatabaseOps;
import me.skhull.formswebapplication.models.LANPortRequest;
import me.skhull.formswebapplication.models.ProjectEmployeesAndOthersRequest;
import me.skhull.formswebapplication.models.VMReallocationRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.Array;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class Utilities {
    public static String capitalize(String str) {
        if (str == null || str.isEmpty()) {return "";}
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    public static String removeStart(final String str, final String remove) {
        if (str.isEmpty() || remove.isEmpty()) {
            return str;
        }
        if (str.startsWith(remove)){
            return str.substring(remove.length());
        }
        return str;
    }

    public static String removeEnd(final String str, final String remove) {
        if (str.isEmpty() || remove.isEmpty()) {
            return str;
        }
        if (str.endsWith(remove)){
            return str.substring(0, str.length() - remove.length());
        }
        return str;
    }

    public static boolean validatePhone(String phone) {
        String phoneNum = phone
                .replace("+91", "")
                .replace("-", "")
                .trim();

        return phone.matches("^\\d{10,14}$");
    }

    public static boolean validateEmail(String email) {
        // Write Checks here for all the validation of the email
        // and the name and the unit/dept. matches

        return email != null && email.matches("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$");
    }

    public static boolean validatePfNumber(String pfNumber) {
        return pfNumber.matches("^[a-zA-Z0-9]*$");
    }

    public static boolean validateIp(String ip) {
        return ip != null && ip.matches("^(?:[0-9]{1,3}\\.){3}[0-9]{1,3}$");
    }

    public static void serveInvalidResponse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain");

        PrintWriter out = response.getWriter();

        out.println("Invalid Response");
    }

    public static java.sql.Date getCurrentSqlDate(Date date){
        return new java.sql.Date(date.getTime());
    }

    public static int[] getYearMonthDate(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        return new int[]{year, month, day};
    }

    public static void serveResponse(LANPortRequest lanPortRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        boolean dataSavedInDatabase = false;
        Date date = new Date();
        String ReferenceKey = UUID.randomUUID().toString();

        if (lanPortRequest.validateRequestDetails()){
            try {
                dataSavedInDatabase = DatabaseOps.insertData(lanPortRequest, date, ReferenceKey);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        if (dataSavedInDatabase) {
            String generatedPDFLocation = PDFGenerator.generatePDF(lanPortRequest, ReferenceKey+".pdf", date);
            PDFGenerator.servePDF(request, response, generatedPDFLocation, ReferenceKey);
        } else{
            Utilities.serveInvalidResponse(request, response);
        }
    }

    public static void serveResponse(ProjectEmployeesAndOthersRequest projEmpRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        boolean dataSavedInDatabase = false;
        Date date = new Date();
        String ReferenceKey = UUID.randomUUID().toString();

        if (projEmpRequest.validateRequestDetails()){
            try {
                dataSavedInDatabase = DatabaseOps.insertData(projEmpRequest, date, ReferenceKey);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        if (dataSavedInDatabase) {
            String generatedPDFLocation = PDFGenerator.generatePDF(projEmpRequest, ReferenceKey+".pdf");
            PDFGenerator.servePDF(request, response, generatedPDFLocation, ReferenceKey);
        } else{
            Utilities.serveInvalidResponse(request, response);
        }
    }

    public static void serveResponse(VMReallocationRequest vmReallocationRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        boolean dataSavedInDatabase = false;
        Date date = new Date();
        String ReferenceKey = UUID.randomUUID().toString();

        if (vmReallocationRequest.validateRequestDetails()){
            try {
                dataSavedInDatabase = DatabaseOps.insertData(vmReallocationRequest, date, ReferenceKey);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        if (dataSavedInDatabase) {
            String generatedPDFLocation = PDFGenerator.generatePDF(vmReallocationRequest, ReferenceKey+".pdf");
            PDFGenerator.servePDF(request, response, generatedPDFLocation, ReferenceKey);
        } else{
            Utilities.serveInvalidResponse(request, response);
        }
    }

}
