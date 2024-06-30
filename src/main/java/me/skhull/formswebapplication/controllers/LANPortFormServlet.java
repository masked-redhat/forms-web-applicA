package me.skhull.formswebapplication.controllers;

import java.io.*;
import java.sql.SQLException;
import java.util.Date;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import me.skhull.formswebapplication.Utilities.PDFGenerator;
import me.skhull.formswebapplication.Utilities.Utilities;
import me.skhull.formswebapplication.database.DatabaseOps;
import me.skhull.formswebapplication.models.LANPortRequest;

@WebServlet(name = "LANPortFormServlet", urlPatterns = "/lanportform")
public class LANPortFormServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Serve the LAN Port Form HTML
        getServletContext().getRequestDispatcher("/LANPortForm.html").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Request Parameters
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String description = request.getParameter("description");
        String pfNumber = request.getParameter("pfNumber");
        String unit = request.getParameter("unit");

        LANPortRequest lanPortRequest = new LANPortRequest(name, email, phone, description, pfNumber, unit);

        Utilities.serveResponse(lanPortRequest, request, response);

    }

}