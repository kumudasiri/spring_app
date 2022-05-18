package com.demo.librarymanagementsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.librarymanagementsystem.entity.Book;
import com.demo.librarymanagementsystem.entity.Student;
import com.demo.librarymanagementsystem.service.BookService;
import com.demo.librarymanagementsystem.service.StudentService;

@Controller
public class StudentController {
	private final StudentService studentService;
	private final BookService bookService;

	@Autowired
	public StudentController(StudentService studentService, BookService bookService) {
		this.bookService = bookService;
		this.studentService = studentService;
	}

	@RequestMapping("/students")
	public String findAllStudents(Model model) {
		final List<Student> students = studentService.findAllStudents();
		model.addAttribute("students", students);
		return "list-students";
	}

	@RequestMapping("/student/{id}")
	public String findBookById(@PathVariable("id") Long id, Model model) {
		final Student student = studentService.findStudentById(id);
		model.addAttribute("student", student);
		return "list-student";
	}

	@GetMapping("/addStudent")
	public String showCreateForm(Student student, Model model) {
		model.addAttribute("books", bookService.findAllBooks());
		return "add-student";
	}

	@RequestMapping("/add-student")
	public String createStudent(Student student, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "add-student";
		}
		studentService.createStudent(student);
		model.addAttribute("student", studentService.findAllStudents());
		return "redirect:/students";
	}

	@GetMapping("/updateStudent/{id}")
	public String showUpdateForm(@PathVariable("id") Long id, Model model) {
		final Student student = studentService.findStudentById(id);

		model.addAttribute("student", student);
		return "update-student";
	}

	@RequestMapping("/update-student/{id}")
	public String updateBook(@PathVariable("id") Long id, Student student, BindingResult result, Model model) {
		if (result.hasErrors()) {
			student.setId(id);
			return "update-student";
		}

		studentService.updateStudent(student);
		model.addAttribute("student", studentService.findAllStudents());
		return "redirect:/students";
	}

	@RequestMapping("/remove-student/{id}")
	public String deleteStudent(@PathVariable("id") Long id, Model model) {
		studentService.deleteStudent(id);

		model.addAttribute("student", studentService.findAllStudents());
		return "redirect:/students";
	}
}
