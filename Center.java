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
@Table(name = "centers")
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="idjackson")
public class Center {

	@Transient
	private int idjackson;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int id;
	@Column(name = "center_name")
	private String name;
	@Column
	private String mail;
	
	
	//@JsonIgnore
	@OneToOne
	@JoinColumn(name = "username")
	private UserDto userDto;
	
	
	//@JsonManagedReference
	@OneToMany(cascade = CascadeType.ALL, mappedBy="center",  fetch = FetchType.EAGER) 
	private List<Course> listCourses;
	
	//@JsonIgnore
	//ajout de cette ligne à la place de EAGER pour éviter cette erreur : org.hibernate.loader.MultipleBagFetchException: cannot simultaneously fetch multiple bags
	//Autre solution upgrade hibernate
	@LazyCollection(LazyCollectionOption.FALSE)
	@Cascade({ org.hibernate.annotations.CascadeType.SAVE_UPDATE })
	@ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}) 
	@JoinTable (name = "center_instructor",
		joinColumns = { @JoinColumn(name = "id_center")},
		inverseJoinColumns = { @JoinColumn(name = "id_instructor")})
	private List<Instructor> listInstructors;
	
	public Center() {
		
	}

	public String toString() {
		return this.name;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}



	public List<Course> getListCourses() {
		return listCourses;
	}

	public void setListCourses(List<Course> listCourses) {
		this.listCourses = listCourses;
	}

	public List<Instructor> getListInstructors() {
		return listInstructors;
	}

	public void setListInstructors(List<Instructor> listInstructors) {
		this.listInstructors = listInstructors;
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
