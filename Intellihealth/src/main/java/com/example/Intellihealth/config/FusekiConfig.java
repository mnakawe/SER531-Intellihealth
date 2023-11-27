//package com.example.Intellihealth.config;
//
//import org.apache.jena.query.Dataset;
//import org.apache.jena.query.DatasetAccessor;
//import org.apache.jena.query.DatasetAccessorFactory;
//import org.apache.jena.tdb.TDBFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class FusekiConfig {
//
//    @Value("${fuseki.dataset.path}")
//    private String datasetPath;
//
//    @Bean
//    public Dataset fusekiDataset() {
//        return TDBFactory.createDataset(datasetPath);
//    }
//
//}
