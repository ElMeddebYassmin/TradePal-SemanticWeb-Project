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
@RequestMapping("/organization")
public class Organization {
    String ontologyFile = "data/tradePal.owl";
    @GetMapping("/{className}")
    public String getOrganizationsByCategory(@PathVariable String className) {
        Model model = FileManager.get().loadModel(ontologyFile);

        String sparqlQuery =
                "PREFIX ns: <http://www.semanticweb.org/user/ontologies/2023/9/tradepal#>\n" +
                        "PREFIX ont: <http://www.co-ode.org/ontologies/ont.owl#>\n" +
                        "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                        "SELECT ?instance ?id ?founding_date ?phone ?archived ?name ?website ?description ?location ?email ?is_created_by " +
                        "WHERE { " +
                        "  ?instance rdf:type ont:" + className + " . " +
                        "  ?instance ont:id ?id . " +
                        "  ?instance ont:founding_date ?founding_date . " +
                        "  ?instance ont:phone ?phone . " +
                        "  ?instance ont:archived ?archived . " +
                        "  ?instance ont:name ?name . " +
                        "  ?instance ont:website ?website . " +
                        "  ?instance ont:description ?description . " +
                        "  ?instance ont:location ?location . " +
                        "  ?instance ont:email ?email . " +
                        "  ?instance ont:is_created_by ?is_created_by . " +
                        "}";

        Query query = QueryFactory.create(sparqlQuery);
        QueryExecution qexec = QueryExecutionFactory.create(query, model);

        ResultSet results = qexec.execSelect();

        JSONArray resultArray = new JSONArray();
        while (results.hasNext()) {
            QuerySolution solution = results.nextSolution();
            JSONObject clubObject = new JSONObject();

            clubObject.put("instance", solution.getResource("instance").toString()); // Renamed the key
            clubObject.put("id", solution.getLiteral("id").getLexicalForm());
            clubObject.put("type",className);
            clubObject.put("founding_date", solution.getLiteral("founding_date").getLexicalForm());
            clubObject.put("phone", solution.getLiteral("phone").getLexicalForm());
            clubObject.put("archived", solution.getLiteral("archived").getLexicalForm());
            clubObject.put("name", solution.getLiteral("name").getLexicalForm());
            clubObject.put("website", solution.getLiteral("website").getLexicalForm());
            clubObject.put("description", solution.getLiteral("description").getLexicalForm());
            clubObject.put("location", solution.getLiteral("location").getLexicalForm());
            clubObject.put("email", solution.getLiteral("email").getLexicalForm());
            clubObject.put("is_created_by", solution.getResource("is_created_by").toString());

            resultArray.put(clubObject);
        }

        qexec.close();

        return resultArray.toString();
    }


    @GetMapping("/getAllOrgs")
    public static String getAllOrganizations() {
        String ontURI = "http://www.co-ode.org/ontologies/ont.owl";
        Model model = ModelFactory.createDefaultModel();
        model.read("data/tradePal.owl");
        String prefixes =
                "PREFIX ont: <http://www.co-ode.org/ontologies/ont.owl#>\n" +
                        "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n";
        String query =
                prefixes +
                        "SELECT ?id ?founding_date ?email ?phone ?location ?website ?archived ?name ?description ?type " +
                        "WHERE {" +
                        "  ?organization ont:id ?id ." +
                        "  ?organization ont:founding_date ?founding_date ." +
                        "  ?organization ont:phone ?phone ." +
                        "  ?organization ont:location ?location ." +
                        "  ?organization ont:website ?website ." +
                        "  ?organization ont:archived ?archived ." +
                        "  ?organization ont:email ?email ." +
                        "  ?organization ont:name ?name ." +
                        "  ?organization ont:description ?description ." +
                        "}";

        QueryExecution qe = QueryExecutionFactory.create(query, model);
        ResultSet results = qe.execSelect();
        try {
            JSONArray orgs = new JSONArray();
            while (results.hasNext()) {
                QuerySolution solution = results.nextSolution();
                JSONObject org = new JSONObject();
                Iterator<String> varNames = solution.varNames();
                while (varNames.hasNext()) {
                    String varName = varNames.next();
                    RDFNode rdfNode = solution.get(varName);

                    if (rdfNode.isLiteral()) {
                        org.put(varName, rdfNode.asLiteral().getString());
                    }
                }
                orgs.put(org);
            }
            return orgs.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "Error fetching data.";
        } finally {
            qe.close();
        }
    }

}

