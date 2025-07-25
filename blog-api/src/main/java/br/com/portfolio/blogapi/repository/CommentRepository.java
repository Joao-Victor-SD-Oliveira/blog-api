package br.com.portfolio.blogapi.repository;

import br.com.portfolio.blogapi.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}