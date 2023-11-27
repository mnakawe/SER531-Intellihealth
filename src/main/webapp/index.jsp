<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Health Questionnaire</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            display: flex;
            align-items: center;
            justify-content: center;
            height: 100vh;
        }

        form {
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            text-align: left;
            max-width: 600px;
            width: 100%;
        }

        h1 {
            color: #333;
        }

        label {
            display: inline-block;
            margin: 10px 0;
            font-weight: bold;
            width: 100%;
        }

        input, select {
            width: calc(100% - 10px);
            padding: 8px;
            margin: 5px 0;
            box-sizing: border-box;
            border: 1px solid #ccc;
            border-radius: 4px;
            outline: none;
            display: inline-block;
        }

        select {
            width: calc(100% - 10px);
        }

        input[type="submit"] {
            background-color: #4caf50;
            color: #fff;
            padding: 10px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            display: inline-block;
            width: auto;
        }

        input[type="submit"]:hover {
            background-color: #45a049;
        }

        .disease-options {
            margin-top: 10px;
        }

        .disease-option {
            display: inline-block;
            margin-right: 20px;
        }
    </style>
</head>
<body>

<form action="processInput" method="post">
    <h1>User Health Questionnaire</h1>

    <label for="age">1. Age:</label>
    <input type="number" id="age" name="age" required/>

    <label for="height">2. Height (cm):</label>
    <input type="number" id="height" name="height" required/>

    <label for="weight">3. Weight (kg):</label>
    <input type="number" id="weight" name="weight" required/>

    <label for="gender">4. Gender:</label>
    <select id="gender" name="gender" required>
        <option value="male">Male</option>
        <option value="female">Female</option>
    </select>

    <label for="alcohol">5. Do you consume alcohol?</label>
    <select id="alcohol" name="alcohol" required>
        <option value="yes">Yes</option>
        <option value="no">No</option>
    </select>

    <label for="smoke">6. Do you smoke?</label>
    <select id="smoke" name="smoke" required>
        <option value="yes">Yes</option>
        <option value="no">No</option>
    </select>

    <label>7. Do you take any medications for any of the following health conditions listed below:?</label>
    <div class="disease-options">
        <label class="disease-option">Cholesterol: <select name="cholesterol"><option value="yes">Yes</option><option value="no">No</option></select></label>
        <label class="disease-option">Hypertension: <select name="hypertension"><option value="yes">Yes</option><option value="no">No</option></select></label>
        <label class="disease-option">COPD: <select name="copd"><option value="yes">Yes</option><option value="no">No</option></select></label>
        <label class="disease-option">Diabetes: <select name="diabetes"><option value="yes">Yes</option><option value="no">No</option></select></label>
        <label class="disease-option">Muscular Problems: <select name="muscularProblems"><option value="yes">Yes</option><option value="no">No</option></select></label>
    </div>

    <input type="submit" value="Submit"/>
</form>

</body>
</html>
