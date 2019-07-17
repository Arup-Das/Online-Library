package com.example.arup.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Size;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@Size(min=1)
	@Column(name="user_name",nullable=false,unique=true)
	private String username;
	@Size(min=4)
	@Column(name="password",nullable=false)
	private String password;
	private int active;
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name="user_role",joinColumns=@JoinColumn(name="user_id",referencedColumnName="id"),
	inverseJoinColumns=@JoinColumn(name="role_id",referencedColumnName="id"))
	private Set<Role> roles;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getActive() {
		return active;
	}
	public void setActive(int active) {
		this.active = active;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", active=" + active
				+ ", roles=" + roles + "]";
	}
}
