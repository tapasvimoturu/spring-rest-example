package imqs.example;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


/*
 * TODO: Add logging at the appropriate levels
 */

@Controller
@RequestMapping("/movies")
public class MovieController {

    Map<Integer, Movie> movies = new HashMap<Integer, Movie>();

    public MovieController() {
        // Just add a movie so we can test if we get a list out
        movies.put(1, new Movie("Alien", "pg16", "Ridley Scott"));
        movies.put(2, new Movie("Natural Born Killers", "pg16", "Oliver Stone"));
    }

    // Request of the form /example/rest
    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    Map<Integer, Movie> listMovies() throws IOException {
        return movies;
    }

    // Request of the form /example/movies/{id}
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    Movie getMovie(@PathVariable("id") Integer id, HttpServletResponse response) throws IOException {
        if (movies.containsKey(id)) return movies.get(id);
        // If we get here, it means we don't have the movie in the database. The throw
        // here is just to demonstrate how to generate an appropriate HTTP error if
        // there is a problem
        response.sendError(400, "Movie \"" + id + "\" does not exist in the database\n");
        return new Movie("", "", ""); // Dummy return
    }

    // Request of the form "example/movies/name/rating/director"
    // Note we need to specify the RequestParam name as Java cannot infer it if it is compiled without debugging
    // enabled.
    @RequestMapping(value = "/{name:.+}/{rating:.+}/{director:.+}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.CREATED)
    public void addMovie(@PathVariable("name") String name, @PathVariable("rating") String rating,
                         @PathVariable("director") String director) {
        Iterator<Integer> it = movies.keySet().iterator();
        int i = 0;
        // Cheap way to calculate a unique id
        while (it.hasNext()) {
            int t = it.next();
            if (i < t) i = t;
        }
        movies.put(i + 1, new Movie(name, rating, director));
    }

    // Request of the form "example/movies/id" to delete an entry in the database
    // If fails silently if there is no matching entry in the database
    // TODO This deletes the required record, but it generates a warning (No mapping found for HTTP request with URI
    // [/example/movies/movies/3)
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteMovie(@PathVariable("id") int id) {
        if (movies.containsKey(id)) {
            movies.remove(id);
        }
    }
}
