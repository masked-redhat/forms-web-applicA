package me.skhull.formswebapplication.Utilities;

import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import me.skhull.formswebapplication.models.LANPortRequest;
import me.skhull.formswebapplication.models.ProjectEmployeesAndOthersRequest;
import me.skhull.formswebapplication.models.VMReallocationRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Arrays;
import java.util.Date;

public class PDFGenerator {
    static String LANPortPDFLoc = "LANPortForm.pdf";
    static String ProjectEmployeesAndOthersPDFLoc = "ProjectEmployeesAndOthers.pdf";
    static String VMReallocPDFLoc = "VMRenewForm.pdf";
    static String PDFOutputPathLoc = "E:\\P1_DDIA\\FormsWebApplication\\src\\main\\webapp\\OutputPDFs\\";
    static String PDFInputPathLoc = "E:\\P1_DDIA\\FormsWebApplication\\src\\main\\webapp\\PDFs\\";

    public static PdfReader inputPdfFilename(String inputSource, String pdfLoc) throws IOException {
        return new PdfReader(inputSource + pdfLoc);
    }

    public static String generatePDF(LANPortRequest request, String Filename, Date date) throws IOException {
        PdfReader originalDocument = inputPdfFilename(PDFInputPathLoc, LANPortPDFLoc);
        PdfWriter editedDocument = new PdfWriter(PDFOutputPathLoc + Filename);

        PdfDocument pdf = new PdfDocument(originalDocument, editedDocument);

        PdfAcroForm form = PdfAcroForm.getAcroForm(pdf, true);

        // Fill Form Fields with the information given
        form.getField("name").setValue(request.getName());
        form.getField("email").setValue(request.getEmail());
        form.getField("unit").setValue(request.getUnit());
        form.getField("description").setValue(request.getDescription());
        form.getField("pfNumber").setValue(request.getPfNumber());
        for (int i = 1; i<=10; i++) {
            form.getField("p"+i).setValue(request.getPhone().substring(i-1, i));
        }

        int[] filledDate = Utilities.getYearMonthDate(date);

        form.getField("date").setValue(String.valueOf(filledDate[2]));
        form.getField("month").setValue(String.valueOf(filledDate[1]));
        form.getField("year").setValue(String.valueOf(filledDate[0]));

        form.flattenFields();
        pdf.close();

        return PDFOutputPathLoc + Filename;
    }

    public static String generatePDF(ProjectEmployeesAndOthersRequest request, String Filename) throws IOException {
        PdfReader originalDocument = inputPdfFilename(PDFInputPathLoc, ProjectEmployeesAndOthersPDFLoc);
        PdfWriter editedDocument = new PdfWriter(PDFOutputPathLoc + Filename);

        PdfDocument pdf = new PdfDocument(originalDocument, editedDocument);

        PdfAcroForm form = PdfAcroForm.getAcroForm(pdf, true);

        form.getField("firstName").setValue(request.getFirstName());
        form.getField("middleName").setValue(request.getMiddleName());
        form.getField("surname").setValue(request.getSurname());
        form.getField("pfNumber").setValue(request.getPfNumber());
        form.getField("projectNumber").setValue(request.getProjectNumber());
        form.getField("projectInvestigatorFullName").setValue(request.getProjectInvestigatorFullName());
        form.getField("projectInvestigatorAddress").setValue(request.getProjectInvestigatorAddress());
        form.getField("typeAccount").setValue(request.getTypeAccount());
        form.getField("freshOrRenewal").setValue(request.isFreshAccount());
        form.getField("oldLoginID").setValue(request.getOldLoginID());
        form.getField("oldPfNumber").setValue(request.getOldPfNumber());
        form.getField("contactPersonal").setValue(request.getContactPersonal());
        form.getField("contactOffice").setValue(request.getContactOffice());
        form.getField("amt").setValue(Integer.toString(request.getAmt()));
        form.getField("date").setValue(request.getDate());

        form.flattenFields();
        pdf.close();

        return PDFOutputPathLoc + Filename;
    }

    public static String generatePDF(VMReallocationRequest request, String Filename) throws IOException {
        PdfReader originalDocument = inputPdfFilename(PDFInputPathLoc, VMReallocPDFLoc);
        PdfWriter editedDocument = new PdfWriter(PDFOutputPathLoc + Filename);

        PdfDocument pdf = new PdfDocument(originalDocument, editedDocument);

        PdfAcroForm form = PdfAcroForm.getAcroForm(pdf, true);

        String services = Utilities.removeStart(Arrays.toString(request.getServices()), "[");
        services = Utilities.removeEnd(services, "]");

        form.getField("name").setValue(request.getUserName());
        form.getField("pfNumber").setValue(request.getPfNumber());
        form.getField("email").setValue(request.getEmail());
        form.getField("department").setValue(request.getDepartment());
        form.getField("phone").setValue(request.getMobileNumber());
        form.getField("ip").setValue(request.getVmIp());
        form.getField("purpose").setValue(request.getVmPurpose());
        form.getField("services").setValue(services);
        form.getField("fdn").setValue(request.getVmFullDomainName());

        form.flattenFields();
        pdf.close();

        return PDFOutputPathLoc + Filename;
    }

    public static void servePDF(HttpServletRequest request, HttpServletResponse response, String FileName, String refKey) throws ServletException, IOException {
        // PDF file
        File pdfFile = new File(FileName);

        // Response header
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "inline; filename=\""+refKey+".pdf\"");

        // Stream the PDF file
        InputStream inputStream = new FileInputStream(FileName);
        OutputStream outputStream = response.getOutputStream();

        byte[] buffer = new byte[1024];
        int bytesRead = -1;

        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }

        inputStream.close();
        outputStream.close();

    }
}
