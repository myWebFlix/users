package com.webflix.users.models.entities;

import javax.persistence.*;

@Entity
@Table(name = "users_data")
@NamedQueries(value =
		{
				@NamedQuery(name = "UserEntity.getAll",
						query = "SELECT usr FROM UserEntity usr")
		})
public class UserEntity {

	// Fields

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "google_user_id", unique = true)
	private String google_user_id;

	@Column(name = "user_name")
	private String user_name;

	@Column(name = "user_email")
	private String user_email;

	@Column(name = "user_age")
	private Integer user_age;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public Integer getUser_age() {
		return user_age;
	}

	public void setUser_age(Integer user_age) {
		this.user_age = user_age;
	}

	public String getGoogle_user_id() {
		return google_user_id;
	}

	public void setGoogle_user_id(String google_user_id) {
		this.google_user_id = google_user_id;
	}

	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
}
