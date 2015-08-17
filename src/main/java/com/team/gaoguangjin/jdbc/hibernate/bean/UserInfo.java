package com.team.gaoguangjin.jdbc.hibernate.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity(name = "t_user_info")
@Getter
@Setter
public class UserInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	@Column(name = "name", unique = false, length = 50)
	private String name;
	@Column(name = "password", unique = false, length = 50)
	private String password;
	@Column(name = "email", unique = false, length = 50)
	private String email;
	
	public String toString() {
		return "name=password=email==>" + name + ":" + password + ":" + email;
	}
}
