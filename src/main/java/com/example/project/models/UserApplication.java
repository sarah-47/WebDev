package com.example.project.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Lob;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "userApplication")
public class UserApplication {

	/*----------------------------------------------------------------------------
	TABEL
	----------------------------------------------------------------------------*/
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;

	@NotNull
	private String city;

	@Min(0)
	private int years_experience;

	@Min(1)
	@Max(5)
	private double user_gpa;

	// CV
	private String cvFileName;
	private String cvFileType;
	// LOB is datatype for storing large object data.
	@Lob
	private byte[] cvFileData;

	// Certificate
	private String certFileName;
	private String certFileType;

	// LOB is datatype for storing large object data.
	@Lob
	private byte[] certFileData;

	@Column(updatable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createdAt;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date updatedAt;

	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = new Date();
	}

	/*----------------------------------------------------------------------------
	RELATIONS
	----------------------------------------------------------------------------*/
	// (connection with Skill table)
	// ManyToMany relation: many skills owned by many usersApplications
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "availableSkills_userApp", joinColumns = @JoinColumn(name = "userApplication_id"), inverseJoinColumns = @JoinColumn(name = "availableSkills_id"))
	private List<AvailableSkills> skills_for_appl;

	// (connection with User table)
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	/*----------------------------------------------------------------------------
	CONSTRUCTOR
	----------------------------------------------------------------------------*/
	public UserApplication(String cvFileName, String cvFileType, byte[] cvFileData, String certFileName,
			String certFileType, byte[] certFileData) {
		this.cvFileName = cvFileName;
		this.cvFileType = cvFileType;
		this.cvFileData = cvFileData;

		this.certFileName = certFileName;
		this.certFileType = certFileType;
		this.certFileData = certFileData;
	}

	public UserApplication() {
	}

	/*----------------------------------------------------------------------------
	GETTERS AND SETTERS
	----------------------------------------------------------------------------*/
	public void setCvFileName(String cvFileName) {
		this.cvFileName = cvFileName;
	}

	public String getCvFileName() {
		return cvFileName;
	}

	public String getCvFileType() {
		return cvFileType;
	}

	public void setCvFileType(String cvFileType) {
		this.cvFileType = cvFileType;
	}

	public byte[] getCvFileData() {
		return cvFileData;
	}

	public void setCvFileData(byte[] cvFileData) {
		this.cvFileData = cvFileData;
	}

	public String getCertFileName() {
		return certFileName;
	}

	public void setCertFileName(String certFileName) {
		this.certFileName = certFileName;
	}

	public String getCertFileType() {
		return certFileType;
	}

	public void setCertFileType(String certFileType) {
		this.certFileType = certFileType;
	}

	public byte[] getCertFileData() {
		return certFileData;
	}

	public void setCertFileData(byte[] certFileData) {
		this.certFileData = certFileData;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getYears_experience() {
		return years_experience;
	}

	public void setYears_experience(int years_experience) {
		this.years_experience = years_experience;
	}

	public double getUser_gpa() {
		return user_gpa;
	}

	public void setUser_gpa(double user_gpa) {
		this.user_gpa = user_gpa;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<AvailableSkills> getSkills_for_appl() {
		return skills_for_appl;
	}

	public void setSkills_for_appl(List<AvailableSkills> skills_for_appl) {
		this.skills_for_appl = skills_for_appl;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getId() {
		return id;
	}

}