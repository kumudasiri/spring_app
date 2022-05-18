package com.demo.librarymanagementsystem.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.demo.librarymanagementsystem.entity.Book;


public interface SortRepository extends PagingAndSortingRepository<Book, Long>{

}
