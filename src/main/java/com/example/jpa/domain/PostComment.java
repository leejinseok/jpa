package com.example.jpa.domain;

import com.example.jpa.dto.PostCommentDto;
import com.example.jpa.serializer.PostCommentSerializer;
import com.example.jpa.serializer.PostSerializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
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
//  @JsonIgnore
  @JsonSerialize(using = PostSerializer.class)
  private Post post;

  public PostComment(PostCommentDto.RegisterReq dto) {
    this.content = dto.getContent();
  }
}
