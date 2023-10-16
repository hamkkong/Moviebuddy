package moviebuddy;

import moviebuddy.domain.CSVMovieReader;
import moviebuddy.domain.MovieFinder;

public class MoviebuddyFactory {
	
	public MovieFinder movieFinder() {
		return new MovieFinder(new CSVMovieReader());
	}

}
