package com.example.jpa.repository;

import com.example.jpa.domain.Post;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Integer> {
  Optional<Post> findOneById(Integer id);
}
