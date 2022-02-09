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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "instructors")
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="idjackson")
public class Instructor {

	@Transient
	private int idjackson;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int id;
	@Column(name="lastname")
	private String lastName;
	@Column(name="firstname")
	private String firstName;
	@Column
	private String mail;

	//@JsonIgnore
	@OneToOne
	@Cascade({ org.hibernate.annotations.CascadeType.SAVE_UPDATE })
	@JoinColumn(name = "username")
	private UserDto userDto;
	
	
	//@JsonIgnore
	@Cascade({ org.hibernate.annotations.CascadeType.SAVE_UPDATE })
	@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}) 
	@JoinTable (name = "center_instructor",
		joinColumns = {@JoinColumn(name = "id_instructor")},
		inverseJoinColumns = { @JoinColumn(name = "id_center")})
	private List<Center> listCenters;
	
//	@JsonIgnore
//	@JsonBackReference
	@Cascade({ org.hibernate.annotations.CascadeType.SAVE_UPDATE })
	@OneToMany(mappedBy = "instructor",  fetch = FetchType.EAGER, cascade = {CascadeType.ALL}) 
	private List<TrainingSession> listSessions;
	

	
	public Instructor() {
		
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



	public List<Center> getListCenters() {
		return listCenters;
	}



	public void setListCenters(List<Center> listCenters) {
		this.listCenters = listCenters;
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
