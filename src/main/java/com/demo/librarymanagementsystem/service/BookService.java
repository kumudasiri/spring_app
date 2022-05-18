package com.demo.librarymanagementsystem.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.demo.librarymanagementsystem.entity.Book;
import com.demo.librarymanagementsystem.entity.Contact;

public interface BookService {

	public List<Book> findAllBooks();

	public List<Book> searchBooks(String keyword);

	public Book findBookById(Long id);

	public void createBook(Book book);

	public void updateBook(Book book);
	
	//public Contact findContactById(Long id);
	public void deleteBook(Long id);

	//public List<Book> findPaginatedSorted(int pageNo, int pageSize, String sortBy);

	//public Page<Book> findPaginated(int pageNo, int pageSize);

	Page<Book> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);

	///public Iterable<Book> sortBook();

	//public Iterable<Book> sortBook(Book book);
	
	//public Page<Book> searchPaginated(String keyword);

}
