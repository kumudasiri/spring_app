package com.demo.librarymanagementsystem.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.demo.librarymanagementsystem.entity.Book;
import com.demo.librarymanagementsystem.entity.Contact;
import com.demo.librarymanagementsystem.exception.NotFoundException;
import com.demo.librarymanagementsystem.repository.BookRepository;
import com.demo.librarymanagementsystem.repository.BookRepository2;
import com.demo.librarymanagementsystem.repository.ContactRepository;
import com.demo.librarymanagementsystem.repository.SortRepository;
import com.demo.librarymanagementsystem.service.BookService;

@Service
public class BookServiceImpl implements BookService {
	@Autowired
	private final BookRepository bookRepository;
	@Autowired
	private final SortRepository sortRepository;
	@Autowired
	private final BookRepository2 bookRepository2;
	@Autowired
	private final ContactRepository contactRepository;

	public BookServiceImpl(BookRepository bookRepository,SortRepository sortRepository,BookRepository2 bookRepository2,ContactRepository contactRepository) {
		this.bookRepository = bookRepository;
		this.sortRepository = sortRepository;
		this.bookRepository2 = bookRepository2;
		this.contactRepository = contactRepository;
	}

	@Override
	public List<Book> findAllBooks() {
		return bookRepository.findAll();
	}

	@Override
	public List<Book> searchBooks(String keyword) {
		if (keyword != null) {
			return bookRepository.search(keyword);
		}
		return bookRepository.findAll();
	}

	@Override
	public Book findBookById(Long id) {
		return bookRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(String.format("Book not found with ID %d", id)));
	}

	@Override
	public void createBook(Book book) {
		try {
			bookRepository.save(book);
		} catch (DataIntegrityViolationException e) {
			e.getMessage();
		}
	}

	@Override
	public void updateBook(Book book) {
		bookRepository.save(book);
	}

	@Override
	public void deleteBook(Long id) {
		final Book book = bookRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(String.format("Book not found with ID %d", id)));

		bookRepository.deleteById(book.getId());
	}

	/*@Override
	public Page<Book> findPaginated(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
		return this.bookRepository.findAll(pageable);
	}*/
	

	@Override
	public Page<Book> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
	    Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
	     Sort.by(sortField).descending();
	 
	    Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
	  
	    return this.bookRepository2.findAll(pageable);
	}

	/*@Override
	public Contact findContactById(Long id) {
		return contactRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(String.format("Contact not found with ID %d", id)));
	}*/

	/*@Override
	public Iterable<Book> sortBook(Book book){
		Sort sortOrder = Sort.by(book.getName()); 
		 
		Iterable<Book> list = sortRepository.findAll(sortOrder);
		return list;
	}*/


}
