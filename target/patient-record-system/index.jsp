<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Hospital Patient Record System</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 30px; }
        a { display: block; margin: 10px 0; }
    </style>
</head>
<body>
<h2>Hospital Patient Record System</h2>
<a href="addPatient.jsp">Add Patient</a>
<a href="displayPatients">View All Patients</a>

<h3>Search Patient</h3>
<form action="searchPatient" method="get">
    <label>Patient ID:</label>
    <input type="number" name="patientId" required>
    <button type="submit">Search</button>
</form>
</body>
</html>
