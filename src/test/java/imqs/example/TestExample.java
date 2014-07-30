package imqs.example;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.webapp.WebAppContext;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * User: David Weber
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
            fail("Could not start server");
        }
        assertTrue(server.isRunning());
    }

    @AfterClass
    public static void shutdown() {
        try {
            server.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    @Test
//    public void CollectionReadTest() {
//        ObjectMapper mapper = new ObjectMapper();
//        Map<Integer, Movie> movieMap = new HashMap<>();
//        TypeReference thisMap = new TypeReference<Map<Integer, Movie>>() {};
//        ResponseEntity<String> r = rest.getForEntity(rootURL + "/example/movies/", String.class, "");
//        assertEquals(r.getStatusCode(), HttpStatus.OK);
//
//        try {
//            movieMap = mapper.readValue(r.getBody(), thisMap);
//        } catch (JsonParseException e) {
//            fail("Invalid JSON syntax\n" + e.getMessage());
//        } catch (JsonMappingException e) {
//            fail("Failed to map retrieved JSON to a ReportDescriptor type: " + e.getMessage());
//        } catch (IOException e) {
//            fail("Unknown IO exception thrown by the ObjectMapper");
//        }
//
//        // The list has one report. Check that we can get the JSON params and
//        // download a sample report.
//        assertTrue(movieMap.size() + " is not more than 0", movieMap.size() == 2);
//
//    }
//
//    @Test
//    public void ItemReadTest() {
//        ObjectMapper mapper = new ObjectMapper();
//        Movie m = new Movie();
//        TypeReference thisElement = new TypeReference<Movie>() {};
//        ResponseEntity<String> r = rest.getForEntity(rootURL + "/example/movies/2", String.class, "");
//        assertEquals(r.getStatusCode(), HttpStatus.OK);
//
//        try {
//            m = mapper.readValue(r.getBody(), thisElement);
//        } catch (JsonParseException e) {
//            fail("Invalid JSON syntax\n" + e.getMessage());
//        } catch (JsonMappingException e) {
//            fail("Failed to map retrieved JSON to a ReportDescriptor type: " + e.getMessage());
//        } catch (IOException e) {
//            fail("Unknown IO exception thrown by the ObjectMapper");
//        }
//        assertEquals("pg16", m.getRating());
//    }

//    @Test
//    public void itemCreate() throws Exception{
//        // Add a movie to the database
//    	Movie m = new Movie();
//    	m.setDirector("Christopher Nolan");
//    	m.setName("Prestige");
//    	m.setRating("pg16");
//        rest.postForEntity(rootURL + "/example/movies", m, "application/json",null);
//        ObjectMapper mapper = new ObjectMapper();
//
//        // Check that it is actually there.
//        Movie m2 = new Movie();
//        TypeReference thisElement = new TypeReference<Movie>() {};
//        ResponseEntity<String> r = rest.getForEntity(rootURL + "/example/movies/3", String.class, "");
//        assertEquals(r.getStatusCode(), HttpStatus.OK);
//
//        try {
//            m2 = mapper.readValue(r.getBody(), thisElement);
//        } catch (JsonParseException e) {
//            fail("Invalid JSON syntax\n" + e.getMessage());
//        } catch (JsonMappingException e) {
//            fail("Failed to map retrieved JSON to a ReportDescriptor type: " + e.getMessage());
//        } catch (IOException e) {
//            fail("Unknown IO exception thrown by the ObjectMapper");
//        }
//        assertEquals("Quentin Tarantino", m.getDirector());
//    }
}
