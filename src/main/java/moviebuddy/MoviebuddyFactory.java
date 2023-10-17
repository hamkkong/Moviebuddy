package moviebuddy;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = {"moviebuddy"})
@Import({ MovieBuddyFactory.DomainModuleConfig.class, MovieBuddyFactory.DataSourceModuleConfig.class })
public class MovieBuddyFactory {
	
	@Configuration   // 내부 클래스화 하면서 각각의 Bean을 갖는 어노테이션으로 작업해준다. 
	static class DomainModuleConfig {
		
	}
	
	@Configuration
	static class DataSourceModuleConfig{
		
	}
}
