package com.example.demo.controller;

/**
 * @author mayssaalwaoui
 * @project TradePal-WS
 */
import org.apache.jena.query.*;
import org.apache.jena.rdf.model.*;
import org.apache.jena.util.FileManager;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/person")

public class User {

    @GetMapping("/Admins")
    public String getAdmins() {
        String ontologyFile = "data/tradePal.owl";

        String sparqlQuery = "PREFIX ns: <http://www.semanticweb.org/user/ontologies/2023/9/tradepal#>\n" +
                "PREFIX ont: <http://www.co-ode.org/ontologies/ont.owl#>\n" +
                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
                "SELECT ?admin (SAMPLE(?id) as ?uniqueId) (SAMPLE(?profile_picture) as ?uniquePicture) " +
                "(SAMPLE(?email) as ?uniqueEmail) (SAMPLE(?username) as ?uniqueUsername) " +
                "(SAMPLE(?password) as ?uniquePassword) (GROUP_CONCAT(DISTINCT ?creates ; separator='|') as ?uniqueCreates) " +
                "(GROUP_CONCAT(DISTINCT ?adds ; separator='|') as ?uniqueAdds) " +
                "(GROUP_CONCAT(DISTINCT ?is_author_of ; separator='|') as ?uniqueIsAuthorOf)\n" +
                "WHERE {\n" +
                "  ?admin rdf:type ont:Admin .\n" +
                "  ?admin ont:id ?id .\n" +
                "  ?admin ont:profile_picture ?profile_picture .\n" +
                "  ?admin ont:email ?email .\n" +
                "  ?admin ont:username ?username .\n" +
                "  ?admin ont:password ?password .\n" +
                "  OPTIONAL { ?admin ont:creates ?creates } .\n" +
                "  OPTIONAL { ?admin ont:adds ?adds } .\n" +
                "  OPTIONAL { ?admin ont:is_author_of ?is_author_of } .\n" +
                "}\n" +
                "GROUP BY ?admin";

        Model model = FileManager.get().loadModel(ontologyFile);

        Query query = QueryFactory.create(sparqlQuery);
        QueryExecution qexec = QueryExecutionFactory.create(query, model);

        ResultSet results = qexec.execSelect();

        JSONArray resultArray = new JSONArray();
        while (results.hasNext()) {
            QuerySolution solution = results.nextSolution();
            JSONObject adminObject = new JSONObject();

            adminObject.put("admin", solution.getResource("admin").toString());
            adminObject.put("id", solution.getLiteral("uniqueId").getLexicalForm());
            adminObject.put("profile_picture", solution.getLiteral("uniquePicture").getLexicalForm());
            adminObject.put("email", solution.getLiteral("uniqueEmail").getLexicalForm());
            adminObject.put("username", solution.getLiteral("uniqueUsername").getLexicalForm());
            adminObject.put("password", solution.getLiteral("uniquePassword").getLexicalForm());

            if (solution.contains("uniqueCreates")) {
                adminObject.put("creates", solution.getLiteral("uniqueCreates").getLexicalForm());
            }
            if (solution.contains("uniqueAdds")) {
                adminObject.put("adds", solution.getLiteral("uniqueAdds").getLexicalForm());
            }
            if (solution.contains("uniqueIsAuthorOf")) {
                adminObject.put("is_author_of", solution.getLiteral("uniqueIsAuthorOf").getLexicalForm());
            }

            resultArray.put(adminObject);
        }

        return resultArray.toString();
    }

