package moviebuddy.domain;

import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import org.springframework.stereotype.Repository;

import moviebuddy.ApplicationException;

@Repository
public class JAXBMovieReader implements MovieReader {

	@Override
	public List<Movie> loadMovies() {
		
		try {
			final JAXBContext jaxb = JAXBContext.newInstance(MovieMetadata.class); // JAXBContext 객체를 생성(필수)
			final Unmarshaller unmarshaller = jaxb.createUnmarshaller();  // Unmashaller 사용(예외처리 발생)
			final InputStream content = ClassLoader.getSystemResourceAsStream("movie_metadata.xml");
			final Source source = new StreamSource(content);
			final MovieMetadata metadata = (MovieMetadata) unmarshaller.unmarshal(source); //moviemetadata로 캐스팅 
			
			return metadata.toMovies();
			
		}catch(JAXBException error) {
			throw new ApplicationException("failed to load movies data", error);
		}

	}
	
	@XmlRootElement(name = "moviemetadata") // XML 문서의 moviemetadata 를 불러오는 작업이다. 
	public static class MovieMetadata { // movie_metadata.xml 의 정보를 맵핑한다.  
		
		private List<MovieData> movies;  //movies 는 movie_metadata의 VO 속성이다 -정보를 받으려면 getter,setter 필요 

		public List<MovieData> getMovies() {
			return movies;
		}

		public void setMovies(List<MovieData> movies) {
			this.movies = movies;
		}
		
		public List<Movie> toMovies(){
				return movies.stream().map(MovieData::toMovie).collect(Collectors.toList());
		}
		
		public static class MovieData {  // 컴파일 에러 해걸 및  movies의 속성을 정의
			
				private String title;
				private List<String>genres;
				private String language;
				private String country;
				private int releasedYear;
				private String director;
				private List<String> actors;
				private URL imdbLink;
				private String watchedDate;
				
				
				public String getTitle() {
					return title;
				}
				public void setTitle(String title) {
					this.title = title;
				}
				public List<String> getGenres() {
					return genres;
				}
				public void setGenres(List<String> genres) {
					this.genres = genres;
				}
				public String getLanguage() {
					return language;
				}
				public void setLanguage(String language) {
					this.language = language;
				}
				public String getCountry() {
					return country;
				}
				public void setCountry(String country) {
					this.country = country;
				}
				public int getReleasedYear() {
					return releasedYear;
				}
				public void setReleasedYear(int releasedYear) {
					this.releasedYear = releasedYear;
				}
				public String getDirector() {
					return director;
				}
				public void setDirector(String director) {
					this.director = director;
				}
				public List<String> getActors() {
					return actors;
				}
				public void setActors(List<String> actors) {
					this.actors = actors;
				}
				public URL getImdbLink() {
					return imdbLink;
				}
				public void setImdbLink(URL imdbLink) {
					this.imdbLink = imdbLink;
				}
				public String getWatchedDate() {
					return watchedDate;
				}
				public void setWatchedDate(String watchedDate) {
					this.watchedDate = watchedDate;
				}
				
				public Movie toMovie() {
					 String title = getTitle();
					 List<String>genres = getGenres();
					 String language = getLanguage();
					 String country = getCountry();
					 int releasedYear = getReleasedYear();
					 String director = getDirector();
					 List<String> actors = getActors();
					 URL imdbLink = getImdbLink();
					 String watchedDate = getWatchedDate();
					
					return Movie.of(title, genres, language, country, releasedYear, director, actors, imdbLink, watchedDate);
				}
				
		}
		
	}
}
