package com.demo.librarymanagementsystem.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.demo.librarymanagementsystem.entity.Book;

public interface BookRepository2 extends PagingAndSortingRepository<Book,Long>{

}
