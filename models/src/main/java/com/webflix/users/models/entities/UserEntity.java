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

	@Column(name = "user_name")
	private String user_name;

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
}
