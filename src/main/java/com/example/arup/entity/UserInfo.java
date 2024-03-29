package com.example.arup.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name="user_information")
public class UserInfo {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@Size(min=1)
	@Column(name="first_name",nullable=false)
	private String firstName;
	@Size(min=1)
	@Column(name="last_name",nullable=false)
	private String lastName;
	@Size(min=1)
	@Column(name="email",nullable=false,unique=true)
	private String email;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "UserInfo [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + "]";
	}
}
