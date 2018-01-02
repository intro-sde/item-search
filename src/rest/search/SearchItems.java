package rest.search;

import com.recombee.api_client.RecombeeClient;
import com.recombee.api_client.api_requests.ListItems;
import com.recombee.api_client.bindings.Item;
import com.recombee.api_client.exceptions.ApiException;

import rest.connection.Connection;

public class SearchItems {
	static RecombeeClient client = Connection.createRecombeeClient();
	
	public static Item[] search(String filter, int count) throws ApiException {
		Item [] result = client.send(new ListItems()
				  .setFilter(filter)
				  .setCount(count)
				  .setReturnProperties(true)
				);
		return result;
	}

}
