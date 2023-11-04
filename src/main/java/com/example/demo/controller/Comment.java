package com.example.demo.controller;

import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.util.FileManager;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

/**
 * @author Aymen Laroussi
 * @project TradePal-WS
 */

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/comment")
public class Comment {

    // works
    @GetMapping ("all")
    public String getAllComments() {
        String ontologyFile = "data/tradePal.owl";

        String sparqlQuery = "PREFIX ns: <http://www.semanticweb.org/user/ontologies/2023/9/tradepal#>\n" +
                "PREFIX ont: <http://www.co-ode.org/ontologies/ont.owl#>\n" +
                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                "SELECT ?individual ?id ?content ?blogTitle ?writerName " +
                "WHERE { " +
                "  ?individual rdf:type ont:Comment . " +
                "  ?individual ont:id ?id . " +
                "  ?individual ont:content ?content . " +
                "  ?individual ont:has_writer ?writer . " +
                "  ?writer ont:username ?writerName . " +
                "  ?individual ont:under_blog ?blog . " +
                "  ?blog ont:title ?blogTitle . " +
                "}";

        Model model = FileManager.get().loadModel(ontologyFile);

        Query query = QueryFactory.create(sparqlQuery);
        QueryExecution qexec = QueryExecutionFactory.create(query, model);

        ResultSet results = qexec.execSelect();

        JSONArray resultArray = new JSONArray();
        while (results.hasNext()) {
            QuerySolution solution = results.nextSolution();
            JSONObject commentObject = new JSONObject();

            commentObject.put("individual", solution.getResource("individual").toString());
            commentObject.put("id", solution.getLiteral("id").getLexicalForm());
            commentObject.put("content", solution.getLiteral("content").getLexicalForm());
            commentObject.put("blogTitle", solution.getLiteral("blogTitle").getLexicalForm());
            commentObject.put("writerName", solution.getLiteral("writerName").getLexicalForm());

            resultArray.put(commentObject);
        }

        return resultArray.toString();
    }

    // works
    @GetMapping("{username}")
    public String getCommentsByUser(@PathVariable String username) {
        String ontologyFile = "data/tradePal.owl";

        String sparqlQuery = "PREFIX ont: <http://www.co-ode.org/ontologies/ont.owl#>\n" +
                "SELECT ?comment ?id ?content ?blogTitle " +
                "WHERE { " +
                "  ?comment <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> ont:Comment . " +
                "  ?comment ont:has_writer ?user . " +
                "  ?user ont:username \"" + username + "\" . " +
                "  ?comment ont:id ?id . " +
                "  ?comment ont:content ?content . " +
                "  ?comment ont:under_blog ?blog . " +
                "  ?blog ont:title ?blogTitle . " +
                "}";

        Model model = FileManager.get().loadModel(ontologyFile);

        Query query = QueryFactory.create(sparqlQuery);
        QueryExecution qexec = QueryExecutionFactory.create(query, model);

        ResultSet results = qexec.execSelect();

        JSONArray resultArray = new JSONArray();
        while (results.hasNext()) {
            QuerySolution solution = results.nextSolution();
            JSONObject commentObject = new JSONObject();

            commentObject.put("comment", solution.getResource("comment").toString());
            commentObject.put("id", solution.getLiteral("id").getLexicalForm());
            commentObject.put("content", solution.getLiteral("content").getLexicalForm());
            commentObject.put("blogTitle", solution.getLiteral("blogTitle").getLexicalForm());
            commentObject.put("writerName", username);
            resultArray.put(commentObject);
        }

        return resultArray.toString();
    }

}
