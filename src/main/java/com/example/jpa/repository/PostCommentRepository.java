package com.example.jpa.repository;

import com.example.jpa.domain.PostComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostCommentRepository extends JpaRepository<PostComment, Integer> {
}
