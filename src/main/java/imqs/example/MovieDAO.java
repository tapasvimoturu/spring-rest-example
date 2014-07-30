package imqs.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;



public class MovieDAO {
	private static Map<Integer, Movie> movies = new HashMap<Integer, Movie>();
	private static Integer idCounter = 1;

	public static Movie createMovie(Movie m) {
		m.setId(idCounter);
		movies.put(idCounter, m);
		idCounter++;
		return m;
	}
	
	public static Movie getMovie(Integer id) {
		Movie m = null;
		if (movies.containsKey(id)) {
			m = movies.get(id);
		}
		return m;
	}
	
	public static Movie updateMovie(Integer id, Movie m) {
		if (movies.containsKey(id)) {
			movies.remove(id);
			m.setId(id);
			movies.put(id, m);
		}
		return m;
	}
	
	public static List<Movie> getAllMovies() {
		List<Movie> r = new ArrayList<Movie>();
		Iterator<Integer> i = movies.keySet().iterator();
		while (i.hasNext()) {
            Integer key = i.next();
            r.add(movies.get(key));
        }
		return r;
	}
	
	public static Movie deleteMovie(Integer id) {
		Movie m = null;
		if (movies.containsKey(id)) {
			m = movies.get(id);
			movies.remove(id);
		}
		return m;
	}

	
}