    @GetMapping("/User/{username}")
    public String getUser(@PathVariable String username) {
        String ontologyFile = "data/tradePal.owl";

        String sparqlQuery = "PREFIX ont: <http://www.co-ode.org/ontologies/ont.owl#>\n" +
                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
                "SELECT ?user (SAMPLE(?id) as ?uniqueId) (SAMPLE(?phone) as ?uniquePhone) " +
                "(SAMPLE(?amount) as ?uniqueAmount) (SAMPLE(?account_status) as ?uniqueAccountStatus) " +
                "(SAMPLE(?password) as ?uniquePassword) (SAMPLE(?bio) as ?uniqueBio) " +
                "(SAMPLE(?email) as ?uniqueEmail) (SAMPLE(?name) as ?uniqueName) " +
                "(SAMPLE(?profile_picture) as ?uniqueProfilePicture) " +
                "(GROUP_CONCAT(DISTINCT ?has_items ; separator='|') as ?uniqueHasItems) " +
                "(GROUP_CONCAT(DISTINCT ?writes_comments ; separator='|') as ?uniqueWritesComments) " +
                "(GROUP_CONCAT(DISTINCT ?has_responses ; separator='|') as ?uniqueHasResponses) " +
                "(GROUP_CONCAT(DISTINCT ?donates ; separator='|') as ?uniqueDonates)\n" +
                "WHERE {\n" +
                "  ?user rdf:type ont:User .\n" +
                "  ?user ont:username \"" + username + "\" .\n" +
                "  ?user ont:id ?id .\n" +
                "  ?user ont:phone ?phone .\n" +
                "  ?user ont:amount ?amount .\n" +
                "  ?user ont:account_status ?account_status .\n" +
                "  ?user ont:password ?password .\n" +
                "  ?user ont:bio ?bio .\n" +
                "  ?user ont:email ?email .\n" +
                "  ?user ont:name ?name .\n" +
                "  ?user ont:profile_picture ?profile_picture .\n" +
                "  OPTIONAL { ?user ont:has_items ?has_items } .\n" +
                "  OPTIONAL { ?user ont:writes_comments ?writes_comments } .\n" +
                "  OPTIONAL { ?user ont:has_responses ?has_responses } .\n" +
                "  OPTIONAL { ?user ont:donates ?donates } .\n" +
                "}\n" +
                "GROUP BY ?user";

        Model model = FileManager.get().loadModel(ontologyFile);

        Query query = QueryFactory.create(sparqlQuery);
        QueryExecution qexec = QueryExecutionFactory.create(query, model);

        ResultSet results = qexec.execSelect();

        JSONArray resultArray = new JSONArray();
        while (results.hasNext()) {
            QuerySolution solution = results.nextSolution();
            JSONObject userObject = new JSONObject();

            userObject.put("user", solution.getResource("user").toString());
            userObject.put("id", solution.getLiteral("uniqueId").getLexicalForm());
            userObject.put("phone", solution.getLiteral("uniquePhone").getLexicalForm());
            userObject.put("amount", solution.getLiteral("uniqueAmount").getLexicalForm());
            userObject.put("account_status", solution.getLiteral("uniqueAccountStatus").getLexicalForm());
            userObject.put("password", solution.getLiteral("uniquePassword").getLexicalForm());
            userObject.put("bio", solution.getLiteral("uniqueBio").getLexicalForm());
            userObject.put("email", solution.getLiteral("uniqueEmail").getLexicalForm());
            userObject.put("name", solution.getLiteral("uniqueName").getLexicalForm());
            userObject.put("profile_picture", solution.getLiteral("uniqueProfilePicture").getLexicalForm());

            if (solution.contains("uniqueHasItems")) {
                userObject.put("has_items", solution.getLiteral("uniqueHasItems").getLexicalForm().split("\\|"));
            }
            if (solution.contains("uniqueWritesComments")) {
                userObject.put("writes_comments", solution.getLiteral("uniqueWritesComments").getLexicalForm().split("\\|"));
            }
            if (solution.contains("uniqueHasResponses")) {
                userObject.put("has_responses", solution.getLiteral("uniqueHasResponses").getLexicalForm().split("\\|"));
            }
            if (solution.contains("uniqueDonates")) {
                userObject.put("donates", solution.getLiteral("uniqueDonates").getLexicalForm().split("\\|"));
            }

            resultArray.put(userObject);
        }

        return resultArray.toString();
    }

