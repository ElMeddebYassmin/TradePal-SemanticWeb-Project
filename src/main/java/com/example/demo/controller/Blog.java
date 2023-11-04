package com.example.demo.controller;
import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.util.FileManager;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Aymen Laroussi
 * @project TradePal-WS
 */

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/blog")
public class Blog {

    // works
    @GetMapping("/{className}")
    public String getBlogsByType(@PathVariable String className) {
        String ontologyFile = "data/tradePal.owl";

        String sparqlQuery = "PREFIX ns: <http://www.semanticweb.org/user/ontologies/2023/9/tradepal#>\n" +
                "PREFIX ont: <http://www.co-ode.org/ontologies/ont.owl#>\n" +
                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                "SELECT ?individual ?id ?views ?publicationDate ?likes ?title ?featuredImage ?tags ?content ?status ?username " +
                "WHERE { " +
                "  ?individual rdf:type ont:" + className + " . " +
                "  ?individual ont:id ?id . " +
                "  ?individual ont:views ?views . " +
                "  ?individual ont:publicationDate ?publicationDate . " +
                "  ?individual ont:likes ?likes . " +
                "  ?individual ont:title ?title . " +
                "  ?individual ont:featuredImage ?featuredImage . " +
                "  ?individual ont:tags ?tags . " +
                "  ?individual ont:content ?content . " +
                "  ?individual ont:status ?status . " +
                "  ?individual ont:has_author ?author . " +
                "  ?author ont:username ?username . " +
                "}";

        Model model = FileManager.get().loadModel(ontologyFile);

        Query query = QueryFactory.create(sparqlQuery);
        QueryExecution qexec = QueryExecutionFactory.create(query, model);

        ResultSet results = qexec.execSelect();

        JSONArray resultArray = new JSONArray();
        while (results.hasNext()) {
            QuerySolution solution = results.nextSolution();
            JSONObject blogObject = new JSONObject();

            blogObject.put("individual", solution.getResource("individual").toString());
            blogObject.put("id", solution.getLiteral("id").getLexicalForm());
            blogObject.put("views", solution.getLiteral("views").getLexicalForm());
            blogObject.put("publicationDate", solution.getLiteral("publicationDate").getLexicalForm());
            blogObject.put("likes", solution.getLiteral("likes").getLexicalForm());
            blogObject.put("title", solution.getLiteral("title").getLexicalForm());
            blogObject.put("featuredImage", solution.getLiteral("featuredImage").getLexicalForm());
            blogObject.put("tags", solution.getLiteral("tags").getLexicalForm());
            blogObject.put("content", solution.getLiteral("content").getLexicalForm());
            blogObject.put("status", solution.getLiteral("status").getLexicalForm());
            blogObject.put("username", solution.getLiteral("username").getLexicalForm());

            resultArray.put(blogObject);
        }

        return resultArray.toString();
    }

    // works
    @GetMapping("/u/{username}/b1/{blog}")
    public String getBlogByUser1(@PathVariable String username, @PathVariable String blog) {
        try {
            String ontologyFile = "data/tradePal.owl";

            String sparqlQuery = "PREFIX ont: <http://www.co-ode.org/ontologies/ont.owl#>\n" +
                    "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                    "SELECT ?blog ?id ?views ?publicationDate ?likes ?title ?featuredImage ?tags ?content ?status ?contains_comments " +
                    "WHERE { " +
                    "  ?blog rdf:type ont:" + blog + " . " +
                    "  ?blog ont:has_author ?author . " +
                    "  ?author ont:username \"" + username + "\" . " +
                    "  ?blog ont:id ?id . " +
                    "  ?blog ont:views ?views . " +
                    "  ?blog ont:publicationDate ?publicationDate . " +
                    "  ?blog ont:likes ?likes . " +
                    "  ?blog ont:title ?title . " +
                    "  ?blog ont:featuredImage ?featuredImage . " +
                    "  ?blog ont:tags ?tags . " +
                    "  ?blog ont:content ?content . " +
                    "  ?blog ont:status ?status . " +
                    "}";

            Model model = FileManager.get().loadModel(ontologyFile);

            Query query = QueryFactory.create(sparqlQuery);
            QueryExecution qexec = QueryExecutionFactory.create(query, model);

            ResultSet results = qexec.execSelect();

            JSONArray resultArray = new JSONArray();
            while (results.hasNext()) {
                QuerySolution solution = results.nextSolution();
                JSONObject blogObject = new JSONObject();

                blogObject.put("blog", solution.getResource("blog").toString());
                blogObject.put("id", solution.getLiteral("id").getLexicalForm());
                blogObject.put("views", solution.getLiteral("views").getLexicalForm());
                blogObject.put("publicationDate", solution.getLiteral("publicationDate").getLexicalForm());
                blogObject.put("likes", solution.getLiteral("likes").getLexicalForm());
                blogObject.put("title", solution.getLiteral("title").getLexicalForm());
                blogObject.put("featuredImage", solution.getLiteral("featuredImage").getLexicalForm());
                blogObject.put("tags", solution.getLiteral("tags").getLexicalForm());
                blogObject.put("content", solution.getLiteral("content").getLexicalForm());
                blogObject.put("status", solution.getLiteral("status").getLexicalForm());
                blogObject.put("username", username);

                resultArray.put(blogObject);
            }

            return resultArray.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error").toString();
        }
    }

