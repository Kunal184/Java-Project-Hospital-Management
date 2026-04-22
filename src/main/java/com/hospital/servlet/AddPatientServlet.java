package com.hospital.servlet;

import com.hospital.patient.Patient;
import com.hospital.service.HospitalService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/addPatient")
public class AddPatientServlet extends HttpServlet {
    private final HospitalService hospitalService = new HospitalService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int patientId = Integer.parseInt(request.getParameter("patientId"));
            String patientName = request.getParameter("patientName");
            int age = Integer.parseInt(request.getParameter("age"));
            String disease = request.getParameter("disease");

            Patient patient = new Patient(patientId, patientName, age, disease);
            hospitalService.addPatient(patient);

            response.sendRedirect(request.getContextPath() + "/displayPatients");
        } catch (Exception e) {
            request.setAttribute("errorMessage", e.getMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher("/error.jsp");
            dispatcher.forward(request, response);
        }
    }
}
