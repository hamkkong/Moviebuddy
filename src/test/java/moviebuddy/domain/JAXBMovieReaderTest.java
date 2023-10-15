package moviebuddy.domain;

import java.util.List;

public class JAXBMovieReaderTest {

	public static void main(String[] args) {
		JAXBMovieReader movieReader = new JAXBMovieReader();
		
		List<Movie> movies = movieReader.loadMovies();
		// movies.size() => XML 문서에 등록된 영화 수와 동일한가?
		
		MovieFinderTest.assertEquals(1375, movies.size()); 
	}
}
