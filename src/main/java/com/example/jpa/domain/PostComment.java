package com.example.jpa.domain;

import com.example.jpa.serializer.PostSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

@Entity
@Table(name = "post_comments")
@Getter @Setter
@NoArgsConstructor
@Slf4j
public class PostComment {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  @Column
  private String content;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "post_id", nullable = false)
  @JsonSerialize(using = PostSerializer.class)
  private Post post;

  @Builder
  public PostComment(String content, Post post) {
    this.content = content;
    this.post = post;
  }
}
