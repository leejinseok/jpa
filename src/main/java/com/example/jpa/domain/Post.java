package com.example.jpa.domain;

import com.example.jpa.dto.PostDto;
import com.example.jpa.serializer.PostCommentSerializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "posts")
@NoArgsConstructor
@Getter @Setter
public class Post {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  @Column(nullable = false, length = 75)
  private String title;

  @Column(nullable = false, columnDefinition = "text")
  private String content;

  @OneToMany(mappedBy = "post", orphanRemoval = true, cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
  @JsonSerialize(using = PostCommentSerializer.class)
  private Collection<PostComment> comments = new ArrayList<>();

  @CreatedDate
  private LocalDateTime createdAt;

  @LastModifiedDate
  private LocalDateTime updatedAt;

  public Post(PostDto.RegisterReq dto) {
    this.title = dto.getTitle();
    this.content = dto.getContent();
  }

  @Builder
  public Post(int id, String title, String content) {
    this.id = id;
    this.title = title;
    this.content = content;
  }
}
