#!/usr/bin/env python3
import pandas as pd
import os
from rdflib import Graph, URIRef, Literal, Namespace, RDF, OWL, XSD

# Define the base namespace for your ontology
base_namespace = Namespace("https://example.com/ontology/")

# Load your RDF ontology
ontology_file = "/Users/minalnakawe/Documents/SER 531/Project/intellihealth.rdf"
g = Graph()
g.parse(ontology_file, format="xml")

# Specify the directory containing your cleaned Cardiovascular CSV files
cardio_cleaned_directory = "/Users/minalnakawe/Documents/SER 531/Project/Datasets/cleaned"

# List all cleaned Cardiovascular CSV files in the directory
cardio_cleaned_files = [file for file in os.listdir(cardio_cleaned_directory) if file.startswith("cleaned_") and file.endswith("_Cardio.csv")]

# Loop through each cleaned Cardiovascular CSV file
for cardio_cleaned_file in cardio_cleaned_files:
    # Read cleaned Cardiovascular CSV file into DataFrame
    cardio_cleaned_path = os.path.join(cardio_cleaned_directory, cardio_cleaned_file)
    df_cardio_cleaned = pd.read_csv(cardio_cleaned_path)

    # Create RDF triples for each row in the cleaned Cardiovascular DataFrame
    for index, row in df_cardio_cleaned.iterrows():
        # Create a URI for the Cardiovascular patient based on the row index
        cardio_patient_uri = base_namespace[f"CardiovascularPatient/{index}"]

        # Add RDF triples for Cardiovascular patient properties
        for column, value in row.items():
            # Skip columns that are not part of the ontology
            if column not in ["gender", "apHigh", "apLow", "cholesterol", "glucose", "height", "weight"]:
                continue

            # Map DataFrame columns to ontology properties for Cardiovascular
            property_mapping_cardio = {
                "gender": base_namespace.hasGender,
                "apHigh": base_namespace.hasBloodPressure,
                "apLow": base_namespace.hasBloodPressure,
                "cholesterol": base_namespace.hasCholestrol,
                "glucose": base_namespace.hasDiabetes,
                "height": base_namespace.hasHeight,
                "weight": base_namespace.hasWeight,
            }

            # Create RDF triples
            if pd.notna(value):
                if isinstance(value, (int, float)):
                    value_literal = Literal(value, datatype=XSD.integer)
                elif isinstance(value, bool):
                    value_literal = Literal(value, datatype=XSD.boolean)
                else:
                    value_literal = Literal(value, datatype=XSD.string)

                g.add((cardio_patient_uri, property_mapping_cardio[column], value_literal))

# Specify the directory containing your cleaned COPD CSV files
copd_cleaned_directory = "/Users/minalnakawe/Documents/SER 531/Project/Datasets/cleaned"

# List all cleaned COPD CSV files in the directory
copd_cleaned_files = [file for file in os.listdir(copd_cleaned_directory) if file.startswith("cleaned_") and file.endswith("_COPD.csv")]

# Loop through each cleaned COPD CSV file
for copd_cleaned_file in copd_cleaned_files:
    # Read cleaned COPD CSV file into DataFrame
    copd_cleaned_path = os.path.join(copd_cleaned_directory, copd_cleaned_file)
    df_copd_cleaned = pd.read_csv(copd_cleaned_path)

    # Create RDF triples for each row in the cleaned COPD DataFrame
    for index, row in df_copd_cleaned.iterrows():
        # Create a URI for the COPD patient based on the row index
        copd_patient_uri = base_namespace[f"CopdPatient/{index}"]

        # Add RDF triples for COPD patient properties
        for column, value in row.items():
            # Skip columns that are not part of the ontology
            if column not in ["gender", "smoking", "Diabetes", "hypertension", "muscular", "IHD"]:
                continue

            # Map DataFrame columns to ontology properties for COPD
            property_mapping_copd = {
                "gender": base_namespace.hasGender,
                "smoking": base_namespace.isSmoker,
                "Diabetes": base_namespace.hasDiabetes,
                "hypertension": base_namespace.hasHypertension,
                "muscular": base_namespace.hasMuscularCondition,
                "IHD": base_namespace.hasIschemicHeartDisease,
            }

            # Create RDF triples
            if pd.notna(value):
                if isinstance(value, (int, float)):
                    value_literal = Literal(value, datatype=XSD.integer)
                elif isinstance(value, bool):
                    value_literal = Literal(value, datatype=XSD.boolean)
                else:
                    value_literal = Literal(value, datatype=XSD.string)

                g.add((copd_patient_uri, property_mapping_copd[column], value_literal))

# Specify the directory containing your cleaned COVID CSV files
covid_cleaned_directory = "/Users/minalnakawe/Documents/SER 531/Project/Datasets/cleaned"

# List all cleaned COVID CSV files in the directory
covid_cleaned_files = [file for file in os.listdir(covid_cleaned_directory) if file.startswith("cleaned_") and file.endswith("_COVID.csv")]

# Loop through each cleaned COVID CSV file
for covid_cleaned_file in covid_cleaned_files:
    # Read cleaned COVID CSV file into DataFrame
    covid_cleaned_path = os.path.join(covid_cleaned_directory, covid_cleaned_file)
    df_covid_cleaned = pd.read_csv(covid_cleaned_path)

    # Create RDF triples for each row in the cleaned COVID DataFrame
    for index, row in df_covid_cleaned.iterrows():
        # Create a URI for the COVID patient based on the row index
        covid_patient_uri = base_namespace[f"CovidPatient/{index}"]

        # Add RDF triples for COVID patient properties
        for column, value in row.items():
            # Skip columns that are not part of the ontology
            if column not in ["SEX", "PNEUMONIA", "DIABETES", "COPD", "HIPERTENSION", "OTHER_DISEASE", "CARDIOVASCULAR", "OBESITY", "TOBACCO"]:
                continue

            # Map DataFrame columns to ontology properties for COVID
            property_mapping_covid = {
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

            # Create RDF triples
            if pd.notna(value):
                if isinstance(value, (int, float)):
                    value_literal = Literal(value, datatype=XSD.integer)
                elif isinstance(value, bool):
                    value_literal = Literal(value, datatype=XSD.boolean)
                else:
                    value_literal = Literal(value, datatype=XSD.string)

                g.add((covid_patient_uri, property_mapping_covid[column], value_literal))

# Save the RDF triples to a new file
output_rdf_file = "/Users/minalnakawe/Documents/SER 531/Project/RDF_Triples.rdf"
g.serialize(destination=output_rdf_file, format="xml")
