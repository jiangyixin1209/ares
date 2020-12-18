package top.jiangyixin.ares.admin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * TODO
 * @version 1.0
 * @author jiangyixin
 * @date 2020/12/18 下午5:31
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
	
	@Bean
	public Docket docket() {
		return new Docket(DocumentationType.SWAGGER_2)
				.pathMapping("/")
				.select()
				.apis(RequestHandlerSelectors.basePackage("top.jiangyixin.ares.admin"))
				.paths(PathSelectors.any())
				.build().apiInfo(new ApiInfoBuilder()
						.title("Ares")
						.description("Ares分布式缓存监控系统")
						.version("1.0")
						.contact(new Contact("jiangyixin", "jiangyixin.top", "17602173915@163.com"))
						.build());
	}
}
