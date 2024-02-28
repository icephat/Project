package org.cassava.util;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;

import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson.JacksonFactory;
import com.google.api.services.oauth2.Oauth2;
import com.google.api.services.oauth2.model.Tokeninfo;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Simple server to demonstrate token verification.
 *
 * @author cartland@google.com (Chris Cartland)
 */
@Configuration
@PropertySource("classpath:/application-oauth2.properties")
public class GoogleTokenVerification {
	/**
	 * Replace this with the client ID you got from the Google APIs console.
	 */
	// @Value("spring.security.oauth2.client.registration.ss.client-id")
	//private static String CLIENT_ID = "279975869260-v5l141hsqcra1f5rs8m92f0679lkougu.apps.googleusercontent.com";
	private static String ANDRIOD_CLIENT_ID = "468092107461-mjcj0o67hnfacbro396kb57msop5k77j.apps.googleusercontent.com";
	
	
	private static String IOS_CLIENT_ID = "468092107461-1b11q7s1sibp4p9ut31rs8oa1tus1p25.apps.googleusercontent.com";
	
	/**
	 * Replace this with the client secret you got from the Google APIs console.
	 */
	// @Value("spring.security.oauth2.client.registration.ss.client-secret")
	private static String CLIENT_SECRET = "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";
	/**
	 * Optionally replace this with your application's name.
	 */
	private static final String APPLICATION_NAME = "Google + Java Token Verification";

	/**
	 * Default HTTP transport to use to make HTTP requests.
	 */
	private static final HttpTransport TRANSPORT = new NetHttpTransport();
	/**
	 * Default JSON factory to use to deserialize JSON.
	 */
	private static final JacksonFactory JSON_FACTORY = new JacksonFactory();

	/**
	 * Gson object to serialize JSON responses to requests to this servlet.
	 */

	/**
	 * Register all endpoints that we'll handle in our server.
	 * 
	 * @param args Command-line arguments.
	 */

	public static boolean verify(String accessToken) {

		GoogleCredential credential = new GoogleCredential().setAccessToken(accessToken);
		Oauth2 oauth2 = new Oauth2.Builder(TRANSPORT, JSON_FACTORY, credential).build();
		try {
			Tokeninfo tokenInfo = oauth2.tokeninfo().setAccessToken(accessToken).execute();
			if (tokenInfo.containsKey("error")	) {
				// System.out.println("-----------error");
				return false;
			} else if (!(tokenInfo.getIssuedTo().equals(ANDRIOD_CLIENT_ID)  || tokenInfo.getIssuedTo().equals(IOS_CLIENT_ID))) {
				 //System.out.println(tokenInfo.getIssuedTo()+"-----------"+ANDRIOD_CLIENT_ID);
				return false;
			}

		} catch (IOException e) {
			// System.out.println("-----------Exception");
			return false;
		}

		return true;
	}

	/**
	 * JSON representation of a token's status.
	 */

}