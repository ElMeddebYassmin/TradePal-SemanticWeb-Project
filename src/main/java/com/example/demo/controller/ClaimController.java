package com.example.demo.controller;

import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.util.FileManager;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;


@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("Claim")
public class ClaimController {


    @GetMapping("/getAllClaims")
    public String getClaimsWithResponseCount() {
        String ontologyFile = "data/tradePal.owl";

        // Define the SPARQL query to retrieve Claims with the count of their responses
        String sparqlQuery = "PREFIX ns: <http://www.semanticweb.org/user/ontologies/2023/9/tradepal#>\n" +
                "PREFIX ont: <http://www.co-ode.org/ontologies/ont.owl#>\n" +
                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                "SELECT ?Claim ?subject ?claim_date ?description ?status ?has_claimant ?claimantName ?claimantEmail (COUNT(?response) AS ?responseCount)\n" +
                "WHERE {\n" +
                "  ?Claim rdf:type ont:Claim .\n" +
                "  ?Claim ont:subject ?subject .\n" +
                "  ?Claim ont:claim_date ?claim_date .\n" +
                "  ?Claim ont:description ?description .\n" +
                "  ?Claim ont:status ?status .\n" +
                "  ?Claim ont:has_claimant ?has_claimant .\n" +
                "  ?has_claimant ont:name ?claimantName .\n" +
                "  ?has_claimant ont:email ?claimantEmail .\n" +
                "  OPTIONAL { ?Claim ont:has_responses ?response . }\n" +
                "}"
                + "GROUP BY ?Claim ?subject ?claim_date ?description ?status ?has_claimant ?claimantName ?claimantEmail";

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
            JSONObject reclamationObject = new JSONObject();

            // Retrieve and add individual attributes to the JSON structure
            reclamationObject.put("Claim", solution.getResource("Claim").toString());
            reclamationObject.put("subject", solution.getLiteral("subject").toString());
            reclamationObject.put("claim_date", solution.getLiteral("claim_date").toString());
            reclamationObject.put("description", solution.getLiteral("description").toString());
            reclamationObject.put("status", solution.getLiteral("status").toString());
            reclamationObject.put("has_claimant", solution.getResource("has_claimant").toString());
            reclamationObject.put("claimantName", solution.getLiteral("claimantName").toString());
            reclamationObject.put("claimantEmail", solution.getLiteral("claimantEmail").toString());
            String responseCountString = solution.getLiteral("responseCount").toString();
            int responseCount = Integer.parseInt(responseCountString.split("\\^")[0]);
            reclamationObject.put("responseCount", responseCount);
            resultArray.put(reclamationObject);
        }

        return resultArray.toString();
    }


    @GetMapping("/ClaimByName")
    public String getClaimBySubject(@RequestParam String subject) {
        String ontologyFile = "data/tradePal.owl";

        // Define the SPARQL query to retrieve a specific Claim by the subject
        String sparqlQuery = "PREFIX ns: <http://www.semanticweb.org/user/ontologies/2023/9/tradepal#>\n" +
                "PREFIX ont: <http://www.co-ode.org/ontologies/ont.owl#>\n" +
                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                "SELECT ?Claim ?subject ?claim_date ?description ?status ?has_claimant ?claimantName ?claimantEmail (COUNT(?response) AS ?responseCount)\n" +
                "WHERE {\n" +
                "  ?Claim rdf:type ont:Claim .\n" +
                "  ?Claim ont:subject ?subject .\n" +
                "  ?Claim ont:claim_date ?claim_date .\n" +
                "  ?Claim ont:description ?description .\n" +
                "  ?Claim ont:status ?status .\n" +
                "  ?Claim ont:has_claimant ?has_claimant .\n" +
                "  ?has_claimant ont:name ?claimantName .\n" +
                "  ?has_claimant ont:email ?claimantEmail .\n" +
                "  OPTIONAL { ?Claim ont:has_responses ?response . }\n" +
                "  FILTER (?subject = \"" + subject + "\") .\n" +
                "} GROUP BY ?Claim ?subject ?claim_date ?description ?status ?has_claimant ?claimantName ?claimantEmail";


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
            JSONObject reclamationObject = new JSONObject();

            // Retrieve and add individual attributes to the JSON structure
            reclamationObject.put("Claim", solution.getResource("Claim").toString());
            reclamationObject.put("subject", solution.getLiteral("subject").toString());
            reclamationObject.put("claim_date", solution.getLiteral("claim_date").toString());
            reclamationObject.put("description", solution.getLiteral("description").toString());
            reclamationObject.put("status", solution.getLiteral("status").toString());
            reclamationObject.put("has_claimant", solution.getResource("has_claimant").toString());
            reclamationObject.put("claimantName", solution.getLiteral("claimantName").toString());
            reclamationObject.put("claimantEmail", solution.getLiteral("claimantEmail").toString());
            String responseCountString = solution.getLiteral("responseCount").toString();
            int responseCount = Integer.parseInt(responseCountString.split("\\^")[0]);
            reclamationObject.put("responseCount", responseCount);
            resultArray.put(reclamationObject);
        }

        return resultArray.toString();
    }

    @GetMapping("/ClaimsByStatus")
    public String getClaimsByStatus(@RequestParam String status) {
        String ontologyFile = "data/tradePal.owl";

        // Define the SPARQL query to retrieve Claims by status
        String sparqlQuery = "PREFIX ns: <http://www.semanticweb.org/user/ontologies/2023/9/tradepal#>\n" +
                "PREFIX ont: <http://www.co-ode.org/ontologies/ont.owl#>\n" +
                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                "SELECT ?Claim ?subject ?claim_date ?description ?status ?has_claimant ?claimantName ?claimantEmail (COUNT(?response) AS ?responseCount)\n" +
                "WHERE {\n" +
                "  ?Claim rdf:type ont:Claim .\n" +
                "  ?Claim ont:subject ?subject .\n" +
                "  ?Claim ont:claim_date ?claim_date .\n" +
                "  ?Claim ont:description ?description .\n" +
                "  ?Claim ont:status ?status .\n" +
                "  ?Claim ont:has_claimant ?has_claimant .\n" +
                "  ?has_claimant ont:name ?claimantName .\n" +
                "  ?has_claimant ont:email ?claimantEmail .\n" +
                "  OPTIONAL { ?Claim ont:has_responses ?response . }\n" +
                "  FILTER (?status = \"" + status + "\") .\n" +
                "} GROUP BY ?Claim ?subject ?claim_date ?description ?status ?has_claimant ?claimantName ?claimantEmail";

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
            JSONObject reclamationObject = new JSONObject();

            // Retrieve and add individual attributes to the JSON structure
            reclamationObject.put("Claim", solution.getResource("Claim").toString());
            reclamationObject.put("subject", solution.getLiteral("subject").toString());
            reclamationObject.put("claim_date", solution.getLiteral("claim_date").toString());
            reclamationObject.put("description", solution.getLiteral("description").toString());
            reclamationObject.put("status", solution.getLiteral("status").toString());
            reclamationObject.put("has_claimant", solution.getResource("has_claimant").toString());
            reclamationObject.put("claimantName", solution.getLiteral("claimantName").toString());
            reclamationObject.put("claimantEmail", solution.getLiteral("claimantEmail").toString());
            String responseCountString = solution.getLiteral("responseCount").toString();
            int responseCount = Integer.parseInt(responseCountString.split("\\^")[0]);
            reclamationObject.put("responseCount", responseCount);
            resultArray.put(reclamationObject);

        }

        return resultArray.toString();
    }



}
