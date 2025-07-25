package br.com.portfolio.blogapi.service;

import br.com.portfolio.blogapi.model.Post;
import br.com.portfolio.blogapi.model.User;
import br.com.portfolio.blogapi.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public Post create(String title, String content) {
        User author = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Post newPost = Post.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
        return postRepository.save(newPost);
    }

    public Post update(Long id, String title, String content) {
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Post post = postRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Post não encontrado"));

        if (!post.getAuthor().getId().equals(loggedUser.getId())) {
            throw new SecurityException("Usuário não autorizado a editar este post");
        }

        post.setTitle(title);
        post.setContent(content);
        return postRepository.save(post);
    }
}