package com.example.demo.controller;


import org.apache.jena.query.*;
import org.apache.jena.rdf.model.*;
import org.apache.jena.util.FileManager;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.util.Iterator;

//import com.example.demo.tools.JenaEngine;






@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/Event")
//@CrossOrigin(origins = "http://localhost:3001")
public class Event {
    @GetMapping("/helloFromEvent")
    public String getAllToursByTitler() {

        return "hello Manou";
    }

    @GetMapping("/getClothing_Exchange")
    public String getEvents() {

        String ontologyFile = "data/tradePal.owl"; // Replace with the actual path to your ontology file





        String sparqlQuery = "PREFIX ns: <http://www.semanticweb.org/user/ontologies/2023/9/tradepal#>\n" +
                "PREFIX ont: <http://www.co-ode.org/ontologies/ont.owl#>\n" +
                "PREFIX www: <http://www.w3.org/2002/07/>\n"+
                "PREFIX test6: <file:/C:/Users/Mouna/Desktop/Desktop/5eme/sem1/WebSemantique/antologies/test6.rdf#>\n"+
                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                "SELECT ?Clothing_Exchange ?date ?start ?end ?id ?color ?nom ?description ?image_path ?Lieu ?is_added_by " +
                "WHERE { " +
                "  ?Clothing_Exchange rdf:type ont:Clothing_Exchange . " +
                "  ?Clothing_Exchange ont:date ?date . " +
                "  ?Clothing_Exchange ont:start ?start . " +
                "  ?Clothing_Exchange ont:end ?end . " +
                "  ?Clothing_Exchange ont:id ?id . " +
                "  ?Clothing_Exchange ont:color ?color . " +
                "  ?Clothing_Exchange ont:nom ?nom . " +
                "  ?Clothing_Exchange ont:description ?description . " +
                "  ?Clothing_Exchange ont:image_path ?image_path . " +
                "  ?Clothing_Exchange ont:Lieu ?Lieu . " +
                "  ?Clothing_Exchange ont:is_added_by ?is_added_by . " +
                "}";


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
            JSONObject  exchangeObject = new JSONObject();
            exchangeObject.put("Clothing_Exchange", solution.getResource("Clothing_Exchange").toString()); // Renomme la clé
            exchangeObject.put("date", solution.getLiteral("date").getLexicalForm());
            exchangeObject.put("start", solution.getLiteral("start").getLexicalForm());
            exchangeObject.put("end", solution.getLiteral("end").getLexicalForm());
            exchangeObject.put("id", solution.getLiteral("id").getLexicalForm());
            exchangeObject.put("color", solution.getLiteral("color").getLexicalForm());
            exchangeObject.put("nom", solution.getLiteral("nom").getLexicalForm());
            exchangeObject.put("description", solution.getLiteral("description").getLexicalForm());
            exchangeObject.put("image_path", solution.getLiteral("image_path").getLexicalForm());
            exchangeObject.put("Lieu", solution.getLiteral("Lieu").getLexicalForm());
            exchangeObject.put("is_added_by", solution.getResource("is_added_by").toString());







            resultArray.put( exchangeObject);
        }

        return resultArray.toString();
    }

    @GetMapping("/getFood_Exchange")
    public String getFood_Exchange() {

        String ontologyFile = "data/tradePal.owl"; // Replace with the actual path to your ontology file





        String sparqlQuery = "PREFIX ns: <http://www.semanticweb.org/user/ontologies/2023/9/tradepal#>\n" +
                "PREFIX ont: <http://www.co-ode.org/ontologies/ont.owl#>\n" +
                "PREFIX www: <http://www.w3.org/2002/07/>\n"+
                "PREFIX test6: <file:/C:/Users/Mouna/Desktop/Desktop/5eme/sem1/WebSemantique/antologies/test6.rdf#>\n"+
                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                "SELECT ?Food_Exchange ?date ?start ?end ?id ?color ?nom ?description ?image_path ?Lieu ?is_added_by " +
                "WHERE { " +
                "  ?Food_Exchange rdf:type ont:Food_Exchange . " +
                "  ?Food_Exchange ont:date ?date . " +
                "  ?Food_Exchange ont:start ?start . " +
                "  ?Food_Exchange ont:end ?end . " +
                "  ?Food_Exchange ont:id ?id . " +
                "  ?Food_Exchange ont:color ?color . " +
                "  ?Food_Exchange ont:nom ?nom . " +
                "  ?Food_Exchange ont:description ?description . " +
                "  ?Food_Exchange ont:image_path ?image_path . " +
                "  ?Food_Exchange ont:Lieu ?Lieu . " +
                "  ?Food_Exchange ont:is_added_by ?is_added_by . " +
                "}";


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
            JSONObject  exchangeObject = new JSONObject();
            exchangeObject.put("Food_Exchange", solution.getResource("Food_Exchange").toString()); // Renomme la clé
            exchangeObject.put("date", solution.getLiteral("date").getLexicalForm());
            exchangeObject.put("start", solution.getLiteral("start").getLexicalForm());
            exchangeObject.put("end", solution.getLiteral("end").getLexicalForm());
            exchangeObject.put("id", solution.getLiteral("id").getLexicalForm());
            exchangeObject.put("color", solution.getLiteral("color").getLexicalForm());
            exchangeObject.put("nom", solution.getLiteral("nom").getLexicalForm());
            exchangeObject.put("description", solution.getLiteral("description").getLexicalForm());
            exchangeObject.put("image_path", solution.getLiteral("image_path").getLexicalForm());
            exchangeObject.put("Lieu", solution.getLiteral("Lieu").getLexicalForm());
            exchangeObject.put("is_added_by", solution.getResource("is_added_by").toString());







            resultArray.put( exchangeObject);
        }

        return resultArray.toString();
    }


//    @GetMapping("/getEventsByCategory")
//    public String getEventsByCategory(@RequestParam("category") String category) {
//        String ontologyFile = "data/tradePal.owl"; // Remplacez par le chemin réel de votre fichier ontologique
//        String categoryVariable = "Food_Exchange";
//        if ("Clothing_Exchange".equals(category)) {
//            categoryVariable = "Clothing_Exchange";
//        } // Ajoutez d'autres catégories au besoin
//
//        // Requête SPARQL pour récupérer les événements
//        String sparqlQuery = "PREFIX ns: <http://www.semanticweb.org/user/ontologies/2023/9/tradepal#>\n" +
//                "PREFIX ont: <http://www.co-ode.org/ontologies/ont.owl#>\n" +
//                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
//                "SELECT ?Event ?date ?start ?end ?id ?color ?nom ?description ?image_path ?Lieu ?is_added_by " +
//                "WHERE { " +
//                "  ?Event rdf:type ont:" + categoryVariable + " . " +
//                "  ?Event ont:date ?date . " +
//                "  ?Event ont:start ?start . " +
//                "  ?Event ont:end ?end . " +
//                "  ?Event ont:id ?id . " +
//                "  ?Event ont:color ?color . " +
//                "  ?Event ont:nom ?nom . " +
//                "  ?Event ont:description ?description . " +
//                "  ?Event ont:image_path ?image_path . " +
//                "  ?Event ont:Lieu ?Lieu . " +
//                "  ?Event ont:is_added_by ?is_added_by . " +
//                "}";
//
//        Model model = FileManager.get().loadModel(ontologyFile);
//        Query query = QueryFactory.create(sparqlQuery);
//        QueryExecution qexec = QueryExecutionFactory.create(query, model);
//        ResultSet results = qexec.execSelect();
//
//        JSONArray resultArray = new JSONArray();
//        while (results.hasNext()) {
//            QuerySolution solution = results.nextSolution();
//            JSONObject eventObject = new JSONObject();
//            eventObject.put("Event", solution.getResource("Event").toString());
//            eventObject.put("date", solution.getLiteral("date").getLexicalForm());
//            eventObject.put("start", solution.getLiteral("start").getLexicalForm());
//            eventObject.put("end", solution.getLiteral("end").getLexicalForm());
//            eventObject.put("id", solution.getLiteral("id").getLexicalForm());
//            eventObject.put("color", solution.getLiteral("color").getLexicalForm());
//            eventObject.put("nom", solution.getLiteral("nom").getLexicalForm());
//            eventObject.put("description", solution.getLiteral("description").getLexicalForm());
//            eventObject.put("image_path", solution.getLiteral("image_path").getLexicalForm());
//            eventObject.put("Lieu", solution.getLiteral("Lieu").getLexicalForm());
//
//            // Ajoutez d'autres propriétés de l'utilisateur
//            Resource userResource = solution.getResource("is_added_by");
//            if (userResource != null) {
//                StmtIterator userStatements = userResource.listProperties();
//                while (userStatements.hasNext()) {
//                    Statement userStatement = userStatements.nextStatement();
//                    if (userStatement.getObject().isLiteral()) {
//                        String userProperty = userStatement.getPredicate().getLocalName();
//                        String userValue = userStatement.getLiteral().getString();
//                        eventObject.put("is_added_by_" + userProperty, userValue);
//                    }
//                }
//            }
//
//            resultArray.put(eventObject);
//        }
//
//        return resultArray.toString();
//    }

