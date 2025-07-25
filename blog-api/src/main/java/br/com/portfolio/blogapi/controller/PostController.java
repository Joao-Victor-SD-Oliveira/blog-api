package br.com.portfolio.blogapi.controller;

import br.com.portfolio.blogapi.dto.PostDetailsDTO;
import br.com.portfolio.blogapi.dto.PostFormDTO;
import br.com.portfolio.blogapi.model.Post;
import br.com.portfolio.blogapi.repository.PostRepository;
import br.com.portfolio.blogapi.service.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostService postService;

    @GetMapping
    public ResponseEntity<List<PostDetailsDTO>> getAll() {
        List<PostDetailsDTO> posts = postRepository.findAll().stream().map(PostDetailsDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDetailsDTO> getById(@PathVariable Long id) {
        return postRepository.findById(id)
                .map(post -> ResponseEntity.ok(new PostDetailsDTO(post)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PostDetailsDTO> create(@RequestBody @Valid PostFormDTO data, UriComponentsBuilder uriBuilder) {
        Post post = postService.create(data.title(), data.content());
        var uri = uriBuilder.path("/posts/{id}").buildAndExpand(post.getId()).toUri();
        return ResponseEntity.created(uri).body(new PostDetailsDTO(post));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostDetailsDTO> update(@PathVariable Long id, @RequestBody @Valid PostFormDTO data) {
        Post updatedPost = postService.update(id, data.title(), data.content());
        return ResponseEntity.ok(new PostDetailsDTO(updatedPost));
    }
}