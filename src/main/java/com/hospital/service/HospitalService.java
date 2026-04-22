package com.hospital.service;

import com.hospital.exception.DuplicatePatientException;
import com.hospital.exception.InvalidAgeException;
import com.hospital.exception.PatientNotFoundException;
import com.hospital.patient.Patient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HospitalService {

    public void addPatient(Patient p) throws InvalidAgeException, DuplicatePatientException, SQLException {
        if (p.getAge() < 0 || p.getAge() > 120) {
            throw new InvalidAgeException("Age must be between 0 and 120.");
        }

        String checkSql = "SELECT COUNT(*) FROM patients WHERE patientId = ?";
        String insertSql = "INSERT INTO patients (patientId, patientName, age, disease) VALUES (?, ?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement checkStmt = connection.prepareStatement(checkSql)) {

            checkStmt.setInt(1, p.getPatientId());
            try (ResultSet rs = checkStmt.executeQuery()) {
                if (rs.next() && rs.getInt(1) > 0) {
                    throw new DuplicatePatientException("Patient ID already exists.");
                }
            }

            try (PreparedStatement insertStmt = connection.prepareStatement(insertSql)) {
                insertStmt.setInt(1, p.getPatientId());
                insertStmt.setString(2, p.getPatientName());
                insertStmt.setInt(3, p.getAge());
                insertStmt.setString(4, p.getDisease());
                insertStmt.executeUpdate();
            }
        }

        if (p.getAge() > 60 && "Heart Problem".equalsIgnoreCase(p.getDisease())) {
            System.out.println("⚠ Priority Patient – Immediate Attention Required");
        }
    }

    public Patient searchPatient(int patientId) throws PatientNotFoundException, SQLException {
        String sql = "SELECT patientId, patientName, age, disease FROM patients WHERE patientId = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, patientId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Patient(
                            rs.getInt("patientId"),
                            rs.getString("patientName"),
                            rs.getInt("age"),
                            rs.getString("disease")
                    );
                }
            }
        }

        throw new PatientNotFoundException("Patient not found for ID: " + patientId);
    }

    public List<Patient> displayPatients() throws SQLException {
        List<Patient> patients = new ArrayList<>();
        String sql = "SELECT patientId, patientName, age, disease FROM patients";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                patients.add(new Patient(
                        rs.getInt("patientId"),
                        rs.getString("patientName"),
                        rs.getInt("age"),
                        rs.getString("disease")
                ));
            }
        }

        return patients;
    }
}