//    @GetMapping("/getEventById/{id}")
//    public String getEventById(@PathVariable String id) {
//        String ontologyFile = "data/tradePal.owl"; // Remplacez par le chemin réel de votre fichier ontologique
//
//        // Requête SPARQL pour récupérer un événement par son ID
//        String sparqlQuery = "PREFIX ns: <http://www.semanticweb.org/user/ontologies/2023/9/tradepal#>\n" +
//                "PREFIX ont: <http://www.co-ode.org/ontologies/ont.owl#>\n" +
//                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
//                "SELECT ?Event ?date ?start ?end ?color ?nom ?description ?image_path ?Lieu ?is_added_by " +
//                "WHERE { " +
//                "  ?Event rdf:type ont:Event . " +
//                "  ?Event ont:id ?id . " +
//                "  ?Event ont:date ?date . " +
//                "  ?Event ont:start ?start . " +
//                "  ?Event ont:end ?end . " +
//                "  ?Event ont:color ?color . " +
//                "  ?Event ont:nom ?nom . " +
//                "  ?Event ont:description ?description . " +
//                "  ?Event ont:image_path ?image_path . " +
//                "  ?Event ont:Lieu ?Lieu . " +
//                "  ?Event ont:is_added_by ?is_added_by . " +
//                "  FILTER (?id = '" + id + "') " +
//                "}";
//
//        Model model = FileManager.get().loadModel(ontologyFile);
//        Query query = QueryFactory.create(sparqlQuery);
//        QueryExecution qexec = QueryExecutionFactory.create(query, model);
//        ResultSet results = qexec.execSelect();
//
//        JSONArray resultArray = new JSONArray();
//        while (results.hasNext()) {
//            QuerySolution solution = results.nextSolution();
//            JSONObject eventObject = new JSONObject();
//            eventObject.put("Event", solution.getResource("Event").toString());
//            eventObject.put("date", solution.getLiteral("date").getLexicalForm());
//            eventObject.put("start", solution.getLiteral("start").getLexicalForm());
//            eventObject.put("end", solution.getLiteral("end").getLexicalForm());
//            eventObject.put("color", solution.getLiteral("color").getLexicalForm());
//            eventObject.put("nom", solution.getLiteral("nom").getLexicalForm());
//            eventObject.put("description", solution.getLiteral("description").getLexicalForm());
//            eventObject.put("image_path", solution.getLiteral("image_path").getLexicalForm());
//            eventObject.put("Lieu", solution.getLiteral("Lieu").getLexicalForm());
//            eventObject.put("is_added_by", solution.getResource("is_added_by").getLocalName());
//            resultArray.put(eventObject);
//        }
//
//        return resultArray.toString();
//    }


    @GetMapping("/getParticipantsByCategory")
    public String getParticipantsByCategory(@RequestParam("category") String category) {
        String ontologyFile = "data/tradePal.owl"; // Remplacez par le chemin réel de votre fichier ontologique

        // Définissez la variable SPARQL pour la catégorie
        String categoryVariable = "Skill_Swap";
        if ("Food_Exchange".equals(category)) {
            categoryVariable = "Food_Exchange";
        } else if ("Clothing_Exchange".equals(category)) {
            categoryVariable = "Clothing_Exchange";
        } // Ajoutez d'autres catégories au besoin

        // Requête SPARQL pour récupérer le nombre de participants
        String sparqlQuery = "PREFIX ns: <http://www.semanticweb.org/user/ontologies/2023/9/tradepal#>\n" +
                "PREFIX ont: <http://www.co-ode.org/ontologies/ont.owl#>\n" +
                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                "SELECT ?Event ?nom (COUNT(?participant) AS ?participantCount) " +
                "WHERE { " +
                "  ?Event rdf:type ont:" + categoryVariable + " . " +
                "  ?Event ont:nom ?nom . " +
                "  ?Event ont:has_participations ?participant . " +
                "} GROUP BY ?Event ?nom";

        Model model = FileManager.get().loadModel(ontologyFile);
        Query query = QueryFactory.create(sparqlQuery);
        QueryExecution qexec = QueryExecutionFactory.create(query, model);
        ResultSet results = qexec.execSelect();

        JSONArray resultArray = new JSONArray();
        while (results.hasNext()) {
            QuerySolution solution = results.nextSolution();
            JSONObject eventObject = new JSONObject();
            eventObject.put("Event", solution.getResource("Event").toString());
            eventObject.put("nom", solution.getLiteral("nom").getLexicalForm());
            eventObject.put("participantCount", solution.getLiteral("participantCount").getInt());
            resultArray.put(eventObject);
        }

        return resultArray.toString();
    }

    @GetMapping("/getParticipantsByEventName")
    public String getParticipantsByEventName(@RequestParam("eventName") String eventName) {
        String ontologyFile = "data/tradePal.owl"; // Remplacez par le chemin réel de votre fichier ontologique

        // Requête SPARQL pour récupérer le nombre de participants pour un événement spécifique par son nom
        String sparqlQuery = "PREFIX ns: <http://www.semanticweb.org/user/ontologies/2023/9/tradepal#>\n" +
                "PREFIX ont: <http://www.co-ode.org/ontologies/ont.owl#>\n" +
                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                "SELECT ?Event ?eventName (COUNT(?participant) AS ?participantCount) " +
                "WHERE { " +
                "  ?Event rdf:type ont:Skill_Swap . " + // Remplacez le type d'événement si nécessaire
                "  ?Event ont:nom ?eventName . " +
                "  ?Event ont:has_participations ?participant . " +
                "} GROUP BY ?Event ?eventName";

        Model model = FileManager.get().loadModel(ontologyFile);
        Query query = QueryFactory.create(sparqlQuery);
        QueryExecution qexec = QueryExecutionFactory.create(query, model);
        ResultSet results = qexec.execSelect();

        JSONArray resultArray = new JSONArray();
        while (results.hasNext()) {
            QuerySolution solution = results.nextSolution();
            JSONObject eventObject = new JSONObject();
            eventObject.put("Event", solution.getResource("Event").toString());
            eventObject.put("eventName", solution.getLiteral("eventName").getLexicalForm());
            eventObject.put("participantCount", solution.getLiteral("participantCount").getInt());
            resultArray.put(eventObject);
        }

        return resultArray.toString();
    }


    @GetMapping("/getAllFoodExchanges")
    public String getAllFoodExchanges() {
        String ontologyFile = "data/tradePal.owl"; // Assurez-vous que le chemin est correct

        // Requête SPARQL pour récupérer tous les événements de type "Food_Exchange"
        String sparqlQuery = "PREFIX ns: <http://www.semanticweb.org/user/ontologies/2023/9/tradepal#>\n" +
                "PREFIX ont: <http://www.co-ode.org/ontologies/ont.owl#>\n" +
                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                "SELECT ?Event ?date ?start ?end ?color ?nom ?description ?image_path ?Lieu ?is_added_by " +
                "WHERE { " +
                "  ?Event rdf:type ont:Food_Exchange . " + // Ciblez la classe "Food_Exchange"
                "  ?Event ont:id ?id . " +
                "  ?Event ont:date ?date . " +
                "  ?Event ont:start ?start . " +
                "  ?Event ont:end ?end . " +
                "  ?Event ont:color ?color . " +
                "  ?Event ont:nom ?nom . " +
                "  ?Event ont:description ?description . " +
                "  ?Event ont:image_path ?image_path . " +
                "  ?Event ont:Lieu ?Lieu . " +
                "  ?Event ont:is_added_by ?is_added_by . " +
                "}";

        Model model = FileManager.get().loadModel(ontologyFile);
        Query query = QueryFactory.create(sparqlQuery);
        QueryExecution qexec = QueryExecutionFactory.create(query, model);
        ResultSet results = qexec.execSelect();

        JSONArray resultArray = new JSONArray();
        while (results.hasNext()) {
            QuerySolution solution = results.nextSolution();
            JSONObject eventObject = new JSONObject();
            eventObject.put("Event", solution.getResource("Event").toString());
            eventObject.put("date", solution.getLiteral("date").getLexicalForm());
            eventObject.put("start", solution.getLiteral("start").getLexicalForm());
            eventObject.put("end", solution.getLiteral("end").getLexicalForm());
            eventObject.put("color", solution.getLiteral("color").getLexicalForm());
            eventObject.put("nom", solution.getLiteral("nom").getLexicalForm());
            eventObject.put("description", solution.getLiteral("description").getLexicalForm());
            eventObject.put("image_path", solution.getLiteral("image_path").getLexicalForm());
            eventObject.put("Lieu", solution.getLiteral("Lieu").getLexicalForm());
            eventObject.put("is_added_by", solution.getResource("is_added_by").getLocalName());
            resultArray.put(eventObject);
        }

        return resultArray.toString();
    }


    @GetMapping("/getAllEvents")
    public String getAllEvents() {
        String ontologyFile = "data/tradePal.owl"; // Remplacez par le chemin réel de votre fichier ontologique

        // Requête SPARQL pour récupérer toutes les instances de la classe "Event"
        String sparqlQuery = "SELECT ?event WHERE { " +
                "?event rdf:type ont:Event. " +
                "}";

        Model model = FileManager.get().loadModel(ontologyFile);
        Query query = QueryFactory.create(sparqlQuery);
        QueryExecution qexec = QueryExecutionFactory.create(query, model);
        ResultSet results = qexec.execSelect();

        JSONArray resultArray = new JSONArray();
        while (results.hasNext()) {
            QuerySolution solution = results.nextSolution();
            JSONObject eventObject = new JSONObject();
            eventObject.put("event", solution.getResource("event").toString());
            resultArray.put(eventObject);
        }

        return resultArray.toString();
    }

    @GetMapping("/eventsByLieu")
    public String getEventsByLocation(@RequestParam(value = "location", required = false) String locationName) {
        String ontologyFile = "data/tradePal.owl"; // Remplacez par le chemin réel de votre fichier ontologique

        String sparqlQuery;
        if (locationName != null && !locationName.isEmpty()) {
            sparqlQuery = "SELECT ?Event WHERE { " +
                    "?Event rdf:type ont:Event. " +
                    "?Event ont:Lieu \"" + locationName + "\"." +
                    "}";
        } else {
            // Si aucun paramètre de lieu n'est fourni, récupérez tous les événements.
            sparqlQuery = "SELECT ?Event WHERE { " +
                    "?Event rdf:type ont:Event. " +
                    "}";
        }

        Model model = FileManager.get().loadModel(ontologyFile);
        Query query = QueryFactory.create(sparqlQuery);
        QueryExecution qexec = QueryExecutionFactory.create(query, model);
        ResultSet results = qexec.execSelect();

        JSONArray resultArray = new JSONArray();
        while (results.hasNext()) {
            QuerySolution solution = results.nextSolution();
            JSONObject eventObject = new JSONObject();
            eventObject.put("Event", solution.getResource("Event").toString());
            resultArray.put(eventObject);
        }

        return resultArray.toString();
    }



    // avec liey et category
    @GetMapping("/events")
    public String getEventsByCategoryAndLocation(
            @RequestParam(value = "location", required = false) String locationName,
            @RequestParam(value = "category", required = false) String categoryName
    ) {
        String ontologyFile = "data/tradePal.owl"; // Remplacez par le chemin réel de votre fichier ontologique

        // Construction de la requête SPARQL
        String sparqlQuery = "PREFIX ns: <http://www.semanticweb.org/user/ontologies/2023/9/tradepal#>\n" +
                "PREFIX ont: <http://www.co-ode.org/ontologies/ont.owl#>\n" +
                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                "SELECT ?event ?eventName WHERE { " +
                "?event rdf:type ns:Event.";

        if (locationName != null && !locationName.isEmpty()) {
            sparqlQuery += "?event ns:Lieu ?location." +
                    "FILTER (str(?location) = \"" + locationName + "\").";
        }

        if (categoryName != null && !categoryName.isEmpty()) {
            sparqlQuery += "?event ns:yourCategoryProperty ?category." + // Remplacez par la propriété de catégorie réelle
                    "FILTER (str(?category) = \"" + categoryName + "\").";
        }

        sparqlQuery += "?event ns:nom ?eventName." +
                "}";

        Model model = FileManager.get().loadModel(ontologyFile);
        Query query = QueryFactory.create(sparqlQuery);
        QueryExecution qexec = QueryExecutionFactory.create(query, model);
        ResultSet results = qexec.execSelect();

        JSONArray resultArray = new JSONArray();
        while (results.hasNext()) {
            QuerySolution solution = results.nextSolution();
            JSONObject eventObject = new JSONObject();
            eventObject.put("event", solution.getResource("event").toString());
            eventObject.put("eventName", solution.getLiteral("eventName").getString());
            resultArray.put(eventObject);
        }

        return resultArray.toString();
    }



    // nbPart ByLocation FooExch && lieu
    @GetMapping("/getFood_ExchangeAndLieu")
    public ResponseEntity<String> getFoodExchange(
            @RequestParam(value = "Lieu", required = false) String lieu) {
        String ontologyFile = "data/tradePal.owl"; // Remplacez par le chemin réel de votre fichier ontologique

        // Requête SPARQL pour récupérer les événements de la catégorie "Food_Exchange" (optionnellement filtrés par lieu)
        String sparqlQuery = "PREFIX ont: <http://www.co-ode.org/ontologies/ont.owl#>\n" +
                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +


                "SELECT ?Food_Exchange ?date ?start ?end ?id ?color ?nom ?description ?image_path ?Lieu ?is_added_by " +
                "WHERE { " +
                "  ?Food_Exchange rdf:type ont:Food_Exchange . " +
                "  ?Food_Exchange ont:date ?date . " +
                "  ?Food_Exchange ont:start ?start . " +
                "  ?Food_Exchange ont:end ?end . " +
                "  ?Food_Exchange ont:id ?id . " +
                "  ?Food_Exchange ont:color ?color . " +
                "  ?Food_Exchange ont:nom ?nom . " +
                "  ?Food_Exchange ont:description ?description . " +
                "  ?Food_Exchange ont:image_path ?image_path . " +
                "  ?Food_Exchange ont:Lieu ?Lieu . " +
                "  ?Food_Exchange ont:is_added_by ?is_added_by . " +
                (lieu != null ? "FILTER (str(?Lieu) = \"" + lieu + "\")" : "") +
                "}";

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
            JSONObject exchangeObject = new JSONObject();
            exchangeObject.put("Food_Exchange", solution.getResource("Food_Exchange").toString());
            exchangeObject.put("date", solution.getLiteral("date").getLexicalForm());
            exchangeObject.put("start", solution.getLiteral("start").getLexicalForm());
            exchangeObject.put("end", solution.getLiteral("end").getLexicalForm());
            exchangeObject.put("id", solution.getLiteral("id").getLexicalForm());
            exchangeObject.put("color", solution.getLiteral("color").getLexicalForm());
            exchangeObject.put("nom", solution.getLiteral("nom").getLexicalForm());
            exchangeObject.put("description", solution.getLiteral("description").getLexicalForm());
            exchangeObject.put("image_path", solution.getLiteral("image_path").getLexicalForm());
            exchangeObject.put("Lieu", solution.getLiteral("Lieu").getLexicalForm());
            exchangeObject.put("is_added_by", solution.getResource("is_added_by").toString());
            resultArray.put(exchangeObject);
        }

        return ResponseEntity.ok(resultArray.toString());
    }



    @GetMapping("/getEventCategories")
    public ResponseEntity<String> getEventCategories() {
        String ontologyFile = "data/tradePal.owl"; // Remplacez par le chemin réel de votre fichier ontologique

        // Requête SPARQL pour récupérer les catégories d'événements distinctes
        String sparqlQuery =

                "PREFIX ont: <http://www.co-ode.org/ontologies/ont.owl#>\n" +
                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                "SELECT DISTINCT ?category " +
                "WHERE { " +
                "  ?event rdf:type ?category . " +
                "}";

        Model model = FileManager.get().loadModel(ontologyFile);

        // Create a QueryExecution to execute the SPARQL query
        Query query = QueryFactory.create(sparqlQuery);
        QueryExecution qexec = QueryExecutionFactory.create(query, model);

        // Execute the query and get the results
        ResultSet results = qexec.execSelect();

        // Create a JSON array to store the category data
        JSONArray categoryArray = new JSONArray();

        while (results.hasNext()) {
            QuerySolution solution = results.nextSolution();
            RDFNode category = solution.get("category");
            String categoryName = category.asResource().getLocalName(); // Get the local name of the category

            // For each category, retrieve the events of that category
            JSONArray eventsArray = getEventsInCategory(model, categoryName);

            // Create a JSON object for the category and add its name and events
            JSONObject categoryObject = new JSONObject();
            categoryObject.put("CategoryName", categoryName);
            categoryObject.put("Events", eventsArray);

            categoryArray.put(categoryObject);
        }

        return ResponseEntity.ok(categoryArray.toString());
    }

    // Helper method to retrieve events in a specific category
    private JSONArray getEventsInCategory(Model model, String categoryName) {
        String sparqlQuery = "PREFIX ont: <http://www.co-ode.org/ontologies/ont.owl#>\n" +
                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                "SELECT ?Event ?date ?start ?end ?id ?color ?nom ?description ?image_path ?Lieu ?is_added_by " +
                "WHERE { " +
                "  ?Event rdf:type ont:" + categoryName + " . " +
                "  ?Event ont:date ?date . " +
                "  ?Event ont:start ?start . " +
                "  ?Event ont:end ?end . " +
                "  ?Event ont:id ?id . " +
                "  ?Event ont:color ?color . " +
                "  ?Event ont:nom ?nom . " +
                "  ?Event ont:description ?description . " +
                "  ?Event ont:image_path ?image_path . " +
                "  ?Event ont:Lieu ?Lieu . " +
                "  ?Event ont:is_added_by ?is_added_by . " +
                "}";

        Query query = QueryFactory.create(sparqlQuery);
        QueryExecution qexec = QueryExecutionFactory.create(query, model);
        ResultSet results = qexec.execSelect();

        JSONArray eventsArray = new JSONArray();

        while (results.hasNext()) {
            QuerySolution solution = results.nextSolution();
            JSONObject eventObject = new JSONObject();
            eventObject.put("Event", solution.getResource("Event").toString());
            eventObject.put("date", solution.getLiteral("date").getLexicalForm());
            eventObject.put("start", solution.getLiteral("start").getLexicalForm());
            eventObject.put("end", solution.getLiteral("end").getLexicalForm());
            eventObject.put("id", solution.getLiteral("id").getLexicalForm());
            eventObject.put("color", solution.getLiteral("color").getLexicalForm());
            eventObject.put("nom", solution.getLiteral("nom").getLexicalForm());
            eventObject.put("description", solution.getLiteral("description").getLexicalForm());
            eventObject.put("image_path", solution.getLiteral("image_path").getLexicalForm());
            eventObject.put("Lieu", solution.getLiteral("Lieu").getLexicalForm());
            eventObject.put("is_added_by", solution.getResource("is_added_by").toString());

            eventsArray.put(eventObject);
        }

        return eventsArray;
    }


    // get ALL Name OfEvents
    @GetMapping("/getNameOfEvents")
    public static String getAllNameEvents() {
        // Charger le modèle RDF à partir de votre ontologie OWL
        String ontURI = "http://www.co-ode.org/ontologies/ont.owl";
        Model model = ModelFactory.createDefaultModel();
        model.read("data/tradePal.owl");

        // Définir les préfixes
        String prefixes =
                "PREFIX ont: <http://www.co-ode.org/ontologies/ont.owl#>\n" +
                        "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n";

        // Écrire votre requête SPARQL pour extraire les noms de toutes les ressources
        String query =
                prefixes +
                        "SELECT DISTINCT ?eventName " +
                        "WHERE {" +
                        "  ?event ont:nom ?eventName ." +
                        "}";

        // Exécuter la requête SPARQL
        QueryExecution qe = QueryExecutionFactory.create(query, model);
        ResultSet results = qe.execSelect();

        try {
            // Créer un tableau JSON pour stocker les noms
            JSONArray eventNames = new JSONArray();

            while (results.hasNext()) {
                QuerySolution solution = results.nextSolution();
                RDFNode eventNameNode = solution.get("eventName");
                if (eventNameNode.isLiteral()) {
                    String eventName = eventNameNode.asLiteral().getString();
                    eventNames.put(eventName);
                }
            }

            // Construire la réponse en format JSON
            JSONObject response = new JSONObject();
            response.put("eventNames", eventNames);

            // Renvoyer la réponse en tant que chaîne JSON
            return response.toString();
        } catch (Exception e) {
            // Gérer les exceptions ici
            e.printStackTrace();
            return "Erreur lors de la récupération des données.";
        } finally {
            // Fermer la requête
            qe.close();
        }
    }

