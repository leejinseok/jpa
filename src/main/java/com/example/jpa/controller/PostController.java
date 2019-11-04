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

@RestController
@RequestMapping(value = "api/posts", produces = "application/json; charset=utf-8")
@RequiredArgsConstructor
public class PostController {

  private final PostRepository postRepository;

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
}
