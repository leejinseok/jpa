package com.example.jpa.dto;

import com.example.jpa.domain.Post;
import com.example.jpa.domain.PostComment;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class PostCommentDto {

  @Getter
  public static class RegisterReq {
    @NotEmpty
    private String content;

    @Builder
    public RegisterReq(int postId, String content) {
      this.content = content;
    }
  }

  @Getter
  public static class Res {
    private PostDto.Res post;
    private String content;

    public Res(PostComment comment) {
//      this.post = new PostDto.Res(comment.getPost());
      this.content = comment.getContent();
    }
  }
}
