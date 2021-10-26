package com.javaoktato.blog.repositories;

import com.javaoktato.blog.domain.BlogPost;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface BlogPostRepositoryCustom {

    List<BlogPost> findRecentPosts();

    List<BlogPost> findByCriteria(String titleKeyword,
                                  String contentKeyword,
                                  LocalDateTime newerThan,
                                  LocalDateTime olderThan,
                                  String author,
                                  Boolean activeAuthor,
                                  Pageable pageable);

}
