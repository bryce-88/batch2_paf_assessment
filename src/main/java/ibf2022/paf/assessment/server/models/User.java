package ibf2022.paf.assessment.server.models;

// Do not change this class

public class User {

	private String userId;
	private String username;
	private String name;

	public User() { }

	public User(String userId, String username, String name) {
		this.userId = userId;
		this.username = username;
		this.name = name;
	}

	public User(String username) {
		this.username = username;
	}

	public String getUserId() { return this.userId; }
	public void setUserId(String userId) { this.userId = userId; }

	public String getUsername() { return this.username; }
	public void setUsername(String username) { this.username = username; }

	public String getName() { return this.name; }
	public void setName(String name) { this.name = name; }

	@Override
	public String toString() {
		return "user_id: %s, username: %s, name: %s"
				.formatted(userId, username, name);
	}
}
