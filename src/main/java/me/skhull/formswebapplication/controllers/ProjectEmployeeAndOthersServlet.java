package me.skhull.formswebapplication.controllers;

import java.io.*;
import java.sql.SQLException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import me.skhull.formswebapplication.Utilities.PDFGenerator;
import me.skhull.formswebapplication.Utilities.Utilities;
import me.skhull.formswebapplication.database.DatabaseOps;
import me.skhull.formswebapplication.models.ProjectEmployeesAndOthersRequest;

@WebServlet(name = "ProjectEmployeeAndOthersServlet", urlPatterns = "/projemp")
public class ProjectEmployeeAndOthersServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Serve the LAN Port Form HTML
        getServletContext().getRequestDispatcher("/ProjectEmployeeAndOthersForm.html").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Request Parameters
        String firstName = request.getParameter("firstName");
        String middleName = request.getParameter("middleName");
        String lastName = request.getParameter("surname");
        String pfNumber = request.getParameter("pfNumber");
        String projectNumber = request.getParameter("projectNumber");
        String projectInvestigatorFullName = request.getParameter("projectInvestigatorFullName");
        String projectInvestigatorAddress = request.getParameter("projectInvestigatorAddress");
        String accountType = request.getParameter("accountType");
        String newOrRenewAcc = request.getParameter("newOrRenewAcc");
        String oldLoginID = request.getParameter("oldLoginID");
        String oldPfNumber = request.getParameter("oldPfNumber");
        String contactPersonal = request.getParameter("contactPersonal");
        String contactOffice = request.getParameter("contactOffice");
        String amount = request.getParameter("amount");
        String date = request.getParameter("date");

        ProjectEmployeesAndOthersRequest projEmpRequest = new ProjectEmployeesAndOthersRequest(firstName, middleName, lastName, pfNumber, projectNumber, projectInvestigatorFullName, projectInvestigatorAddress, accountType, newOrRenewAcc, oldLoginID, oldPfNumber, contactPersonal, contactOffice, amount, date);

        Utilities.serveResponse(projEmpRequest, request, response);

    }

}