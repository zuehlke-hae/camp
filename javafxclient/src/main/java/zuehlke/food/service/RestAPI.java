package zuehlke.food.service;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import ch.zuehlke.camp.jpa.EatenFood;
import ch.zuehlke.camp.jpa.Food;

public class RestAPI implements ServiceApi {

	private static final String hostname = "http://192.168.1.80:8080";
	
	private static DefaultHttpClient httpClient = new DefaultHttpClient();
	private static ObjectMapper mapper = new ObjectMapper();

	/* (non-Javadoc)
	 * @see zuehlke.food.service.ServiceApi#findFood(java.lang.String)
	 */
	@Override
	public List<Food> findFood(String searchString) throws IOException {
		// Define a HttpGet request; You can choose between HttpPost,
		// HttpDelete or HttpPut also.
		// Choice depends on type of method you will be invoking.
		HttpGet getRequest = new HttpGet(
				hostname+"/ejb/rest-prefix/findFood/"
						+ URLEncoder.encode(searchString, "UTF-8"));

		// Set the API media type in http accept header
		// getRequest.addHeader("accept", "text/plain");

		// Send the request; It will immediately return the response in
		// HttpResponse object
		HttpResponse response = httpClient.execute(getRequest);

		// verify the valid error code first
		int statusCode = response.getStatusLine().getStatusCode();
		if (statusCode != 200) {
			throw new RuntimeException("Failed with HTTP error code : "
					+ statusCode);
		}

		// Now pull back the response object
		HttpEntity httpEntity = response.getEntity();
		String apiOutput = EntityUtils.toString(httpEntity);

		// Lets see what we got from API
		System.out.println("Webservice delivered response: " + apiOutput); // Some
																			// JSON
		return mapper.readValue(apiOutput, new TypeReference<List<Food>>() {
		});
	}

	/* (non-Javadoc)
	 * @see zuehlke.food.service.ServiceApi#addMeal(java.util.Date, java.util.List, java.util.List)
	 */
	@Override
	public void addMeal(Date eatenDate, List<Food> foodsEaten,
			List<Double> values) throws ClientProtocolException, IOException {
		// Define a HttpGet request; You can choose between HttpPost,
		// HttpDelete or HttpPut also.
		// Choice depends on type of method you will be invoking.
		HttpPost postRequest = new HttpPost(
				hostname+"/ejb/rest-prefix/addMeal/"
						+ URLEncoder.encode(
								mapper.writeValueAsString(eatenDate), "UTF-8")
						+ "/"
						+ URLEncoder.encode(
								mapper.writeValueAsString(foodsEaten), "UTF-8")
						+ "/"
						+ URLEncoder.encode(mapper.writeValueAsString(values), "UTF-8"));

		// Set the API media type in http accept header
		// getRequest.addHeader("accept", "text/plain");

		// Send the request; It will immediately return the response in
		// HttpResponse object
		HttpResponse response = httpClient.execute(postRequest);

		// verify the valid error code first
		int statusCode = response.getStatusLine().getStatusCode();
		if (statusCode != 204) {
			throw new RuntimeException("Failed with HTTP error code : "
					+ statusCode);
		}
	}

	/* (non-Javadoc)
	 * @see zuehlke.food.service.ServiceApi#report()
	 */
	@Override
	public List<EatenFood> report() throws ClientProtocolException,
			IOException {
		// Define a HttpGet request; You can choose between HttpPost,
		// HttpDelete or HttpPut also.
		// Choice depends on type of method you will be invoking.
		HttpGet getRequest = new HttpGet(
				hostname+"/ejb/rest-prefix/report/");

		// Set the API media type in http accept header
		// getRequest.addHeader("accept", "text/plain");

		// Send the request; It will immediately return the response in
		// HttpResponse object
		HttpResponse response = httpClient.execute(getRequest);

		// verify the valid error code first
		int statusCode = response.getStatusLine().getStatusCode();
		if (statusCode != 200) {
			throw new RuntimeException("Failed with HTTP error code : "
					+ statusCode);
		}

		// Now pull back the response object
		HttpEntity httpEntity = response.getEntity();
		String apiOutput = EntityUtils.toString(httpEntity);

		// Lets see what we got from API
		System.out.println("Webservice delivered response: " + apiOutput); // Some
																			// JSON
		return mapper.readValue(apiOutput,
				new TypeReference<List<EatenFood>>() {
				});
	}

}
