package com.demo.librarymanagementsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.demo.librarymanagementsystem.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
	@Query("SELECT b FROM Category b WHERE b.name LIKE %?1%")
	public List<Category> search(String keyword);
}
