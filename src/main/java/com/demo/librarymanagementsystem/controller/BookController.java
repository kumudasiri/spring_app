package com.demo.librarymanagementsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.demo.librarymanagementsystem.entity.Author;
import com.demo.librarymanagementsystem.entity.Book;
import com.demo.librarymanagementsystem.entity.Contact;
import com.demo.librarymanagementsystem.service.AuthorService;
import com.demo.librarymanagementsystem.service.BookService;
import com.demo.librarymanagementsystem.service.CategoryService;
import com.demo.librarymanagementsystem.service.PublisherService;

@Controller
public class BookController {

	private final BookService bookService;
	private final AuthorService authorService;
	private final CategoryService categoryService;
	private final PublisherService publisherService;
	

	@Autowired
	public BookController(BookService bookService, AuthorService authorService, CategoryService categoryService,
			PublisherService publisherService) {
		this.bookService = bookService;
		this.authorService = authorService;
		this.categoryService = categoryService;
		this.publisherService = publisherService;
	}
	
	/*@RequestMapping("/assigned")
	public String findAllAuthors(Model model) {
		final List<Book> books = bookService.findAllBooks();
		model.addAttribute("books", books);
		return "list-assigned";
	}*/

	/*
	 * @RequestMapping("/books") public String findAllBooks(Model model) { final
	 * List<Book> books = bookService.findAllBooks();
	 * 
	 * model.addAttribute("books", books); return "list-books"; }
	 */
	/*@RequestMapping("/book")
	public String findAllBooks(Model model) {
		final List<Book> books = bookService.findAllBooks();

		model.addAttribute("book", books);
		return "list-books";
	}*/

	/*
	 * @RequestMapping("/searchBook") public String searchBook(@Param("keyword")
	 * String keyword, Model model) { final List<Book> books =
	 * bookService.searchBooks(keyword);
	 * 
	 * model.addAttribute("books", books); model.addAttribute("keyword", keyword);
	 * return "list-books"; }
	 */

	@RequestMapping("/book/{id}")
	public String findBookById(@PathVariable("id") Long id, Model model) {
		final Book book = bookService.findBookById(id);
		model.addAttribute("book", book);
		return "list-book";
	}

	@GetMapping("/add")
	public String showCreateForm(Book book, Model model) {
		model.addAttribute("categories", categoryService.findAllCategories());
		model.addAttribute("authors", authorService.findAllAuthors());
		model.addAttribute("publishers", publisherService.findAllPublishers());
		//model.addAttribute("contact",book.getContacts());
		return "add-book";
	}

	@RequestMapping("/add-book")
	public String createBook(Book book, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "add-book";
		}

		bookService.createBook(book);
		model.addAttribute("book", bookService.findAllBooks());
		//model.addAttribute("contact",book.getContacts());
		return "redirect:/books";
	}

	@GetMapping("/update/{id}")
	public String showUpdateForm(@PathVariable("id") Long id, Model model) {
		final Book book = bookService.findBookById(id);

		model.addAttribute("book", book);
		return "update-book";
	}

	@RequestMapping("/update-book/{id}")
	public String updateBook(@PathVariable("id") Long id, Book book, BindingResult result, Model model) {
		if (result.hasErrors()) {
			book.setId(id);
			return "update-book";
		}

		bookService.updateBook(book);
		model.addAttribute("book", bookService.findAllBooks());
		return "redirect:/books";
	}

	@RequestMapping("/remove-book/{id}")
	public String deleteBook(@PathVariable("id") Long id, Model model) {
		bookService.deleteBook(id);

		model.addAttribute("book", bookService.findAllBooks());
		return "redirect:/books";
	}

	@GetMapping("/books")
	public String viewHomePage(Model model,Book book) {
		return findPaginated(1, model,book);
	}

	/*@RequestMapping("/books/page/{pageNo}")
	public String findPaginated(@PathVariable("pageNo") int pageNo, Model model) {
		int pageSize = 5;
		Page<Book> page = bookService.findPaginated(pageNo, pageSize);
		List<Book> listBooks = page.getContent();
		model.addAttribute("listBooks", listBooks);
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		return "list-books";

	}*/
	@RequestMapping("/bookCategory")
	public String searchCategory(@Param("keyword") String keyword, Model model) {
		final List<Book> books = bookService.searchBooks(keyword);
		model.addAttribute("books", books);
		model.addAttribute("keyword", keyword);
		return "book-category";
	}
	@RequestMapping(value = "/test")
	public String showCheckbox(Model model) {
	    boolean myBooleanVariable = false;
	    model.addAttribute("myBooleanVariable", myBooleanVariable);
	    return "sample-checkbox";
	}
	@RequestMapping("/books/page/{pageNo}")
	public String findPaginated(@PathVariable("pageNo") int pageNo, Model model,Book book) {
		int pageSize = 5;
		String sortField = "isbn";
		String sortDirection = "Sort.Direction.ASC.name()";
		//String sortDirection = " ";
		Page<Book> page = bookService.findPaginated(pageNo, pageSize,sortField,sortDirection);
		List<Book> listBooks = page.getContent();
		model.addAttribute("listBooks", listBooks);
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		return "list-books";
	}
	

	
	/* @GetMapping("/books")
	    public List<Book> findPaginatedSorted(
	                        @RequestParam(defaultValue = "0") Integer pageNo, 
	                        @RequestParam(defaultValue = "10") Integer pageSize,
	                        @RequestParam(defaultValue = "id") String sortBy) 
	    {
	        List<Book> list = bookService.findPaginatedSorted(pageNo, pageSize, sortBy);
	 
	        return list;
	    }*/

	/*
	 * @RequestMapping("/search/{keyword}") public String
	 * searchPaginated(@Param("keyword") String keyword, Model model) { Page<Book>
	 * page = bookService.searchPaginated(keyword); List<Book> books =
	 * page.getContent(); model.addAttribute("books", books);
	 * model.addAttribute("keyword", keyword); return "list-books";
	 * 
	 * }
	 * 
	 * @GetMapping("/search/searchName") public String searchBook(Model
	 * model, @Param("Keyword") String keyword) { return searchPaginated(keyword,
	 * model); }
	 */
	@RequestMapping("/searchBook")
	public String searchBook(@Param("keyword") String keyword, Model model) {
		final List<Book> b = bookService.searchBooks(keyword);
		model.addAttribute("b", b);
		model.addAttribute("keyword", keyword);
		return "book-search";
	}
	
	/*@RequestMapping("/contact/{userId}")
	public ModelAndView viewPublisherContact(@PathVariable(name="userId") Long userId) {
		ModelAndView mav = new ModelAndView("view_contact");
		ResponseEntity<Contact> res = new RestTemplate().getForEntity("http://localhost:8091/contact/user/{userId}",Contact.class);
		Contact contact = res.getBody();
		mav.addObject("contact",contact);
		return mav;
	}*/

}