// get All Events
@GetMapping("/getAll")
public static String getAllEventsSubClasses() {
    // Charger le modèle RDF à partir de votre ontologie OWL
    String ontURI = "http://www.co-ode.org/ontologies/ont.owl";
    Model model = ModelFactory.createDefaultModel();
    model.read("data/tradePal.owl");

    // Définir les préfixes
    String prefixes =
            "PREFIX ont: <http://www.co-ode.org/ontologies/ont.owl#>\n" +
                    "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n";

    // Écrire votre requête SPARQL pour extraire toutes les propriétés des ressources
    String query =
            prefixes +
                    "SELECT ?eventName ?date ?start ?end ?description ?id ?Lieu ?imagePath ?color ?addedByUsername " +
                    "WHERE {" +
                    "  ?event ont:nom ?eventName ." +
                    "  ?event ont:date ?date ." +
                    "  ?event ont:start ?start ." +
                    "  ?event ont:end ?end ." +
                    "  ?event ont:description ?description ." +
                    "  ?event ont:id ?id ." +
                    "  ?event ont:Lieu ?Lieu ." +
                    "  ?event ont:image_path ?imagePath ." +
                    "  ?event ont:color ?color ." +
                    "  ?event ont:is_added_by ?addedBy ." +
                    "  ?addedBy ont:username ?addedByUsername ." +
                    "}";

    // Exécuter la requête SPARQL
    QueryExecution qe = QueryExecutionFactory.create(query, model);
    ResultSet results = qe.execSelect();

    try {
        // Créer un tableau JSON pour stocker les événements avec toutes leurs propriétés
        JSONArray events = new JSONArray();

        while (results.hasNext()) {
            QuerySolution solution = results.nextSolution();

            JSONObject event = new JSONObject();

            // Itérer sur les variables dans la requête SPARQL pour extraire les valeurs
            Iterator<String> varNames = solution.varNames();
            while (varNames.hasNext()) {
                String varName = varNames.next();
                RDFNode rdfNode = solution.get(varName);

                if (rdfNode.isLiteral()) {
                    // Si le nœud est un littéral, l'ajouter au JSON de l'événement
                    event.put(varName, rdfNode.asLiteral().getString());
                }
            }

            events.put(event);
        }

        // Construire la réponse en format JSON
        JSONObject response = new JSONObject();
        response.put("events", events);

        // Renvoyer la réponse en tant que chaîne JSON
        return response.toString();
    } catch (Exception e) {
        // Gérer les exceptions ici
        e.printStackTrace();
        return "Erreur lors de la récupération des données.";
    } finally {
        // Fermer la requête
        qe.close();
    }
}



