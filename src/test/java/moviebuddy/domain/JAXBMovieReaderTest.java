package moviebuddy.domain;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JAXBMovieReaderTest {
	
	@Test
	void NotEmpty_LoadedMovies() {
		JAXBMovieReader movieReader = new JAXBMovieReader();
		
		List<Movie> movies = movieReader.loadMovies();
		// movies.size() => XML 문서에 등록된 영화 수와 동일한가?
		Assertions.assertEquals(1375, movies.size());
	}
}
