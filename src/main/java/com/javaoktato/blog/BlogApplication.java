package com.javaoktato.blog;

import com.javaoktato.blog.domain.BlogPost;
import com.javaoktato.blog.domain.BlogUser;
import com.javaoktato.blog.repositories.BlogPostRepository;
import com.javaoktato.blog.repositories.BlogUserRepository;
import com.javaoktato.blog.repositories.FileRepository;
import com.javaoktato.blog.service.FileService;
import java.io.File;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.ResourceUtils;

@SpringBootApplication
public class BlogApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class, args);
    }

    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private FileService fileService;

    @Autowired
    private BlogUserRepository blogUserRepository;

    @Autowired
    private BlogPostRepository blogPostRepository;

    @Override
    public void run(String... args) throws Exception {

        // File
        File file = ResourceUtils.getFile("classpath:meme.jpg");
        byte[] data = Files.readAllBytes(file.toPath());
        com.javaoktato.blog.domain.File fileToSave =
                new com.javaoktato.blog.domain.File("meme.jpg", "image/jpeg", fileService.checksum(data), data);
        fileRepository.save(fileToSave);

        // BlogUsers
        BlogUser jack = new BlogUser("jack@example.com", "Jack", "abcdef", true);
        BlogUser jill = new BlogUser("jill@example.com", "Jill", "defgh", true);
        BlogUser joe = new BlogUser("joe@example.com", "Joe", "qwert", false);
        blogUserRepository.saveAllAndFlush(List.of(jack, jill, joe));

        // BlogPosts
        List<BlogPost> jillPosts = List.of(
                new BlogPost("A romantic dinner with the best husband",
                        "With Jack, we went to the local Mexican restaurant...",
                        LocalDateTime.of(2020, 4, 2, 17, 0, 0),
                        jill),
                new BlogPost("Found a wallet in the parking lot",
                        "Lorem ipsum",
                        LocalDateTime.of(2020, 5, 2, 17, 20, 0),
                        jill),
                new BlogPost("Eric's first day at school",
                        "Lorem ipsum",
                        LocalDateTime.of(2020, 9, 3, 19, 28, 0),
                        jill)
        );
        blogPostRepository.saveAllAndFlush(jillPosts);

        List<BlogPost> jackPosts = List.of(
                new BlogPost("Moving to Chicago",
                        "Finally we moved into the cozy new flat that took so long to renovate...",
                        LocalDateTime.of(2020, 4, 21, 17, 0, 0),
                        jack),
                new BlogPost("The best birthday present ever",
                        "Lorem ipsum",
                        LocalDateTime.of(2020, 5, 12, 17, 40, 0),
                        jack),
                new BlogPost("Trying out a new workout plan",
                        "Lorem ipsum",
                        LocalDateTime.of(2020, 6, 3, 12, 48, 0),
                        jack),
                new BlogPost("It has been a long time I haven't written anything",
                        "So I'm giving you an update on what I have been doing lately...",
                        LocalDateTime.now().minusDays(1),
                        jack)
        );
        blogPostRepository.saveAllAndFlush(jackPosts);

        List<BlogPost> joePosts = List.of(
                new BlogPost("Times have changed",
                        "I decided to take some time away from keyboard to do some digital detoxication so I won't be blogging for a while....",
                        LocalDateTime.of(2020, 7, 21, 17, 0, 0),
                        jack)
        );
        blogPostRepository.saveAllAndFlush(jackPosts);


    }
}