// get events by category
@GetMapping("/getEventsByCategory")
public static String getEventsByCategory(@RequestParam("category") String category) {
    // Charger le modèle RDF à partir de votre ontologie OWL
    String ontURI = "http://www.co-ode.org/ontologies/ont.owl";
    Model model = ModelFactory.createDefaultModel();
    model.read("data/tradePal.owl");

    // Définir les préfixes
    String prefixes =
            "PREFIX ont: <http://www.co-ode.org/ontologies/ont.owl#>\n" +
                    "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n";

    // Écrire votre requête SPARQL pour extraire les attributs des ressources de la catégorie spécifiée avec le nom de l'ajouté
    String query =
            prefixes +
                    "SELECT ?eventName ?date ?start ?end ?description ?id ?nom ?Lieu ?color ?imagePath ?isAddedBy ?addedByUsername " +
                    "WHERE {" +
                    "  ?event ont:nom ?eventName ." +
                    "  ?event ont:date ?date ." +
                    "  ?event ont:start ?start ." +
                    "  ?event ont:end ?end ." +
                    "  ?event ont:description ?description ." +
                    "  ?event ont:id ?id ." +
                    "  ?event ont:nom ?nom ." +
                    "  ?event ont:Lieu ?Lieu ." +
                    "  ?event ont:color ?color ." +
                    "  ?event ont:image_path ?imagePath ." +
                    "  ?event ont:is_added_by ?isAddedBy ." +
                    "  ?isAddedBy ont:username ?addedByUsername ." +
                    "  ?event rdf:type ont:" + category + " . " +
                    "}";

    // Exécuter la requête SPARQL
    QueryExecution qe = QueryExecutionFactory.create(query, model);
    ResultSet results = qe.execSelect();

    try {
        // Créer un tableau JSON pour stocker les événements avec tous leurs attributs, y compris le nom de l'ajouté
        JSONArray events = new JSONArray();

        while (results.hasNext()) {
            QuerySolution solution = results.nextSolution();
            RDFNode eventNameNode = solution.get("eventName");
            RDFNode dateNode = solution.get("date");
            RDFNode startNode = solution.get("start");
            RDFNode endNode = solution.get("end");
            RDFNode descriptionNode = solution.get("description");
            RDFNode idNode = solution.get("id");
            RDFNode nomNode = solution.get("nom");
            RDFNode LieuNode = solution.get("Lieu");
            RDFNode colorNode = solution.get("color");
            RDFNode imagePathNode = solution.get("imagePath");
            RDFNode addedByUsernameNode = solution.get("addedByUsername");

            if (eventNameNode.isLiteral() && dateNode.isLiteral() && startNode.isLiteral() &&
                    endNode.isLiteral() && descriptionNode.isLiteral() && idNode.isLiteral() &&
                    nomNode.isLiteral() && LieuNode.isLiteral() && colorNode.isLiteral() &&
                    imagePathNode.isLiteral() && addedByUsernameNode.isLiteral()) {
                String eventName = eventNameNode.asLiteral().getString();
                String date = dateNode.asLiteral().getString();
                String start = startNode.asLiteral().getString();
                String end = endNode.asLiteral().getString();
                String description = descriptionNode.asLiteral().getString();
                String id = idNode.asLiteral().getString();
                String nom = nomNode.asLiteral().getString();
                String Lieu = LieuNode.asLiteral().getString();
                String color = colorNode.asLiteral().getString();
                String imagePath = imagePathNode.asLiteral().getString();
                String addedByUsername = addedByUsernameNode.asLiteral().getString();

                JSONObject event = new JSONObject();
                event.put("eventName", eventName);
                event.put("date", date);
                event.put("start", start);
                event.put("end", end);
                event.put("description", description);
                event.put("id", id);
                event.put("nom", nom);
                event.put("Lieu", Lieu);
                event.put("color", color);
                event.put("imagePath", imagePath);
                event.put("addedByUsername", addedByUsername);
                events.put(event);
            }
        }

        // Construire la réponse en format JSON
        JSONObject response = new JSONObject();
        response.put("events", events);

        // Renvoyer la réponse en tant que chaîne JSON
        return response.toString();
    } catch (Exception e) {
        // Gérer les exceptions ici
        e.printStackTrace();
        return "Erreur lors de la récupération des données.";
    } finally {
        // Fermer la requête
        qe.close();
    }
}
    @GetMapping("/getEventCategories2")
    public String getEventCategories2() {
        String ontURI = "http://www.co-ode.org/ontologies/ont.owl";
        Model model = ModelFactory.createDefaultModel();
        model.read("data/tradePal.owl");

        // Construction de la requête SPARQL pour extraire les catégories d'événements
        String sparqlQuery =
                "PREFIX ont: <http://www.co-ode.org/ontologies/ont.owl#>\n" +
                        "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                        "SELECT DISTINCT ?category " +
                        "WHERE {" +
                        "  ?event rdf:type ?category ." +
                        "  FILTER(STRSTARTS(STR(?category), STR(ont:)))" +
                        "}";

        Query query = QueryFactory.create(sparqlQuery);
        QueryExecution qexec = QueryExecutionFactory.create(query, model);

        ResultSet results = qexec.execSelect();

        JSONArray resultArray = new JSONArray();
        while (results.hasNext()) {
            QuerySolution solution = results.nextSolution();
            RDFNode categoryNode = solution.get("category");
            if (categoryNode.isURIResource()) {
                String category = categoryNode.asResource().getLocalName();
                resultArray.put(category);
            }
        }

        qexec.close();

        return resultArray.toString();
    }

