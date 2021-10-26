package com.javaoktato.blog.repositories;

import com.javaoktato.blog.domain.BlogPost;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogPostRepository extends JpaRepository<BlogPost, Integer>, BlogPostRepositoryCustom {

    public List<BlogPost> findAllByContentContainingIgnoreCase(String str, Pageable pageable);

    @Query("SELECT bp FROM BlogPost bp WHERE LENGTH(bp.content) > 30")
    public List<BlogPost> findLongPosts(Pageable pageable);
}