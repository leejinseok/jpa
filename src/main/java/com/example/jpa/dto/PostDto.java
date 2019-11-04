package com.example.jpa.dto;

import com.example.jpa.domain.Post;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

public class PostDto {

  @Getter
  public static class RegisterReq {

    @NotEmpty
    private String title;
    @NotEmpty
    private String content;

    public RegisterReq(String title, String content) {
      this.title  = title;
      this.content = content;
    }
  }

  @Getter
  public static class Res {

    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Res(Post post) {
      this.title = post.getTitle();
      this.content = post.getContent();
      this.createdAt = post.getCreatedAt();
      this.updatedAt = post.getUpdatedAt();
    }
  }
}
