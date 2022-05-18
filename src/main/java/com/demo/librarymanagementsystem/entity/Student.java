package com.demo.librarymanagementsystem.entity;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "students")
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "rollNo", nullable = false, unique = true)
	private Long rollNo;
	@Column(name = "name", length = 100, nullable = false)
	private String name;
	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private Set<Book> books = new HashSet<Book>();
	
	@Column(name = "doj",nullable = false)
	private Date doj;
	@Column(name = "dob",nullable = false)
	private Date dob;
	
	
	public Student(Long id, Long rollNo, String name, Date dob,Date doj) {
		super();
		this.id = id;
		this.rollNo = rollNo;
		this.name = name;
		this.dob = dob;
		this.doj = doj;
	}

	

	/*public Student(Long rollNo, String name) {
		super();
		this.rollNo = rollNo;
		this.name = name;
	}*/

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRollNo() {
		return rollNo;
	}

	public void setRollNo(Long rollNo) {
		this.rollNo = rollNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Book> getBooks() {
		return books;
	}

	public void setBooks(Set<Book> books) {
		this.books = books;
	}

	/*public void addBooks(Book book) {
		this.books.add(book);
		book.getStudents().add(this);
	}

	public void removeBooks(Book book) {
		this.books.remove(book);
		book.getStudents().remove(this);
	}*/

	//public Student() {

	//}



	public Date getDoj() {
		return doj;
	}

	public void setDoj(Date doj) {
		this.doj = doj;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}
}
