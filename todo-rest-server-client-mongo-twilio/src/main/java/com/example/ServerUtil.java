package com.example;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mongodb.*;

import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerUtil {

	private static final String BASE_URI = "http://safe-ocean-7286.herokuapp.com/todo/";
	private static ServerUtil singleton;

	public static synchronized ServerUtil instance() {
		if (singleton == null)
			singleton = new ServerUtil();
		return singleton;
	}

	public JsonNode getRoot(int page, int size) {

		HttpResponse<JsonNode> jsonResponse;
		try {
			jsonResponse = Unirest.get(BASE_URI).field("page", page).field("size", size)
					.header("accept", "application/json").asJson();
			System.out.println(jsonResponse.getBody());

			return jsonResponse.getBody();

			// System.out.println(response.getObject().getJSONObject("_embedded").getJSONArray("todos"));

		} catch (UnirestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public JsonNode deleteTodo(String id) {

		HttpResponse<JsonNode> jsonResponse;
		try {
			jsonResponse = Unirest.delete(BASE_URI + id).header("accept", "application/json").asJson();
			System.out.println(jsonResponse.getBody());

			return jsonResponse.getBody();

			// System.out.println(response.getObject().getJSONObject("_embedded").getJSONArray("todos"));

		} catch (UnirestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public HttpResponse<JsonNode> CreateNewTodo(String title, String body) {
		// System.out.println(title + "----"+body);
		HttpResponse<JsonNode> jsonResponse;
		try {
			jsonResponse = Unirest.post(BASE_URI).header("Content-Type", "application/json")
					.body("{  \"title\" : \"" + title + "\",  \"body\" : \"" + body + "\" }").asJson();

			System.out.println(jsonResponse.getCode());
			System.out.println(jsonResponse.getBody());

			return jsonResponse;

			// System.out.println(response.getObject().getJSONObject("_embedded").getJSONArray("todos"));

		} catch (UnirestException e) {
			// TODO Auto-generated catch block
			System.out.println("errrr");
			System.out.println(e);
		}

		return null;
	}

	
	
	
	public JsonNode Update(String id, boolean done) {
		System.out.println(id + "----"+done);
				HttpResponse<JsonNode> jsonResponse;
				try {
					jsonResponse = Unirest.patch(BASE_URI+id)
							  .header("Content-Type", "application/json")
							  .body("{  \"done\" :"+done+" }")
							  .asJson();
					
					
		 			System.out.println(jsonResponse.getBody());

					return jsonResponse.getBody();

					// System.out.println(response.getObject().getJSONObject("_embedded").getJSONArray("todos"));

				} catch (UnirestException e) {
					// TODO Auto-generated catch block
					System.out.println(e);
				}

				return null;
			}
	/*
	public HttpResponse Update(String id, boolean done) {
		System.out.println(id + "----" + done);
		HttpResponse<JsonNode> jsonResponse;
		try {
			jsonResponse = Unirest.patch(BASE_URI + id).header("Content-Type", "application/json")
					.body("{  \"done\" :" + done + " }").asJson();

			System.out.println(jsonResponse.getBody());

			return jsonResponse;

			// System.out.println(response.getObject().getJSONObject("_embedded").getJSONArray("todos"));

		} catch (UnirestException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}

		return null;
	}
	*/

	public static void main(String[] args) {
		System.out.println("%%%%%%%%%%");
		ServerUtil s = ServerUtil.instance();
		// System.out.println(s.getRoot());

		System.out.println(s.CreateNewTodo("a", "a"));

		// System.out.println(s.getRoot());

	}

}
