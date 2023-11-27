package com.example.Intellihealth.Service;

import org.springframework.stereotype.Service;

@Service
public class SPARQLService {

    public String executeSPARQLQuery(String query) {
        // Execute the SPARQL query using Jena or another RDF library
        // Return the result as a string
        return "Sample Result for query: " + query;
    }
}
