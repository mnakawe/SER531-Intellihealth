#!/usr/bin/env python3
import pandas as pd
import os
from rdflib import Graph, Literal, Namespace, XSD

# Define the base namespace for your ontology
base_namespace = Namespace("https://example.com/ontology/")

# Load your RDF ontology
ontology_file = "/Users/minalnakawe/Documents/SER 531/Project/intellihealth.rdf"
g = Graph()
g.parse(ontology_file, format="xml")

# Specify the directory containing your cleaned CSV files
cleaned_directory = "/Users/minalnakawe/Documents/SER 531/Project/Datasets/cleaned"

# Initialize an empty DataFrame to store CSV triples
csv_data = pd.DataFrame()

# Define a list of diseases for which to generate CSV triples
diseases = ["Cardio", "COPD", "COVID"]

for disease in diseases:
    # List all cleaned CSV files for the current disease in the directory
    cleaned_files = [file for file in os.listdir(cleaned_directory) if file.startswith(f"cleaned_") and file.endswith(f"_{disease}.csv")]

    # Loop through each cleaned CSV file
    for cleaned_file in cleaned_files:
        # Read cleaned CSV file into DataFrame
        cleaned_path = os.path.join(cleaned_directory, cleaned_file)
        df_cleaned = pd.read_csv(cleaned_path)

        # Create CSV triples for each row in the cleaned DataFrame
        for index, row in df_cleaned.iterrows():
            # Create a dictionary to store CSV triples
            triples = {
                "Subject": f"{disease}Patient/{index}",
                "Predicate": [],
                "Object": []
            }

            # Add CSV triples for patient properties
            for column, value in row.items():
                # Skip columns that are not part of the ontology
                if column not in ["gender", "apHigh", "apLow", "cholesterol", "glucose", "height", "weight",
                                  "smoking", "Diabetes", "hypertension", "muscular", "IHD",
                                  "SEX", "PNEUMONIA", "DIABETES", "COPD", "HIPERTENSION", "OTHER_DISEASE", "CARDIOVASCULAR", "OBESITY", "TOBACCO"]:
                    continue

                # Map DataFrame columns to ontology properties
                property_mapping = {
                    "gender": base_namespace.hasGender,
                    "apHigh": base_namespace.hasBloodPressure,
                    "apLow": base_namespace.hasBloodPressure,
                    "cholesterol": base_namespace.hasCholestrol,
                    "glucose": base_namespace.hasDiabetes,
                    "height": base_namespace.hasHeight,
                    "weight": base_namespace.hasWeight,
                    "smoking": base_namespace.isSmoker,
                    "Diabetes": base_namespace.hasDiabetes,
                    "hypertension": base_namespace.hasHypertension,
                    "muscular": base_namespace.hasMuscularCondition,
                    "IHD": base_namespace.hasIschemicHeartDisease,
                    "SEX": base_namespace.hasGender,
                    "PNEUMONIA": base_namespace.hasPneumonia,
                    "DIABETES": base_namespace.hasDiabetes,
                    "COPD": base_namespace.hasCOPD,
                    "HIPERTENSION": base_namespace.hasHypertension,
                    "OTHER_DISEASE": base_namespace.hasOtherDisease,
                    "CARDIOVASCULAR": base_namespace.hasCardiovascularCondition,
                    "OBESITY": base_namespace.hasObesity,
                    "TOBACCO": base_namespace.isTobaccoUser,
                }

                # Add CSV triples to the dictionary
                triples["Predicate"].append(property_mapping[column])
                triples["Object"].append(value)

            # Convert the dictionary to a DataFrame and append it to csv_data
            triples_df = pd.DataFrame(triples)
            csv_data = pd.concat([csv_data, triples_df], ignore_index=True)

# Save the CSV triples to a new CSV file
output_csv_file = "/Users/minalnakawe/Documents/SER 531/Project/CSV_Triples_All.csv"
csv_data.to_csv(output_csv_file, index=False)
