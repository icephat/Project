package org.cassava.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.endpoint.DefaultAuthorizationCodeTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AccessTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AuthorizationCodeGrantRequest;
import org.springframework.security.oauth2.client.http.OAuth2ErrorResponseErrorHandler;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.AuthorizationRequestRepository;
import org.springframework.security.oauth2.client.web.HttpSessionOAuth2AuthorizationRequestRepository;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.security.oauth2.core.http.converter.OAuth2AccessTokenResponseHttpMessageConverter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.CharacterEncodingFilter;

import org.cassava.security.MultiRoleUrlAuthenticationSuccessHandler;
import org.cassava.security.OAuth2AuthenticationSuccessHandler;
import org.cassava.security.filter.ApiKeyFilter;
import org.cassava.security.filter.JwtAuthenticationFilter;
import org.cassava.security.oauth2request.CustomRequestEntityConverter;
import org.cassava.security.oauth2request.CustomTokenResponseConverter;
import org.cassava.services.AADOAuth2UserService;
import org.json.JSONObject;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.cassava.security.oauth2request.CustomAuthorizationRequestResolver;

@EnableWebSecurity
public class MultiSecurityConfiguration {

	@Autowired
	private MultiRoleUrlAuthenticationSuccessHandler multiRoleUrlAuthenticationSuccessHandler;

	// @Autowired
	// private UserDetailsService userDetailsService;

	@Autowired
	private AADOAuth2UserService oidcUserService;

	@Configuration
	@EnableGlobalMethodSecurity(prePostEnabled = true)
	@Order(1)
	public class APISecurityConfigration extends WebSecurityConfigurerAdapter {

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.cors().and().csrf().disable();
			http.antMatcher("/api/**").authorizeRequests()
					.antMatchers("/api/manual/download","/api/oauth/google", "/api/oauth/google/access_token", "/api/oauth/refreshtoken")
					.permitAll().anyRequest().authenticated().and().exceptionHandling()
					.accessDeniedHandler(new AccessDeniedExceptionHandler())
					.authenticationEntryPoint((req, resp, e) -> {

					});

			// http.addFilterBefore(authenticationApiKeyFilterBean(),
			// BasicAuthenticationFilter.class);
			http.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
		}

		@Override
		@Bean
		public AuthenticationManager authenticationManagerBean() throws Exception {
			return super.authenticationManagerBean();
		}

		@Bean
		public JwtAuthenticationFilter authenticationTokenFilterBean() throws Exception {
			return new JwtAuthenticationFilter();
		}

