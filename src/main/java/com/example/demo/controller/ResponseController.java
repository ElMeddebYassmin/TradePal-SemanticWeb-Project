package com.example.demo.controller;


import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.util.FileManager;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;


@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("Response")
public class ResponseController {

    @GetMapping("/getAllResponses")
    public String getResponsesWithResponderInfo() {
        try {
            String ontologyFile = "data/tradePal.owl";
            String sparqlQuery = "PREFIX ns: <http://www.semanticweb.org/user/ontologies/2023/9/tradepal#>\n" +
                    "PREFIX ont: <http://www.co-ode.org/ontologies/ont.owl#>\n" +
                    "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                    "SELECT ?id ?content ?response_date ?is_created_by ?responderEmail ?responderUsername\n" +
                    "WHERE {\n" +
                    "  ?response ont:id ?id .\n" +
                    "  ?response ont:content ?content .\n" +
                    "  ?response ont:response_date ?response_date .\n" +
                    "  ?response ont:is_created_by ?is_created_by .\n" +
                    "  ?is_created_by ont:email ?responderEmail .\n" +
                    "  ?is_created_by ont:username ?responderUsername .\n" +
                    "}";

            System.out.println("Ontology File: " + ontologyFile);
            System.out.println("SPARQL Query: " + sparqlQuery);

            Model model = FileManager.get().loadModel(ontologyFile);
            Query query = QueryFactory.create(sparqlQuery);
            QueryExecution qexec = QueryExecutionFactory.create(query, model);
            ResultSet results = qexec.execSelect();

            JSONArray resultArray = new JSONArray();
            while (results.hasNext()) {
                QuerySolution solution = results.nextSolution();
                JSONObject responseObject = new JSONObject();

                responseObject.put("id", solution.get("id").asLiteral().getString());
                responseObject.put("content", solution.get("content").asLiteral().getString());
                responseObject.put("response_date", solution.get("response_date").asLiteral().getString());
                responseObject.put("responderEmail", solution.get("responderEmail").asLiteral().getString());
                responseObject.put("responderUsername", solution.get("responderUsername").asLiteral().getString());
                resultArray.put(responseObject);
            }

            return resultArray.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "Error fetching data: " + e.getMessage();
        }
    }


    @GetMapping("/ResponsesByResponderName")
    public String getResponsesByResponderName(@RequestParam String className) {
        String ontologyFile = "data/tradePal.owl";

        // Define the SPARQL query to retrieve Responses by responder's name
        String sparqlQuery = "PREFIX ns: <http://www.semanticweb.org/user/ontologies/2023/9/tradepal#>\n" +
                "PREFIX ont: <http://www.co-ode.org/ontologies/ont.owl#>\n" +
                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                "SELECT ?instance ?id ?content ?response_date ?is_created_by ?responderName ?responderEmail\n" +
                "WHERE {\n" +
                "  ?instance rdf:type ont:" + className + " .\n" +
                "  ?instance ont:id ?id .\n" +
                "  ?instance ont:content ?content .\n" +
                "  ?instance ont:response_date ?response_date .\n" +
                "  ?instance ont:is_created_by ?is_created_by .\n" +
                "  ?is_created_by ont:username ?responderName .\n" +
                "  ?is_created_by ont:email ?responderEmail .\n" +
                "}";

        // Log the constructed SPARQL query
        System.out.println("Constructed SPARQL Query: " + sparqlQuery);

        Model model = FileManager.get().loadModel(ontologyFile);

        // Create a QueryExecution to execute the SPARQL query
        Query query = QueryFactory.create(sparqlQuery);
        QueryExecution qexec = QueryExecutionFactory.create(query, model);

        // Execute the query and get the results
        ResultSet results = qexec.execSelect();

        // Convert the ResultSet to JSON
        JSONArray resultArray = new JSONArray();
        while (results.hasNext()) {
            QuerySolution solution = results.nextSolution();
            JSONObject responseObject = new JSONObject();

            // Retrieve and add individual attributes to the JSON structure
            responseObject.put("instance", solution.getResource("instance").toString()); // Renamed the key
            responseObject.put("id", solution.getLiteral("id").getLexicalForm());
            responseObject.put("type",className);            responseObject.put("content", solution.getLiteral("content").toString());
            responseObject.put("response_date", solution.getLiteral("response_date").toString());
//            responseObject.put("is_created_by", solution.getResource("is_created_by").toString());
            responseObject.put("responderUsername", solution.getLiteral("responderName").toString());
            responseObject.put("responderEmail", solution.getLiteral("responderEmail").toString());

            resultArray.put(responseObject);
        }

        // Log the resultArray
        System.out.println("Result Array: " + resultArray.toString());

        return resultArray.toString();
    }


}