// getBy Lieu

    @GetMapping("/getEventsByLieu")
    public static String getEventsByLieu(@RequestParam("location") String location) {
        // Charger le modèle RDF à partir de votre ontologie OWL
        String ontURI = "http://www.co-ode.org/ontologies/ont.owl";
        Model model = ModelFactory.createDefaultModel();
        model.read("data/tradePal.owl");

        // Définir les préfixes
        String prefixes =
                "PREFIX ont: <http://www.co-ode.org/ontologies/ont.owl#>\n" +
                        "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n";

        // Écrire votre requête SPARQL pour extraire les attributs des ressources de l'emplacement spécifié avec le nom de l'ajouté
        String query =
                prefixes +
                        "SELECT ?eventName ?date ?start ?end ?description ?id ?nom ?Lieu ?color ?imagePath ?addedByUsername " +
                        "WHERE {" +
                        "  ?event ont:nom ?eventName ." +
                        "  ?event ont:date ?date ." +
                        "  ?event ont:start ?start ." +
                        "  ?event ont:end ?end ." +
                        "  ?event ont:description ?description ." +
                        "  ?event ont:id ?id ." +
                        "  ?event ont:nom ?nom ." +
                        "  ?event ont:Lieu ?Lieu ." +
                        "  ?event ont:color ?color ." +
                        "  ?event ont:image_path ?imagePath ." +
                        "  ?event ont:is_added_by ?isAddedBy ." +
                        "  ?isAddedBy ont:username ?addedByUsername ." +
                        "  FILTER (str(?Lieu) = \"" + location + "\")" +
                        "}";

        // Exécuter la requête SPARQL
        QueryExecution qe = QueryExecutionFactory.create(query, model);
        ResultSet results = qe.execSelect();

        try {
            // Créer un tableau JSON pour stocker les événements avec tous leurs attributs, y compris le nom de l'ajouté
            JSONArray events = new JSONArray();

            while (results.hasNext()) {
                QuerySolution solution = results.nextSolution();
                RDFNode eventNameNode = solution.get("eventName");
                RDFNode dateNode = solution.get("date");
                RDFNode startNode = solution.get("start");
                RDFNode endNode = solution.get("end");
                RDFNode descriptionNode = solution.get("description");
                RDFNode idNode = solution.get("id");
                RDFNode nomNode = solution.get("nom");
                RDFNode LieuNode = solution.get("Lieu");
                RDFNode colorNode = solution.get("color");
                RDFNode imagePathNode = solution.get("imagePath");
                RDFNode addedByUsernameNode = solution.get("addedByUsername");

                if (eventNameNode.isLiteral() && dateNode.isLiteral() && startNode.isLiteral() &&
                        endNode.isLiteral() && descriptionNode.isLiteral() && idNode.isLiteral() &&
                        nomNode.isLiteral() && LieuNode.isLiteral() && colorNode.isLiteral() &&
                        imagePathNode.isLiteral() && addedByUsernameNode.isLiteral()) {
                    String eventName = eventNameNode.asLiteral().getString();
                    String date = dateNode.asLiteral().getString();
                    String start = startNode.asLiteral().getString();
                    String end = endNode.asLiteral().getString();
                    String description = descriptionNode.asLiteral().getString();
                    String id = idNode.asLiteral().getString();
                    String nom = nomNode.asLiteral().getString();
                    String Lieu = LieuNode.asLiteral().getString();
                    String color = colorNode.asLiteral().getString();
                    String imagePath = imagePathNode.asLiteral().getString();
                    String addedByUsername = addedByUsernameNode.asLiteral().getString();

                    JSONObject event = new JSONObject();
                    event.put("eventName", eventName);
                    event.put("date", date);
                    event.put("start", start);
                    event.put("end", end);
                    event.put("description", description);
                    event.put("id", id);
                    event.put("nom", nom);
                    event.put("Lieu", Lieu);
                    event.put("color", color);
                    event.put("imagePath", imagePath);
                    event.put("addedByUsername", addedByUsername);
                    events.put(event);
                }
            }

            // Construire la réponse en format JSON
            JSONObject response = new JSONObject();
            response.put("events", events);

            // Renvoyer la réponse en tant que chaîne JSON
            return response.toString();
        } catch (Exception e) {
            // Gérer les exceptions ici
            e.printStackTrace();
            return "Erreur lors de la récupération des données.";
        } finally {
            // Fermer la requête
            qe.close();
        }
    }
    @GetMapping("/getEventsByDate")
    public static String getEventsByDate(@RequestParam("date") String date) {
        // Charger le modèle RDF à partir de votre ontologie OWL
        Model model = ModelFactory.createDefaultModel();
        model.read("data/tradePal.owl");

        // Définir les préfixes
        String prefixes =
                "PREFIX ont: <http://www.co-ode.org/ontologies/ont.owl#>\n" +
                        "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n";

        // Écrire votre requête SPARQL pour extraire les attributs des ressources de la date spécifiée avec le nom de l'ajouté
        String query =
                prefixes +
                        "SELECT ?eventName ?date ?start ?end ?description ?id ?nom ?Lieu ?color ?imagePath ?addedByUsername " +
                        "WHERE {" +
                        "  ?event ont:nom ?eventName ." +
                        "  ?event ont:date ?date ." +
                        "  ?event ont:start ?start ." +
                        "  ?event ont:end ?end ." +
                        "  ?event ont:description ?description ." +
                        "  ?event ont:id ?id ." +
                        "  ?event ont:nom ?nom ." +
                        "  ?event ont:Lieu ?Lieu ." +
                        "  ?event ont:color ?color ." +
                        "  ?event ont:image_path ?imagePath ." +
                        "  ?event ont:is_added_by ?isAddedBy ." +
                        "  ?isAddedBy ont:username ?addedByUsername ." +
                        "  FILTER (str(?date) = \"" + date + "\")" +
                        "}";

        // Exécuter la requête SPARQL
        QueryExecution qe = QueryExecutionFactory.create(query, model);
        ResultSet results = qe.execSelect();

        try {
            // Créer un tableau JSON pour stocker les événements avec tous leurs attributs, y compris le nom de l'ajouté
            JSONArray events = new JSONArray();

            while (results.hasNext()) {
                QuerySolution solution = results.nextSolution();
                RDFNode eventNameNode = solution.get("eventName");
                RDFNode dateNode = solution.get("date");
                RDFNode startNode = solution.get("start");
                RDFNode endNode = solution.get("end");
                RDFNode descriptionNode = solution.get("description");
                RDFNode idNode = solution.get("id");
                RDFNode nomNode = solution.get("nom");
                RDFNode LieuNode = solution.get("Lieu");
                RDFNode colorNode = solution.get("color");
                RDFNode imagePathNode = solution.get("imagePath");
                RDFNode addedByUsernameNode = solution.get("addedByUsername");

                if (eventNameNode.isLiteral() && dateNode.isLiteral() && startNode.isLiteral() &&
                        endNode.isLiteral() && descriptionNode.isLiteral() && idNode.isLiteral() &&
                        nomNode.isLiteral() && LieuNode.isLiteral() && colorNode.isLiteral() &&
                        imagePathNode.isLiteral() && addedByUsernameNode.isLiteral()) {
                    String eventName = eventNameNode.asLiteral().getString();
                    String dateValue = dateNode.asLiteral().getString();
                    String start = startNode.asLiteral().getString();
                    String end = endNode.asLiteral().getString();
                    String description = descriptionNode.asLiteral().getString();
                    String id = idNode.asLiteral().getString();
                    String nom = nomNode.asLiteral().getString();
                    String Lieu = LieuNode.asLiteral().getString();
                    String color = colorNode.asLiteral().getString();
                    String imagePath = imagePathNode.asLiteral().getString();
                    String addedByUsername = addedByUsernameNode.asLiteral().getString();

                    JSONObject event = new JSONObject();
                    event.put("eventName", eventName);
                    event.put("date", dateValue);
                    event.put("start", start);
                    event.put("end", end);
                    event.put("description", description);
                    event.put("id", id);
                    event.put("nom", nom);
                    event.put("Lieu", Lieu);
                    event.put("color", color);
                    event.put("imagePath", imagePath);
                    event.put("addedByUsername", addedByUsername);
                    events.put(event);
                }
            }

            // Construire la réponse en format JSON
            JSONObject response = new JSONObject();
            response.put("events", events);

            // Renvoyer la réponse en tant que chaîne JSON
            return response.toString();
        } catch (Exception e) {
            // Gérer les exceptions ici
            e.printStackTrace();
            return "Erreur lors de la récupération des données.";
        } finally {
            // Fermer la requête
            qe.close();
        }
    }

