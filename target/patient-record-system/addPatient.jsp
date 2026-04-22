<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Patient</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 30px; }
        input { margin: 6px 0; padding: 6px; width: 250px; }
        button { padding: 6px 12px; }
    </style>
</head>
<body>
<h2>Add Patient</h2>
<form action="addPatient" method="post">
    <div>
        <label>Patient ID:</label><br>
        <input type="number" name="patientId" required>
    </div>
    <div>
        <label>Patient Name:</label><br>
        <input type="text" name="patientName" required>
    </div>
    <div>
        <label>Age:</label><br>
        <input type="number" name="age" required>
    </div>
    <div>
        <label>Disease:</label><br>
        <input type="text" name="disease" required>
    </div>
    <button type="submit">Save Patient</button>
</form>
<p><a href="index.jsp">Back to Home</a></p>
</body>
</html>
