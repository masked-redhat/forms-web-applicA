package me.skhull.formswebapplication.controllers;

import java.io.*;
import java.sql.SQLException;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import me.skhull.formswebapplication.Utilities.PDFGenerator;
import me.skhull.formswebapplication.Utilities.Utilities;
import me.skhull.formswebapplication.database.DatabaseOps;
import me.skhull.formswebapplication.models.LANPortRequest;
import me.skhull.formswebapplication.models.VMReallocationRequest;

@WebServlet(name = "VMReallocationFormServlet", urlPatterns = "/vmrealloc")
public class VMReallocationFormServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Serve the LAN Port Form HTML
        getServletContext().getRequestDispatcher("/VMReallocationForm.html").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Request Parameters
        String userName = request.getParameter("userName");
        String pfNumber = request.getParameter("pfNumber");
        String department = request.getParameter("department");
        String email = request.getParameter("email");
        String mobileNumber = request.getParameter("mobileNumber");
        String vmIp = request.getParameter("vmIp");
        String vmFullDomainName = request.getParameter("fullDomainName");
        String[] servicesArray = request.getParameterValues("services");
        String otherService = request.getParameter("otherService");
        String vmPurpose = request.getParameter("vmPurpose");

        String[] services= new String[6];
        int k = 0;
        if (servicesArray != null) {
            for (int i = 0; i < servicesArray.length; i++) {
                if (Objects.equals(servicesArray[i], "other") && otherService!=null && !otherService.isEmpty()) {
                    String[] otherServiceArray = Arrays.stream(otherService.split(",")).map(String::trim).toArray(String[]::new);
                    for (int j = 0; j < otherServiceArray.length; j++) {
                        services[i++] = otherServiceArray[j];
                        k++;
                    }
                    continue;
                }
                k++;
                services[i] = servicesArray[i];
            }
        }

        VMReallocationRequest vmreallocRequest = new VMReallocationRequest(userName, pfNumber, department, email, mobileNumber, vmIp, vmFullDomainName, Arrays.copyOfRange(services, 0, k), vmPurpose);

        Utilities.serveResponse(vmreallocRequest, request, response);


    }

}