		@Bean
		public ApiKeyFilter authenticationApiKeyFilterBean() throws Exception {
			return new ApiKeyFilter();
		}
	}

	@Configuration
	@PropertySource("classpath:/application-oauth2.properties")
	@Order(2)
	public class WebSecurityConfigration extends WebSecurityConfigurerAdapter {

		@Autowired
		private OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;

		@Autowired
		private MultiRoleUrlAuthenticationSuccessHandler multiRoleUrlAuthenticationSuccessHandler;

		private List<String> clients = Arrays.asList("google");

		private String CLIENT_PROPERTY_KEY = "spring.security.oauth2.client.registration.";

		@Bean
		public InvalidSessionStrategy sessionInformationExpiredStrategy() {
			return new CustomInvalidSessiondStrategy();
		}

		@Override
		protected void configure(HttpSecurity http) throws Exception {

			http.csrf().disable();

			http.authorizeRequests().antMatchers("/", "/varietyvue", "/diseasevue", "/pestvue", "/naturalenemyvue",
					"/logout", "/plant/", "/resources/**", "/province/**", "/district/**", "/v2/api/**", "/v3/api/**").permitAll();

			http.sessionManagement().invalidSessionStrategy(sessionInformationExpiredStrategy());

			http.authorizeRequests().antMatchers("/register/**").access("hasAnyRole('Anonymous','waitingApprove')")

					.antMatchers("/login/**").access("hasRole('Anonymous')");

			http.authorizeRequests().antMatchers("/admin/**").access("hasRole('systemAdmin')")

					.antMatchers("/inactive").access("hasAnyRole('farmer','staff')")

					.antMatchers("/approve/**").access("hasRole('imageExaminer')")

					.antMatchers("/disease/**").access("hasRole('systemAdmin')")

					.antMatchers("/targetofsurvey/**").access("hasRole('systemAdmin')")

					.antMatchers("/export/**").access("hasAnyRole('systemAdmin','imageExaminer','imageExporter')")

					.antMatchers("/farmer/**").access("hasAnyRole('fieldRegistrar','systemAdmin')")

					.antMatchers("/farmerorganization/**").access("hasRole('fieldRegistrar')")

					.antMatchers("/field/**").access("hasAnyRole('fieldRegistrar','fieldResponsible')")

					.antMatchers("/message/**").access("hasAnyRole('systemAdmin','infoAdmin')")

					.antMatchers("/naturalenemy/**").access("hasRole('systemAdmin')")

					.antMatchers("/organization/**").access("hasRole('systemAdmin')")

					.antMatchers("/pathogen/**").access("hasRole('systemAdmin')")

					.antMatchers("/permission/**").access("hasRole('systemAdmin')")

					.antMatchers("/pest/**").access("hasRole('systemAdmin')")

					.antMatchers("/pestcide/**").access("hasRole('staff')")

					.antMatchers("/registusercode/**", "/registfarmercode/**")
					.access("hasAnyRole('systemAdmin','fieldRegistrar')")

					.antMatchers("/staff/**").access("hasAnyRole('systemAdmin','fieldRegistrar')")

					.antMatchers("/variety/**").access("hasRole('systemAdmin')")

					.antMatchers("/home/**").access("hasAnyRole('staff','farmer','systemAdmin')")

					.antMatchers("/planting/**").access("hasAnyRole('staff','farmer')")

					.antMatchers("/imgsurveytargetpoint/**").access("hasAnyRole('staff','farmer')")

					.antMatchers("/survey/**").access("hasAnyRole('staff','farmer')")

					.antMatchers("/imgdisease/**").access("hasRole('systemAdmin')")

					.antMatchers("/imgnaturalenemy/**").access("hasRole('systemAdmin')")

					.antMatchers("/imgpest/**").access("hasRole('systemAdmin')")

					.antMatchers("/imgvariety/**").access("hasRole('systemAdmin')");

			http.authorizeRequests().anyRequest().authenticated().and().oauth2Login()
					.loginPage("/login/oauth2/authorization/google")
					.authorizationEndpoint(ae -> ae
							.authorizationRequestResolver(new CustomAuthorizationRequestResolver(
									clientRegistrationRepository(), "/login/oauth2/authorization"))
							.baseUri("/login/oauth2/authorization")
							.authorizationRequestRepository(authorizationRequestRepository()).and().tokenEndpoint()
							.accessTokenResponseClient(accessTokenResponseClient()))
					.successHandler(oAuth2AuthenticationSuccessHandler).userInfoEndpoint()
					.oidcUserService(oidcUserService);
			// http.logout().logoutUrl("/cassava/logout").logoutSuccessHandler(multiLogoutSuccessHandler())
			http.logout().logoutUrl("/cassava/logout").logoutSuccessHandler(multiLogoutSuccessHandler())
					.invalidateHttpSession(false).logoutSuccessUrl("/login?logout=true").deleteCookies("JSESSIONID")
					.and().exceptionHandling().accessDeniedPage("/403");

		}

		public OAuth2AccessTokenResponseClient<OAuth2AuthorizationCodeGrantRequest> accessTokenResponseClient() {
			DefaultAuthorizationCodeTokenResponseClient accessTokenResponseClient = new DefaultAuthorizationCodeTokenResponseClient();
			accessTokenResponseClient.setRequestEntityConverter(new CustomRequestEntityConverter());

			OAuth2AccessTokenResponseHttpMessageConverter tokenResponseHttpMessageConverter = new OAuth2AccessTokenResponseHttpMessageConverter();
			tokenResponseHttpMessageConverter.setTokenResponseConverter(new CustomTokenResponseConverter());
			RestTemplate restTemplate = new RestTemplate(
					Arrays.asList(new FormHttpMessageConverter(), tokenResponseHttpMessageConverter));
			restTemplate.setErrorHandler(new OAuth2ErrorResponseErrorHandler());
			accessTokenResponseClient.setRestOperations(restTemplate);

			return accessTokenResponseClient;
		}

		public AuthorizationRequestRepository<OAuth2AuthorizationRequest> authorizationRequestRepository() {
			return new HttpSessionOAuth2AuthorizationRequestRepository();
		}

		@Bean
		public ClientRegistrationRepository clientRegistrationRepository() {
			List<ClientRegistration> registrations = clients.stream().map(c -> getRegistration(c))
					.filter(registration -> registration != null).collect(Collectors.toList());

			return new InMemoryClientRegistrationRepository(registrations);
		}

		@Autowired
		private Environment env;

		private ClientRegistration getRegistration(String client) {
			String clientId = env.getProperty(CLIENT_PROPERTY_KEY + client + ".client-id");

			if (clientId == null) {
				clientId = env.getProperty(CLIENT_PROPERTY_KEY + ".provider." + client + ".client-id");
				if (clientId == null)
					return null;
			}

			String clientSecret = env.getProperty(CLIENT_PROPERTY_KEY + client + ".client-secret");
			if (client.equals("google")) {

				return CommonOAuth2Provider.GOOGLE.getBuilder(client).clientId(clientId).clientSecret(clientSecret)
						.build();
			}

			return null;
		}

		@Bean
		public JwtAuthenticationFilter authenticationTokenFilterBean() throws Exception {
			return new JwtAuthenticationFilter();
		}

		@Bean
		public LogoutSuccessHandler multiLogoutSuccessHandler() {
			return new MultiRoleLogoutSuccessHandler();
		}

	}

}