package imqs.example;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


/*
 * TODO: Add logging at the appropriate levels
 */

@Controller
@RequestMapping("/movies")
public class MovieController {
	public MovieController() {
        // Just add a movie so we can test if we get a list out
    	MovieDAO.createMovie(new Movie("Alien", "pg16", "Ridley Scott"));
    	MovieDAO.createMovie(new Movie("Natural Born Killers", "pg16", "Oliver Stone"));
    }

    // Request of the form /example/rest
    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    List<Movie> listMovies() throws IOException {
        return MovieDAO.getAllMovies();
    }

    // Request of the form /example/movies/{id}
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    Movie getMovie(@PathVariable("id") Integer id, HttpServletResponse response) throws IOException {
        Movie r = MovieDAO.getMovie(id);
    	if (r != null) return r;
        // If we get here, it means we don't have the movie in the database. The throw
        // here is just to demonstrate how to generate an appropriate HTTP error if
        // there is a problem
        response.sendError(400, "Movie \"" + id + "\" does not exist in the database\n");
        return null; // Dummy return
    }

    // Request of the form "example/movies/name/rating/director"
    // Note we need to specify the RequestParam name as Java cannot infer it if it is compiled without debugging
    // enabled.
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public 
    @ResponseBody 
    Movie createMovie(@RequestBody Movie movie) {
         return MovieDAO.createMovie(movie);
 
    }

    // Request of the form "example/movies/id" to delete an entry in the database
    // If fails silently if there is no matching entry in the database
    // TODO This deletes the required record, but it generates a warning (No mapping found for HTTP request with URI
    // [/example/movies/movies/3)
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public @ResponseBody Movie deleteMovie(@PathVariable("id") int id) {
        return MovieDAO.deleteMovie(id);
    }
}
