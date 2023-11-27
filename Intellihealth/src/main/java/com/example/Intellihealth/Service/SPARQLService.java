package com.example.Intellihealth.Service;

import org.apache.jena.query.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SPARQLService {

    //private final Dataset fusekiDataset;
    @Value("${fuseki.endpoint.url}")
    private String fusekiEndpointUrl;

//    @Autowired
//    public SPARQLService(Dataset fusekiDataset) {
//        this.fusekiDataset = fusekiDataset;
//    }
//
//    public void executeSparqlQuery(String sparqlQuery) {
//        try (QueryExecution qexec = QueryExecutionFactory.create(QueryFactory.create(sparqlQuery), fusekiDataset)) {
//            ResultSet results = qexec.execSelect();
//            while (results.hasNext()) {
//                QuerySolution soln = results.nextSolution();
//                // Process query results as needed
//            }
//        }
//    }

    public void runHelloWorldQuery() {
        String sparqlQuery = "SELECT ?subject ?predicate ?object WHERE { ?subject ?predicate ?object } LIMIT 3";

        try (QueryExecution qexec = QueryExecutionFactory.sparqlService(fusekiEndpointUrl, QueryFactory.create(sparqlQuery))) {
            ResultSet results = qexec.execSelect();
            while (results.hasNext()) {
                QuerySolution soln = results.nextSolution();
                // Process query results as needed
                System.out.println("Subject: " + soln.get("subject"));
                System.out.println("Predicate: " + soln.get("predicate"));
                System.out.println("Object: " + soln.get("object"));
                System.out.println(results);
            }
        }
    }
}