    @GetMapping("/Users")
    public String getUsers() {
        String ontologyFile = "data/tradePal.owl";

        String sparqlQuery = "PREFIX ont: <http://www.co-ode.org/ontologies/ont.owl#>\n" +
                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
                "SELECT ?user (SAMPLE(?id) as ?uniqueId) (SAMPLE(?phone) as ?uniquePhone) " +
                "(SAMPLE(?amount) as ?uniqueAmount) (SAMPLE(?account_status) as ?uniqueAccountStatus) " +
                "(SAMPLE(?password) as ?uniquePassword) (SAMPLE(?bio) as ?uniqueBio) " +
                "(SAMPLE(?email) as ?uniqueEmail) (SAMPLE(?name) as ?uniqueName) " +
                "(SAMPLE(?profile_picture) as ?uniqueProfilePicture) " +
                "(GROUP_CONCAT(DISTINCT ?has_items ; separator='|') as ?uniqueHasItems) " +
                "(GROUP_CONCAT(DISTINCT ?writes_comments ; separator='|') as ?uniqueWritesComments) " +
                "(GROUP_CONCAT(DISTINCT ?has_responses ; separator='|') as ?uniqueHasResponses) " +
                "(GROUP_CONCAT(DISTINCT ?donates ; separator='|') as ?uniqueDonates)\n" +
                "WHERE {\n" +
                "  ?user rdf:type ont:User .\n" +
                "  ?user ont:id ?id .\n" +
                "  ?user ont:phone ?phone .\n" +
                "  ?user ont:amount ?amount .\n" +
                "  ?user ont:account_status ?account_status .\n" +
                "  ?user ont:password ?password .\n" +
                "  ?user ont:bio ?bio .\n" +
                "  ?user ont:email ?email .\n" +
                "  ?user ont:name ?name .\n" +
                "  ?user ont:profile_picture ?profile_picture .\n" +
                "  OPTIONAL { ?user ont:has_items ?has_items } .\n" +
                "  OPTIONAL { ?user ont:writes_comments ?writes_comments } .\n" +
                "  OPTIONAL { ?user ont:has_responses ?has_responses } .\n" +
                "  OPTIONAL { ?user ont:donates ?donates } .\n" +
                "}\n" +
                "GROUP BY ?user";

        Model model = FileManager.get().loadModel(ontologyFile);

        Query query = QueryFactory.create(sparqlQuery);
        QueryExecution qexec = QueryExecutionFactory.create(query, model);

        ResultSet results = qexec.execSelect();

        JSONArray resultArray = new JSONArray();
        while (results.hasNext()) {
            QuerySolution solution = results.nextSolution();
            JSONObject userObject = new JSONObject();

            userObject.put("user", solution.getResource("user").toString());
            userObject.put("id", solution.getLiteral("uniqueId").getLexicalForm());
            userObject.put("phone", solution.getLiteral("uniquePhone").getLexicalForm());
            userObject.put("amount", solution.getLiteral("uniqueAmount").getLexicalForm());
            userObject.put("account_status", solution.getLiteral("uniqueAccountStatus").getLexicalForm());
            userObject.put("password", solution.getLiteral("uniquePassword").getLexicalForm());
            userObject.put("bio", solution.getLiteral("uniqueBio").getLexicalForm());
            userObject.put("email", solution.getLiteral("uniqueEmail").getLexicalForm());
            userObject.put("name", solution.getLiteral("uniqueName").getLexicalForm());
            userObject.put("profile_picture", solution.getLiteral("uniqueProfilePicture").getLexicalForm());

            if (solution.contains("uniqueHasItems")) {
                userObject.put("has_items", solution.getLiteral("uniqueHasItems").getLexicalForm().split("\\|"));
            }
            if (solution.contains("uniqueWritesComments")) {
                userObject.put("writes_comments", solution.getLiteral("uniqueWritesComments").getLexicalForm().split("\\|"));
            }
            if (solution.contains("uniqueHasResponses")) {
                userObject.put("has_responses", solution.getLiteral("uniqueHasResponses").getLexicalForm().split("\\|"));
            }
            if (solution.contains("uniqueDonates")) {
                userObject.put("donates", solution.getLiteral("uniqueDonates").getLexicalForm().split("\\|"));
            }

            resultArray.put(userObject);
        }

        return resultArray.toString();
    }




}
