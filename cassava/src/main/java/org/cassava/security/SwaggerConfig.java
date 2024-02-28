package org.cassava.security;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	public static final String AUTHORIZATION_HEADER = "Authorization";

	private SecurityContext securityContext() {
		return SecurityContext.builder().securityReferences(defaultAuth()).build();
	}

	List<SecurityReference> defaultAuth() {
		AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
		authorizationScopes[0] = authorizationScope;
		return Arrays.asList(new SecurityReference("JWT", authorizationScopes));
	}

	private ApiKey apiKey() {
		return new ApiKey("JWT", AUTHORIZATION_HEADER, "header");
	}

	@Bean
	public Docket apiV2() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("V2").apiInfo(apiInfoV2())
				.securityContexts(Arrays.asList(securityContext())).securitySchemes(Arrays.asList(apiKey())).select()
				.apis(RequestHandlerSelectors.any()).paths(PathSelectors.regex("/v2/api/.*"))
				.build().apiInfo(apiInfoV2());
	}

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("V1").apiInfo(apiInfo())
				.securityContexts(Arrays.asList(securityContext())).securitySchemes(Arrays.asList(apiKey())).select()
				.apis(RequestHandlerSelectors.any()).paths(PathSelectors.regex("/api/.*"))
				.build().apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("CASSAVA Api").description("DESCRIPTION").version("VERSION")
				.termsOfServiceUrl("http://terms-of-services.url").license("LICENSE").licenseUrl("http://url-to-license.com").build();
	}

	private ApiInfo apiInfoV2() {
		return new ApiInfoBuilder().title("CASSAVA Api V2").description("DESCRIPTION").version("VERSION")
				.termsOfServiceUrl("http://terms-of-services.url").license("LICENSE").licenseUrl("http://url-to-license.com").build();
	}

}