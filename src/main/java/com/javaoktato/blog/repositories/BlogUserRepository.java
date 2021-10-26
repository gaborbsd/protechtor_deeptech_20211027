package com.javaoktato.blog.repositories;

import com.javaoktato.blog.domain.BlogUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogUserRepository extends JpaRepository<BlogUser, String> {
}