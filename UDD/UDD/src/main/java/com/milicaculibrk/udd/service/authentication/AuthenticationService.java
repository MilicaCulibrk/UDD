package com.milicaculibrk.udd.service.authentication;

import com.milicaculibrk.udd.dto.WriterDTO;
import com.milicaculibrk.udd.model.Writer;
import com.milicaculibrk.udd.repository.WriterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    private WriterRepository writerRepository;

    public Writer checkCredentials(WriterDTO request){

        Writer writer = writerRepository.findById(request.getUsername()).orElse(null);

        if(writer == null || !request.getPassword().equals(writer.getPassword()))
            return null;

        return writer;
    }

}
