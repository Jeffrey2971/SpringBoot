package com.jeffrey.repository;

import com.jeffrey.bean.Book;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import java.util.List;

public interface BookRepository extends ElasticsearchRepository<Book, Integer> {
    List<Book> findByBookNameLike(String bookName);
}