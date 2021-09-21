package com.milicaculibrk.udd.controller;

import com.milicaculibrk.udd.model.Writer;
import com.milicaculibrk.udd.repository.WriterRepository;
import com.milicaculibrk.udd.dto.WriterDTO;
import com.milicaculibrk.udd.service.authentication.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("login")
public class AuthenticationController {

    @Autowired
    WriterRepository writerRepository;

    @Autowired
    AuthenticationService writerService;

    @PostMapping()
    public ResponseEntity<?> login(@RequestBody WriterDTO request){

        Writer writer = writerService.checkCredentials(request);

        if (writer == null)
            return new ResponseEntity<>("Bad credentials", HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(writer, HttpStatus.OK);
    }
}
