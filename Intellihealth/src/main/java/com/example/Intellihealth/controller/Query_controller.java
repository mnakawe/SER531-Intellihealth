package com.example.Intellihealth.controller;


import com.example.Intellihealth.Service.SPARQLService;
import com.example.Intellihealth.model.HealthDataDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/sparql")
public class Query_controller {
    private final String RDF_FILE_PATH = "Data_Cleaning/intellihealth.rdf";
    private final String BASE_NAMESPACE = "https://example.com/ontology/";
    @Autowired
    private SPARQLService sparqlService;

    @GetMapping("/hello")
    public void runHelloWorldQuery() {
        sparqlService.runHelloWorldQuery();
    }


    @PostMapping("/saveHealthData")
    public ResponseEntity<String> saveHealthData(@RequestBody HealthDataDTO healthDataDTO) {
        // Here, you can save the health data to your database or perform any desired processing.
        // For simplicity, let's just print the received data.
        System.out.println("Received Health Data: " + healthDataDTO);

        // Return a success response
        return ResponseEntity.ok("Health data saved successfully");
    }

//    @GetMapping("/copd")
//    public String getCopdResults(Model model, @RequestParam("isSmoker") String isSmoker,
//                                 @RequestParam("hasAge") int hasAge) {
//        String query = "Your COPD SPARQL Query with parameters";
//        String result = sparqlService.executeSPARQLQuery(query);
//        model.addAttribute("result", result);
//        return "result";
//    }
//
//    @GetMapping("/cardio")
//    public String getCardioResults(Model model, @RequestParam("isSmoker") String isSmoker,
//                                   @RequestParam("hasAge") int hasAge) {
//        String query = "Your Cardio SPARQL Query with parameters";
//        String result = sparqlService.executeSPARQLQuery(query);
//        model.addAttribute("result", result);
//        return "result";
//    }
//
//    @GetMapping("/covid")
//    public String getCovidResults(Model model, @RequestParam("isSmoker") String isSmoker,
//                                  @RequestParam("hasAge") int hasAge) {
//        String query = "Your COVID SPARQL Query with parameters";
//        String result = sparqlService.executeSPARQLQuery(query);
//        model.addAttribute("result", result);
//        return "result";
//    }

}
