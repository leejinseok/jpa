package com.example.jpa.controller;

import com.example.jpa.domain.PostComment;
import com.example.jpa.dto.PostCommentDto;
import com.example.jpa.dto.PostDto;
import com.example.jpa.domain.Post;
import com.example.jpa.repository.PostCommentRepository;
import com.example.jpa.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "api/posts", produces = "application/json; charset=utf-8")
@RequiredArgsConstructor
@Slf4j
public class PostController {

  private final PostRepository postRepository;
  private final PostCommentRepository postCommentRepository;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public Collection<PostDto.Res> getPosts() {
    return postRepository.findAll().stream().map(PostDto.Res::new).collect(Collectors.toList());
  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public PostDto.ResDetail getPost(@PathVariable("id") final int id) throws ChangeSetPersister.NotFoundException {
    return new PostDto.ResDetail(postRepository.findOneById(id).orElseThrow(ChangeSetPersister.NotFoundException::new));
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public PostDto.Res registerPost(@RequestBody @Valid final PostDto.RegisterReq dto) {
    return new PostDto.Res(postRepository.save(new Post(dto)));
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

  @PostMapping("/{id}/comments")
  @ResponseStatus(HttpStatus.CREATED)
  public PostCommentDto.Res registerPostComment(@PathVariable("id") final int id, @RequestBody @Valid final PostCommentDto.RegisterReq dto) throws ChangeSetPersister.NotFoundException {
    PostComment postComment = PostComment.builder()
      .content(dto.getContent())
      .post(postRepository.findOneById(id).orElseThrow(ChangeSetPersister.NotFoundException::new))
      .build();

    return new PostCommentDto.Res(postCommentRepository.save(postComment));
  }

}
