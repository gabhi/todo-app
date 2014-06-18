package com.example;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
//todo separate mongo
//connecion to mongodb configurable
//singleton mongodb class
//Generic object class

import com.mashape.unirest.http.HttpResponse;

////////
/**
 * Root resource (exposed at "todos" path)
 */
@Path("/todos")
public class MyResource {

	ServerUtil serverUtil = ServerUtil.instance();

	/**
	 * Method handling HTTP GET requests. The returned object will be sent to
	 * the client as "text/plain" media type.
	 * 
	 * @return String that will be returned as a text/plain response.
	 */
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String get(@DefaultValue("0") @QueryParam("page") int page, @DefaultValue("10") @QueryParam("size") int size) {
		try {
			return serverUtil.getRoot(page, size).toString();
		} catch (Exception e) {
			return e.getMessage();
		}

	}

	@GET
	@Path("/search/?q={term}")
	@Produces(MediaType.TEXT_PLAIN)
	public String searchByTerm(@PathParam("term") String term) {
		return "searching " + term;
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public String getTodoById(@PathParam("id") String id) {
		return "getting " + id;
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public String delete(@PathParam("id") String id) {
		return serverUtil.deleteTodo(id).toString();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Path("/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public String update(@PathParam("id") String id, @FormParam("name") String name) {
		return "Updating todo for " + id + " new name " + name;
	}

	@PATCH
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Path("/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public String patch(@PathParam("id") String id, @FormParam("done") boolean done) {

		System.out.println("**999999***" + id + "===" + done);
		
		return (serverUtil.Update(id, done).toString());

	}

	@POST
	@Consumes({ "application/xml", "application/json", "application/x-www-form-urlencoded" })
	@Produces(MediaType.TEXT_PLAIN)
	public Response create(@FormParam("title") String title, @FormParam("body") String body) {
		System.out.println(title + "*******" + body);
		return convert(serverUtil.CreateNewTodo(title, body));

		// return null;

	}

	public Response convert(HttpResponse r) {
		if (r.getCode() != 200) {
			return Response.status(r.getCode()).entity(r.getBody().toString()).build();
		} else {
			return Response.ok(r.getBody().toString()).build();
		}
	}

}
