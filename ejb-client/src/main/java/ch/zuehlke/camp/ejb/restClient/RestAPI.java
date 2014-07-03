package ch.zuehlke.camp.ejb.restClient;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import ch.zuehlke.camp.jpa.Food;

public class RestAPI {

	public static List<Food> findFood(String searchString) throws IOException {
		DefaultHttpClient httpClient = new DefaultHttpClient();
	    try
	    {
	        //Define a HttpGet request; You can choose between HttpPost, HttpDelete or HttpPut also.
	        //Choice depends on type of method you will be invoking.
	        HttpGet getRequest = new HttpGet("http://localhost:8080/ejb/rest-prefix/findFood/" + URLEncoder.encode(searchString, "UTF-8"));
	         
	        //Set the API media type in http accept header
	        //getRequest.addHeader("accept", "text/plain");
	          
	        //Send the request; It will immediately return the response in HttpResponse object
	        HttpResponse response = httpClient.execute(getRequest);
	         
	        //verify the valid error code first
	        int statusCode = response.getStatusLine().getStatusCode();
	        if (statusCode != 200)
	        {
	            throw new RuntimeException("Failed with HTTP error code : " + statusCode);
	        }
	         
	        //Now pull back the response object
	        HttpEntity httpEntity = response.getEntity();
	        String apiOutput = EntityUtils.toString(httpEntity);
	         
	        //Lets see what we got from API
	        System.out.println("Webservice delivered response: " + apiOutput); //Some JSON
	        
	        ObjectMapper mapper = new ObjectMapper();
	        return mapper.readValue(apiOutput, new TypeReference<List<Food>>() { });
	    }
	    finally
	    {
	        //Important: Close the connect
	        httpClient.getConnectionManager().shutdown();
	    }
	}

}
