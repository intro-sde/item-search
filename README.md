# Item search service
RESTful service for searching items based on keywords in database.

The following method for the https://sde-item-search-ws.herokuapp.com/search endpoint is available in this service:
- @GET | [keyword, count(optional)] | Returns list of items that contains the given keywords. 

For example https://sde-item-search-ws.herokuapp.com/search?keyword=Toscana Cycling&count=3 gives back 3 activities with topic=Cycling and city=Toscana.

Reference:
Recombee API (version 1.6.0), Available at: https://docs.recombee.com/api.html