//


    @GetMapping("/getAllEventDates")
    public static String getAllEventDates() {
        // Charger le modèle RDF à partir de votre ontologie OWL
        Model model = ModelFactory.createDefaultModel();
        model.read("data/tradePal.owl");

        // Définir les préfixes
        String prefixes =
                "PREFIX ont: <http://www.co-ode.org/ontologies/ont.owl#>\n" +
                        "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n";

        // Écrire votre requête SPARQL pour extraire toutes les dates des événements
        String query =
                prefixes +
                        "SELECT DISTINCT ?date " +
                        "WHERE {" +
                        "  ?event ont:date ?date ." +
                        "}";

        // Exécuter la requête SPARQL
        QueryExecution qe = QueryExecutionFactory.create(query, model);
        ResultSet results = qe.execSelect();

        try {
            // Créer un tableau JSON pour stocker les dates des événements
            JSONArray dates = new JSONArray();

            while (results.hasNext()) {
                QuerySolution solution = results.nextSolution();
                RDFNode dateNode = solution.get("date");

                if (dateNode.isLiteral()) {
                    String date = dateNode.asLiteral().getString();
                    dates.put(date);
                }
            }

            // Construire la réponse en format JSON
            JSONObject response = new JSONObject();
            response.put("eventDates", dates);

            // Renvoyer la réponse en tant que chaîne JSON
            return response.toString();
        } catch (Exception e) {
            // Gérer les exceptions ici
            e.printStackTrace();
            return "Erreur lors de la récupération des dates des événements.";
        } finally {
            // Fermer la requête
            qe.close();
        }
    }







