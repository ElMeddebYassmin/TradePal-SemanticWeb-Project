package com.example.demo.controller;


import java.io.ByteArrayOutputStream;

import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.util.FileManager;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.tools.JenaEngine;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/items")
public class ItemController {

    @GetMapping("/getItems")
    public String getItems() {

        String ontologyFile = "data/tradePal.owl";


        String sparqlQuery = "PREFIX ns: <http://www.semanticweb.org/user/ontologies/2023/9/tradepal#>\n" +
                "PREFIX ont: <http://www.co-ode.org/ontologies/ont.owl#>\n" +
                "PREFIX www: <http://www.w3.org/2002/07/>\n"+
                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                "SELECT ?Item ?id ?amount ?status ?name ?description ?category ?categoryName ?owner ?username " +
                "WHERE { " +
                "  ?Item rdf:type ont:Item . " +
                "  ?Item ont:id ?id . " +
                "  ?Item ont:amount ?amount . " +
                "  ?Item ont:status ?status . " +
                "  ?Item ont:name ?name . " +
                "  ?Item ont:description ?description . " +
                "  ?Item ont:has_category ?category . " +
                "  ?category rdf:type ont:ItemCategory . " +
                "  ?category ont:name ?categoryName . " +
                "  ?Item ont:is_owned_by ?owner . " +
                "  ?owner rdf:type ont:User . " +
                "  ?owner ont:username ?username . " +
                "}";

        Model model = FileManager.get().loadModel(ontologyFile);




        Query query = QueryFactory.create(sparqlQuery);
        QueryExecution qexec = QueryExecutionFactory.create(query, model);


        ResultSet results = qexec.execSelect();


        JSONArray resultArray = new JSONArray();
        while (results.hasNext()) {
            QuerySolution solution = results.nextSolution();
            JSONObject itemObject = new JSONObject();

            itemObject.put("Item", solution.getResource("Item").toString());
            itemObject.put("id", solution.getLiteral("id").getLexicalForm());
            itemObject.put("amount", solution.getLiteral("amount").getLexicalForm());
            itemObject.put("status", solution.getLiteral("status").getLexicalForm());
            itemObject.put("name", solution.getLiteral("name").getLexicalForm());
            itemObject.put("description", solution.getLiteral("description").getLexicalForm());
            itemObject.put("category", solution.getResource("category").toString());
            itemObject.put("CategoryName", solution.getLiteral("categoryName").getLexicalForm());
            itemObject.put("owner", solution.getResource("owner").toString());
            itemObject.put("OwnerUsername", solution.getLiteral("username").getLexicalForm());



            resultArray.put(itemObject);
        }


        qexec.close();

        return resultArray.toString();

    }
    @GetMapping("/getItemsByCategory/{category}")
    public String getItemsByCategory(@PathVariable String category) {

        String ontologyFile = "data/tradePal.owl";


        String sparqlQuery = "PREFIX ns: <http://www.semanticweb.org/user/ontologies/2023/9/tradepal#>\n" +
                "PREFIX ont: <http://www.co-ode.org/ontologies/ont.owl#>\n" +
                "PREFIX www: <http://www.w3.org/2002/07/>\n"+
                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                "SELECT ?Item ?id ?amount ?status ?name ?description ?category  ?owner ?username " +
                "WHERE { " +
                "  ?Item rdf:type ont:Item . " +
                "  ?Item ont:id ?id . " +
                "  ?Item ont:amount ?amount . " +
                "  ?Item ont:status ?status . " +
                "  ?Item ont:name ?name . " +
                "  ?Item ont:description ?description . " +
                "  ?Item ont:has_category ?category . " +
                "  ?Item ont:is_owned_by ?owner . " +
                "  ?owner rdf:type ont:User . " +
                "  ?owner ont:username ?username . " +
                "  ?category rdf:type ont:ItemCategory . " +
                "  ?category ont:name \"" + category + "\" . " +
                "}";
        Model model = FileManager.get().loadModel(ontologyFile);




        Query query = QueryFactory.create(sparqlQuery);
        QueryExecution qexec = QueryExecutionFactory.create(query, model);


        ResultSet results = qexec.execSelect();


        JSONArray resultArray = new JSONArray();
        while (results.hasNext()) {
            QuerySolution solution = results.nextSolution();
            JSONObject itemObject = new JSONObject();

            itemObject.put("Item", solution.getResource("Item").toString());
            itemObject.put("id", solution.getLiteral("id").getLexicalForm());
            itemObject.put("amount", solution.getLiteral("amount").getLexicalForm());
            itemObject.put("status", solution.getLiteral("status").getLexicalForm());
            itemObject.put("name", solution.getLiteral("name").getLexicalForm());
            itemObject.put("description", solution.getLiteral("description").getLexicalForm());
            itemObject.put("category", solution.getResource("category").toString());
            itemObject.put("CategoryName", category);
            itemObject.put("owner", solution.getResource("owner").toString());
            itemObject.put("OwnerUsername", solution.getLiteral("username").getLexicalForm());




            resultArray.put(itemObject);
        }


        qexec.close();

        return resultArray.toString();

    }

