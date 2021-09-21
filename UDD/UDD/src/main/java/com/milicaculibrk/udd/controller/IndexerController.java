package com.milicaculibrk.udd.controller;

import com.milicaculibrk.udd.repository.BookRepository;
import com.milicaculibrk.udd.repository.WriterRepository;
import com.milicaculibrk.udd.dto.SearchDTO;
import com.milicaculibrk.udd.service.book.BookService;
import com.milicaculibrk.udd.service.Indexer;
import com.milicaculibrk.udd.service.book.QueryBuilder;
import org.apache.lucene.queryparser.classic.ParseException;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("index")
public class IndexerController {

    @Autowired
    private WriterRepository writerRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookService bookService;

    @Autowired
    private Indexer indexer;


    @GetMapping("")
    public ResponseEntity<?> getAllBooks() {

        return new ResponseEntity<>(bookRepository.findAll(), HttpStatus.OK);

    }

    @PostMapping()
    public ResponseEntity<?> addNewBook(@RequestParam("files") MultipartFile files,
                                                       @RequestParam("title") String title,
                                                       @RequestParam("keywords") String keywords,
                                                       @RequestParam("genres") String[] genres,
                                                       @RequestParam("writer") String writer) {

        try{
            bookService.addNewBook(title, writer, genres, keywords, files);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        System.out.println("Dodato");
        return new ResponseEntity<>("Book successfully uploaded!", HttpStatus.OK);

    }

    @DeleteMapping("/{fileName}")
    public ResponseEntity<?> deleteFile(@PathVariable String fileName) {

        String targetFilePath = "C:/Users/Milica/Desktop/UDD-main/UDD/UDD/target/classes/files/" + fileName;
        indexer.delete(targetFilePath);

        return new ResponseEntity<>("Successfully deleted!", HttpStatus.OK);

    }


    @PostMapping("/download")
    public ResponseEntity<?> downloadBook(@RequestParam("url") String url) {

        Resource resource = bookService.downloadBook(url);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/pdf"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

}
