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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "courses")
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="idjackson")
public class Course {
	
	@Transient
	private int idjackson;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int id;
	@Column(name = "duration_days")
	private int duration;
	@Column(name = "capacity")
	private int studentNb;
	@Column
	private int price;
	@Column
	private String title;
	@Column(name = "information")
	private String informations;
	
	//@JsonManagedReference
	@Cascade({ org.hibernate.annotations.CascadeType.SAVE_UPDATE })
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}) 
	@JoinColumn(name="id_topic")
	private Topic topic;
	
	//@JsonBackReference
	@Cascade({ org.hibernate.annotations.CascadeType.SAVE_UPDATE })
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}) 
	@JoinColumn(name="id_center")
	private Center center;
	
	//@JsonBackReference
	@Cascade({ org.hibernate.annotations.CascadeType.SAVE_UPDATE })
	@OneToMany(mappedBy = "course", cascade = CascadeType.ALL,  fetch = FetchType.EAGER)
	private List<TrainingSession> listSessions;
	
	public Course() {
		
	}
	public String toString() {
		return (this.title +  " ("+this.center.getName()+")" );
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int getStudentNb() {
		return studentNb;
	}

	public void setStudentNb(int studentNb) {
		this.studentNb = studentNb;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getInformations() {
		return informations;
	}

	public void setInformations(String informations) {
		this.informations = informations;
	}

	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}

	public Center getCenter() {
		return center;
	}

	public void setCenter(Center center) {
		this.center = center;
	}

	public List<TrainingSession> getListSessions() {
		return listSessions;
	}

	public void setListSessions(List<TrainingSession> listSessions) {
		this.listSessions = listSessions;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	public int getIdjackson() {
		return idjackson;
	}
	public void setIdjackson(int idjackson) {
		this.idjackson = idjackson;
	}


	
}
