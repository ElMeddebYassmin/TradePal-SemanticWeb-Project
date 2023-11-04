package com.example.demo.controller;
import java.io.ByteArrayOutputStream;

import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.util.FileManager;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/requests")
public class RequestController {



    @GetMapping("/getRequests")
    public String getRequests() {

        String ontologyFile = "data/tradePal.owl";


        String sparqlQuery = "PREFIX ns: <http://www.semanticweb.org/user/ontologies/2023/9/tradepal#>\n" +
                "PREFIX ont: <http://www.co-ode.org/ontologies/ont.owl#>\n" +
                "PREFIX www: <http://www.w3.org/2002/07/>\n"+
                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                "SELECT ?Request ?id ?status ?note ?is_by ?belongs_to ?is_for ?user ?desired ?exchangeable ?amount " +
                "WHERE { " +
                "  ?Request rdf:type ont:Request . " +
                "  ?Request ont:id ?id . " +
                "  ?Request ont:status ?status . " +
                "  ?Request ont:amount ?amount . " +
                "  ?Request ont:note ?note . " +
                "  ?Request ont:is_by ?is_by . " +
                "  ?is_by rdf:type ont:Item . " +
                "  ?is_by ont:name ?exchangeable . " +
                "  ?Request ont:belongs_to ?belongs_to . " +
                "  ?belongs_to rdf:type ont:User . " +
                "  ?belongs_to ont:username ?user . " +
                "  ?Request ont:is_for ?is_for . " +
                "  ?is_for rdf:type ont:Item . " +
                "  ?is_for ont:name ?desired. " +
                "}";

        Model model = FileManager.get().loadModel(ontologyFile);




        Query query = QueryFactory.create(sparqlQuery);
        QueryExecution qexec = QueryExecutionFactory.create(query, model);


        ResultSet results = qexec.execSelect();


        JSONArray resultArray = new JSONArray();
        while (results.hasNext()) {
            QuerySolution solution = results.nextSolution();
            JSONObject requestObject = new JSONObject();


            requestObject.put("Request", solution.getResource("Request").toString());
            requestObject.put("id", solution.getLiteral("id").getLexicalForm());
            requestObject.put("status", solution.getLiteral("status").getLexicalForm());
            requestObject.put("amount", solution.getLiteral("amount").getLexicalForm());
            requestObject.put("note", solution.getLiteral("note").getLexicalForm());
            requestObject.put("is_by", solution.getResource("is_by").toString());
            requestObject.put("exchangeable_object", solution.getLiteral("exchangeable").getLexicalForm());
            requestObject.put("belongs_to", solution.getResource("belongs_to").toString());
            requestObject.put("owner", solution.getLiteral("user").getLexicalForm());
            requestObject.put("is_for", solution.getResource("is_for").toString());
            requestObject.put("desired_object", solution.getLiteral("desired").getLexicalForm());



            resultArray.put(requestObject);
        }


        qexec.close();

        return resultArray.toString();

    }

    @GetMapping("/getRequestsByDesiredObject/{desired}/{exch}")
    public String getRequestsByDesiredObject(@PathVariable String desired,@PathVariable String exch) {

        String ontologyFile = "data/tradePal.owl";


        String sparqlQuery = "PREFIX ns: <http://www.semanticweb.org/user/ontologies/2023/9/tradepal#>\n" +
                "PREFIX ont: <http://www.co-ode.org/ontologies/ont.owl#>\n" +
                "PREFIX www: <http://www.w3.org/2002/07/>\n"+
                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                "SELECT ?Request ?id ?status ?note ?is_by ?belongs_to ?is_for ?user ?desired ?exchangeable  ?amount " +
                "WHERE { " +
                "  ?Request rdf:type ont:Request . " +
                "  ?Request ont:id ?id . " +
                "  ?Request ont:status ?status . " +
                "  ?Request ont:note ?note . " +
                "  ?Request ont:amount ?amount . " +
                "  ?Request ont:is_by ?is_by . " +
                "  ?is_by rdf:type ont:Item . " +
                "  ?is_by ont:name  \"" + exch + "\" . " +
                "  ?Request ont:belongs_to ?belongs_to . " +
                "  ?belongs_to rdf:type ont:User . " +
                "  ?belongs_to ont:username ?user . " +
                "  ?Request ont:is_for ?is_for . " +
                "  ?is_for rdf:type ont:Item . " +
                "  ?is_for ont:name \"" + desired + "\" . " +
                "}";


        Model model = FileManager.get().loadModel(ontologyFile);





        Query query = QueryFactory.create(sparqlQuery);
        QueryExecution qexec = QueryExecutionFactory.create(query, model);


        ResultSet results = qexec.execSelect();


        JSONArray resultArray = new JSONArray();
        while (results.hasNext()) {
            QuerySolution solution = results.nextSolution();
            JSONObject requestObject = new JSONObject();


            requestObject.put("Request", solution.getResource("Request").toString());
            requestObject.put("id", solution.getLiteral("id").getLexicalForm());
            requestObject.put("status", solution.getLiteral("status").getLexicalForm());
            requestObject.put("amount", solution.getLiteral("amount").getLexicalForm());
            requestObject.put("note", solution.getLiteral("note").getLexicalForm());
            requestObject.put("is_by", solution.getResource("is_by").toString());
            requestObject.put("exchangeable_object", exch);
            requestObject.put("belongs_to", solution.getResource("belongs_to").toString());
            requestObject.put("owner", solution.getLiteral("user").getLexicalForm());
            requestObject.put("is_for", solution.getResource("is_for").toString());
            requestObject.put("desired_object", desired);



            resultArray.put(requestObject);
        }


        qexec.close();

        return resultArray.toString();

    }

}


