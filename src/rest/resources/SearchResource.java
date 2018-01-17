package rest.resources;

import java.io.IOException;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import org.json.JSONArray;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.recombee.api_client.bindings.Item;
import com.recombee.api_client.exceptions.ApiException;

import rest.search.SearchItems;

@Stateless
@LocalBean
@Path("/search")
public class SearchResource {
	
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	
	public static String format(String jsonString) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		Object json = mapper.readValue(jsonString, Object.class);
		String prettyJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);

		return prettyJson;
	}
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public String search(@DefaultValue("")@QueryParam("keyword") String keyword, @QueryParam("count") String count) throws ApiException, IOException {
		System.out.println("--> SearchResource request...");
		System.out.println("--> URI = "+uriInfo);
		System.out.println("--> request = "+request);
		String items;
		if (count==null) {
			items = SearchItems.search(keyword);
		}else {
			items = SearchItems.searchWithCount(keyword, count);
		}
		return items;
	}
	
}
