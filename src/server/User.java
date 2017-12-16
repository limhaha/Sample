package server;

import java.io.Serializable;

public class User implements Serializable{
	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public String getGender() {
		return gender;
	}

	public float getHeight() {
		return height;
	}

	public float getWeight() {
		return weight;
	}

	public float getGoal_weight() {
		return goal_weight;
	}

	public String getPassword() {
		return password;
	}

	private final String id;
	private final String password;
	private final String name;
	private final int age;
	private final String gender;
	private final float height;
	private final float weight;
	private final float goal_weight;
	
	public User(String id, String password,String name, int age, String gender, float height, float weight, float goal_weight) {
		this.id =id;
		this.password = password;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.height = height;
		this.weight = weight;
		this.goal_weight = goal_weight;
	}
	
	
}
