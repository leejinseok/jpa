package com.example.jpa.service;

import com.example.jpa.domain.Post;
import com.example.jpa.dto.PostDto;
import com.example.jpa.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class PostService {
  private final PostRepository postRepository;

  public Collection<Post> getPosts() {
    return postRepository.findAll();
  }

  public Post getPost(int id) throws ChangeSetPersister.NotFoundException {
    return postRepository.findOneById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);
  }

  public Post savePost(Post post) {
    return postRepository.save(post);
  }

  public Post updatePost(int id, PostDto.RegisterReq dto) {
    Post post = Post.builder()
      .id(id)
      .title(dto.getTitle())
      .content(dto.getContent())
      .build();

    return postRepository.save(post);
  }

}
