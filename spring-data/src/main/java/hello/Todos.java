package hello;

import org.springframework.data.annotation.Id;

public class Todos {

	@Id
	private String id;

	private String title;
	private String body;

	private boolean done;

	// done status

	public boolean isDone() {
		return done;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

}
