#!/usr/bin/env python3
import pandas as pd
import os
from rdflib import Graph, Namespace, Literal, RDF, XSD

# Define the base namespace for your ontology
base_namespace = Namespace("https://example.com/ontology/")

# Load your RDF ontology
ontology_file = "/Users/minalnakawe/SER531-Intellihealth/Data_Cleaning/intellihealth.rdf"
g = Graph()
g.parse(ontology_file, format="xml")

# Specify the directory containing your cleaned CSV files
cleaned_directory = '/Users/minalnakawe/SER531-Intellihealth/Data_Cleaning/Datasets/cleaned'

# List all cleaned CSV files in the directory
cleaned_files = [file for file in os.listdir(cleaned_directory) if file.startswith("cleaned_") and file.endswith(".csv")]

# Property mapping for columns to ontology properties
property_mapping = {
    "AGE": base_namespace.hasAge,
    "SEX": base_namespace.hasGender,
    "AP_HIGH": base_namespace.hasBloodPressure,
    "CHOLESTEROL": base_namespace.hasCholesterol,
    "GLUCOSE": base_namespace.hasGlucose,
    "SMOKE": base_namespace.isSmoker,
    "CARDIO_DISEASE": base_namespace.sufferedFromCardiovascular,
    "COPDSEVERITY": base_namespace.hasCOPDSeverity,
    "MWT1Best": base_namespace.passesWalkTest,
    "gender": base_namespace.hasGender,
    "smoking": base_namespace.isSmoker,
    "Diabetes": base_namespace.hasDiabetes,
    "muscular": base_namespace.hasMuscularProblems,
    "hypertension": base_namespace.hasBloodPressure,
    "IHD": base_namespace.hasIschemicHeartDisease,
    "PNEUMONIA": base_namespace.hasPneumonia,
    "DIABETES": base_namespace.hasDiabetes,
    "COPD": base_namespace.infectsCOPD,
    "HIPERTENSION": base_namespace.hasBloodPressure,
    "OTHER_DISEASE": base_namespace.hasOtherDisease,
    "CARDIOVASCULAR": base_namespace.infectsCardiovascular,
    "OBESITY": base_namespace.hasObesity,
    "TOBACCO": base_namespace.consumesTobacco,
}

# Loop through each cleaned CSV file
for cleaned_file in cleaned_files:
    # Extract dataset name from the file name
    dataset_name = cleaned_file.split("_")[1].split(".")[0]

    # Read cleaned CSV file into DataFrame
    cleaned_path = os.path.join(cleaned_directory, cleaned_file)
    df_cleaned = pd.read_csv(cleaned_path)

    # Loop through each row in the cleaned DataFrame
    for index, row in df_cleaned.iterrows():
        # Create a URI for the patient based on the row index
        patient_uri = base_namespace[f"{dataset_name}Patient/{index}"]

        # Add RDF triples for patient properties
        for column, value in row.items():
            # Add RDF triples to the graph
            if pd.notna(value):
                if isinstance(value, (int, float)):
                    value_literal = Literal(value, datatype=XSD.integer)
                elif isinstance(value, bool):
                    value_literal = Literal(value, datatype=XSD.boolean)
                else:
                    value_literal = Literal(value, datatype=XSD.string)

                # Use the property_mapping to get the appropriate predicate for the column
                predicate = property_mapping.get(column, base_namespace[column])

                g.add((patient_uri, predicate, value_literal))

# Save the RDF triples to a new file
output_rdf_file = "/Users/minalnakawe/SER531-Intellihealth/Data_Cleaning/Triples/RDF_Triples.rdf"
g.serialize(destination=output_rdf_file, format="xml")
