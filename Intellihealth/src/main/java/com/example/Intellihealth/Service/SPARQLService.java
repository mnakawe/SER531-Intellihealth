package com.example.Intellihealth.Service;

import com.example.Intellihealth.model.HealthDataDTO;
import org.apache.jena.query.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SPARQLService {

    //private final Dataset fusekiDataset;
    @Value("${fuseki.endpoint.url}")
    private String fusekiEndpointUrl;
    public static final String PREFIXES =
            "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                    "PREFIX ontology: <https://example.com/ontology/>\n" +
                    "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>";

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

    public int buildCustomQuery(HealthDataDTO healthData) {
        String sparqlQuery = PREFIXES +
                "\nSELECT (COUNT(?user) AS ?userCount)\n" +
                "WHERE {\n" +
                "  ?user rdf:type ontology:COPDpatient;\n" +
                "    ontology:hasAge ?userAge;\n" +
                "    ontology:hasBloodPressure ?userBloodPressure;\n" +
                "    ontology:hasDiabetes ?userDiabetes;\n" +
                "    ontology:hasGender ?userGender;\n" +
                "    ontology:hasMuscularProblems ?userMuscularProblems;\n" +
                "    ontology:isSmoker ?userSmoker.\n" +
                "  FILTER(\n" +
                "    xsd:integer(?userAge) >= " + healthData.getAge() + " && xsd:integer(?userAge) <= " + healthData.getAge() + 10 + " &&\n" +
                "    ?userBloodPressure = \"" + healthData.getBloodPressure() + "\" &&\n" +
                "    ?userDiabetes = \"" + healthData.getDiabetes() + "\" &&\n" +
                "    ?userGender = \"" + healthData.getGender() + "\" &&\n" +
                "    ?userMuscularProblems = \"" + healthData.getMuscularProblems() + "\" &&\n" +
                "    ?userSmoker = \"" + healthData.getSmoke() + "\"\n" +
                "  )\n" +
                "}";


        System.out.println(sparqlQuery);

        return executeCustomQuery(sparqlQuery.toString());
    }

    private static void appendFilterCondition(StringBuilder query, String property, String value) {
        if (value != null) {
            // Use parameterized query and proper escaping for string literals
            query.append("    FILTER(?").append(property).append(" = '").append(value).append("') &&\n");
        }
    }

    private int executeCustomQuery(String sparqlQuery) {
        try (QueryExecution qexec = QueryExecutionFactory.sparqlService(fusekiEndpointUrl, QueryFactory.create(sparqlQuery))) {
            ResultSet results = qexec.execSelect();
            if (results.hasNext()) {
                QuerySolution soln = results.nextSolution();
                return soln.getLiteral("userCount").getInt();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }
}
