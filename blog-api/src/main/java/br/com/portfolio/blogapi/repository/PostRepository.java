package br.com.portfolio.blogapi.repository;

import br.com.portfolio.blogapi.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}