    // works
    @GetMapping("/author/{titleBlog}")
    public String getAuthorProfile(@PathVariable String titleBlog) {
        String ontologyFile = "data/tradePal.owl";

        String sparqlQuery = "PREFIX ns: <http://www.semanticweb.org/user/ontologies/2023/9/tradepal#>\n" +
                "PREFIX ont: <http://www.co-ode.org/ontologies/ont.owl#>\n" +
                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                "SELECT ?author ?authorName ?authorEmail ?authorBio " +
                "WHERE { " +
                "  ?blog rdf:type ont:Blog . " +
                "  ?blog ont:title ?titleBlog . " +
                "  ?blog ont:has_author ?author . " +
                "  ?author ont:fullName ?authorName . " +
                "  ?author ont:email ?authorEmail . " +
                "  ?author ont:bio ?authorBio . " +
                "}";

        Model model = FileManager.get().loadModel(ontologyFile);

        Query query = QueryFactory.create(sparqlQuery);
        QueryExecution qexec = QueryExecutionFactory.create(query, model);
        ResultSet results = qexec.execSelect();

        JSONArray resultArray = new JSONArray();
        while (results.hasNext()) {
            QuerySolution solution = results.nextSolution();
            JSONObject authorProfile = new JSONObject();

            authorProfile.put("author", solution.getResource("author").toString());
            authorProfile.put("authorName", solution.getLiteral("authorName").getLexicalForm());
            authorProfile.put("authorEmail", solution.getLiteral("authorEmail").getLexicalForm());
            authorProfile.put("authorBio", solution.getLiteral("authorBio").getLexicalForm());

            resultArray.put(authorProfile);
        }

        return resultArray.toString();
    }

    // works
    @GetMapping("/i/{className}")
    public List<String> getIndividualsByType(@PathVariable String className) {
        String ontologyFile = "data/tradePal.owl";

        String sparqlQuery = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                "PREFIX ont: <http://www.co-ode.org/ontologies/ont.owl#>\n" +
                "SELECT ?individual\n" +
                "WHERE {\n" +
                "  ?individual rdf:type ont:" + className + " . " +
                "}" ;

        Model model = FileManager.get().loadModel(ontologyFile);

        Query query = QueryFactory.create(sparqlQuery);
        QueryExecution qexec = QueryExecutionFactory.create(query, model);

        ResultSet results = qexec.execSelect();

        List<String> individualList = new ArrayList<>();
        while (results.hasNext()) {
            QuerySolution solution = results.nextSolution();
            individualList.add(solution.getResource("individual").toString());
        }

        return individualList;
    }

    // not needed
//    @GetMapping("/getAll")
//    public String getAllTypes() {
//        String ontologyFile = "data/tradePal.owl";
//
//        String prefixes =
//                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
//                        "PREFIX ont: <http://www.semanticweb.org/user/ontologies/2023/9/tradepal#>\n";
//
//        String sparqlQuery = prefixes +
//                "SELECT DISTINCT ?type " +
//                "WHERE { " +
//                "  ?individual rdf:type ?type . " +
//                "}";
//
//        Model model = FileManager.get().loadModel(ontologyFile);
//
//        Query query = QueryFactory.create(sparqlQuery);
//        QueryExecution qexec = QueryExecutionFactory.create(query, model);
//
//        ResultSet results = qexec.execSelect();
//
//        JSONArray typeArray = new JSONArray();
//        while (results.hasNext()) {
//            QuerySolution solution = results.nextSolution();
//            RDFNode type = solution.get("type");
//            typeArray.put(type.toString());
//        }
//
//        return typeArray.toString();
//    }

