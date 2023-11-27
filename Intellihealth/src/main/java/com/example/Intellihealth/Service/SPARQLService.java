package com.example.Intellihealth.Service;

import org.apache.jena.query.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SPARQLService {

    private final Dataset fusekiDataset;

    @Autowired
    public SPARQLService(Dataset fusekiDataset) {
        this.fusekiDataset = fusekiDataset;
    }

    public void executeSparqlQuery(String sparqlQuery) {
        try (QueryExecution qexec = QueryExecutionFactory.create(QueryFactory.create(sparqlQuery), fusekiDataset)) {
            ResultSet results = qexec.execSelect();
            while (results.hasNext()) {
                QuerySolution soln = results.nextSolution();
                // Process query results as needed
            }
        }
    }
}
