package moviebuddy.domain;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import moviebuddy.MovieBuddyFactory;

/**
 * @author springrunner.kr@gmail.com
 */
@SpringJUnitConfig(MovieBuddyFactory.class)
//@ExtendWith(SpringExtension.class)
//@ContextConfiguration(classes = MovieBuddyFactory.class) // 해당Bean을 사용해 컨테이너 구성 
public class MovieFinderTest {	

	@Autowired MovieFinder movieFinder;  //가능하면 사용하지 않는 것을 추천 
	
//	@Autowired
//	MovieFinderTest(MovieFinder movieFinder){  //생성자를 통한 의존관계 주입 
//		this.movieFinder = movieFinder;
//	}
	 
//	@Autowired
//	void setMovieFinderTest(MovieFinder movieFinder) { // 세터를 사용
//		this.movieFinder = movieFinder;
//	}
	
	@Test
	void NotEmpty_directedBy() {
		List<Movie> movies = movieFinder.directedBy("Michael Bay");
		Assertions.assertEquals(3,movies.size());
		
	}
	@Test
	void NotEmpty_ReleasedYearBy() {
		List<Movie> movies = movieFinder.releasedYearBy(2015);
		Assertions.assertEquals(225, movies.size());
	}
	
}
