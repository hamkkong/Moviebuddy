package moviebuddy.data;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CSVMovieReaderTest {

	@Test
	void Valid_MetaData() throws Exception {
		CSVMovieReader movieReader = new CSVMovieReader();
		movieReader.setMetadata("movie_metadata.csv");
		
		movieReader.afterPropertiesSet();
	}
	@Test
	void Invalid_Metadata() {
		CSVMovieReader movieReader = new CSVMovieReader();
		
	Assertions.assertThrows(FileNotFoundException.class,() -> {
		movieReader.setMetadata("invalid");
		movieReader.afterPropertiesSet();
	});
		
	}
}
