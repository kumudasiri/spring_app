package com.demo.librarymanagementsystem.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.demo.librarymanagementsystem.entity.Book;
import com.demo.librarymanagementsystem.entity.Student;
import com.demo.librarymanagementsystem.exception.NotFoundException;
import com.demo.librarymanagementsystem.repository.BookRepository;
import com.demo.librarymanagementsystem.repository.StudentRepository;
import com.demo.librarymanagementsystem.service.StudentService;
@Service
public class StudentServiceImple implements StudentService{
	@Autowired
	private StudentRepository repo;
	
	public StudentServiceImple(StudentRepository repo) {
		this.repo = repo;
	}

	@Override
	public List<Student> findAllStudents() {
		return repo.findAll();
	}

	@Override
	public Student findStudentById(Long id) {
		return repo.findById(id)
				.orElseThrow(() -> new NotFoundException(String.format("Student not found with ID %d", id)));
	}

	@Override
	public void createStudent(Student student) {
		try {
			repo.save(student);
		} catch (DataIntegrityViolationException e) {
			e.getMessage();
		}
		
	}

	@Override
	public void updateStudent(Student student) {
		// TODO Auto-generated method stub
		repo.save(student);
		
	}

	@Override
	public void deleteStudent(Long id) {
		// TODO Auto-generated method stub
		final Student student = repo.findById(id)
				.orElseThrow(() -> new NotFoundException(String.format("Student not found with ID %d", id)));

		repo.deleteById(student.getId());
		
	}

}
