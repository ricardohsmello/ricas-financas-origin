package br.com.ricasfinancas.swaggerconfig;
import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author ricardo.mello
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
        		
        		.globalOperationParameters(
        		        Arrays.asList(new ParameterBuilder()
        		            .name("Authorization")
        		            .description("Description of header")
        		            .modelRef(new ModelRef("string"))
        		            .parameterType("header")
        		            .defaultValue("token")
        		            .required(true)
        		            .build()))
        		
                .select()                 
                	.apis(RequestHandlerSelectors.withClassAnnotation(ControllerDocumentation.class))
                		.paths(PathSelectors.any())
                	.paths(PathSelectors.any())
                	
                .build()
                .apiInfo(metaData());
        
    }
    
    private ApiInfo metaData() {
        ApiInfo apiInfo = new ApiInfo(
                "Ricas Finanças Endpoint Rest API.",
                "Endpoint Access API Documentation.",
                "1.0",
                "Terms of service",
                new Contact("Ricardo Mello", "https://github.com/ricardohsmello", "ricardohsmello@gmail.com"),
               "Apache License Version 2.0",
                "https://www.apache.org/licenses/LICENSE-2.0");
        return apiInfo;
    }
}
//
//	@Autowired
//	private JwtTokenUtil jwtTokenUtil;
//
//	@Autowired
//	private UserDetailsService userDetailsService;
//
//	@Bean
//	public Docket api() {
//		return new Docket(DocumentationType.SWAGGER_2).select()
//				.apis(RequestHandlerSelectors.basePackage("br.com.ricasfinancas.controller"))
//				.paths(PathSelectors.any()).build()
//				.apiInfo(apiInfo());
//	}
//
//	private ApiInfo apiInfo() {
//		return new ApiInfoBuilder().title("Ricas Finanças API")
//				.description("Ricas Finance Endpoint Access API Documentation.").version("1.0")
//				.build();
//	}
//
//	@Bean
//	public SecurityConfiguration security() {
//		String token;
//		try {
//			UserDetails userDetails = this.userDetailsService.loadUserByUsername("admin@email.com.br");
//			token = this.jwtTokenUtil.obterToken(userDetails);
//		} catch (Exception e) {
//			token = "";
//		}
//
//		return new SecurityConfiguration(null, null, null, null, "Bearer " + token, ApiKeyVehicle.HEADER,
//				"Authorization", ",");
//	}

//}