package imqs.example;

import imqs.example.Movie;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.junit.Assert;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.webapp.WebAppContext;
import org.springframework.http.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;


/**
 * User: weber
 * Date: 2013/05/29
 * Time: 11:28 AM
 */
public class TestExample {
    static int testPort = 8078;
    static Server server = new Server(testPort);
    static String contextPath = "";
    static String rootURL = "http://localhost:" + testPort + contextPath;

    static RestTemplate rest = new RestTemplate();


    @BeforeClass
    public static void setup() {

        WebAppContext context = new WebAppContext();
        context.setDescriptor("Report testing system");
        context.setResourceBase("src/main/webapp");
        context.setContextPath(contextPath);
        context.setParentLoaderPriority(true);



        server.setHandler(context);

        // Kick the server to life, waiting 10 seconds for it to get its act
        // together
        try {
            server.start();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Could not start server");
        }
        Assert.assertTrue(server.isRunning());

    }

    @AfterClass
    public static void shutdown() {
        try {
            server.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void CollectionTest() throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        Map<Integer, Movie> movieMap = new HashMap<>();

        TypeReference thisList = new TypeReference<Map<Integer, Movie>>() {};

        ResponseEntity<String> r = rest.getForEntity(rootURL + "/example/movies/", String.class, "");
        Assert.assertEquals(r.getStatusCode(), HttpStatus.OK);

        try {
            movieMap = mapper.readValue(r.getBody(), thisList);
        } catch (JsonParseException e) {
            Assert.fail("Invalid JSON syntax\n" + e.getMessage());
        } catch (JsonMappingException e) {
            Assert.fail("Failed to map retrieved JSON to a ReportDescriptor type: " + e.getMessage());
        } catch (IOException e) {
            Assert.fail("Unknown IO exception thrown by the ObjectMapper");
        }

        // The list has one report. Check that we can get the JSON params and
        // download a sample report.
        Assert.assertTrue(movieMap.size() + " is not more than 0", movieMap.size() > 0);

    }

}
