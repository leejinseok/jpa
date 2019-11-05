package com.example.jpa.service;

import com.example.jpa.domain.PostComment;
import com.example.jpa.dto.PostCommentDto;
import com.example.jpa.repository.PostCommentRepository;
import com.example.jpa.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostCommentService {

  private final PostCommentRepository postCommentRepository;
  private final PostRepository postRepository;

  public PostComment saveComment(int id, PostCommentDto.RegisterReq dto) throws ChangeSetPersister.NotFoundException {
    PostComment comment = PostComment.builder()
      .content(dto.getContent())
      .post(postRepository.findOneById(id).orElseThrow(ChangeSetPersister.NotFoundException::new))
      .build();

    return postCommentRepository.save(comment);
  }
}
