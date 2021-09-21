package com.milicaculibrk.udd.controller;

import com.milicaculibrk.udd.dto.SearchDTO;
import com.milicaculibrk.udd.repository.BookRepository;
import com.milicaculibrk.udd.repository.WriterRepository;
import com.milicaculibrk.udd.service.Indexer;
import com.milicaculibrk.udd.service.book.BookService;
import com.milicaculibrk.udd.service.book.QueryBuilder;
import com.milicaculibrk.udd.service.search.SearchService;
import org.apache.lucene.queryparser.classic.ParseException;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("search")
public class SearchController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private SearchService searchService;

    @PostMapping()
    public ResponseEntity<?> searchBook(@RequestBody SearchDTO searchDTO) throws ParseException {

        BoolQueryBuilder query = searchService.searchBook(searchDTO);

        return new ResponseEntity<>(bookRepository.search(query), HttpStatus.OK);

    }

}
