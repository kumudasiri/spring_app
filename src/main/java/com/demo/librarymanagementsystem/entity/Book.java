package com.demo.librarymanagementsystem.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "books")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "isbn", length = 50, nullable = false, unique = true)
	private String isbn;

	@Column(name = "name", length = 100, nullable = false)
	private String name;

	@Column(name = "serialName", length = 50, nullable = false)
	private String serialName;

	@Column(name = "description", length = 250, nullable = false)
	private String description;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "books_authors", joinColumns = { @JoinColumn(name = "book_id") }, inverseJoinColumns = {
			@JoinColumn(name = "author_id") })
	private Set<Author> authors = new HashSet<Author>();

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "books_categories", joinColumns = { @JoinColumn(name = "book_id") }, inverseJoinColumns = {
			@JoinColumn(name = "category_id") })
	private Set<Category> categories = new HashSet<Category>();

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "books_publishers", joinColumns = { @JoinColumn(name = "book_id") }, inverseJoinColumns = {
			@JoinColumn(name = "publisher_id") })
	private Set<Publisher> publishers = new HashSet<Publisher>();
	
	/*@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "books_contacts", joinColumns = { @JoinColumn(name = "book_id") }, inverseJoinColumns = {
			@JoinColumn(name = "c_id") })
	List<Contact> contacts=new ArrayList<>();*/
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "books_students", joinColumns = { @JoinColumn(name = "book_id") }, inverseJoinColumns = {
			@JoinColumn(name = "s_id") })
	private Set<Student> students = new HashSet<Student>();
	
	

	public Book(String isbn, String name, String serialName, String description) {
		this.isbn = isbn;
		this.name = name;
		this.serialName = serialName;
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSerialName() {
		return serialName;
	}

	public void setSerialName(String serialName) {
		this.serialName = serialName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(Set<Author> authors) {
		this.authors = authors;
	}

	public Set<Category> getCategories() {
		return categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}

	public Set<Publisher> getPublishers() {
		return publishers;
	}

	public void setPublishers(Set<Publisher> publishers) {
		this.publishers = publishers;
	}
	public Set<Student> getStudents() {
		return students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}


	public void addAuthors(Author author) {
		this.authors.add(author);
		author.getBooks().add(this);
	}

	public void removeAuthors(Author author) {
		this.authors.remove(author);
		author.getBooks().remove(this);
	}
	
	public void addCategories(Category category) {
		this.categories.add(category);
		category.getBooks().add(this);
	}

	public void removeCategories(Category category) {
		this.categories.remove(category);
		category.getBooks().remove(this);
	}

	public void addPublishers(Publisher publisher) {
		this.publishers.add(publisher);
		publisher.getBooks().add(this);
	}

	public void removePublishers(Publisher publisher) {
		this.publishers.remove(publisher);
		publisher.getBooks().remove(this);
	}

	//public Book() {
		//super();
		// TODO Auto-generated constructor stub
	//}
	
}