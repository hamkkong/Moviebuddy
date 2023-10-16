package moviebuddy.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import moviebuddy.MovieBuddyFactory;

public class BeanScopeTests {

	@Test
	void Equals_MovieFinderBean() {
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MovieBuddyFactory.class);
		MovieFinder movieFinder = applicationContext.getBean(MovieFinder.class);
		
		Assertions.assertEquals(movieFinder, applicationContext.getBean(MovieFinder.class));
	} // 기본적으로 설정하지 않으면 Bean은 싱글톤 스코프로 지정된다.  즉 스프링 컨테이너 내에 존재하는 단 하나의 객체임 
}
