package com.javaoktato.blog.controller;

import com.javaoktato.blog.domain.File;
import com.javaoktato.blog.repositories.FileRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/files")
public class FileController {
    private static final int MAX_AGE = 60 * 10; // 10 minutes

    @Autowired
    private FileRepository repository;

    @GetMapping("{name}")
    public ResponseEntity<?> getByName(@PathVariable String name,
                                       @RequestHeader(
                                               value = "If-None-Match",
                                               required = false,
                                               defaultValue = ""
                                       )
                                               String ifNoneMatch) {
        Optional<File> file = repository.findById(name);
        if (file.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        if (file.get().getEtag().equals(ifNoneMatch))
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Cache-Control", "max-age=" + MAX_AGE);
        headers.set("ETag", file.get().getEtag());

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(file.get().getContentType()))
                .headers(headers)
                .body(file.get().getPayload().getData());
    }
}
