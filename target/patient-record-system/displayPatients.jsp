<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.hospital.patient.Patient" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>All Patients</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 30px; }
        table { border-collapse: collapse; width: 80%; }
        th, td { border: 1px solid #999; padding: 8px; text-align: left; }
        th { background-color: #f0f0f0; }
    </style>
</head>
<body>
<h2>Patient List</h2>
<table>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Age</th>
        <th>Disease</th>
    </tr>
    <%
        List<Patient> patients = (List<Patient>) request.getAttribute("patients");
        if (patients != null) {
            for (Patient p : patients) {
    %>
    <tr>
        <td><%= p.getPatientId() %></td>
        <td><%= p.getPatientName() %></td>
        <td><%= p.getAge() %></td>
        <td><%= p.getDisease() %></td>
    </tr>
    <%
            }
        }
    %>
</table>
<p><a href="index.jsp">Back to Home</a></p>
</body>
</html>