    @GetMapping("/getItemsByStatus/{status}")
    public String getItemsByStatus(@PathVariable String status) {

        String ontologyFile = "data/tradePal.owl";


        String sparqlQuery = "PREFIX ns: <http://www.semanticweb.org/user/ontologies/2023/9/tradepal#>\n" +
                "PREFIX ont: <http://www.co-ode.org/ontologies/ont.owl#>\n" +
                "PREFIX www: <http://www.w3.org/2002/07/>\n"+
                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                "SELECT ?Item ?id ?amount ?status ?name ?description ?category ?categoryName ?owner ?username " +
                "WHERE { " +
                "  ?Item rdf:type ont:Item . " +
                "  ?Item ont:id ?id . " +
                "  ?Item ont:amount ?amount . " +
                "  ?Item ont:status  \"" + status + "\" . " +
                "  ?Item ont:name ?name . " +
                "  ?Item ont:description ?description . " +
                "  ?Item ont:has_category ?category . " +
                "  ?category rdf:type ont:ItemCategory . " +
                "  ?category ont:name ?categoryName . " +
                "  ?Item ont:is_owned_by ?owner . " +
                "  ?owner rdf:type ont:User . " +
                "  ?owner ont:username ?username . " +
                "}";

        Model model = FileManager.get().loadModel(ontologyFile);




        Query query = QueryFactory.create(sparqlQuery);
        QueryExecution qexec = QueryExecutionFactory.create(query, model);


        ResultSet results = qexec.execSelect();


        JSONArray resultArray = new JSONArray();
        while (results.hasNext()) {
            QuerySolution solution = results.nextSolution();
            JSONObject itemObject = new JSONObject();

            itemObject.put("Item", solution.getResource("Item").toString());
            itemObject.put("id", solution.getLiteral("id").getLexicalForm());
            itemObject.put("amount", solution.getLiteral("amount").getLexicalForm());
            itemObject.put("status", status);
            itemObject.put("name", solution.getLiteral("name").getLexicalForm());
            itemObject.put("description", solution.getLiteral("description").getLexicalForm());
            itemObject.put("category", solution.getResource("category").toString());
            itemObject.put("CategoryName", solution.getLiteral("categoryName").getLexicalForm());
            itemObject.put("owner", solution.getResource("owner").toString());
            itemObject.put("OwnerUsername", solution.getLiteral("username").getLexicalForm());



            resultArray.put(itemObject);
        }


        qexec.close();

        return resultArray.toString();

    }



    @GetMapping("/getCategoryItems")
    public String getCategoryItems() {

        String ontologyFile = "data/tradePal.owl";


        String sparqlQuery = "PREFIX ns: <http://www.semanticweb.org/user/ontologies/2023/9/tradepal#>\n" +
                "PREFIX ont: <http://www.co-ode.org/ontologies/ont.owl#>\n" +
                "PREFIX www: <http://www.w3.org/2002/07/>\n"+
                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                "SELECT ?ItemCategory ?id ?name ?contains_items " +
                "WHERE { " +
                "  ?ItemCategory rdf:type ont:ItemCategory . " +
                "  ?ItemCategory ont:id ?id . " +
                "  ?ItemCategory ont:name ?name . " +
                "  ?ItemCategory ont:contains_items ?contains_items . " +
                "}";

        Model model = FileManager.get().loadModel(ontologyFile);




        Query query = QueryFactory.create(sparqlQuery);
        QueryExecution qexec = QueryExecutionFactory.create(query, model);


        ResultSet results = qexec.execSelect();


        JSONArray resultArray = new JSONArray();
        while (results.hasNext()) {
            QuerySolution solution = results.nextSolution();
            JSONObject itemCategoryObject = new JSONObject();
            itemCategoryObject.put("ItemCategory", solution.getResource("ItemCategory").toString());
            itemCategoryObject.put("id", solution.getLiteral("id").getLexicalForm());
            itemCategoryObject.put("name", solution.getLiteral("name").getLexicalForm());


            RDFNode containsItems = solution.get("contains_items");
            if (containsItems != null && containsItems.isResource()) {
                // Récupérez les détails des éléments liés
                Resource itemResource = containsItems.asResource();
                String itemId = itemResource.getProperty(model.getProperty("http://www.co-ode.org/ontologies/ont.owl#", "id")).getLiteral().getString();
                String itemName = itemResource.getProperty(model.getProperty("http://www.co-ode.org/ontologies/ont.owl#", "name")).getLiteral().getString();
                itemCategoryObject.put("contains_items", itemId + ": " + itemName);
            }
            resultArray.put(itemCategoryObject);
        }


        qexec.close();

        return resultArray.toString();

    }


}
