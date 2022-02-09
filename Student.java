package com.coursesMP.model;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "students")
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="idjackson")
public class Student {
	
	@Transient
	private int idjackson;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int id;
	@Column(name = "lastname")
	private String lastName;
	@Column(name = "firstname")
	private String firstName;
	@Column
	private String mail;
	
	//@JsonIgnore
	@OneToOne
	@JoinColumn(name = "username")
	@Cascade({ org.hibernate.annotations.CascadeType.SAVE_UPDATE })
	private UserDto userDto;
	
	//@JsonIgnore
	//ajout EAGER
	@Cascade({ org.hibernate.annotations.CascadeType.SAVE_UPDATE })
	@ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},fetch=FetchType.EAGER) 
	@JoinTable (name = "session_student",
		joinColumns = {@JoinColumn(name = "id_student")},
		inverseJoinColumns = { @JoinColumn(name = "id_session")})
	private List<TrainingSession> listSessions;
	

	
	public Student() {
		
	}

	public String toString() {
		return (this.lastName +" "+ this.firstName);
	}
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getMail() {
		return mail;
	}


	public void setMail(String mail) {
		this.mail = mail;
	}




	public List<TrainingSession> getListSessions() {
		return listSessions;
	}


	public void setListSessions(List<TrainingSession> listSessions) {
		this.listSessions = listSessions;
	}

	public UserDto getUserDto() {
		return userDto;
	}

	public void setUserDto(UserDto userDto) {
		this.userDto = userDto;
	}

	public int getIdjackson() {
		return idjackson;
	}

	public void setIdjackson(int idjackson) {
		this.idjackson = idjackson;
	}

	

	
}
