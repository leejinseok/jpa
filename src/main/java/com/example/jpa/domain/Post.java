package com.example.jpa.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "posts")
@Getter @Setter
public class Post {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  @Column(name = "title", nullable = false, length = 75)
  private String title;

  @Column(name = "content", nullable = false, columnDefinition = "text")
  private String content;

  @OneToMany(mappedBy = "post", orphanRemoval = true, cascade = CascadeType.PERSIST)
  private Collection<PostComment> postComments = new ArrayList<>();

  @CreatedDate
  private LocalDateTime createdAt;

  @LastModifiedDate
  private LocalDateTime updatedAt;
}
