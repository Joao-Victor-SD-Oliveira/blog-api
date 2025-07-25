package br.com.portfolio.blogapi.dto;

import br.com.portfolio.blogapi.model.Post;
import java.time.LocalDateTime;

public record PostDetailsDTO(Long id, String title, String content, String authorName, LocalDateTime createdAt) {
    public PostDetailsDTO(Post post) {
        this(post.getId(), post.getTitle(), post.getContent(), post.getAuthor().getName(), post.getCreatedAt());
    }
}