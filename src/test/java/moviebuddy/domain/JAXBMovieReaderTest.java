package moviebuddy.domain;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import moviebuddy.MovieBuddyFactory;

@SpringJUnitConfig(MovieBuddyFactory.class) 

public class JAXBMovieReaderTest {
	
	@Autowired MovieFinder movieFinder; 
	
	@Test
	void NotEmpty_LoadedMovies() {
		JAXBMovieReader movieReader = new JAXBMovieReader();
		
		List<Movie> movies = movieReader.loadMovies();
		// movies.size() => XML 문서에 등록된 영화 수와 동일한가?
		Assertions.assertEquals(1375, movies.size());
	}
}
