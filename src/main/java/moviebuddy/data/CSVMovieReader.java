package moviebuddy.data;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import moviebuddy.ApplicationException;
import moviebuddy.MovieBuddyProfile;
import moviebuddy.domain.Movie;
import moviebuddy.domain.MovieReader;
import moviebuddy.util.FileSystemUtils;

@Profile(MovieBuddyProfile.CSV_MODE)
@Repository
public class CSVMovieReader implements MovieReader,InitializingBean, DisposableBean {
	
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	private String metadata; 
	
    public String getMetadata() {
		return metadata;
	}

	public void setMetadata(String metadata) {   
	
		this.metadata = metadata;
	}


	/**
     * 영화 메타데이터를 읽어 저장된 영화 목록을 불러온다.
     * 
     * @return 불러온 영화 목록
     */
	@Override
    public List<Movie> loadMovies() {
        try {
            final URI resourceUri = ClassLoader.getSystemResource(getMetadata()).toURI();
            final Path data = Path.of(FileSystemUtils.checkFileSystem(resourceUri));
            final Function<String, Movie> mapCsv = csv -> {
                try {
                    // split with comma
                    String[] values = csv.split(",");

                    String title = values[0];
                    List<String> genres = Arrays.asList(values[1].split("\\|"));
                    String language = values[2].trim();
                    String country = values[3].trim();
                    int releaseYear = Integer.valueOf(values[4].trim());
                    String director = values[5].trim();
                    List<String> actors = Arrays.asList(values[6].split("\\|"));
                    URL imdbLink = new URL(values[7].trim());
                    String watchedDate = values[8];

                    return Movie.of(title, genres, language, country, releaseYear, director, actors, imdbLink, watchedDate);
                } catch (IOException error) {
                    throw new ApplicationException("mapping csv to object failed.", error);
                }
            };

            return Files.readAllLines(data, StandardCharsets.UTF_8)
                        .stream()
                        .skip(1)
                        .map(mapCsv)
                        .collect(Collectors.toList());
        } catch (IOException | URISyntaxException error) {
            throw new ApplicationException("failed to load movies data.", error);
        }
    }

	@Override
	public void afterPropertiesSet() throws Exception {
		URL metadataUrl = ClassLoader.getSystemResource(metadata);
		if(Objects.isNull(metadataUrl)) {
			throw new FileNotFoundException(metadata);
		}
		
		if(Files.isReadable(Path.of(metadataUrl.toURI())) == false) {
			throw new ApplicationException(String.format("cannot read to metadata [%s]", metadata));
		}
		
		
	}

	@Override
	public void destroy() throws Exception {
		log.info("Destroyed bean");
		
	}


}
