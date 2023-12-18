package com.example.toolshopapi.repository;

import com.example.toolshopapi.model.models.product.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommentsRepository extends JpaRepository<Comment, Long> {
    Optional<Comment> findByIdAndAuthorEmail(Long commentId, String authorEmail);
}
