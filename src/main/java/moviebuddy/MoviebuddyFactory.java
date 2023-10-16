package moviebuddy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import moviebuddy.domain.CSVMovieReader;
import moviebuddy.domain.MovieFinder;
import moviebuddy.domain.MovieReader;

@Configuration
@Import({ MovieBuddyFactory.DomainModuleConfig.class, MovieBuddyFactory.DataSourceModuleConfig.class })
public class MovieBuddyFactory {
	
	@Configuration   // 내부 클래스화 하면서 각각의 Bean을 갖는 어노테이션으로 작업해준다. 
	static class DomainModuleConfig {
		@Bean
		public MovieFinder movieFinder(MovieReader movieReader) {
			return new MovieFinder(movieReader);
		}
	}
	
	@Configuration
	static class DataSourceModuleConfig{
		@Bean
		public MovieReader movieReader() {
			return new CSVMovieReader();
		}
	}
}
