package com.psk.bank.model;

import java.time.LocalDateTime;

public class User {

	private Long id;
	private String name;
	private LocalDateTime date;


	public User()
	{}

	
	public User(Long id, String name, LocalDateTime date) {
		super();
		this.id = id;
		this.name = name;
		this.date = date;
	}
	

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	//@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss") 
	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", date=" + date + "]";
	}
	
}
