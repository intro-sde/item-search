package rest.resources;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

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
	
	
	@GET
	@Produces({MediaType.TEXT_XML, MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Item[] search(@DefaultValue("")@QueryParam("filter") String filter, @DefaultValue("")@QueryParam("count") String count) throws ApiException {
		System.out.println("--> RecommendationResource request...");
		System.out.println("--> URI = "+uriInfo);
		System.out.println("--> request = "+request);
		Item[] items = SearchItems.search(filter, Integer.parseInt(count));
		return items;
	}
	
}
