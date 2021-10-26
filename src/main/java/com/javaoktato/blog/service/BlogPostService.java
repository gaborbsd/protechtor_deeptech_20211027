package com.javaoktato.blog.service;

import com.javaoktato.blog.controller.ValidationException;
import com.javaoktato.blog.domain.BlogPost;
import com.javaoktato.blog.domain.BlogUser;
import com.javaoktato.blog.dto.CreateBlogPost;
import com.javaoktato.blog.dto.ValidationMessage;
import com.javaoktato.blog.repositories.BlogPostRepository;
import com.javaoktato.blog.repositories.BlogUserRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlogPostService {

    @Autowired
    private BlogPostRepository blogPostRepository;

    @Autowired
    private BlogUserRepository blogUserRepository;

    @Transactional
    public BlogPost createBlogPost(CreateBlogPost dto) {
        BlogPost bp = new BlogPost();
        bp.setTitle(dto.getTitle());
        bp.setContent(dto.getContent());

        Optional<BlogUser> author = blogUserRepository.findById(dto.getAuthorEmail());
        if (author.isEmpty()) {
            throw new ValidationException(List.of(new ValidationMessage("authorEmail", "Invalid author email")));
        }
        bp.setAuthor(author.get());
        return blogPostRepository.save(bp);
    }
}
