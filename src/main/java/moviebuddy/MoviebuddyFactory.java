package moviebuddy;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import moviebuddy.data.CSVMovieReader;

@Configuration
@ComponentScan(basePackages = {"moviebuddy"})
@Import({ MovieBuddyFactory.DomainModuleConfig.class, MovieBuddyFactory.DataSourceModuleConfig.class })
public class MovieBuddyFactory {
	
	@Bean
	public Jaxb2Marshaller jaxb2Marshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setPackagesToScan("moviebuddy");
		
		return marshaller;
		
	}
	
	@Configuration   // 내부 클래스화 하면서 각각의 Bean을 갖는 어노테이션으로 작업해준다. 
	static class DomainModuleConfig {
		
	}
	
	@Configuration
	static class DataSourceModuleConfig{
		
		@Profile(MovieBuddyProfile.CSV_MODE)
		@Bean
		public CSVMovieReader CSVMovieReader() throws FileNotFoundException,URISyntaxException {
			CSVMovieReader movieReader = new CSVMovieReader();
			movieReader.setMetadata("movie_metadata.csv");
			
			return movieReader;
		
		}
	}

}
