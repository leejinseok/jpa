package com.example.jpa.controller;

import com.example.jpa.dto.PostDto;
import com.example.jpa.domain.Post;
import com.example.jpa.domain.PostComment;
import com.example.jpa.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "api/posts", produces = "application/json; charset=utf-8")
@RequiredArgsConstructor
public class PostController {

  private final PostRepository postRepository;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public Collection<PostDto.Res> getPosts() {
    return postRepository.findAll().stream().map(PostDto.Res::new).collect(Collectors.toList());
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public PostDto.Res register(@RequestBody @Valid final PostDto.RegisterReq dto) {

    Post post = new Post();
    post.setTitle(dto.getTitle());
    post.setContent(dto.getContent());

    PostComment postComment = new PostComment();
    postComment.setContent("댓글");
    postComment.setPost(post);

    post.getPostComments().add(postComment);

    postRepository.save(post);

    return new PostDto.Res(post);
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public PostDto.Res update(@PathVariable("id") int id, @RequestBody @Valid final PostDto.RegisterReq dto) {
    Post post = Post.builder()
      .id(id)
      .title(dto.getTitle())
      .content(dto.getContent())
      .build();

    return new PostDto.Res(postRepository.save(post));
  }

}
