package com.example.Intellihealth.controller;


import com.example.Intellihealth.Service.SPARQLService;
import com.example.Intellihealth.model.HealthDataDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/sparql")
@CrossOrigin(origins = "http://localhost:8080//Intellihealth-0.0.1-SNAPSHOT/")

public class Query_controller {
    @Autowired
    private SPARQLService sparqlService;

    @PostMapping("/saveHealthData")
    public String executeCustomQuery(@RequestBody HealthDataDTO healthData, Model model) {
        System.out.println(healthData.getSmoke());
        System.out.println(healthData.getBloodPressure());
        System.out.println(healthData.getAge());
        List<Integer> queryResults = sparqlService.buildCustomQuery(healthData);
        model.addAttribute("copdResults", queryResults.get(0));
        model.addAttribute("covidResults", queryResults.get(1));
        model.addAttribute("cardioResults", queryResults.get(2));
        System.out.println(queryResults.get(0));
        System.out.println(queryResults.get(1));
        System.out.println(queryResults.get(2));
        //return new ResponseEntity<>(Integer.toString(queryResults), HttpStatus.OK);
        return "displayData";
    }
}
