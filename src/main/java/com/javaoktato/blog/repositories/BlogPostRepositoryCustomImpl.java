package com.javaoktato.blog.repositories;

import com.javaoktato.blog.domain.BlogPost;
import com.javaoktato.blog.domain.BlogPost_;
import com.javaoktato.blog.domain.BlogUser_;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

public class BlogPostRepositoryCustomImpl implements BlogPostRepositoryCustom {

    @Autowired
    private EntityManager em;

    @Override
    public List<BlogPost> findRecentPosts() {
        String queryString = "SELECT p FROM BlogPost p WHERE p.creationDate > :startDate";
        TypedQuery<BlogPost> query = em.createQuery(queryString, BlogPost.class);
        query.setParameter("startDate", LocalDateTime.now().minusDays(7));
        return query.getResultList();
    }

    @Override
    public List<BlogPost> findByCriteria(String titleKeyword,
                                         String contentKeyword,
                                         LocalDateTime newerThan,
                                         LocalDateTime olderThan,
                                         String author,
                                         Boolean activeAuthor,
                                         Pageable pageable) {

        if (pageable.getPageSize() < 1) {
            throw new IllegalArgumentException("Page size must be greater or equal to 1.");
        }

        if (pageable.getPageNumber() < 0) {
            throw new IllegalArgumentException("Page number must be greater or equal to 0.");
        }

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<BlogPost> cq = cb.createQuery(BlogPost.class);

        Root<BlogPost> root = cq.from(BlogPost.class);
        cq.select(root);

        List<Predicate> predicates = new ArrayList<>();

        if (titleKeyword != null && !titleKeyword.isEmpty()) {
            Predicate titlePredicate = cb.like(cb.lower(root.get(BlogPost_.title)),
                    "%" + titleKeyword.toLowerCase() + "%");
            predicates.add(titlePredicate);
        }

        if (contentKeyword != null && !contentKeyword.isEmpty()) {
            Predicate contentPredicate = cb.like(cb.lower(root.get(BlogPost_.content)),
                    "%" + contentKeyword.toLowerCase() + "%");
            predicates.add(contentPredicate);
        }

        if (newerThan != null) {
            Predicate newerThanPredicate = cb.greaterThanOrEqualTo(root.get(BlogPost_.creationDate), newerThan);
            predicates.add(newerThanPredicate);
        }

        if (olderThan != null) {
            Predicate olderThanPredicate = cb.lessThanOrEqualTo(root.get(BlogPost_.creationDate), olderThan);
            predicates.add(olderThanPredicate);
        }

        if (author != null && !author.isEmpty()) {
            Predicate authorPredicate = cb.like(cb.lower(root.get(BlogPost_.author).get(BlogUser_.name)),
                    "%" + author.toLowerCase() + "%");
            predicates.add(authorPredicate);
        }

        if (activeAuthor != null) {
            Predicate activeAuthorPredicate = cb.equal(root.get(BlogPost_.author).get(BlogUser_.active), activeAuthor);
            predicates.add(activeAuthorPredicate);
        }

        cq.where(predicates.toArray(new Predicate[0]));
        cq.orderBy(cb.desc(root.get(BlogPost_.creationDate)));
        TypedQuery<BlogPost> query = em.createQuery(cq);
        query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());
        return query.getResultList();
    }
}
