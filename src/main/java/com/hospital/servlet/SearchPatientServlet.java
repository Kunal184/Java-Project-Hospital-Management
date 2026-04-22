package com.hospital.servlet;

import com.hospital.exception.PatientNotFoundException;
import com.hospital.patient.Patient;
import com.hospital.service.HospitalService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SearchPatientServlet extends HttpServlet {
    private final HospitalService hospitalService = new HospitalService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int patientId = Integer.parseInt(request.getParameter("patientId"));
            Patient patient = hospitalService.searchPatient(patientId);
            request.setAttribute("patient", patient);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/searchResult.jsp");
            dispatcher.forward(request, response);
        } catch (PatientNotFoundException e) {
            request.setAttribute("errorMessage", e.getMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher("/error.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            request.setAttribute("errorMessage", "Invalid request or server error.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/error.jsp");
            dispatcher.forward(request, response);
        }
    }
}
