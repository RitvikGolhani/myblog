package com.blog.bl.repository;

import com.blog.bl.entity.Comment;
import com.blog.bl.payload.CommentDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {

    List<Comment> findByPostId(long id);
}
