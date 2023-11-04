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
@RequestMapping("/donation")
public class Donation {

    @GetMapping("/{donationClassName}")
    public String getHazelnutsDonation(
            @PathVariable String donationClassName
    ) {
        String ontologyFile = "data/tradePal.owl";

        String sparqlQuery = "PREFIX ns: <http://www.semanticweb.org/user/ontologies/2023/9/tradepal#>\n" +
                "PREFIX ont: <http://www.co-ode.org/ontologies/ont.owl#>\n" +
                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                "SELECT ?donation ?amount ?id ?object ?donorName " +
                "WHERE { " +
                "  ?donation rdf:type ont:" + donationClassName + " . " +
                "  ?donation ont:amount ?amount . " +
                "  ?donation ont:id ?id . " +
                "  ?donation ont:object ?object . " +
                "  ?donation ont:has_donor ?donor . " +
                "  ?donor ont:name ?donorName . " +
                "}";

        Model model = FileManager.get().loadModel(ontologyFile);

        Query query = QueryFactory.create(sparqlQuery);
        QueryExecution qexec = QueryExecutionFactory.create(query, model);

        ResultSet results = qexec.execSelect();

        JSONArray resultArray = new JSONArray();
        while (results.hasNext()) {
            QuerySolution solution = results.nextSolution();
            JSONObject hazelnutsDonationObject = new JSONObject();

            hazelnutsDonationObject.put("donation", solution.getResource("donation").toString());
            hazelnutsDonationObject.put("amount", solution.getLiteral("amount").getLexicalForm());
            hazelnutsDonationObject.put("id", solution.getLiteral("id").getLexicalForm());
            hazelnutsDonationObject.put("object", solution.getLiteral("object").getLexicalForm());
            hazelnutsDonationObject.put("donorName", solution.getLiteral("donorName").getLexicalForm());

            resultArray.put(hazelnutsDonationObject);
        }

        return resultArray.toString();
    }


    @GetMapping("/{donationClassName}/{nonprofitClassName}")
    public String getDonationsToNonProfits(@PathVariable String donationClassName, @PathVariable String nonprofitClassName) {
        String ontologyFile = "data/tradePal.owl";
        String sparqlQuery = "PREFIX ns: <http://www.semanticweb.org/user/ontologies/2023/9/tradepal#>\n" +
                "PREFIX ont: <http://www.co-ode.org/ontologies/ont.owl#>\n" +
                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                "SELECT ?donation ?id ?amount ?object ?donorName ?nonprofitOrg ?orgName ?orgDescription " +
                "WHERE { " +
                "  ?donation rdf:type ont:" + donationClassName + " . " +
                "  ?donation ont:id ?id . " +
                "  ?donation ont:amount ?amount . " +
                "  ?donation ont:object ?object . " +
                "  ?donation ont:has_donor ?donor . " +
                "  ?donor ont:name ?donorName . " +
                "  ?nonprofitOrg rdf:type ont:" + nonprofitClassName + " . " +
                "  ?nonprofitOrg ont:has_donations ?donation . " +
                "  ?nonprofitOrg ont:name ?orgName . " +
                "  ?nonprofitOrg ont:description ?orgDescription . " +
                "}";

        Model model = FileManager.get().loadModel(ontologyFile);

        Query query = QueryFactory.create(sparqlQuery);
        QueryExecution qexec = QueryExecutionFactory.create(query, model);

        ResultSet results = qexec.execSelect();

        JSONArray resultArray = new JSONArray();
        while (results.hasNext()) {
            QuerySolution solution = results.nextSolution();
            JSONObject donationObject = new JSONObject();

            donationObject.put("donation", solution.getResource("donation").toString());
            donationObject.put("id", solution.getLiteral("id").getLexicalForm());
            donationObject.put("amount", solution.getLiteral("amount").getLexicalForm());
            donationObject.put("object", solution.getLiteral("object").getLexicalForm());
            donationObject.put("donorName", solution.getLiteral("donorName").getLexicalForm());
            donationObject.put("nonprofitOrg", solution.getResource("nonprofitOrg").toString());
            donationObject.put("orgName", solution.getLiteral("orgName").getLexicalForm());
            donationObject.put("orgDescription", solution.getLiteral("orgDescription").getLexicalForm());

            resultArray.put(donationObject);
        }

        return resultArray.toString();
    }

    @GetMapping("/getAllDonations")
    public static String getAllDonations() {
        String ontURI = "http://www.co-ode.org/ontologies/ont.owl";
        Model model = ModelFactory.createDefaultModel();
        model.read("data/tradePal.owl");
        String prefixes =
                "PREFIX ns: <http://www.semanticweb.org/user/ontologies/2023/9/tradepal#>\n" +
                        "PREFIX ont: <http://www.co-ode.org/ontologies/ont.owl#>\n" +
                        "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" ;
        String query =
                prefixes +
                        "SELECT ?id ?amount ?object ?donorName " +
                        "WHERE {" +
                        "  ?donation ont:id ?id . " +
                        "  ?donation ont:amount ?amount ." +
                        "  ?donation ont:object ?object ." +
                        "  ?donation ont:has_donor ?donor . " +
                        "  ?donor ont:name ?donorName . " +
                        "}";

        QueryExecution qe = QueryExecutionFactory.create(query, model);
        ResultSet results = qe.execSelect();
        try {
            JSONArray donations = new JSONArray();
            while (results.hasNext()) {
                QuerySolution solution = results.nextSolution();
                JSONObject donation = new JSONObject();
                Iterator<String> varNames = solution.varNames();
                while (varNames.hasNext()) {
                    String varName = varNames.next();
                    RDFNode rdfNode = solution.get(varName);

                    if (rdfNode.isLiteral()) {
                        donation.put(varName, rdfNode.asLiteral().getString());
                    }
                }
                donations.put(donation);
            }

            return donations.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "Error fetching data.";
        } finally {
            qe.close();
        }
    }

}
