package com.example.jpa.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "post_comments")
@Getter @Setter
public class PostComment {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  @Column
  private String content;

  @ManyToOne
  private Post post;
}
