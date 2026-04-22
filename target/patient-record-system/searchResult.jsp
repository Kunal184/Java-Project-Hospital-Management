<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.hospital.patient.Patient" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Search Result</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 30px; }
    </style>
</head>
<body>
<h2>Patient Details</h2>
<%
    Patient patient = (Patient) request.getAttribute("patient");
    if (patient != null) {
%>
<p><strong>ID:</strong> <%= patient.getPatientId() %></p>
<p><strong>Name:</strong> <%= patient.getPatientName() %></p>
<p><strong>Age:</strong> <%= patient.getAge() %></p>
<p><strong>Disease:</strong> <%= patient.getDisease() %></p>
<%
    }
%>
<p><a href="index.jsp">Back to Home</a></p>
</body>
</html>
