package com.example.Intellihealth.config;

import org.apache.jena.query.Dataset;
import org.apache.jena.tdb.TDBFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FusekiConfig {

    @Bean
    public Dataset fusekiDataset() {
        // Adjust the URL to your Fuseki dataset endpoint
        String fusekiEndpoint = "http://localhost:3030/dataset";
        return TDBFactory.createDataset(fusekiEndpoint);
    }
}