//    public static void main(String[] args) {
//        String jsonResult = getAllEvents();
//        System.out.println(jsonResult);
//    }







    @GetMapping("/getLocationsOfEvents")
    public static String getLocationsOfEvents() {
        // Charger le modèle RDF à partir de votre ontologie OWL
        String ontURI = "http://www.co-ode.org/ontologies/ont.owl";
        Model model = ModelFactory.createDefaultModel();
        model.read("data/tradePal.owl");

        // Définir les préfixes
        String prefixes =
                "PREFIX ont: <http://www.co-ode.org/ontologies/ont.owl#>\n" +
                        "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n";

        // Écrire votre requête SPARQL pour extraire uniquement les lieux des événements
        String query =
                prefixes +
                        "SELECT DISTINCT ?location " +
                        "WHERE {" +
                        "  ?event ont:Lieu ?location ." +
                        "}";

        // Exécuter la requête SPARQL
        QueryExecution qe = QueryExecutionFactory.create(query, model);
        ResultSet results = qe.execSelect();

        try {
            // Créer une liste JSON pour stocker les lieux des événements
            JSONArray locations = new JSONArray();

            while (results.hasNext()) {
                QuerySolution solution = results.nextSolution();
                RDFNode locationNode = solution.get("location");

                if (locationNode.isLiteral()) {
                    String location = locationNode.asLiteral().getString();
                    locations.put(location);
                }
            }

            // Construire la réponse en format JSON
            JSONObject response = new JSONObject();
            response.put("locations", locations);

            // Renvoyer la réponse en tant que chaîne JSON
            return response.toString();
        } catch (Exception e) {
            // Gérer les exceptions ici
            e.printStackTrace();
            return "Erreur lors de la récupération des données.";
        } finally {
            // Fermer la requête
            qe.close();
        }
    }

    @GetMapping("/getAllCategories")
    public static String getAllCategories() {
        // Charger le modèle RDF à partir de votre ontologie OWL
        Model model = ModelFactory.createDefaultModel();
        model.read("data/tradePal.owl");

        // Définir les préfixes
        String prefixes =
                "PREFIX ont: <http://www.co-ode.org/ontologies/ont.owl#>\n" +
                        "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n";

        // Écrire votre requête SPARQL pour extraire tous les noms des ressources de type "rdf:resource"
        String query =
                prefixes +
                        "SELECT DISTINCT ?categoryName " +
                        "WHERE {" +
                        "  ?event rdf:type ?category ." +
                        "  ?category rdf:resource ?categoryName ." +
                        "}";

        // Exécuter la requête SPARQL
        QueryExecution qe = QueryExecutionFactory.create(query, model);
        ResultSet results = qe.execSelect();

        try {
            // Créer une liste JSON pour stocker les noms des catégories
            JSONArray categories = new JSONArray();

            while (results.hasNext()) {
                QuerySolution solution = results.nextSolution();
                RDFNode categoryNameNode = solution.get("categoryName");

                if (categoryNameNode.isResource()) {
                    String categoryName = categoryNameNode.asResource().getLocalName();
                    categories.put(categoryName);
                }
            }

            // Construire la réponse en format JSON
            JSONObject response = new JSONObject();
            response.put("categories", categories);

            // Renvoyer la réponse en tant que chaîne JSON
            return response.toString();
        } catch (Exception e) {
            // Gérer les exceptions ici
            e.printStackTrace();
            return "Erreur lors de la récupération des données.";
        } finally {
            // Fermer la requête
            qe.close();
        }
    }

    @GetMapping("/getAddedByUsers")
    public String getAddedByUsers() {
        // Charger le modèle RDF à partir de votre ontologie OWL
        Model model = ModelFactory.createDefaultModel();
        model.read("data/tradePal.owl");

        // Définir les préfixes
        String prefixes =
                "PREFIX ont: <http://www.co-ode.org/ontologies/ont.owl#>\n" +
                        "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n";

        // Écrire votre requête SPARQL pour extraire les noms d'utilisateurs "added by" pour tous les événements
        String query =
                prefixes +
                        "SELECT DISTINCT ?addedByUsername " +
                        "WHERE {" +
                        "  ?event ont:is_added_by ?addedBy ." +
                        "  ?addedBy ont:username ?addedByUsername ." +
                        "}";

        // Exécuter la requête SPARQL
        QueryExecution qe = QueryExecutionFactory.create(query, model);
        ResultSet results = qe.execSelect();

        try {
            // Créer un tableau JSON pour stocker les noms d'utilisateurs "added by"
            JSONArray users = new JSONArray();

            while (results.hasNext()) {
                QuerySolution solution = results.nextSolution();
                RDFNode addedByUsernameNode = solution.get("addedByUsername");

                if (addedByUsernameNode.isLiteral()) {
                    String addedByUsername = addedByUsernameNode.asLiteral().getString();
                    users.put(addedByUsername);
                }
            }

            // Construire la réponse en format JSON
            JSONObject response = new JSONObject();
            response.put("addedByUsers", users);

            // Renvoyer la réponse en tant que chaîne JSON
            return response.toString();
        } catch (Exception e) {
            // Gérer les exceptions ici
            e.printStackTrace();
            return "Erreur lors de la récupération des données.";
        } finally {
            // Fermer la requête
            qe.close();
        }
    }


    @GetMapping("/getEventsByAddedByUsername")
    public String getEventsByAddedByUsername(@RequestParam("addedByUsername") String addedByUsername) {
        // Charger le modèle RDF à partir de votre ontologie OWL
        Model model = ModelFactory.createDefaultModel();
        model.read("data/tradePal.owl");

        // Définir les préfixes
        String prefixes =
                "PREFIX ont: <http://www.co-ode.org/ontologies/ont.owl#>\n" +
                        "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n";

        // Écrire votre requête SPARQL pour extraire les événements ajoutés par un utilisateur spécifique
        String query =
                prefixes +
                        "SELECT ?eventName ?date ?start ?end ?description ?id ?Lieu ?color ?imagePath " +
                        "WHERE {" +
                        "  ?event ont:is_added_by ?isAddedBy ." +
                        "  ?isAddedBy ont:username \"" + addedByUsername + "\" ." +
                        "  ?isAddedBy ont:username ?username ." + // Sélectionnez le nom d'utilisateur

                        "  ?event ont:nom ?eventName ." +
                        "  ?event ont:date ?date ." +
                        "  ?event ont:start ?start ." +
                        "  ?event ont:end ?end ." +
                        "  ?event ont:description ?description ." +
                        "  ?event ont:id ?id ." +
                        "  ?event ont:Lieu ?Lieu ." +
                        "  ?event ont:color ?color ." +
                        "  ?event ont:image_path ?imagePath ." +
                        "}";

        // Exécuter la requête SPARQL
        QueryExecution qe = QueryExecutionFactory.create(query, model);
        ResultSet results = qe.execSelect();

        try {
            // Créer un tableau JSON pour stocker les événements ajoutés par l'utilisateur spécifié
            JSONArray events = new JSONArray();

            while (results.hasNext()) {
                QuerySolution solution = results.nextSolution();
                RDFNode eventNameNode = solution.get("eventName");
                RDFNode dateNode = solution.get("date");
                RDFNode startNode = solution.get("start");
                RDFNode endNode = solution.get("end");
                RDFNode descriptionNode = solution.get("description");
                RDFNode idNode = solution.get("id");
                RDFNode LieuNode = solution.get("Lieu");
                RDFNode colorNode = solution.get("color");
                RDFNode imagePathNode = solution.get("imagePath");

                if (eventNameNode.isLiteral() && dateNode.isLiteral() && startNode.isLiteral() &&
                        endNode.isLiteral() && descriptionNode.isLiteral() && idNode.isLiteral() &&
                        LieuNode.isLiteral() && colorNode.isLiteral() && imagePathNode.isLiteral()) {
                    String eventName = eventNameNode.asLiteral().getString();
                    String date = dateNode.asLiteral().getString();
                    String start = startNode.asLiteral().getString();
                    String end = endNode.asLiteral().getString();
                    String description = descriptionNode.asLiteral().getString();
                    String id = idNode.asLiteral().getString();
                    String Lieu = LieuNode.asLiteral().getString();
                    String color = colorNode.asLiteral().getString();
                    String imagePath = imagePathNode.asLiteral().getString();

                    JSONObject event = new JSONObject();
                    event.put("eventName", eventName);
                    event.put("date", date);
                    event.put("start", start);
                    event.put("end", end);
                    event.put("description", description);
                    event.put("id", id);
                    event.put("Lieu", Lieu);
                    event.put("color", color);
                    event.put("imagePath", imagePath);
                    event.put("addedByUsername", addedByUsername);
                    events.put(event);
                }
            }

            // Construire la réponse en format JSON
            JSONObject response = new JSONObject();
            response.put("events", events);

            // Renvoyer la réponse en tant que chaîne JSON
            return response.toString();
        } catch (Exception e) {
            // Gérer les exceptions ici
            e.printStackTrace();
            return "Erreur lors de la récupération des données.";
        } finally {
            // Fermer la requête
            qe.close();
        }
    }

    @GetMapping("/LocName")
    public String getEventsByEventNameAndLocation(
            @RequestParam(value = "eventName", required = false) String eventName,
            @RequestParam(value = "location", required = false) String location
    ) {
        String ontologyFile = "data/tradePal.owl"; // Remplacez par le chemin réel de votre fichier ontologique

        // Construction de la requête SPARQL en fonction des paramètres eventName et location fournis
        String sparqlQuery = "PREFIX ont: <http://www.co-ode.org/ontologies/ont.owl#>\n" +
                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                "SELECT ?eventName ?date ?start ?end ?description ?id ?Lieu ?color ?imagePath ?addedByUsername " +
                "WHERE { " +
                "?event rdf:type ont:Event.";

        if (eventName != null && !eventName.isEmpty()) {
            sparqlQuery += "?event ont:nom \"" + eventName + "\".";
        }

        if (location != null && !location.isEmpty()) {
            sparqlQuery += "?event ont:Lieu \"" + location + "\".";
        }

        sparqlQuery += "?event ont:nom ?eventName." +
                "?event ont:date ?date." +
                "?event ont:start ?start." +
                "?event ont:end ?end." +
                "?event ont:description ?description." +
                "?event ont:id ?id." +
                "?event ont:Lieu ?Lieu." +
                "?event ont:color ?color." +
                "?event ont:image_path ?imagePath." +
                "?event ont:is_added_by ?isAddedBy." +
                "?isAddedBy ont:username ?addedByUsername." +
                "}";

        Model model = FileManager.get().loadModel(ontologyFile);
        Query query = QueryFactory.create(sparqlQuery);
        QueryExecution qexec = QueryExecutionFactory.create(query, model);
        ResultSet results = qexec.execSelect();

        JSONArray resultArray = new JSONArray();
        while (results.hasNext()) {
            QuerySolution solution = results.nextSolution();
            JSONObject eventObject = new JSONObject();
            eventObject.put("eventName", solution.get("eventName").toString());
            eventObject.put("date", solution.get("date").toString());
            eventObject.put("start", solution.get("start").toString());
            eventObject.put("end", solution.get("end").toString());
            eventObject.put("description", solution.get("description").toString());
            eventObject.put("id", solution.get("id").toString());
            eventObject.put("Lieu", solution.get("Lieu").toString());
            eventObject.put("color", solution.get("color").toString());
            eventObject.put("imagePath", solution.get("imagePath").toString());
            eventObject.put("addedByUsername", solution.get("addedByUsername").toString());
            resultArray.put(eventObject);
        }

        return resultArray.toString();
    }


    @GetMapping("/getParticipations")
    public String getParticipations() {
        String ontologyFile = "data/tradePal.owl"; // Remplacez par le chemin réel de votre fichier ontologique

        // Construction de la requête SPARQL pour extraire toutes les participations avec le nom de l'événement associé
        String sparqlQuery = "PREFIX ont: <http://www.co-ode.org/ontologies/ont.owl#>\n" +
                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                "SELECT ?id ?nomUser ?eventName " +
                "WHERE { " +
                "?participation rdf:type ont:Participation." +
                "?participation ont:id ?id." +
                "?participation ont:nomUser ?nomUser." +
                "?participation ont:related_to ?event." +
                "?event ont:nom ?eventName." +
                "}";

        Model model = FileManager.get().loadModel(ontologyFile);
        Query query = QueryFactory.create(sparqlQuery);
        QueryExecution qexec = QueryExecutionFactory.create(query, model);
        ResultSet results = qexec.execSelect();

        JSONArray resultArray = new JSONArray();
        while (results.hasNext()) {
            QuerySolution solution = results.nextSolution();
            JSONObject participationObject = new JSONObject();
            participationObject.put("id", solution.get("id").asLiteral().getLexicalForm());
            participationObject.put("nomUser", solution.get("nomUser").asLiteral().getLexicalForm());
            participationObject.put("eventName", solution.get("eventName").asLiteral().getLexicalForm());
            resultArray.put(participationObject);
        }

        return resultArray.toString();
    }
    @GetMapping("/getParticipationsBYEventName")
    public String getParticipationsBYEventName(@RequestParam(value = "eventName", required = false) String eventName) {
        String ontologyFile = "data/tradePal.owl"; // Remplacez par le chemin réel de votre fichier ontologique

        // Construction de la requête SPARQL pour extraire les participations en fonction du nom de l'événement
        String sparqlQuery = "PREFIX ont: <http://www.co-ode.org/ontologies/ont.owl#>\n" +
                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                "SELECT ?id ?nomUser ?eventName " +
                "WHERE { " +
                "?participation rdf:type ont:Participation." +
                "?participation ont:id ?id." +
                "?participation ont:nomUser ?nomUser." +
                "?participation ont:related_to ?event." +
                "?event ont:nom ?eventName.";

        if (eventName != null && !eventName.isEmpty()) {
            sparqlQuery += "FILTER (str(?eventName) = \"" + eventName + "\").";
        }

        sparqlQuery += "}";

        Model model = FileManager.get().loadModel(ontologyFile);
        Query query = QueryFactory.create(sparqlQuery);
        QueryExecution qexec = QueryExecutionFactory.create(query, model);
        ResultSet results = qexec.execSelect();

        JSONArray resultArray = new JSONArray();
        while (results.hasNext()) {
            QuerySolution solution = results.nextSolution();
            JSONObject participationObject = new JSONObject();
            participationObject.put("id", solution.get("id").asLiteral().getLexicalForm());
            participationObject.put("nomUser", solution.get("nomUser").asLiteral().getLexicalForm());
            participationObject.put("eventName", solution.get("eventName").asLiteral().getLexicalForm());
            resultArray.put(participationObject);
        }

        return resultArray.toString();
    }

    @GetMapping("/getEventNames")
    public String getEventNames() {
        String ontologyFile = "data/tradePal.owl"; // Remplacez par le chemin réel de votre fichier ontologique

        // Construction de la requête SPARQL pour extraire tous les noms d'événements
        String sparqlQuery = "PREFIX ont: <http://www.co-ode.org/ontologies/ont.owl#>\n" +
                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                "SELECT ?eventName " +
                "WHERE { " +
                "?event rdf:type ont:Event." +
                "?event ont:nom ?eventName." +
                "}";

        Model model = FileManager.get().loadModel(ontologyFile);
        Query query = QueryFactory.create(sparqlQuery);
        QueryExecution qexec = QueryExecutionFactory.create(query, model);
        ResultSet results = qexec.execSelect();

        JSONArray resultArray = new JSONArray();
        while (results.hasNext()) {
            QuerySolution solution = results.nextSolution();
            JSONObject eventNameObject = new JSONObject();
            eventNameObject.put("eventName", solution.get("eventName").asLiteral().getLexicalForm());
            resultArray.put(eventNameObject);
        }

        return resultArray.toString();
    }



    @GetMapping("/getEventsByCategoryAndLocation3")
    public String getEventsByCategoryAndLocation2(
            @RequestParam("location") String location,
            @RequestParam("category") String category
    ) {
        // Utilisez vos méthodes existantes pour récupérer les événements en fonction de la catégorie et de l'emplacement
        String eventsByLocation = getEventsByLieu(location);
        String eventsByCategory = getEventsByCategory(category);

        // Analysez les chaînes JSON renvoyées par les méthodes pour obtenir les événements
        JSONObject locationJSON = new JSONObject(eventsByLocation);
        JSONObject categoryJSON = new JSONObject(eventsByCategory);

        // Obtenez les tableaux d'événements des deux réponses
        JSONArray eventsFromLocation = locationJSON.getJSONArray("events");
        JSONArray eventsFromCategory = categoryJSON.getJSONArray("events");

        // Initialisez un tableau pour stocker les événements filtrés
        JSONArray filteredEvents = new JSONArray();

        // Parcourez les événements de l'emplacement
        for (int i = 0; i < eventsFromLocation.length(); i++) {
            JSONObject event = eventsFromLocation.getJSONObject(i);
            String eventName = event.getString("eventName");

            // Vérifiez si l'événement est également présent dans les événements de la catégorie
            for (int j = 0; j < eventsFromCategory.length(); j++) {
                JSONObject categoryEvent = eventsFromCategory.getJSONObject(j);
                String categoryEventName = categoryEvent.getString("eventName");

                if (eventName.equals(categoryEventName)) {
                    // L'événement correspond à la fois à la catégorie et à l'emplacement
                    filteredEvents.put(event);
                    break; // Sortez de la boucle interne
                }
            }
        }

        // Construisez la réponse JSON avec les événements filtrés
        JSONObject response = new JSONObject();
        response.put("events", filteredEvents);

        return response.toString();
    }



    @GetMapping("/getEventNames2")
    public String getEventNames2() {
        // Charger le modèle RDF à partir de votre ontologie OWL
        String ontURI = "http://www.co-ode.org/ontologies/ont.owl";
        Model model = ModelFactory.createDefaultModel();
        model.read("data/tradePal.owl");

        // Définir les préfixes
        String prefixes =
                "PREFIX ont: <http://www.co-ode.org/ontologies/ont.owl#>\n" +
                        "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n";

        // Écrire votre requête SPARQL pour extraire tous les noms d'événements
        String query =
                prefixes +
                        "SELECT ?eventName " +
                        "WHERE {" +
                        "  ?event ont:nom ?eventName ." +
                        "}";

        // Exécuter la requête SPARQL
        QueryExecution qe = QueryExecutionFactory.create(query, model);
        ResultSet results = qe.execSelect();

        try {
            // Créer un tableau JSON pour stocker les noms des événements
            JSONArray eventNames = new JSONArray();

            while (results.hasNext()) {
                QuerySolution solution = results.nextSolution();
                RDFNode eventNameNode = solution.get("eventName");

                if (eventNameNode.isLiteral()) {
                    String eventName = eventNameNode.asLiteral().getString();
                    eventNames.put(eventName);
                }
            }

            // Construire la réponse en format JSON
            JSONObject response = new JSONObject();
            response.put("eventNames", eventNames);

            // Renvoyer la réponse en tant que chaîne JSON
            return response.toString();
        } catch (Exception e) {
            // Gérer les exceptions ici
            e.printStackTrace();
            return "Erreur lors de la récupération des noms d'événements.";
        } finally {
            // Fermer la requête
            qe.close();
        }
    }


}

















