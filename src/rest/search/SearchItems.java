package rest.search;

import java.io.IOException;
import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;
import org.json.JSONArray;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.recombee.api_client.RecombeeClient;
import com.recombee.api_client.api_requests.ListItems;
import com.recombee.api_client.bindings.Item;
import com.recombee.api_client.exceptions.ApiException;

import rest.connection.Connection;

public class SearchItems {
	public static WebTarget config() {
    	ClientConfig clientConfig = new ClientConfig();
        Client client = ClientBuilder.newClient(clientConfig);
        WebTarget service = client.target(getBaseURI());
        System.out.println("Calling " + getBaseURI() ); 
        return service;
    }
	
	 public static String format(String jsonString) throws IOException {
		 ObjectMapper mapper = new ObjectMapper();
		 Object json = mapper.readValue(jsonString, Object.class);
		 String prettyJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);

	      return prettyJson;
	  }
	 private static URI getBaseURI() {
		 return UriBuilder.fromUri(
				 "https://sde-storage-ws.herokuapp.com/").build();
	 }
	
	public static String search(String keyword) throws IOException {	
		WebTarget service = config();
		Response resp = service.path("/rdb/items").queryParam("filter", SearchItems.conertQueryToFilter(keyword)).request().accept(MediaType.APPLICATION_JSON).header("Content-type","application/json").get();
		String response = resp.readEntity(String.class);
		String json = format(response);
		return json;
		 
	}

	private static String conertQueryToFilter(String query) {
		String[] parts = query.split(" ");
		
		String keyword = parts[0];
		String filter = conertKWToFilter(keyword);
		if(parts.length>1) {
			for (int i=1;i<parts.length;i++) {
				String nextKeyWord = parts[i];
				filter = "(" + filter + ")" + " and " + "(" + conertKWToFilter(nextKeyWord) + ")";
			}
		}
		System.out.println(filter);
		return filter;
		
	}
	
	private static String conertKWToFilter(String keyword) {
		keyword = "\""+keyword+"\"";
		String filter = keyword + " in 'name' or " + keyword + " in 'city' or "+ keyword +" in 'type' or " +keyword + " in 'topic' or " +keyword+ " in 'from' or "+keyword+" in 'to' or " +keyword+" in 'address' or " + keyword+" in 'rating'";
		return filter;
	}
	
	
	
	
}
