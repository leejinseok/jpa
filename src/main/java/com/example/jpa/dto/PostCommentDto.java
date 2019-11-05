package com.example.jpa.dto;

import com.example.jpa.domain.PostComment;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

public class PostCommentDto {

  @NoArgsConstructor
  @Getter
  public static class RegisterReq {
    @NotEmpty
    private String content;
  }

  @Getter
  public static class Res {
    private int post;
    private String content;

    public Res(PostComment comment) {
      this.post = comment.getPost().getId();
      this.content = comment.getContent();
    }
  }
}
