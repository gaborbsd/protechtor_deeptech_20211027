package com.javaoktato.blog.controller;

import com.javaoktato.blog.domain.BlogPost;
import com.javaoktato.blog.dto.CreateBlogPost;
import com.javaoktato.blog.repositories.BlogPostRepository;
import com.javaoktato.blog.service.BlogPostService;
import java.time.LocalDateTime;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
public class BlogPostController {

    @Autowired
    private BlogPostRepository repository;

    @Autowired
    private BlogPostService service;

    @GetMapping(params = {"keyword"})
    public List<BlogPost> simpleSearch(@RequestParam String keyword,
                                       @RequestParam(required = false, defaultValue = "0") int pageNumber,
                                       @RequestParam(required = false, defaultValue = "-1") int pageSize) {
        return repository.findAllByContentContainingIgnoreCase(keyword,
                PageRequest.of(pageNumber, pageSize == -1 ? Integer.MAX_VALUE : pageSize));
    }

    @GetMapping("/long")
    public List<BlogPost> getLongPosts(@RequestParam(required = false, defaultValue = "0") int pageNumber,
                                       @RequestParam(required = false, defaultValue = "-1") int pageSize) {
        return repository.findLongPosts(PageRequest.of(pageNumber, pageSize == -1 ? Integer.MAX_VALUE : pageSize));
    }

    @GetMapping("/recent")
    public List<BlogPost> getRecentPosts() {
        return repository.findRecentPosts();
    }

    @GetMapping()
    public List<BlogPost> simpleSearch(@RequestParam(required = false, defaultValue = "") String titleKeyword,
                                       @RequestParam(required = false, defaultValue = "") String contentKeyword,
                                       @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) @RequestParam(required = false) LocalDateTime newerThan,
                                       @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) @RequestParam(required = false) LocalDateTime olderThan,
                                       @RequestParam(required = false) String author,
                                       @RequestParam(required = false) Boolean activeAuthor,
                                       @RequestParam(required = false, defaultValue = "0") int pageNumber,
                                       @RequestParam(required = false, defaultValue = "-1") int pageSize) {
        return repository.findByCriteria(titleKeyword, contentKeyword, newerThan, olderThan, author, activeAuthor,
                PageRequest.of(pageNumber, pageSize == -1 ? Integer.MAX_VALUE : pageSize));
    }

    @PostMapping
    public BlogPost create(@RequestBody @Valid CreateBlogPost dto) {
        return service.createBlogPost(dto);
    }
}