    // works
    @GetMapping("/au/{username}")
    public String getBlogsByAuthor(@PathVariable String username) {
        String ontologyFile = "data/tradePal.owl";

        String sparqlQuery =
                "PREFIX ns: <http://www.semanticweb.org/user/ontologies/2023/9/tradepal#>\n" +
                        "PREFIX ont: <http://www.co-ode.org/ontologies/ont.owl#>\n" +
                        "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                        "SELECT ?id ?title ?content ?publicationDate ?status ?tags ?views ?likes " +
                        "WHERE { " +
                        "  ?blog ont:has_author ?author . " +
                        "  ?author ont:username '" + username + "' . " +
                        "  ?blog ont:title ?title . " +
                        "  ?blog ont:id ?id ." +
                        "  ?blog ont:content ?content . " +
                        "  ?blog ont:views ?views . " +
                        "  ?blog ont:likes ?likes . " +
                        "  ?blog ont:publicationDate ?publicationDate . " +
                        "  ?blog ont:status ?status . " +
                        "  ?blog ont:tags ?tags . " +
                        "}";

        Model model = FileManager.get().loadModel(ontologyFile);

        Query query = QueryFactory.create(sparqlQuery);
        QueryExecution qexec = QueryExecutionFactory.create(query, model);

        ResultSet results = qexec.execSelect();

        JSONArray resultArray = new JSONArray();
        while (results.hasNext()) {
            QuerySolution solution = results.nextSolution();
            JSONObject blogObject = new JSONObject();
            blogObject.put("title", solution.getLiteral("title").getLexicalForm());
            blogObject.put("content", solution.getLiteral("content").getLexicalForm());
            blogObject.put("publicationDate", solution.getLiteral("publicationDate").getLexicalForm());
            blogObject.put("status", solution.getLiteral("status").getLexicalForm());
            blogObject.put("id", solution.getLiteral("id").getLexicalForm());
            blogObject.put("tags", solution.getLiteral("tags").getLexicalForm());
            blogObject.put("views", solution.getLiteral("views").getLexicalForm());
            blogObject.put("likes", solution.getLiteral("likes").getLexicalForm());
            blogObject.put("username", username);

            resultArray.put(blogObject);
        }

        return resultArray.toString();
    }

    // Works
    @GetMapping("/getAllBlogs")
    public static String getAllBlogs() {
        String ontURI = "http://www.co-ode.org/ontologies/ont.owl";
        Model model = ModelFactory.createDefaultModel();
        model.read("data/tradePal.owl");

        String prefixes =
                "PREFIX ont: <http://www.co-ode.org/ontologies/ont.owl#>\n" +
                        "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n";
        String query =
                prefixes +
                        "SELECT ?id ?title ?likes ?tags ?content ?status ?username ?views ?publicationDate " +
                        "WHERE {" +
                        "  ?blog ont:title ?title ." +
                        "  ?blog ont:id ?id ." +
                        "  ?blog ont:likes ?likes ." +
                        "  ?blog ont:tags ?tags ." +
                        "  ?blog ont:content ?content ." +
                        "  ?blog ont:views ?views ." +
                        "  ?blog ont:status ?status ." +
                        "  ?blog ont:has_author ?author ." +
                        "  ?author ont:username ?username ." +
                        "  ?blog ont:publicationDate ?publicationDate ." +
                        "}";

        QueryExecution qe = QueryExecutionFactory.create(query, model);
        ResultSet results = qe.execSelect();

        try {
            JSONArray blogs = new JSONArray();

            while (results.hasNext()) {
                QuerySolution solution = results.nextSolution();

                JSONObject blog = new JSONObject();

                Iterator<String> varNames = solution.varNames();
                while (varNames.hasNext()) {
                    String varName = varNames.next();
                    RDFNode rdfNode = solution.get(varName);

                    if (rdfNode.isLiteral()) {
                        blog.put(varName, rdfNode.asLiteral().getString());
                    }
                }
                blogs.put(blog);
            }

            return blogs.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "Error to fetch";
        } finally {
            qe.close();
        }
    }

}
